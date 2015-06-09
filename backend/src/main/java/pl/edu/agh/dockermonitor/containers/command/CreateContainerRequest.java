package pl.edu.agh.dockermonitor.containers.command;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.agh.dockermonitor.containers.command.BoundPorts;

import java.util.Optional;

/**
 * Created by novy on 09.06.15.
 */

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateContainerRequest {

    private String imageId;
    private Optional<String> name = Optional.empty();
    private Optional<String> command = Optional.empty();
    private Optional<BoundPorts> ports = Optional.empty();
}
