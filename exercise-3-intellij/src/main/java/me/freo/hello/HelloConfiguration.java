package me.freo.hello;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("/")
public class HelloConfiguration extends ResourceConfig {
    public HelloConfiguration() {}

    @PostConstruct
    public void setUp() {
        register(Resource.class);
    }
}
