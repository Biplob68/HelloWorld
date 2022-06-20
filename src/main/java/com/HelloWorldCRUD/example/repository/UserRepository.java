package com.HelloWorldCRUD.example.repository;

import com.HelloWorldCRUD.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    List<User> findByFirstName(String firstname);
    List<User> findByLastName(String lastname);
    List<User> findByIsActiveTrue();

    //List<User> findByUSerFirstNameIgnoreCase(String fname);
}
