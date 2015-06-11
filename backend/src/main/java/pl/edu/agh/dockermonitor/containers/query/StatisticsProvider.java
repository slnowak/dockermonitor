package pl.edu.agh.dockermonitor.containers.query;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.StatsCallback;
import com.github.dockerjava.api.model.Statistics;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.edu.agh.dockermonitor.containers.query.containerinfo.ContainerStatistics;

/**
 * Created by novy on 10.06.15.
 */

@Service
public class StatisticsProvider {

    private final DockerClient dockerClient;
    private final EventBus eventBus;

    @Autowired
    public StatisticsProvider(DockerClient dockerClient, EventBus eventBus) {
        this.dockerClient = dockerClient;
        this.eventBus = eventBus;
    }

    @Async
    public void requestStatisticsFor(String containerId) {
        System.out.println("called witj " + containerId + " in " + Thread.currentThread().getName());
        final EventBusAwareCallback statsCallback = new EventBusAwareCallback(containerId, eventBus);

        dockerClient
                .statsCmd(statsCallback)
                .withContainerId(containerId)
                .exec();
    }

    private class EventBusAwareCallback implements StatsCallback {

        private final String containerId;
        private final EventBus eventBus;

        public EventBusAwareCallback(String containerId, EventBus eventBus) {
            this.containerId = containerId;
            this.eventBus = eventBus;
        }

        @Override
        public void onStats(Statistics stats) {
            eventBus.post(
                    new ContainerStatistics(containerId, stats)
            );
        }

        @Override
        public void onException(Throwable throwable) {
        }

        @Override
        public void onCompletion(int numStats) {

        }

        @Override
        public boolean isReceiving() {
            return true;
        }
    }
}

