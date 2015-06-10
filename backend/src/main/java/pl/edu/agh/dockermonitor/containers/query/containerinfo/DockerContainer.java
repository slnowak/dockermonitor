package pl.edu.agh.dockermonitor.containers.query.containerinfo;

import com.github.dockerjava.api.model.Statistics;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * Created by novy on 08.06.15.
 */

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class DockerContainer {

    private BasicData basicData;
    private InspectionData inspectionData;
    private Optional<Statistics> statistics = Optional.empty();

}
