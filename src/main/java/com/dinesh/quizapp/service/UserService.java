package com.dinesh.quizapp.service;

import com.dinesh.quizapp.model.User;
import com.dinesh.quizapp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private UserRepo userRepo;

    public String saveUser(User user) {
         userRepo.save(user);
         String result = user.toString();
        System.out.println(result);
         return "successfully registered user details in the database with user " + result;
    }

    public boolean checkUser(User user) {
        User findUser = userRepo.findByUsername(user.getUsername());
        if(findUser.getUsername().equals(user.getUsername()) && findUser.getPassword().equals(user.getPassword())){
            return true;
        }
        else {
            return false;
        }

    }
}
