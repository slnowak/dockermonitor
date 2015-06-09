package pl.edu.agh.dockermonitor.containers;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.function.Consumer;

import static pl.edu.agh.dockermonitor.containers.ContainerStateTransition.*;

/**
 * Created by novy on 08.06.15.
 */

@RestController
@RequestMapping(value = "/docker")
public class ContainerController {

    private final ContainerRepository containerRepository;
    private final ContainerLifecycleManager manager;
    private final Map<ContainerStateTransition, Consumer<String>> containerStateActions;

    @Autowired
    public ContainerController(ContainerRepository containerRepository, ContainerLifecycleManager manager) {
        this.containerRepository = containerRepository;
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
        return containerRepository.loadContainers();
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

        // todo: validation maybe
        containerStateActions.get(stateTransition.getValue()).accept(containerId);
    }
}

