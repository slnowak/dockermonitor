package pl.edu.agh.dockermonitor.containers.query;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.InspectContainerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.dockermonitor.containers.query.containerinfo.InspectionData;

/**
 * Created by novy on 09.06.15.
 */

@Component
public class InspectionDataProvider {

    private final DockerClient dockerClient;

    @Autowired
    public InspectionDataProvider(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }

    public InspectionData getInspectionDataFor(String containerId) {
        final InspectContainerResponse inspectContainerResponse =
                dockerClient.inspectContainerCmd(containerId).exec();

        return new InspectionData(
                inspectContainerResponse.getNetworkSettings().getIpAddress(),
                inspectContainerResponse.getState().isRunning(),
                inspectContainerResponse.getState().isPaused()
        );
    }
}
