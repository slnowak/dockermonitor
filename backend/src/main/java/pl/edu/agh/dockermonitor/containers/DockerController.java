package pl.edu.agh.dockermonitor.containers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by novy on 08.06.15.
 */

@RestController
@RequestMapping(value = "/docker")
public class DockerController {

    private final DockerRepository dockerRepository;

    @Autowired
    public DockerController(DockerRepository dockerRepository) {
        this.dockerRepository = dockerRepository;
    }

    @RequestMapping(value = "/containers", method = RequestMethod.GET)
    public Collection<DockerContainer> containers() {
        return dockerRepository.loadContainers();
    }
}

