package pl.edu.agh.dockermonitor.containers.query.containerinfo;

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

    private BasicData basicData;
    private InspectionData inspectionData;

}
