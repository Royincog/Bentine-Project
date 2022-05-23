package com.balentine.core.models;


import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Arrays;

@Slf4j
@ToString
@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,resourceType = "balentine/components/thymeleafcomp")
public class DogModel {

    @Getter
    @Inject
    @Default(values = "someDog")
    private String name;

    @Getter
    @Inject
    @Default(values = "someBreed")
    private String breed;

    @Getter
    @Inject
    @Default(values = {"no dog"})
    private String[] favourites;

    @PostConstruct
    private void init()
    {
        log.info("The values for Dog Name " + name);
        log.info("The values for Dog Breed " + breed);
        log.info("The values for Dog favourites " + Arrays.asList(favourites).toString());
    }
    public Integer getDogNameLength(String name){
        return name.length();
    }
}
