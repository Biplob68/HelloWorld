package com.HelloWorldCRUD.example.service;

import com.HelloWorldCRUD.example.entity.User;
import com.HelloWorldCRUD.example.repository.UserRepository;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserServiceImpl userService;

    @MockBean
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder().email("biplob@gmail.com")
                .firstName("Biplob")
                .lastName("Mina")
                .mobileNo("01959xxx")
                .id(1L).isActive(Boolean.TRUE)
                .build();

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        //Mockito.when(userRepository.findByFirstName("Biplob")).thenReturn((List<User>) user);

    }

    @Test // Method to save a user in database
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject(){
        // given - precondition or setup
        User user1 = User.builder().email("biplob@gmail.com")
                .firstName("Biplob")
                .lastName("Mina")
                .mobileNo("01959xxx")
                .id(1L).isActive(Boolean.TRUE)
                .build();
        //given(userRepository.findByEmail(user.getEmail())).willReturn(user);
        //given(userRepository.save(user)).willReturn(user);

        //Mockito.when(userRepository.findByEmail(user1.getEmail())).thenReturn(user1);
        Mockito.when(userRepository.save(user1)).thenReturn(user1);
        User found = userService.saveUser(user1);
        assertEquals(user1, found);
       // System.out.println(userRepository);
      //  System.out.println(userService);

        // when -  action or the behaviour that we are going test
        // Employee savedEmployee = employeeService.saveEmployee(employee);

       // System.out.println(found);
        // then - verify the output
        //assertThat(found).isNotNull();
    }


    @Test  // Method to test getUserByEmail in service layer
    public void validUserEmail_thenUserShouldFound(){
        Mockito.when(userRepository.findByEmail("biplob@gmail.com")).thenReturn(user);
        String email = "biplob@gmail.com";
        User found = userService.getUserByEmail(email);
        assertEquals(email, found.getEmail());
    }

    @Test // Method to test getUserById in service layer
    public void validUserId_thenUserShouldFound(){
        long id = 1;
        User found = userService.getUserById(id);
        assertEquals(id, found.getId());
    }


    @Test // Method to test get all user
    public void givenUserList_whenGetAllUser_thenReturnUsersList(){
        // given - precondition or setup
        User user1 = User.builder().id(2L)
                        .email("hridoy@gmail.com")
                        .firstName("Hridoy").lastName("Mina")
                        .mobileNo("01521xxx").isActive(Boolean.TRUE).build();

        User user2 = User.builder().id(3L)
                .email("hridoy1@gmail.com")
                .firstName("Hridoy1").lastName("Mina")
                .mobileNo("01521xxxx").isActive(Boolean.TRUE).build();
        List<User> userList = new ArrayList<User>();
        userList.add(new User(1L, "Biplob", "Mina", "biplob@gmail.com", "01521xxx", Boolean.TRUE));
        userList.add(new User(2L, "Biplob", "Mina", "biplob1@gmail.com", "01521xxx", Boolean.TRUE));

        Mockito.when(userRepository.findAll()).thenReturn(userList);

        //given(userRepository.findAll()).willReturn(List.of(user,user1));
        List<User> userList1 = userService.getUsers();

        assertEquals(2, userList.size());

       //assertEquals(userList, List.of(user1,user));
       // assertThat(userList).isNotNull();
       // assertThat(userList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for getAllUser method (negative scenario)")
    @Test
    public void givenEmptyUserList_whenGetAllUser_thenReturnEmptyUserList(){
        // given - precondition or setup

        User user1 = User.builder().id(2L)
                .email("hridoy@gmail.com")
                .firstName("Hridoy").lastName("Mina")
                .mobileNo("01521xxx").isActive(Boolean.FALSE).build();

        given(userRepository.findAll()).willReturn(Collections.emptyList());

        // when -  action or the behaviour that we are going test
        List<User> userList = userService.getUsers();

        // then - verify the output
        assertThat(userList).isEmpty();
        assertThat(userList.size()).isEqualTo(0);
    }

    // JUnit test for updateEmployee method
    @DisplayName("JUnit test for updateEmployee method")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee(){
        // given - precondition or setup
        given(userRepository.save(user)).willReturn(user);
        user.setEmail("mina@gmail.com");
        user.setFirstName("mina");
        // when -  action or the behaviour that we are going test
        User updateUser = userService.updateUser(user,1);

        // then - verify the output
        assertThat(updateUser.getEmail()).isEqualTo("mina@gmail.com");
        assertThat(updateUser.getFirstName()).isEqualTo("mina");
    }


    // JUnit test for deleteEmployee method
    @DisplayName("JUnit test for deleteUser method")
    @Test
    public void givenUserId_whenDeleteUser_thenNothing(){
        // given - precondition or setup
        long id = 1L;
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));

        //willDoNothing().given(userRepository).deleteById(id);
        //given(userRepository.deleteById(id)).willReturn(user);
        // when -  action or the behaviour that we are going test
        User temp = userService.deleteUser(id);
        assertEquals(id, temp.getId());

        //assertEquals();
        // then - verify the output
        //verify(userRepository, times(1)).deleteById(id);
    }


}
