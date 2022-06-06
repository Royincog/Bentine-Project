package com.balentine.core.models.impl;

import com.balentine.core.models.ModelToJsonAdapter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.annotation.PostConstruct;
import javax.inject.Named;

@Slf4j
@Data
@Model(adaptables = {Resource.class},adapters = ModelToJsonAdapter.class)
public class ModelToJsonImpl implements ModelToJsonAdapter{

    private Resource currentResource;
    private ObjectWriter objectWriter;




    //inject with constructor
    public ModelToJsonImpl(@SlingObject @Named ("currentResource") final Resource currentResource){
        this.currentResource = currentResource;
        this.objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

    }

    private String getJsonString() {
        String jsonString = "";
        try {
            jsonString = objectWriter.writeValueAsString(this);
            log.info("Gson to json String " + objectWriter.writeValueAsString(this));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return jsonString;
    }

    @PostConstruct
    private void init(){
        log.info("Current Resource is from PT" + currentResource);
        log.info("The GSON Resource string " + getJsonString());

    }


    @Override
    public String getJsonObject() {
        return getJsonString();
    }
}
