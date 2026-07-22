package com.medilabo.gateway_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@Configuration
public class GatewayConfig {

    @Bean
    public RouterFunction<ServerResponse> patientServiceRoute() {
        return route("patient-service")
                .GET("/patients", http())
                .GET("/patients/**", http())
                .POST("/patients", http())
                .PUT("/patients/**", http())
                .before(uri("http://localhost:8082"))
                .build();
    }
}