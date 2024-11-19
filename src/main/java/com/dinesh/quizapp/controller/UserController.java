package com.dinesh.quizapp.controller;

import com.dinesh.quizapp.model.User;
import com.dinesh.quizapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        boolean result = userService.checkUser(user);
        if(result){
         return new ResponseEntity<>("found" + user.toString(), HttpStatus.FOUND);
        }else {
            return  new ResponseEntity<>("User Not Found with " + user.toString(), HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user){
        String result = userService.saveUser(user);
        return new ResponseEntity<>(result, HttpStatus.CREATED);

    }

}
