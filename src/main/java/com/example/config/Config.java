package com.example.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author I.Artyomov
 *         for see how works swagger @see http://springfox.github.io/springfox/docs/current/
 */
@Configuration
@EnableCaching
@EnableJpaRepositories("com.example")
@ImportResource({"classpath:appconfig.xml"})
@EnableSwagger2
public class Config {
    @Bean
    public Docket defaultApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Example Rest Api Title")
                .description("simple description for Example rest api")
                .build();
    }

}
