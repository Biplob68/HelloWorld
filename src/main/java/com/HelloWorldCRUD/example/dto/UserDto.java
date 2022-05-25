package com.HelloWorldCRUD.example.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNo;
    private Boolean isActive = Boolean.TRUE;

    public UserDto() {
    }

    public UserDto(Long id, String firstName, String lastName, String email, String mobileNo, Boolean isActive) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNo = mobileNo;
        this.isActive = isActive;
    }
}
