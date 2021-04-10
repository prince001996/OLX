package com.alpha.olx.clone.controller;

import com.alpha.olx.clone.entity.User;
import com.alpha.olx.clone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    //Register User: Post Request: /register
    @PostMapping("/register")
    private String addUser(@RequestBody User user){
        return userService.addUser(user);
    }


    //Get user with given id: Get Request: /user/{id}
    @GetMapping("/user/{id}")
    private User getUser(@PathVariable("id") Long userId){
        return userService.getUser(userId);
    }


    //Get All users: Get Request: /users
    @GetMapping("/users")
    private List<User> getUsers(){
        return userService.getUsers();
    }


    //Check if user with user id exist: /exist/{id}
    @GetMapping("/exist/{id}")
    private String exist(@PathVariable("id") Long userId){
        return userService.exist(userId);
    }


    //Edit username for given user id: /username/{id}
    @PutMapping("/username/{id}")
    private String updateUsername(@PathVariable("id") Long userId, @RequestBody String userName){
        return userService.updateUsername(userId, userName);
    }


    //Soft delete user with given userid: /delete/{id}
    @DeleteMapping("/delete/{id}")
    private String deleteUser(@PathVariable("id") Long userId){
        return userService.deleteUser(userId);
    }
}
