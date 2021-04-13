package com.alpha.olx.clone.service;

import com.alpha.olx.clone.DTO.Response;
import com.alpha.olx.clone.entity.User;
import com.alpha.olx.clone.exceptions.UserNotFoundException;
import com.alpha.olx.clone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> addUser(User user) {
        Optional<User> present=userRepository.findById(user.getUserId());
        Response response=new Response();
        if(present.isPresent()){
            return new ResponseEntity<>("User already exist with that user id", HttpStatus.BAD_REQUEST);
        }
        userRepository.save(user);
        return new ResponseEntity<>("User Registered", HttpStatus.CREATED);
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
    public ResponseEntity<String> updateUsername(Long userId, String userName) throws UserNotFoundException {
        Optional<User> user=userRepository.findById(userId);
        if(user.isEmpty()){
            throw new UserNotFoundException("User not found in the repository");
        }
        user.get().setUserName(userName);
        userRepository.save(user.get());
        return new ResponseEntity<>("Username updated", HttpStatus.OK);
    }

    /*==================================================================================================*/

    /**
     * Description:Check if user exist for given user id
     * @param userId
     * @return Response
     * @throws UserNotFoundException
     */
    public ResponseEntity<String> exist(Long userId) throws UserNotFoundException{
        Optional<User> user=userRepository.findById(userId);
        if(user.isEmpty()){
            throw new UserNotFoundException("User not found in the repository");
        }
        return new ResponseEntity<>("Yes", HttpStatus.OK);
    }


    /*==================================================================================================*/


    List<User> softCopyOfDeletedUsers=new ArrayList<>();

    /**
     * @Description: Delete user for given user id
     * @param userId
     * @return Response
     */
    public ResponseEntity<String> deleteUser(Long userId) {
        Optional<User> user=userRepository.findById(userId);
        if(user.isEmpty()){
            return new ResponseEntity<>("User not found in repository", HttpStatus.NOT_FOUND);
        }
        softCopyOfDeletedUsers.add(user.get());
        userRepository.deleteById(userId);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
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
    public ResponseEntity<String> updateMobile(Long userId, Long mobile) {
        Optional<User> user=userRepository.findById(userId);
        if(user.isEmpty()){
            return new ResponseEntity<>("User not found in repository", HttpStatus.NOT_FOUND);
        }
        user.get().setMobile(mobile);
        userRepository.save(user.get());
        return new ResponseEntity<>("Mobile Updated", HttpStatus.OK);
    }
}
