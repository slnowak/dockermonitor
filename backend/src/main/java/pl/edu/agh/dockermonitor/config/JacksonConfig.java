package pl.edu.agh.dockermonitor.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Created by novy on 09.06.15.
 */
@Configuration
public class JacksonConfig {

    @Primary
    @Bean
    public ObjectMapper objectMapper(Jdk8Module jdk8Module) {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModules(jdk8Module);
        return objectMapper;
    }

    @Bean
    public Jdk8Module jdk8Module() {
        return new Jdk8Module();
    }

}

