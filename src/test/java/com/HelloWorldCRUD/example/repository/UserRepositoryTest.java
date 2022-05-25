package com.HelloWorldCRUD.example.repository;


import com.HelloWorldCRUD.example.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@DataJpaTest
//@Sql(scripts = "/test_data.sql" ) //, executionPhase = BEFORE_TEST_METHOD)
//@Sql(scripts = "/cleanup-data.sql", executionPhase = AFTER_TEST_METHOD)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    @Sql("/test_data.sql")
    public void findUserByEmailTest() {
        User user = userRepository.findByEmail("biplob@gmail.com");
        Assertions.assertNotNull(user);
        Assertions.assertEquals("Biplob", user.getFirstName());
    }

    @Test
    @Sql("/test_data.sql")
    public void findByFirstNameTest(){
        List<User> user = userRepository.findByFirstName("Biplob");
        Assertions.assertNotNull(user);
        Assertions.assertEquals(user.size(),1);

    }
    @Test
    @Sql("/test_data.sql")
    public void findByLastNameTest(){
        List<User> user = userRepository.findByLastName("Mina");
        Assertions.assertNotNull(user);
        Assertions.assertEquals(user.size(),2);
    }

    @Test
    @Sql("/test_data.sql")
    public void findByIsActiveTrueTest(){
        List<User> userList = userRepository.findByIsActiveTrue();
        Assertions.assertNotNull(userList);
        Assertions.assertEquals(userList.size(),2);
    }
}
