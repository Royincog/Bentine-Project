package com.balentine.core.springservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Primary
@Service
public class GreetingSlingEnglishImpl implements GreetingSling{
    @Override
    public String sayHello() {
        return "Hello Sling From Spring";
    }

    @Bean
    public List<String> nameList() {
        return Arrays.asList("John", "Adam", "Harry");
    }
}
