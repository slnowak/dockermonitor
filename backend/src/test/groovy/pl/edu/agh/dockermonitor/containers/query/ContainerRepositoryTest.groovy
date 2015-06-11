package pl.edu.agh.dockermonitor.containers.query

import pl.edu.agh.dockermonitor.containers.query.containerinfo.BasicData
import pl.edu.agh.dockermonitor.containers.query.containerinfo.DockerContainer
import pl.edu.agh.dockermonitor.containers.query.containerinfo.InspectionData
import pl.edu.agh.dockermonitor.containers.query.containerinfo.State
import spock.lang.Specification

/**
 * Created by novy on 09.06.15.
 */
class ContainerRepositoryTest extends Specification {

    def "should fetch basic data and then merge it with corresponding inspection data"() {

        given:
        def basicDataProviderMock = Mock(BasicDataProvider.class)
        basicDataProviderMock.getBasicData() >> [
                new BasicData(
                        "id1", "imId1", ["name1"] as String[], "status1", "command1", 1
                ),
                new BasicData(
                        "id2", "imId2", ["name2"] as String[], "status2", "command2", 2
                )
        ]

        def inspectionDataProviderMock = Mock(InspectionDataProvider.class)
        inspectionDataProviderMock.getInspectionDataFor("id1") >> new InspectionData(
                "ip1", State.PAUSED
        )
        inspectionDataProviderMock.getInspectionDataFor("id2") >> new InspectionData(
                "ip2", State.STOPPED
        )

        def objectUnderTest = new ContainerRepository(
                basicDataProviderMock,
                inspectionDataProviderMock
        )

        when:
        def actualData = objectUnderTest.loadContainers()

        then:
        actualData == [
                new DockerContainer(
                        new BasicData(
                                "id1", "imId1", ["name1"] as String[], "status1", "command1", 1
                        ),
                        new InspectionData(
                                "ip1", State.PAUSED
                        )
                ),
                new DockerContainer(
                        new BasicData(
                                "id2", "imId2", ["name2"] as String[], "status2", "command2", 2
                        ),
                        new InspectionData(
                                "ip2", State.STOPPED
                        )
                )
        ]
    }
}
