package com.HelloWorldCRUD.example.entity;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    private String mobileNo;

//    @Column(length = 60)
//    private String passWord;
//    private String role;

    private Boolean isActive = Boolean.TRUE;
}
