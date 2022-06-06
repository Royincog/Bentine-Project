package com.balentine.core.springservice;
import org.springframework.stereotype.Service;

@Service
public class GreetingSlingSpanishImpl implements GreetingSling{
    @Override
    public String sayHello() {
        return "Hola Slingo From Springoww";
    }
}
