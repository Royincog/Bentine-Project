package com.balentine.core.handlebarservice;

import com.adobe.cq.social.handlebars.api.TemplateContextProvider;
import com.balentine.core.models.HandleBarV3ModelAdapter;
import com.balentine.core.models.impl.HandleBarV3Model;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jknack.handlebars.helper.StringHelpers;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;


/*
* This class will provide a context --  kind of a sling object we use in our HTL
* And also since its a map it's very flexible to work with .
* */

@Slf4j
@Component(service = TemplateContextProvider.class,enabled = true,immediate = true)
public class HandleBarsCustomContextProvider implements TemplateContextProvider {


    private ObjectMapper objectMapper;

    @Setter
    private HandleBarV3ModelAdapter handleBarV3Model;

    //Constructor for initializations ---

    @Activate
    public HandleBarsCustomContextProvider(){
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public List<String> getResourceTypes() {
        log.info("Inside the resource types of context");
        return Arrays.asList(new String[]{"balentine/components/handleTestV3"});
    }

    @Override
    public Map<String, Object> getContext(SlingHttpServletRequest slingHttpServletRequest, Map<String, Object> map) {



        log.info("Inside getcontext");
        log.info("The resource name from getContext " + slingHttpServletRequest.getResource().getName());

        Resource HandleBarV3Resource = slingHttpServletRequest.getResource();
        this.handleBarV3Model = HandleBarV3Resource.adaptTo(HandleBarV3ModelAdapter.class);

      //  log.info("The model text from v3 is " + handleBarV3Model.toString());
        HashMap<String, Object> mapofModel = objectMapper.convertValue(handleBarV3Model, HashMap.class);
        log.info("The info about the Map object is " + mapofModel.toString());
        return mapofModel;
    }



    @Override
    public String getContextKey() {
        log.info("Inside getcontext keys");
        return "fruitdata";
    }
}
