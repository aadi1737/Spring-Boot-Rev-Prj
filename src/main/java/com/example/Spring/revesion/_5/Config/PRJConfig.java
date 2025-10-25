package com.example.Spring.revesion._5.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PRJConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
