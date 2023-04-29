package com.example.webapp.service;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AgeGenerator implements Generator<String, Integer> {
    @Value("${url}")
    private String url;

    @Override
    public Integer generateObject(String name) {
        HttpResponse<JsonNode> response = Unirest.get(url + "?name=" + name)
                .asJson();
        return response.getBody().getObject().get("age") == null ? -1 : (int) response.getBody().getObject().get("age");
    }
}
