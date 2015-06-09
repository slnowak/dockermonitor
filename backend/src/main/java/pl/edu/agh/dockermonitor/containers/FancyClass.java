package pl.edu.agh.dockermonitor.containers;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.StatsCallback;
import com.github.dockerjava.api.model.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by novy on 09.06.15.
 */

@Component
public class FancyClass {

    private final DockerClient dockerClient;

    @Autowired
    public FancyClass(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }

    @PostConstruct
    private void doSth() {
        dockerClient
                .statsCmd(new StatsCallback() {
                    @Override
                    public void onStats(Statistics stats) {
                        System.out.println(stats.getMemoryStats());
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
                })
                .withContainerId("d0ea0e40a0318834e838c4e36078e9d1977ec1a5ff48c98cea8a9f6af31411ce")
                .exec();
    }
}
