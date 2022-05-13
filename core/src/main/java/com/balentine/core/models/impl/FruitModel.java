package com.balentine.core.models.impl;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import javax.inject.Named;

@ToString
@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FruitModel {


    @Inject
    @Named("fruitName")
    @Getter
    @Setter
    private String fruitName;

}
