package com.spring.loginexample.controller;

import com.spring.loginexample.model.Status;
import com.spring.loginexample.model.User;
import com.spring.loginexample.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

//Mapping of urls handled here

@RestController
public class UserController {

    @Autowired
    UserRepo userRepo;

    //For registering new user
    @PostMapping("/users/signup")
    public Status register(@Valid @RequestBody User user){
        List<User>savedUser = userRepo.findAll();     //retrieving users from database
        for(User temp:savedUser){
            if(temp.equals(user)){
                return Status.ALREADY_EXISTS;    //if requested user details are found in the database
            }
        }
        userRepo.save(user); //else save the new requested user to DB
        return Status.SUCCESS;
    }

    //For sign-in operation
    @PostMapping("/users/login")
    public Status login(@Valid @RequestBody User user){
        List<User>savedUser = userRepo.findAll();       //retrieving users from database
        for(User temp:savedUser){
            if(temp.getUsername().equals(user.getUsername()) &&
               temp.getPassword().equals(user.getPassword())){
                return Status.SUCCESS;               //checks in the DB if a user with requested username and password is found or not
            }
        }
        //if not found returns Failure response
        return Status.FAILURE;
    }

    //For resetting the user password
    @PostMapping("/users/reset")
    public Status resetPassword(@Valid @RequestBody User user){
        //User object contains email and the new password of user who wish to change the password
        List<User>savedUser = userRepo.findAll();
        for(User temp:savedUser){
            //firstly the requested email is searched in our DB
            if(temp.getEmail().equals(user.getEmail())){
                //if found then the new password received from user object replaces the old password of that user
                temp.setPassword(user.getPassword()); //new password is set
                userRepo.save(temp); //the changes are saved!
                return Status.SUCCESS;
            }
        }
        // if the the requested email is not found in DB
        // i.e a new user is trying to change password which is not possible
        //so return Failure response
        return Status.FAILURE;
    }

}
