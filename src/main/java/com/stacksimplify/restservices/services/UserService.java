package com.stacksimplify.restservices.services;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
//Autowire the UserRepository
   @Autowired
   private UserRepository userRepository;

   // getAllUsers Method
   public List<User> getAllUsers() {
      return userRepository.findAll();
   }

   // Create User Method
   public User createUser(User user) {
      return userRepository.save(user);
   }
   // getUserById
   public Optional<User> getUserById(Long id) {
      Optional<User> user = userRepository.findById(id);
      return user;
   }

   // updateUserById

   public User updateUserById(Long id, User user) {
      user.setId(id);
      return userRepository.save(user);
   }

   //deleteUserById
   public void deleteUserById(Long id) {
      if(userRepository.findById(id).isPresent()) {
         userRepository.deleteById(id);

      }


   }

   // getUserByUserName
   public User getUserByUserName(String username) {
      return userRepository.findByUsername(username);

   }
}
