package ru.practicum.explore;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "explore")
public class ConfigProperties {

    private String statistic;
//    private int port;
//    private String from;

    // standard getters and setters
}
