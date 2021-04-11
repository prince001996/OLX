package com.alpha.olx.clone.controller;

import com.alpha.olx.clone.DTO.Response;
import com.alpha.olx.clone.entity.User;
import com.alpha.olx.clone.exceptions.UserNotFoundException;
import com.alpha.olx.clone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * @Description: Resgister user
     * @param: user
     * @return: user
     */
    @PostMapping("/register/")
    private Response addUser(@RequestBody User user){
        return userService.addUser(user);
    }


    /**
     * @Description: Get user from userId
     * @param: userId
     * @return: user
     */
    @GetMapping("/user/{id}")
    private User getUser(@PathVariable("id") Long userId){
        try{
            return userService.getUser(userId);
        }catch (UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


    /**
     * @Description: Get all users
     * @param:
     * @return: list of all users
     */
    @GetMapping("/users")
    private List<User> getUsers(){
        return userService.getUsers();
    }


    /**
     * @Description: User exist or not check
     * @param: userId
     * @return: DTO
     */
    @GetMapping("/exist/{id}")
    private Response exist(@PathVariable("id") Long userId){
        try {
            return userService.exist(userId);
        }catch (UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


    /**
     * @Description: Update username for a given userId
     * @param: userId
     * @param: userName
     * @return: DTO
     */
    @PutMapping("/username/{id}")
    private Response updateUsername(@PathVariable("id") Long userId, @RequestBody String userName){
        try{
            return userService.updateUsername(userId, userName);
        }catch (UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


    /**
     * @Description: Delete user
     * @param: userId
     * @return: DTO
     */
    @DeleteMapping("/delete/{id}")
    private Response deleteUser(@PathVariable("id") Long userId){
        return userService.deleteUser(userId);
    }
}
