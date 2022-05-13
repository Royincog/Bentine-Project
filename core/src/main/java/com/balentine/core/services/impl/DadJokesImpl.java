package com.balentine.core.services.impl;

import com.balentine.core.entities.DadJoke;
import com.balentine.core.services.DadJokes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.common.collect.Maps;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import java.util.Map;

@Slf4j
@Component(service = DadJokes.class,immediate = true,enabled = true)
public class DadJokesImpl implements DadJokes{

    private DadJoke dadJoke;
    private ObjectMapper objectMapper;

    public DadJokesImpl(){
        this.dadJoke = new DadJoke();
        this.objectMapper = new ObjectMapper();
    }

    private String getResponse(){
        HttpResponse<String> response = Unirest.get("https://dad-jokes.p.rapidapi.com/random/joke")
                .header("x-rapidapi-host", "dad-jokes.p.rapidapi.com")
                .header("x-rapidapi-key", "ed117fd37dmshd6eddf4627228d6p1d5fd5jsnca0f4f5f355a")
                .asString();
        return  response.getBody();
    }
    private void getPunchLineAndJoke() throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(getResponse());
        ArrayNode arrayNode = (ArrayNode) jsonNode.get("body");
        JsonNode JokeBody = arrayNode.get(0);
        dadJoke.setSetup(JokeBody.get("setup").asText());
        dadJoke.setPunchLine(JokeBody.get("punchline").asText());
    }

    @Activate
    private void init() throws JsonProcessingException {
        getPunchLineAndJoke();
        log.info("The Map for Jokes " + dadJoke);
    }


    @Override
    public String dadJokes() {
        return null;
    }
}
