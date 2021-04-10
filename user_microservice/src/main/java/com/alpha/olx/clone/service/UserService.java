package com.alpha.olx.clone.service;

import com.alpha.olx.clone.entity.User;
import com.alpha.olx.clone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public String addUser(User user) {
        try{
            Optional<User> present=userRepository.findById(user.getUserId());
            if(present.isPresent()){
                return "Already exist.";
            }
            userRepository.save(user);
            return "Registered.";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public User getUser(Long userId) {
        try {
            return userRepository.findById(userId).get();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<User> getUsers() {
        try{
            return userRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public String updateUsername(Long userId, String userName) {
        try {
            Optional<User> present=userRepository.findById(userId);
            if(present.isEmpty())return "No User found with that userId";
            List<User> users=userRepository.findAll();
            for(User user:users){
                if(user.getUserName().equals(userName)){
                    return "That user name is taken, Try something else.";
                }
            }
            present.get().setUserName(userName);
            return "Successfully Updated";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    List<User> softCopyOfDeletedUsers=new ArrayList<>();
    public String deleteUser(Long userId) {
        try{
            if(userRepository.existsById(userId)){
                softCopyOfDeletedUsers.add(userRepository.findById(userId).get());
            }
            userRepository.deleteById(userId);
            return "Deleted";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public String exist(Long userId) {
        try{
            boolean present=userRepository.existsById(userId);
            return present?"Yes":"No";
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
