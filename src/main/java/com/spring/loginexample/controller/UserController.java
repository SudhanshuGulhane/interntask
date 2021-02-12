package com.spring.loginexample.controller;

import com.spring.loginexample.model.Status;
import com.spring.loginexample.model.User;
import com.spring.loginexample.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepo userRepo;

    @PostMapping("/users/signup")
    public Status register(@Valid @RequestBody User user){
        List<User>savedUser = userRepo.findAll();
        for(User temp:savedUser){
            if(temp.equals(user)){
                return Status.ALREADY_EXISTS;
            }
        }
        userRepo.save(user);
        return Status.SUCCESS;
    }

    @PostMapping("/users/login")
    public Status login(@Valid @RequestBody User user){
        List<User>savedUser = userRepo.findAll();
        for(User temp:savedUser){
            if(temp.getUsername().equals(user.getUsername()) &&
               temp.getPassword().equals(user.getPassword())){
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }

    @PostMapping("/users/reset")
    public Status resetPassword(@Valid @RequestBody User user){
        List<User>savedUser = userRepo.findAll();
        for(User temp:savedUser){
            if(temp.getEmail().equals(user.getEmail())){
                System.out.println(temp.getPassword());
                temp.setPassword(user.getPassword());
                userRepo.save(temp);
                System.out.println(temp.getPassword());
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }

    @DeleteMapping("users/delete")
    public Status deleteAllMembers(){
        userRepo.deleteAll();
        return Status.SUCCESS;
    }

}
