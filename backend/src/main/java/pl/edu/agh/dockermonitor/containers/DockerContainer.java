package pl.edu.agh.dockermonitor.containers;

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
public class DockerContainer {

    private String containerId;
    private String imageId;
    private String[] names;
    private String status;
    private String command;


}
