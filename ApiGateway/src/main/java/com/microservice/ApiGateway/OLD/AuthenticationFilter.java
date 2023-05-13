//package com.microservice.ApiGateway.filter;
//
////import com.microservice.ApiGateway.config.JwtUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//@Component
//public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
//
//    @Autowired
//    private RouteFilter validator;
//
////    @Autowired
////    private JwtUtils jwtUtils;
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    private Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
//    public static class Config {
//    }
//
//    public AuthenticationFilter() {
//        super(Config.class);
//    }
//
//    @Override
//    public GatewayFilter apply(Config config) {
//        return ((exchange, chain) -> {
//            if (validator.isSecured.test(exchange.getRequest())) {
//                //header contains token or not
//                String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
//                String token = authHeader.substring(7);
//                String uri = "http://localhost:3034/auth/validate?token="+token;
//
//                ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
//
//                if (response.getStatusCode()==HttpStatus.OK) {
//                    return chain.filter(exchange);
//                }else {
//                    return unauthorized(exchange);
//                }
//
//
//            }
//
//            return chain.filter(exchange);
//        });
//    }
//    private Mono<Void> unauthorized(ServerWebExchange exchange) {
//        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//        return exchange.getResponse().setComplete();
//    }
//
//}


//---------------------------------NOTE_________________________________
// this is not support the @LoadBalanced
