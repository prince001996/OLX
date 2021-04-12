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

/*========================================================================================================*/

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    /*==================================================================================================*/

    /**
     * @Description: Resgister user
     * @param: user
     * @return: user
     */
    @PostMapping("/register/")
    private Response addUser(@RequestBody User user){
        try {
            return userService.addUser(user);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, e.getMessage());
        }
    }


    /*==================================================================================================*/

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

    /*==================================================================================================*/

    /**
     * @Description: Get user by username
     * @param userName
     * @return user
     */
    @GetMapping("/username/{username}")
    private User getUserByUserName(@PathVariable("username") String userName){
        try{
            return userService.getUserByUserName(userName);
        }catch (UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    /*==================================================================================================*/

    /**
     * @Description: Get all users
     * @param:
     * @return: list of all users
     */
    @GetMapping("/users")
    private List<User> getUsers(){
        try {
            return userService.getUsers();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, e.getMessage());
        }
    }

    /*==================================================================================================*/
    /**
     * @Description: User exist or not check
     * @param: userId
     * @return: Response
     */
    @GetMapping("/exist/{id}")
    private Response exist(@PathVariable("id") Long userId){
        try {
            return userService.exist(userId);
        }catch (UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    /*==================================================================================================*/


    /**
     * @Description: Update username for a given userId
     * @param: userId
     * @param: userName
     * @return: Response
     */
    @PutMapping("/update/username/{id}")
    private Response updateUsername(@PathVariable("id") Long userId, @RequestBody String userName){
        try{
            return userService.updateUsername(userId, userName);
        }catch (UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


    /*==================================================================================================*/


    /**
     * @Description: Update mobile for given user id
     * @param userId
     * @param mobile
     * @return Response
     */
    @PutMapping("/update/mobile/{id}")
    private Response updateMobile(@PathVariable("id") Long userId, @RequestBody Long mobile){
        try {
            return userService.updateMobile(userId, mobile);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, e.getMessage());
        }
    }

    /*==================================================================================================*/


    /**
     * @Description: Delete user
     * @param: userId
     * @return: Response
     */
    @DeleteMapping("/delete/{id}")
    private Response deleteUser(@PathVariable("id") Long userId){
        try{
            return userService.deleteUser(userId);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, e.getMessage());
        }
    }
}
