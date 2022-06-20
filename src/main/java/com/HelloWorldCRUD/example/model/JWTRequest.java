package com.HelloWorldCRUD.example.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTRequest {
    private String username;
    private String password;
}


