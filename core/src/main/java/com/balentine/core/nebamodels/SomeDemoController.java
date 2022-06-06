package com.balentine.core.nebamodels;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jcr.RepositoryException;


@Slf4j
@Controller
public class SomeDemoController {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @GetMapping(value="echoTitle",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String echo(ResourceResolver resourceResolver) throws JsonProcessingException, RepositoryException {
        Resource nebaSpringResource = resourceResolver.getResource("balentine/components/nebacomponent");

        return "hello";
    }
}
