package pl.edu.agh.dockermonitor.images;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by novy on 08.06.15.
 */

@Component
public class ImageRepository {

    private final DockerClient dockerClient;

    @Autowired
    public ImageRepository(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }

    public Collection<DockerImage> loadImages() {

        return getDockerImages()
                .stream()
                .map(image -> new DockerImage(
                        image.getRepoTags()[0],
                        image.getRepoTags()[0],
                        image.getId(),
                        LocalDateTime.now(),
                        image.getSize()
                ))
                .collect(Collectors.toList());
    }

    private List<Image> getDockerImages() {
        return dockerClient.
                listImagesCmd()
                .withShowAll(true)
                .exec();
    }
}
