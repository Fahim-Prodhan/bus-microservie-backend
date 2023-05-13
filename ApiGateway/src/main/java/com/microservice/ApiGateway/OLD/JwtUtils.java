//package com.microservice.ApiGateway.config;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.SecretKey;
//
//@Component
//public class JwtUtils {
//
//    private String SECRET_KEY = "bus_2236_microservice";
//
//
//    public void validateToken(final String token) {
//        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
//    }
//
//
//
//    private SecretKey getSignKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//}
