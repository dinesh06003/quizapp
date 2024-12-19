package com.dinesh.quizapp.service;

import com.dinesh.quizapp.model.User;
import com.dinesh.quizapp.model.UserPrincipal;
import com.dinesh.quizapp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        System.out.println(username);
        if(user == null){
            System.out.println("user not found: MyUserDetailService");
        }
        return new UserPrincipal(user);
    }
}
