package com.chengjiaqi.beijing.subway;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * @author chengjiaqi
 */
@SpringBootApplication
@MapperScan("com.chengjiaqi.beijing.subway.dao")
@EnableConfigurationProperties
public class BeijingSubwayApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeijingSubwayApplication.class, args);
    }

}
