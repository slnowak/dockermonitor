package pl.edu.agh.dockermonitor.containers.command;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.model.PortBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by novy on 08.06.15.
 */

@Component
public class ContainerLifecycleManager {

    private final DockerClient dockerClient;

    @Autowired
    public ContainerLifecycleManager(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }

    public void createContainer(CreateContainerRequest request) {
        final String imageId = request.getImageId();
        final Optional<String> containerName = request.getName();
        final Optional<String> containerCommand = request.getCommand();
        final Optional<BoundPorts> ports = request.getPorts();

        final CreateContainerCmd containerCommandBuilder = dockerClient
                .createContainerCmd(imageId)
                .withImage(imageId);

        if (containerName.isPresent()) {
            containerCommandBuilder
                    .withName(containerName.get());
        }

        if (containerCommand.isPresent()) {
            containerCommandBuilder
                    .withCmd(containerCommand.get());
        }

        if (ports.isPresent()) {
            containerCommandBuilder
                    .withPortBindings(PortBinding.parse(ports.get().toString()));
        }

        containerCommandBuilder.exec();
    }

    public void startContainer(String containerId) {
        System.out.println(containerId);
        dockerClient
                .startContainerCmd(containerId)
                .exec();
    }

    public void stopContainer(String containerId) {
        dockerClient
                .stopContainerCmd(containerId)
                .exec();
    }

    public void pauseContainer(String containerId) {
        dockerClient
                .pauseContainerCmd(containerId)
                .exec();
    }

    public void unpauseContainer(String containerId) {
        dockerClient
                .unpauseContainerCmd(containerId)
                .exec();
    }

    public void removeContainer(String containerId) {
        dockerClient
                .removeContainerCmd(containerId)
                .exec();
    }

}
