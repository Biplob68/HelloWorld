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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    //private ApiResponse apiResponse;
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
        //ApiResponse apiResponse = new ApiResponse("User is successfully saved!", user, HttpStatus.SUCCESS);
    }

    @Test
    public void saveUserTest(){
        ApiResponse apiResponse = new ApiResponse("User is successfully saved!", user, HttpStatus.SUCCESS);
        Mockito.when(userConverter.UserDtoToEntity(userDto1)).thenReturn(user);
        Mockito.when(userService.saveUser(user)).thenReturn(user);
        Mockito.when(userConverter.UserEntityToDTO(user)).thenReturn(userDto1);
        Mockito.when(apiResponseConverter.DtoToResponse(userDto1, "User is successfully saved!", "User not found!")).thenReturn(apiResponse);

        ApiResponse apiResponse1 = userController.saveUser(userDto1);

        Assertions.assertEquals(apiResponse1, apiResponse);
    }

    @Test
    public void getUsers_Test(){
        List<UserDto> userDtoList = new ArrayList<UserDto>();
        userDtoList.add(new UserDto(1L, "Biplob", "Mina", "biplob@gmail.com", "01521xxx", Boolean.TRUE));
        userDtoList.add(new UserDto(2L, "Biplob", "Mina", "biplob1@gmail.com", "01521xxx", Boolean.TRUE));

        ApiResponse apiResponse = new ApiResponse("Users are successfully found!", userDtoList, HttpStatus.SUCCESS);

        Mockito.when(userService.getUsers()).thenReturn(List.of(user,user1));
        Mockito.when(userConverter.UserEntityToDTO(List.of(user,user1))).thenReturn(userDtoList);
        Mockito.when(apiResponseConverter.DtoToResponse(userDtoList, "Users are successfully found!", "User not found!")).thenReturn(apiResponse);

        ApiResponse apiResponse1 = userController.getUsers();

        Assertions.assertEquals(apiResponse1, apiResponse);
        Assertions.assertEquals(apiResponse1.getStatus(), apiResponse.getStatus());
    }
    @Test
    public void getUserById_Test(){
        userDto = new UserDto(1l, "Hridoy", "Mina", "hridoy@gmail.com", "01521xxx", Boolean.TRUE);
        ApiResponse apiResponse = new ApiResponse("User is successfully found!", user, HttpStatus.SUCCESS);

        Mockito.when(userService.getUserById(1L)).thenReturn(user);
        Mockito.when(userConverter.UserEntityToDTO(user)).thenReturn(userDto);
        Mockito.when(apiResponseConverter.DtoToResponse(userDto, "User is successfully found!", "User not found!")).thenReturn(apiResponse);

        ApiResponse apiResponse1 = userController.getUserById(1L);

        Assertions.assertEquals(apiResponse1, apiResponse);
    }

    @Test
    public void getUserByEmail_Test(){
        ApiResponse apiResponse = new ApiResponse("User successfully found!", user, HttpStatus.SUCCESS);

        Mockito.when(userService.getUserByEmail(user.getEmail())).thenReturn(user);
        Mockito.when(apiResponseConverter.DtoToResponse(user, "User successfully found!", "User not found!")).thenReturn(apiResponse);

        ApiResponse apiResponse1 = userController.getUserByEmail(user.getEmail());

        Assertions.assertEquals(apiResponse1, apiResponse);
    }


}
