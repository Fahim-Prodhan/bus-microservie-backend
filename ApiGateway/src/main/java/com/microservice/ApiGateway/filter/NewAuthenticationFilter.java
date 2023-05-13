package com.microservice.ApiGateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class NewAuthenticationFilter extends AbstractGatewayFilterFactory <NewAuthenticationFilter.Config>{

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private RouteFilter validator;

    public static class Config {
    }

    public NewAuthenticationFilter() {
        super(Config.class);
    }


    private Mono<Void> unauthorized(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
           if (validator.isSecured.test(exchange.getRequest())){
               String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
               String token = authHeader.substring(7);

               String serviceUrl = "http://APP-USER/auth/validate?token=" + token;

               return webClientBuilder.build().get().uri(serviceUrl).retrieve().toBodilessEntity()
                       .flatMap(voidResponseEntity -> {
                           if (voidResponseEntity.getStatusCode()== HttpStatus.OK){
                               return chain.filter(exchange);
                           }else {
                               return unauthorized(exchange);
                           }
                       });
           }
            return chain.filter(exchange);
        });

    }


}
