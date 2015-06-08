package pl.edu.agh.dockermonitor.containers;

import com.github.dockerjava.api.DockerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public void startContainer(String imageName) {
        dockerClient
                .createContainerCmd(imageName)
                .exec();
    }

}
