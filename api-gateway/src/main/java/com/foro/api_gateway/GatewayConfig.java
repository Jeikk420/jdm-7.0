package com.foro.api_gateway; 

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            // Aquí le enseñamos a la fuerza la ruta de usuarios
            .route("ms-usuarios", r -> r.path("/api/usuarios/**")
                .uri("lb://MS-USUARIOS"))
            // Puedes agregar los otros microservicios igual después
            .build();
    }
}