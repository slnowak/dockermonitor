package pl.edu.agh.dockermonitor.containers.query;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.edu.agh.dockermonitor.containers.query.containerinfo.ContainerStatistics;

/**
 * Created by novy on 11.06.15.
 */

@Service
public class StatisticsDispatcher {

    private final SimpMessagingTemplate messagingTemplate;
    private final StatisticsProvider statisticsProvider;

    @Autowired
    public StatisticsDispatcher(EventBus eventBus,
                                SimpMessagingTemplate messagingTemplate,
                                StatisticsProvider statisticsProvider) {
        this.messagingTemplate = messagingTemplate;
        this.statisticsProvider = statisticsProvider;
        eventBus.register(this);
    }

    @Async
    public void requestStatisticsFor(String containerId) {
        statisticsProvider.requestStatisticsFor(containerId);
    }

    @Subscribe
    @AllowConcurrentEvents
    public void dispatchContainerStatistics(ContainerStatistics statistics) {

        messagingTemplate.convertAndSend(
                "/containers/statistics", statistics
        );
    }
}
