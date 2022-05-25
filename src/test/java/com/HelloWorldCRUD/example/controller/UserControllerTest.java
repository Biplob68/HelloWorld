package com.HelloWorldCRUD.example.controller;

import com.HelloWorldCRUD.example.converter.ApiResponseConverter;
import com.HelloWorldCRUD.example.converter.UserConverter;
import com.HelloWorldCRUD.example.dto.UserDto;
import com.HelloWorldCRUD.example.entity.HttpStatus;
import com.HelloWorldCRUD.example.entity.User;
import com.HelloWorldCRUD.example.service.UserService;
import com.HelloWorldCRUD.example.service.UserServiceImpl;
import com.HelloWorldCRUD.example.util.ApiResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

import static org.mockito.ArgumentMatchers.any;

//@WebMvcTest(UserController.class)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;

    private User user, user1;
    private UserDto userDto;
    @Mock
    private UserConverter userConverter;
    @Mock
    private ApiResponseConverter apiResponseConverter;
    private ApiResponse apiResponse;
    private UserDto userDto1;

    @BeforeEach
    void setUp() {
        user = User.builder().id(1L)
                .email("hridoy@gmail.com")
                .firstName("Hridoy").lastName("Mina")
                .mobileNo("01521xxx").isActive(Boolean.TRUE).build();

        user1 = User.builder().id(2L)
                .email("hridoy1@gmail.com")
                .firstName("Biplob").lastName("Mina")
                .mobileNo("01959xxx").isActive(Boolean.TRUE).build();

//        List<UserDto> userDtoList = new ArrayList<UserDto>();
//        userDtoList.add(new UserDto(2L, "Biplob", "Mina", "biplob@gmail.com", "01521xxx", Boolean.TRUE));
//        userDtoList.add(new UserDto(3L, "Biplob", "Mina", "biplob1@gmail.com", "01521xxx", Boolean.TRUE));

        userDto1 = new UserDto(1l, "Hridoy", "Mina", "hridoy@gmail.com", "01521xxx", Boolean.TRUE);
        apiResponse = new ApiResponse("User is successfully saved!", user, HttpStatus.SUCCESS);
    }

    @Test
    public void saveUserTest(){
        Mockito.when(userConverter.UserDtoToEntity(userDto1)).thenReturn(user);
        Mockito.when(userService.saveUser(user)).thenReturn(user);
        Mockito.when(userConverter.UserEntityToDTO(user)).thenReturn(userDto1);
        Mockito.when(apiResponseConverter.DtoToResponse(userDto1, "User is successfully saved!", "User not found!")).thenReturn(apiResponse);

        ApiResponse apiResponse1 = userController.saveUser(userDto1);

        Assertions.assertEquals(apiResponse1, apiResponse);
    }

    @Test
    public void getUsersTest(){
        //apiResponse = new ApiResponse("User is successfully saved!", List.of(user, user1), HttpStatus.SUCCESS);
        List<UserDto> userDtoList = new ArrayList<UserDto>();
        userDtoList.add(new UserDto(1L, "Biplob", "Mina", "biplob@gmail.com", "01521xxx", Boolean.TRUE));
        userDtoList.add(new UserDto(2L, "Biplob", "Mina", "biplob1@gmail.com", "01521xxx", Boolean.TRUE));

        Mockito.when(userService.getUsers()).thenReturn(List.of(user,user1));
        Mockito.when(userConverter.UserEntityToDTO(List.of(user,user1))).thenReturn(userDtoList);
        Mockito.when(apiResponseConverter.DtoToResponse(userDtoList, "User is successfully saved!", "User not found!")).thenReturn(apiResponse);

        ApiResponse apiResponse1 = userController.getUsers();

        Assertions.assertEquals(apiResponse1, apiResponse);
    }


}
