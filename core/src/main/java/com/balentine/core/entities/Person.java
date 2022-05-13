package com.balentine.core.entities;

import lombok.Getter;
import lombok.Setter;

public class Person {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String[] skills = {"Java", "JavaScript"};

    public Person(String name) {
        this.name = name;
    }
}
