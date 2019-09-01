package com.stacksimplify.restservices.services;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
   public User createUser(User user) throws UserExistsException {

      // check if user exists using username
      User existingUser = userRepository.findByUsername(user.getUsername());
      // if exists throw UserExists Exception
      if (existingUser != null) {
         throw new UserExistsException("User already exists in repository");
      }



      return userRepository.save(user);
   }
   // getUserById
   public Optional<User> getUserById(Long id) throws UserNotFoundException

   {
      Optional<User> user = userRepository.findById(id);
      if(!user.isPresent()) {
         throw new UserNotFoundException("User " + id.toString() + " not found in user Respository");
      }
      return user;
   }

   // updateUserById

   public User updateUserById(Long id, User user) throws UserNotFoundException{
      Optional<User> optionalUser = userRepository.findById(id);
      if(!optionalUser.isPresent()) {
         throw new UserNotFoundException("User " + id.toString() + " not found in user Respository");
      }
      user.setId(id);
      return userRepository.save(user);
   }

   //deleteUserById
   public void deleteUserById(Long id) {

      Optional<User> optionalUser = userRepository.findById(id);
      if(!optionalUser.isPresent()) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User " + id.toString() + " not found in user Respository");
      }

         userRepository.deleteById(id);




   }

   // getUserByUserName
   public User getUserByUserName(String username) {
      return userRepository.findByUsername(username);

   }
}
