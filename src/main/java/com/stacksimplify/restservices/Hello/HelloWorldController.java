package com.stacksimplify.restservices.Hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Controller
@RestController
public class HelloWorldController {

   // Simple Method
   // URI - /helloworld
   // GET
  // @RequestMapping(method = RequestMethod.GET, path = "/helloworld")
   @GetMapping("/helloworld1")
   public String helloWorld() {
      return "Hello World 1";
   }

   @GetMapping("helloworld-bean")
   public UserDetails helloWorldBean() {

      return new UserDetails("Rob","Glasener","Danville");
   }


}
