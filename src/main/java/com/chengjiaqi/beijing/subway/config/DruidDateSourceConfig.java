package com.chengjiaqi.beijing.subway.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class DruidDateSourceConfig {

    //spring注解 通过yml配置读取相关属性
    @Value(value = "${datasource.druid.url}")
    private String dbUrl;
    @Value(value = "${datasource.druid.username}")
    private String dbUsername;
    @Value(value = "${datasource.druid.password}")
    private String dbPassword;
    @Value(value = "${datasource.druid.driverClassName}")
    private String dbDriverClassName;
    @Value(value = "${datasource.druid.maxActive}")
    private String maxActive;
    @Value(value = "${datasource.druid.initialSize}")
    private String initialSize;

    @Value(value = "${datasource.druid.maxEvictableIdleTimeMillis}")
    private String maxEvictableIdleTimeMillis;

    @Value(value = "${datasource.druid.maxOpenPreparedStatements}")
    private String maxOpenPreparedStatements;
    @Value(value = "${datasource.druid.maxWait}")
    private String maxWait;
    @Value(value = "${datasource.druid.minEvictableIdleTimeMillis}")
    private String minEvictableIdleTimeMillis;
    @Value(value = "${datasource.druid.minIdle}")
    private String minIdle;
    @Value(value = "${datasource.druid.poolPreparedStatements}")
    private String poolPreparedStatements;
    //  time-between-eviction-runs-millis
    @Value(value = "${datasource.druid.timeBetweenEvictionRunsMillis}")
    private String timeBetweenEvictionRunsMillis;
    @Value(value = "${datasource.druid.type}")
    private String dbType;


    @Bean
    public DruidDataSource druidDataSource() {
        try {
            log.info("******数据库连接池开始配置*******dbUrl:{}", dbUrl);
            //druid bug 打印日志防止报异常 验证min是否大于max
            if (Long.parseLong(minEvictableIdleTimeMillis) < Long.parseLong(maxEvictableIdleTimeMillis)) {
                log.info("->->->min:{}->max:{}", minEvictableIdleTimeMillis, maxEvictableIdleTimeMillis);
            }
            DruidDataSource druidDataSource = new DruidDataSource();
            //顺序不能颠倒
            druidDataSource.setMinEvictableIdleTimeMillis(Long.parseLong(minEvictableIdleTimeMillis));
            druidDataSource.setMaxEvictableIdleTimeMillis(Long.parseLong(maxEvictableIdleTimeMillis));
            druidDataSource.setUrl(dbUrl);
            druidDataSource.setUsername(dbUsername);
            druidDataSource.setPassword(dbPassword);
            druidDataSource.setDriverClassName(dbDriverClassName);
            druidDataSource.setMaxActive(Integer.parseInt(maxActive));
            druidDataSource.setInitialSize(Integer.parseInt(initialSize));
            druidDataSource.setMaxOpenPreparedStatements(Integer.parseInt(maxOpenPreparedStatements));
            druidDataSource.setMaxWait(Long.parseLong(maxWait));
            druidDataSource.setMinIdle(Integer.parseInt(minIdle));
            druidDataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(timeBetweenEvictionRunsMillis));
            druidDataSource.setPoolPreparedStatements(Boolean.parseBoolean(poolPreparedStatements));
            druidDataSource.setDbType(dbType);
            return druidDataSource;
        } catch (Exception e) {
            log.error("数据库连接池异常", e);
            throw new RuntimeException("数据库连接池异常");
        }
    }
}
