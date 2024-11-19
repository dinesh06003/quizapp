package com.dinesh.quizapp.controller;

import com.dinesh.quizapp.model.User;
import com.dinesh.quizapp.service.JwtService;
import com.dinesh.quizapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authentication.isAuthenticated()){
            String generatedToken = jwtService.generateToken(user.getUsername());
            return new ResponseEntity<>(generatedToken, HttpStatus.FOUND);
        }else {
            return new ResponseEntity<>("Not Authenticated", HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user){
        String result = userService.saveUser(user);
        return new ResponseEntity<>(result, HttpStatus.CREATED);

    }

}
