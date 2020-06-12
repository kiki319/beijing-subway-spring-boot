package com.chengjiaqi.beijing.subway.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author chengjiaqi
 */
@ConfigurationProperties(prefix = "datasource.druid")
@Component
@Setter
@Getter

public class DruidPoolPropertiesConfig {
    private String driverClassName;
    private int initialSize;
    private int maxActive;
    private Long maxEvictableIdleTimeMillis;
    private Long maxOpenPreparedStatements;
    private int maxWait;
    private Long minEvictableIdleTimeMillis;
    private int minIdle;
    private boolean poolPreparedStatements;
    private Long timeBetweenEvictionRunsMillis;
    private String type;
    private String url;
    private String username;
    private String password;

}
