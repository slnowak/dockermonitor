package pl.edu.agh.dockermonitor.containers.query.containerinfo;

import com.github.dockerjava.api.model.Statistics;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by novy on 11.06.15.
 */

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ContainerStatistics {

    private String containerId;
    private Statistics statistics;
}
