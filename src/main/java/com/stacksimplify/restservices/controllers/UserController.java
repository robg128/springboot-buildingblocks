package com.stacksimplify.restservices.controllers;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

//Controller
@RestController
public class UserController {
   //Autowire the UserService
   @Autowired
   private UserService userService;

   //getAllUsers Method
   @GetMapping("/users")
   public List<User> getAllUsers() {
      return userService.getAllUsers();
   }

   // Create User method
   //@RequestBody
   //@PostMapping
   @PostMapping("/users")
   public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder builder) {

      try {
          userService.createUser(user);
         HttpHeaders headers = new HttpHeaders();
         headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
         return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
      } catch (UserExistsException ex) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
      }
   }

// getUserById
   @GetMapping("/users/{id}")
   public Optional<User> getUserById(@PathVariable("id") Long id) {

      try {
         return userService.getUserById(id);
      } catch (UserNotFoundException ex) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
      }

   }
   // getUserById
   @GetMapping("/users/byusername/{username}")
   public User getUserByUserName(@PathVariable("username") String username) {
      return userService.getUserByUserName(username);
   }

   // updateUserById
   @PutMapping("/users/{id}")
   public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
      try {
         return userService.updateUserById(id, user);
      } catch(UserNotFoundException ex) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
      }

   }

   // deleteUserById
   @DeleteMapping("/users/{id}")
   public void deleteUserById(@PathVariable("id") Long id) {

       userService.deleteUserById(id);
   }



}
