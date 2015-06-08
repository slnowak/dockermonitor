package pl.edu.agh.dockermonitor.containers;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by novy on 08.06.15.
 */

@Component
public class DockerRepository {

    private final DockerClient dockerClient;

    @Autowired
    public DockerRepository(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }

    public Collection<DockerContainer> loadContainers() {
        return getDockerContainers()
                .stream()
                .map(container -> new DockerContainer(
                        container.getId(),
                        container.getStatus(),
                        container.getCommand()
                ))
                .collect(Collectors.toList());
    }

    private List<Container> getDockerContainers() {
        return dockerClient
                .listContainersCmd()
                .withShowAll(true)
                .exec();
    }
}
