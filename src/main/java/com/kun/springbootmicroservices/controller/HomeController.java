package com.kun.springbootmicroservices.controller;

import com.kun.springbootmicroservices.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "Hello World!";
    }

    // @RequestMapping(value = "/user", method = RequestMethod.GET)
    @GetMapping("/user")
    public User user(){
        User user = new User();
        user.setId(1);
        user.setName("Kun");
        user.setEmail("kun@gmail.com");

        return user;
    }
    @GetMapping("/{id1}/{id2}")
    public String pathVariable(@PathVariable int id1, @PathVariable("id2") String name){
        return "The id is: " + id1 + ", the name is: " + name;
    }

    @GetMapping("/requestParam")
    public String requestParams(@RequestParam String name,
                                @RequestParam(name = "email", required = false,defaultValue = "")
                                String emailId){
        return name + "'s email is: " + emailId;
    }
}
