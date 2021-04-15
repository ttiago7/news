package com.news.news.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Esta clase tiene el Bean q se hara cargo de instanaciar el personToPersonDTO
@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        final ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);//true, en caso de q haya un converter q falle q lo ignore

        return modelMapper;
    }
}
