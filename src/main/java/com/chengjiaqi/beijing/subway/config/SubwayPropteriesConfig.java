package com.chengjiaqi.beijing.subway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "subway")
@Data
public class SubwayPropteriesConfig {

    private String url;
}
