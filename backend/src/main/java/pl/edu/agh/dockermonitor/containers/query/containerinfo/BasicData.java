package pl.edu.agh.dockermonitor.containers.query.containerinfo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by novy on 09.06.15.
 */

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class BasicData {

    private String containerId;
    private String imageId;
    private String[] names;
    private String status;
    private String command;
    private long created;
}
