package pl.edu.agh.dockermonitor.config;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by novy on 11.06.15.
 */

@Configuration
public class EventBusConfig {

    @Bean
    public Executor executor() {
        return Executors.newCachedThreadPool();
    }

    @Bean
    public EventBus eventBus(Executor executor) {
        return new AsyncEventBus(executor);
    }

}
