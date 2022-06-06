package com.balentine.core.nebamodels;

import com.balentine.core.services.HybridSpringService;
import com.balentine.core.springservice.GreetingSling;
import io.neba.api.annotations.ResourceModel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Component
@Scope("singleton") //must do a "prototype" for ideal implementations unless you want same clone everywhere
@Lazy(value = true) //optional
@ResourceModel("balentine/components/nebacomponent")
public class SpringNebaModel {

    @Getter
   // @Qualifier("greetingSlingSpanishImpl")  //the primary Qualifier is English unless you define !! <Spring IQ :) >
    @Autowired
    private GreetingSling greetingSling; //Spring Dependency Injectionnnnnnn !!!!

    @Getter
    @Autowired
    private HybridSpringService hybridSpringService;


    @Getter
    @Autowired
    private List<String> nameList;

    @Getter
    private String something;

    @PostConstruct
    private void init(){
        log.info("The info from Greeting sling is " + greetingSling.sayHello());
        if(nameList.isEmpty() || nameList == null){
            log.info("Namelist is empty or null");
        }
        else {
            log.info("List is " + nameList.toString());
        }
    }

}
