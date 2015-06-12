package pl.edu.agh.dockermonitor.containers;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.dockermonitor.containers.command.ContainerLifecycleManager;
import pl.edu.agh.dockermonitor.containers.command.ContainerStateTransition;
import pl.edu.agh.dockermonitor.containers.command.ContainerStateTransitionDTO;
import pl.edu.agh.dockermonitor.containers.command.CreateContainerRequest;
import pl.edu.agh.dockermonitor.containers.query.ContainerRepository;
import pl.edu.agh.dockermonitor.containers.query.StatisticsDispatcher;
import pl.edu.agh.dockermonitor.containers.query.containerinfo.DockerContainer;
import pl.edu.agh.dockermonitor.containers.query.containerinfo.State;

import java.util.Collection;
import java.util.Map;
import java.util.function.Consumer;

import static pl.edu.agh.dockermonitor.containers.command.ContainerStateTransition.*;

/**
 * Created by novy on 08.06.15.
 */

@RestController
@RequestMapping(value = "/docker")
public class ContainerController {

    private final ContainerRepository containerRepository;
    private final StatisticsDispatcher statisticsDispatcher;
    private final ContainerLifecycleManager manager;
    private final Map<ContainerStateTransition, Consumer<String>> containerStateActions;

    @Autowired
    public ContainerController(ContainerRepository containerRepository,
                               StatisticsDispatcher statisticsDispatcher,
                               ContainerLifecycleManager manager) {
        this.containerRepository = containerRepository;
        this.statisticsDispatcher = statisticsDispatcher;
        this.manager = manager;

        containerStateActions = ImmutableMap.of(
                START, manager::startContainer,
                STOP, manager::stopContainer,
                PAUSE, manager::pauseContainer,
                UNPAUSE, manager::unpauseContainer,
                REMOVE, manager::removeContainer
        );
    }

    @RequestMapping(value = "/containers", method = RequestMethod.GET)
    public Collection<DockerContainer> containers() {
        final Collection<DockerContainer> dockerContainers = containerRepository.loadContainers();
        requestStatisticsFor(dockerContainers);
        return dockerContainers;
    }

    @RequestMapping(value = "/containers", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createContainer(@RequestBody CreateContainerRequest request) {
        manager.createContainer(request);
    }

    @RequestMapping(value = "/containers/{containerId}", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void changeContainerState(@PathVariable(value = "containerId") String containerId,
                                     @RequestBody ContainerStateTransitionDTO stateTransition) {

        containerStateActions.get(stateTransition.getValue()).accept(containerId);
    }

    private void requestStatisticsFor(Collection<DockerContainer> containers) {
        containers.stream()
                .filter(container -> container.getInspectionData().getState() != State.STOPPED)
                .forEach(
                        container -> statisticsDispatcher.requestStatisticsFor(
                                container.getBasicData().getContainerId()
                        )
                );
    }
}

