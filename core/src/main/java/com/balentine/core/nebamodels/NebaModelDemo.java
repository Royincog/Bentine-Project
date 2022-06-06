package com.balentine.core.nebamodels;
import io.neba.api.annotations.ResourceModel;
import io.neba.api.annotations.This;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.sling.api.resource.Resource;

import javax.annotation.PostConstruct;

@Slf4j
@ResourceModel("balentine/components/nebacomponent")
public class NebaModelDemo {

    @Getter
    private String something;

    @Getter
    @This
    private Resource nebaModelResource;

    @PostConstruct
    private void init(){
        log.info("Something is " + something);

    }
}
