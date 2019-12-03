package com.example.demo;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {

    @GetMapping("/index")
    public Object index(Authentication authentication){
        if(authentication == null){
            return "{\"msg\": + \"" + authentication.toString() + "\"}";
        }
        return authentication.toString();
    }
}
