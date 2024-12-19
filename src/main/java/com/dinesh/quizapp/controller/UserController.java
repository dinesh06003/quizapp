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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        System.out.println(user.getUsername());
        System.out.println(user.getUsername());
        System.out.println("::::::::::::::::::::::::::::::::");
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authentication.isAuthenticated()){
            String userRole = userService.getUserRole(user.getUsername());
            String generatedToken = jwtService.generateToken(user.getUsername(), userRole);
            return new ResponseEntity<>(generatedToken, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Not Authenticated", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user){

        String result = userService.saveUser(user);
        return new ResponseEntity<>(result, HttpStatus.CREATED);

    }

    @PostMapping("/hello")
    public ResponseEntity<String> greating(@RequestBody String name){
        System.out.println("hello");
        System.out.println("name: "+ name);
        return  new ResponseEntity<>("greating", HttpStatus.ACCEPTED);
    }

}
