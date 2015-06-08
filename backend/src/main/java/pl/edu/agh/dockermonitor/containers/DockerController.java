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
public class DockerController {

    private final DockerRepository dockerRepository;
    private final ContainerLifecycleManager manager;

    @Autowired
    public DockerController(DockerRepository dockerRepository, ContainerLifecycleManager manager) {
        this.dockerRepository = dockerRepository;
        this.manager = manager;
    }

    @RequestMapping(value = "/containers", method = RequestMethod.GET)
    public Collection<DockerContainer> containers() {
        return dockerRepository.loadContainers();
    }

    @RequestMapping(value = "/containers", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void startContainer(@RequestBody String imageName) {
        manager.startContainer(imageName);
    }
}

