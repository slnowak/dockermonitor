package pl.edu.agh.dockermonitor.containers.command;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by novy on 09.06.15.
 */

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContainerStateTransitionDTO {

    private ContainerStateTransition value;
}
