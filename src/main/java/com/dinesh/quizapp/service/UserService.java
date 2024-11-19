package com.dinesh.quizapp.service;

import com.dinesh.quizapp.model.User;
import com.dinesh.quizapp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private UserRepo userRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    public String saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
         userRepo.save(user);
         String result = user.toString();
        System.out.println(result);
         return "successfully registered user details in the database with user " + result;
    }

}
