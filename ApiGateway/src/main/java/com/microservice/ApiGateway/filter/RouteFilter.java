package com.microservice.ApiGateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteFilter {
    public static final List<String> openApiEndpoints = List.of(
            "/auth/token","auth/test","/auth/validate","/api/user/register","/auth/current-user","/api/user/count",
            "/eureka","/api/user/seller-register"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}

