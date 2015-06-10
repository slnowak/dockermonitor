package pl.edu.agh.dockermonitor.containers.query;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.StatsCallback;
import com.github.dockerjava.api.model.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by novy on 10.06.15.
 */

@Component
public class StatisticsProvider {

    private final DockerClient dockerClient;

    @Autowired
    public StatisticsProvider(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }

    public Optional<Statistics> statisticsFor(String containerId) {

        // the api for StatsCmd is async, but I don't really understand why
        // so this is a temporary workaround
        // for more details, check https://github.com/docker-java/docker-java/issues/242

        final BlockingQueue<Optional<Statistics>> queue = new LinkedBlockingDeque<>();
        final BlockingQueueAwareStatsCallback statsCallback = new BlockingQueueAwareStatsCallback(queue);

        dockerClient
                .statsCmd(statsCallback)
                .withContainerId(containerId)
                .exec();

        Optional<Statistics> optionalStats = Optional.empty();
        try {
            optionalStats = queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return optionalStats;
    }

    private class BlockingQueueAwareStatsCallback implements StatsCallback {

        private final BlockingQueue<Optional<Statistics>> queue;

        public BlockingQueueAwareStatsCallback(BlockingQueue<Optional<Statistics>> queue) {
            this.queue = queue;
        }

        @Override
        public void onStats(Statistics stats) {
            System.out.println(stats.getMemoryStats());
            try {
                queue.put(Optional.of(stats));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onException(Throwable throwable) {
            try {
                queue.put(Optional.empty());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

