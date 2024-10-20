package com.registration_12.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiClass {

    @Bean
    public ModelMapper getmodelMapper(){
        return new ModelMapper();

    }
}
