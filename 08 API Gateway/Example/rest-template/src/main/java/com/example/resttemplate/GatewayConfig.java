package com.example.resttemplate;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("order_service", r -> r.path("/order/**")
//                        .uri("lb://order-service"))
//                .route("product_service", r -> r.path("/product/**")
//                        .uri("lb://product-service"))
//                .route("default_route", r -> r.path("/**") // Handle any unmapped requests
//                .uri("https://www.poojajyotish.com"))
//                .build();
//    }
@Bean
public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
    return builder.routes()
            .route("r1", r -> r.host("**.baeldung.com")
                    .and()
                    .path("/baeldung")
                    .uri("http://baeldung.com"))
            .route(r -> r.host("**.baeldung.com")
                    .and()
                    .path("/myOtherRouting")
                    .filters(f -> f.prefixPath("/myPrefix"))
                    .uri("http://othersite.com")
                    )
            .build();
}
}