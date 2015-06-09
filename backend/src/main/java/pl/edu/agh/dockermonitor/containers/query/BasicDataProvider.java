package pl.edu.agh.dockermonitor.containers.query;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.dockermonitor.common.UnixEpochConversionAware;
import pl.edu.agh.dockermonitor.containers.query.containerinfo.BasicData;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by novy on 09.06.15.
 */

@Component
public class BasicDataProvider implements UnixEpochConversionAware {

    private final DockerClient dockerClient;

    @Autowired
    public BasicDataProvider(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }

    public Collection<BasicData> getBasicData() {
        return getDockerContainers()
                .stream()
                .map(container -> new BasicData(
                                container.getId(),
                                container.getImage(),
                                container.getNames(),
                                container.getStatus(),
                                container.getCommand(),
                                fromUnixEpoch(container.getCreated())

                        )
                )
                .collect(Collectors.toList());
    }

    private List<Container> getDockerContainers() {
        return dockerClient
                .listContainersCmd()
                .withShowAll(true)
                .exec();
    }
}