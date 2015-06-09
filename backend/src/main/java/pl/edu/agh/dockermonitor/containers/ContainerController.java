package pl.edu.agh.dockermonitor.containers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by novy on 08.06.15.
 */

@RestController
@RequestMapping(value = "/docker")
public class ContainerController {

    private final ContainerRepository containerRepository;
    private final ContainerLifecycleManager manager;

    @Autowired
    public ContainerController(ContainerRepository containerRepository, ContainerLifecycleManager manager) {
        this.containerRepository = containerRepository;
        this.manager = manager;
    }

    @RequestMapping(value = "/containers", method = RequestMethod.GET)
    public Collection<DockerContainer> containers() {
        return containerRepository.loadContainers();
    }

    @RequestMapping(value = "/containers", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void startContainer(@RequestBody CreateContainerRequest request) {
        manager.startContainer(request);
    }
}

