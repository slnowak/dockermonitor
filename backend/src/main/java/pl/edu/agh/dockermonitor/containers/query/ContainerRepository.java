package pl.edu.agh.dockermonitor.containers.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.dockermonitor.containers.query.containerinfo.DockerContainer;
import pl.edu.agh.dockermonitor.containers.query.containerinfo.InspectionData;
import pl.edu.agh.dockermonitor.containers.query.containerinfo.State;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by novy on 08.06.15.
 */


@Component
public class ContainerRepository {

    private final BasicDataProvider basicDataProvider;
    private final InspectionDataProvider inspectionDataProvider;
    private final StatisticsProvider statisticsProvider;

    @Autowired
    public ContainerRepository(BasicDataProvider basicDataProvider,
                               InspectionDataProvider inspectionDataProvider,
                               StatisticsProvider statisticsProvider) {
        this.basicDataProvider = basicDataProvider;
        this.inspectionDataProvider = inspectionDataProvider;
        this.statisticsProvider = statisticsProvider;
    }

    public Collection<DockerContainer> loadContainers() {
        return basicDataProvider
                .getBasicData()
                .stream()
                .map(basicData -> {
                    final InspectionData inspectionData =
                            inspectionDataProvider.getInspectionDataFor(basicData.getContainerId());

                    final State state = inspectionData.getState();

                    return new DockerContainer(
                            basicData,
                            inspectionData,
                            state == State.STOPPED ?
                                    Optional.empty() : statisticsProvider.statisticsFor(basicData.getContainerId())
                    );
                })
                .collect(Collectors.toList());
    }

}
