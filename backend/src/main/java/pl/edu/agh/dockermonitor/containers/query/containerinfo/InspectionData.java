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
public class InspectionData {

    private String ipAddress;
    private State state;

    public InspectionData(String ipAddress, boolean running, boolean paused) {
        this.ipAddress = ipAddress;
        this.state = determineFrom(running, paused);
    }

    private State determineFrom(boolean running, boolean paused) {
        if (!running) {
            return State.STOPPED;
        } else if (paused) {
            return State.PAUSED;
        } else {
            return State.RUNNING;
        }
    }
}
