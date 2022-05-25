package com.HelloWorldCRUD.example.util;

import com.HelloWorldCRUD.example.entity.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private String message;
    private Object object;
    private HttpStatus status;

    public void setApiResponse(String sms, Object obj, HttpStatus httpStatus){
        message = sms;
        object = obj;
        status = httpStatus;
    }
}
