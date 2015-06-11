package pl.edu.agh.dockermonitor.containers.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.dockermonitor.containers.query.containerinfo.DockerContainer;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by novy on 08.06.15.
 */


@Component
public class ContainerRepository {

    private final BasicDataProvider basicDataProvider;
    private final InspectionDataProvider inspectionDataProvider;

    @Autowired
    public ContainerRepository(BasicDataProvider basicDataProvider, InspectionDataProvider inspectionDataProvider) {
        this.basicDataProvider = basicDataProvider;
        this.inspectionDataProvider = inspectionDataProvider;
    }

    public Collection<DockerContainer> loadContainers() {
        return basicDataProvider
                .getBasicData()
                .stream()
                .map(basicData -> new DockerContainer(
                                basicData,
                                inspectionDataProvider.getInspectionDataFor(basicData.getContainerId())
                        )
                )
                .collect(Collectors.toList());
    }

}
