package com.HelloWorldCRUD.example.controller;

import com.HelloWorldCRUD.example.converter.ApiResponseConverter;
import com.HelloWorldCRUD.example.converter.UserConverter;
import com.HelloWorldCRUD.example.dto.UserDto;
import com.HelloWorldCRUD.example.entity.User;
import com.HelloWorldCRUD.example.repository.UserRepository;
import com.HelloWorldCRUD.example.service.UserServiceImpl;
import com.HelloWorldCRUD.example.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private User user;

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserServiceImpl service;

    @Autowired
    private UserConverter converter;

    @Autowired
    private ApiResponseConverter apiResponseConverter;

    //  Method to save all user into database
    @PostMapping("/users")
    public ApiResponse saveUser(@RequestBody UserDto userDto){
        return apiResponseConverter.DtoToResponse(converter.UserEntityToDTO(service.saveUser(converter.UserDtoToEntity(userDto))),"User is successfully saved!","User not found!");
    }

    // Method to get all user
    @GetMapping("/users")
    public ApiResponse getUsers(){
        return apiResponseConverter.DtoToResponse(converter.UserEntityToDTO(service.getUsers()),"Users are successfully found!","User not found!");
    }

    // Method to get a user by id
    @GetMapping("/users/{id}")
    public ApiResponse getUserById(@PathVariable("id") long id){
        return apiResponseConverter.DtoToResponse(converter.UserEntityToDTO(service.getUserById(id)),"User is successfully found!","User not found!");
    }

    @GetMapping("/users/email")
    public ApiResponse getUserByEmail(@RequestParam String email){
        return apiResponseConverter.DtoToResponse(service.getUserByEmail(email), "User successfully found", "User not found!");
    }

    @GetMapping("/users/fname")
    public ApiResponse getUserByFirstName(@RequestParam String fname){
        return apiResponseConverter.DtoToResponse(service.getUserByFirstName(fname), "User is successfully found!","User not found!");
    }

    @GetMapping("/users/lname")
    public ApiResponse getUserByLastName(@RequestParam String lname){
        return apiResponseConverter.DtoToResponse(service.getUserByLastName(lname),"User is successfully found!", "User not found!");
    }

    // Update a user information in database
    @PutMapping("users/{id}")
    public ApiResponse updateUser(@PathVariable("id") long id,
                           @RequestBody User user){
    return apiResponseConverter.DtoToResponse(service.updateUser(user, id), "User is successfully updated!", "User not found!");
    }

    @DeleteMapping("/users/{id}")
    public ApiResponse deleteUser(@PathVariable("id") long id){
        return apiResponseConverter.DtoToResponse(converter.UserEntityToDTO(service.deleteUser(id)), "User is successfully deleted", "User not found!");
    }

    @PutMapping("/users/deactivate/{id}")
    public ApiResponse deactivateUser(@PathVariable("id") long id){
        return apiResponseConverter.DtoToResponse(converter.UserEntityToDTO(service.deactivateUser(id)), "User is successfully deactivated!", "User not found!");
    }

    @GetMapping("users/active/")
    public ApiResponse getActiveUser(){
        return apiResponseConverter.DtoToResponse(converter.UserEntityToDTO(service.getActiveUsers()), "Active users are successfully found!", "No active user");
    }

}
