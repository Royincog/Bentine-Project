package com.balentine.core.services.impl;

import com.balentine.core.services.HybridSpringService;
import lombok.extern.slf4j.Slf4j;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Component(service = HybridSpringService.class,enabled = true,immediate = true)
public class HybridSpringServiceImpl implements HybridSpringService{

    @Activate
    private void init(){
        log.info("Message from component " + getMeSomething());
    }
    public String getMeSomething() {
        return "Something both Spring and OSGI";
    }

}
