package com.alpha.olx.clone.service;

import com.alpha.olx.clone.DTO.Response;
import com.alpha.olx.clone.entity.User;
import com.alpha.olx.clone.exceptions.UserNotFoundException;
import com.alpha.olx.clone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*=========================================================================================================*/

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /*==================================================================================================*/

    /**
     * @Description: Register/add user
     * @param user
     * @return Response
     */
    public Response addUser(User user) {
        Optional<User> present=userRepository.findById(user.getUserId());
        Response response=new Response();
        if(present.isPresent()){
            response.setMessage("User already exist with that user id");
            response.setStatus(HttpStatus.BAD_REQUEST);
            return response;
        }
        userRepository.save(user);
        response.setStatus(HttpStatus.CREATED);
        response.setMessage("User Registered");
        return response;
    }

    /*==================================================================================================*/

    /**
     * @Description: Get user for given user id
     * @param userId
     * @return User
     * @throws UserNotFoundException
     */
    public User getUser(Long userId) throws UserNotFoundException {
        Optional<User> user=userRepository.findById(userId);
        if(user.isEmpty()){
            throw new UserNotFoundException("User not found in the repository");
        }
        return user.get();
    }

    /*==================================================================================================*/

    /**
     * @Description: Get all user from repository
     * @return List of user
     */
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /*==================================================================================================*/

    /**
     * @Description: Update username for given userID
     * @param userId
     * @param userName
     * @return Response
     * @throws UserNotFoundException
     */
    public Response updateUsername(Long userId, String userName) throws UserNotFoundException {
        Optional<User> user=userRepository.findById(userId);
        if(user.isEmpty()){
            throw new UserNotFoundException("User not found in the repository");
        }
        user.get().setUserName(userName);
        userRepository.save(user.get());
        Response response=new Response();
        response.setMessage("Username updated");
        response.setStatus(HttpStatus.OK);
        return response;
    }

    /*==================================================================================================*/

    /**
     * Description:Check if user exist for given user id
     * @param userId
     * @return Response
     * @throws UserNotFoundException
     */
    public Response exist(Long userId) throws UserNotFoundException{
        Optional<User> user=userRepository.findById(userId);
        if(user.isEmpty()){
            throw new UserNotFoundException("User not found in the repository");
        }
        Response response=new Response();
        response.setMessage("Yes");
        response.setStatus(HttpStatus.OK);
        return response;
    }


    /*==================================================================================================*/


    List<User> softCopyOfDeletedUsers=new ArrayList<>();

    /**
     * @Description: Delete user for given user id
     * @param userId
     * @return Response
     */
    public Response deleteUser(Long userId) {
        Optional<User> user=userRepository.findById(userId);
        Response response=new Response();
        if(user.isEmpty()){
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("User not found in repository");
            return response;
        }
        softCopyOfDeletedUsers.add(user.get());
        userRepository.deleteById(userId);
        response.setMessage("Deleted");
        response.setStatus(HttpStatus.OK);
        return response;
    }


    /*==================================================================================================*/


    /**
     * @Description: Get user by username
     * @param userName
     * @return User
     * @throws UserNotFoundException
     */
    public User getUserByUserName(String userName) throws UserNotFoundException{
        Optional<User> user=userRepository.findByUserName(userName);
        if(user.isEmpty()){
            throw new UserNotFoundException("User not found in repository");
        }
        return user.get();
    }


    /*==================================================================================================*/

    /**
     * @Description: Update mobile no for given user id
     * @param userId
     * @param mobile
     * @return Response
     */
    public Response updateMobile(Long userId, Long mobile) {
        Optional<User> user=userRepository.findById(userId);
        Response response=new Response();
        if(user.isEmpty()){
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("User not found in repository");
            return response;
        }
        user.get().setMobile(mobile);
        userRepository.save(user.get());
        response.setMessage("Mobile no updated");
        response.setStatus(HttpStatus.OK);
        return response;
    }
}
