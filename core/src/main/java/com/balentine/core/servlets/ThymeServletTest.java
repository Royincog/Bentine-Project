package com.balentine.core.servlets;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.jetbrains.annotations.NotNull;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import org.thymeleaf.context.WebContext;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


@Slf4j
@Component
        (
                service = Servlet.class
        )
@SlingServletResourceTypes(
        resourceTypes = "balentine/components/thymeleafcomp",
        methods= HttpConstants.METHOD_GET,
        extensions = "html"
)
public class ThymeServletTest extends SlingSafeMethodsServlet {

    @Reference(policy= ReferencePolicy.DYNAMIC, policyOption = ReferencePolicyOption.GREEDY)
    private volatile ITemplateEngine iTemplateEngine;


    @Override
    protected void doGet(@NotNull SlingHttpServletRequest request, @NotNull SlingHttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Map<String,Object> contextMap = new HashMap<>();
        contextMap.put("name","abcd");
        Map<String,Object> dogMap = Collections.singletonMap("dog",new Dog("Pug","Vodafone"));

        Resource thymeResource = request.getResource();
        String template = thymeResource.getResourceType() + "/" + thymeResource.getName() + ".html"; //"balentine/components/thymeleafcomp/thymeleafcomp.html";
        log.info("The template string is " + template + " Dynamic template is " + request.getResource().getResourceType() + " Resource name " + request.getResource().getName());


        final IContext context = new Context(Locale.ENGLISH,dogMap);



        WebContext webContext = new WebContext(request,response,getServletContext());




        log.info("About the i templateEngine " + iTemplateEngine.toString());
        log.info("Context variable name " + context.getVariableNames().toString());
       // log.info("The template message " + message);


        iTemplateEngine.process(template,context,response.getWriter());

        log.info("The template engine context info " + iTemplateEngine.getConfiguration());
    }
}


class Dog {
    @Getter
    @Setter
    private String breed;
    @Getter
    @Setter
    private String name;

    public Dog(String breed, String name) {
        this.breed = breed;
        this.name = name;
    }
    public Integer getDogNameLength(String name){
        return name.length();
    }
}