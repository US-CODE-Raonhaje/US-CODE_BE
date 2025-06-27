package com.raonhaje.memorymap.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI baseOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("기억의 지도 API")
                        .version("v1")
                        .description("기억의 지도 프로젝트의 API 명세서입니다.")
                );
    }
}
