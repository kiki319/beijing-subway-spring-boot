package com.chengjiaqi.beijing.subway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 接口文档配置类
 * @author chengjiaqi
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Value("${swagger.host}")
    private String swaggerHost;

    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .host(swaggerHost)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.chengjiaqi.beijing.subway.controller"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("[beijing-subway]")
                .description("北京地铁信息")
                .version("0.0.1")
                .build();
    }
}
