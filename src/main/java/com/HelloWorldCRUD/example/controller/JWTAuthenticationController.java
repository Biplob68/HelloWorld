package com.HelloWorldCRUD.example.controller;

import com.HelloWorldCRUD.example.model.JWTRequest;
import com.HelloWorldCRUD.example.model.JWTResponse;
import com.HelloWorldCRUD.example.service.springsecurity.CustomUserDetailsService;
import com.HelloWorldCRUD.example.util.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JWTAuthenticationController {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/authenticate")
    public JWTResponse authenticate(@RequestBody JWTRequest jwtRequest) throws Exception{

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());

        final String token = jwtUtility.generateToken(userDetails);

        return  new JWTResponse(token);
    }
}
