package com.microservice.Appuser.controller;

import com.microservice.Appuser.config.JwtAuthenticationFilter;
import com.microservice.Appuser.config.JwtUtils;
import com.microservice.Appuser.model.AppUser;
import com.microservice.Appuser.model.JwtRequest;
import com.microservice.Appuser.model.JwtResponse;
import com.microservice.Appuser.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthenticateController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private JwtUtils jwtUtils;

    //generate token
    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {

        try {
            authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
        }catch (UsernameNotFoundException e) {
            e.printStackTrace();
            throw new Exception("User Not found");
        }
        // user is authenticated
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (DisabledException e) {
            throw new Exception("User Disable"+e.getMessage());
        }catch (BadCredentialsException e) {
            throw new Exception("Invalid Credential"+e.getMessage());
        }

    }

    @GetMapping("/current-user")
    public AppUser getCurrentUser(Principal principal)  {
        return ((AppUser)userDetailsService.loadUserByUsername(principal.getName()));
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token){
//        String token = authorizationHeader.substring(7);

        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtUtils.extractUsername(token));
        if (jwtUtils.validateToken(token,userDetails)) {
            return "Token Validate";
        }
        return "invalid";
    }

    @GetMapping("/test")
    public String testing(){
        return "test success";
    }
}
