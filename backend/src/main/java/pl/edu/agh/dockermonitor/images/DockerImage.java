package pl.edu.agh.dockermonitor.images;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by novy on 08.06.15.
 */

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class DockerImage {

    private String repository;
    private String tag;
    private String imageId;
    private long created;
    private long size;
}
