package com.HelloWorldCRUD.example.converter;

import com.HelloWorldCRUD.example.entity.HttpStatus;
import com.HelloWorldCRUD.example.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ApiResponseConverter {

    @Autowired
    ApiResponse apiResponse;

    public ApiResponse DtoToResponse(Object dto, String successMessage, String errorMessage){
        apiResponse = new ApiResponse();

        if(Objects.isNull(dto)){
            apiResponse.setApiResponse(errorMessage, dto, HttpStatus.ERROR);
        }else {
            apiResponse.setApiResponse(successMessage, dto, HttpStatus.SUCCESS);
        }
        return apiResponse;
    }
}
