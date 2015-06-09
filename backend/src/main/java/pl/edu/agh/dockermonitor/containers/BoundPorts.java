package pl.edu.agh.dockermonitor.containers;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by novy on 09.06.15.
 */

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoundPorts {

    private int outer;
    private int inner;

    @Override
    public String toString() {
        return outer + ":" + inner;
    }
}
