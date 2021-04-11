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

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public Response addUser(User user) {
        Optional<User> present=userRepository.findById(user.getUserId());
        if(present.isPresent()){
            return new Response(HttpStatus.BAD_REQUEST, "User already exist");
        }
        userRepository.save(user);
        return new Response(HttpStatus.CREATED, "User Registered");
    }

    public User getUser(Long userId) throws UserNotFoundException {
        Optional<User> user=userRepository.findById(userId);
        if(user.isEmpty()){
            throw new UserNotFoundException("User not found in the repository");
        }
        return user.get();
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }


    public Response updateUsername(Long userId, String userName) throws UserNotFoundException {
        Optional<User> user=userRepository.findById(userId);
        if(user.isEmpty()){
            throw new UserNotFoundException("User not found in the repository");
        }
        user.get().setUserName(userName);
        return new Response(HttpStatus.OK, "Username updated");
    }


    /**
     * Description:
     * @param userId
     * @return Response
     * @throws UserNotFoundException
     */
    public Response exist(Long userId) throws UserNotFoundException{
        Optional<User> user=userRepository.findById(userId);
        if(user.isEmpty()){
            throw new UserNotFoundException("User not found in the repository");
        }
        return new Response(HttpStatus.OK, "Yes");
    }


    List<User> softCopyOfDeletedUsers=new ArrayList<>();
    public Response deleteUser(Long userId) {
        Optional<User> user=userRepository.findById(userId);
        if(user.isEmpty()){
            return new Response(HttpStatus.NOT_FOUND, "User not found in repository");
        }
        softCopyOfDeletedUsers.add(user.get());
        userRepository.deleteById(userId);
        return new Response(HttpStatus.OK, "Deleted");
    }
}
