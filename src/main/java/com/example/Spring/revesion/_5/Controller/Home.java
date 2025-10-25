package com.example.Spring.revesion._5.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {


    @Value("${Boss_Name}")
    String name;
    @GetMapping("/health-check")
    public String healthCheck(){
        return "Hello Aditya Ji!\nBy "+name;
    }

}
