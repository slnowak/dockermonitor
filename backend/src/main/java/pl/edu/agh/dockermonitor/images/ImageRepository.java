package pl.edu.agh.dockermonitor.images;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by novy on 08.06.15.
 */

@Component
public class ImageRepository {

    private final DockerClient dockerClient;
    private static final String[] REPO_TAG_WITH_EMPTY_TAGS;

    static {
        REPO_TAG_WITH_EMPTY_TAGS = new String[]{"<none>:<none>"};
    }

    @Autowired
    public ImageRepository(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }

    public Collection<DockerImage> loadImages() {

        return getDockerImages()
                .stream()
                .filter(image -> !Arrays.equals(REPO_TAG_WITH_EMPTY_TAGS, image.getRepoTags()))
                .map(image -> new DockerImage(
                        determineRepositoryName(image.getRepoTags()),
                        determineTag(image.getRepoTags()),
                        image.getId(),
                        image.getCreated(),
                        convertToMbytes(image.getVirtualSize())
                ))
                .collect(Collectors.toList());
    }

    private BigDecimal convertToMbytes(long possiblyBytes) {
        // weird...
        return BigDecimal.valueOf(possiblyBytes / 10000, 2);
    }

    private List<Image> getDockerImages() {
        return dockerClient.
                listImagesCmd()
                .withShowAll(true)
                .exec();
    }

    private String determineRepositoryName(String[] repositoryTags) {
        return repositoryTags[0].split(":")[0];
    }

    private String determineTag(String[] repositoryTags) {
        return repositoryTags[0].split(":")[1];
    }
}
