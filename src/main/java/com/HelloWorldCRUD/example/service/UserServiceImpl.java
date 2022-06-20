package com.HelloWorldCRUD.example.service;

import com.HelloWorldCRUD.example.entity.User;
import com.HelloWorldCRUD.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private User user;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Method to save a user
    public User saveUser(User user){
        User new_user = new User();
        /*if(Objects.nonNull(user) && Objects.isNull(userRepository.findByEmail(user.getEmail())) ) {
            if (user.getId() > 0) new_user.setId(user.getId());
            if (user.getFirstName() != null) new_user.setFirstName(user.getFirstName());
            if (user.getLastName() != null) new_user.setLastName(user.getLastName());
            if (user.getEmail() != null) new_user.setEmail(user.getEmail());
            if (user.getMobileNo() != null) new_user.setMobileNo(user.getMobileNo());
            return userRepository.save(new_user);
        } else return null;*/
        userRepository.save(user);
        return user;
    }

    // Method to save a list of users
    public List<User> saveUsers(List<User> users){
        List<User> new_users = new ArrayList<>();
        new_users.addAll(users);
        return userRepository.saveAll(new_users);
    }

    // Get all user
    public List<User> getUsers(){
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"));
       // return repository.findAll();
    }

    public User getUserById(long id){
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getUserByFirstName(String firstname){
        List<User> users = userRepository.findByFirstName(firstname);
        return (users.size()==0)?null:users;
    }

    public List<User> getUserByLastName(String lname){
        List<User> users = userRepository.findByLastName(lname);
        return (users.size()==0)?null:users;
    }

    public User getUserByEmail(String emailName){

        return userRepository.findByEmail(emailName);
    }

    @Override
    public User deleteUser(long id) {
        User temp_user = userRepository.findById(id).orElse(null);
        if(Objects.nonNull(temp_user)){
            userRepository.delete(temp_user);
        }
        //return temp_user;
        return temp_user;
    }

    @Override
    public User updateUser(User user, long id) {
        User temp_user = userRepository.findById(id).orElse(null);
        if(temp_user != null){
            if(user.getFirstName() != null) temp_user.setFirstName(user.getFirstName());
            if(user.getLastName() != null) temp_user.setLastName(user.getLastName());
            if(user.getEmail() != null) temp_user.setEmail(user.getEmail());
            if(user.getMobileNo() != null) temp_user.setMobileNo(user.getMobileNo());
            return userRepository.save(temp_user);
        } else return temp_user;
    }


    public User deactivateUser(long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user != null){
            user.setIsActive(false);
            userRepository.save(user);
        }
        return user;
    }

    @Override
    public List<User> getActiveUsers() {
        return userRepository.findByIsActiveTrue();
    }


}
