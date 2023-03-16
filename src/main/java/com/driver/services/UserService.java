package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository3;

    public User createUser(String username, String password){

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            userRepository3.save(user);
            return user;
    }

    public void deleteUser(int userId){

        userRepository3.deleteById(userId);

    }

    public User updateUser(Integer id, String password) throws Exception {
        User user;
        try {
            //find the user
             user = userRepository3.findById(id).get();
        }
        catch (Exception e){
            throw new Exception("User not found");
        }
        user.setPassword(password); //set the new password
        userRepository3.save(user);
        return user;
    }
}
