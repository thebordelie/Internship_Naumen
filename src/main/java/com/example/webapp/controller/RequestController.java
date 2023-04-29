package com.example.webapp.controller;

import com.example.webapp.model.Request;
import com.example.webapp.model.ServerRequest;
import com.example.webapp.model.ServerResponse;
import com.example.webapp.model.User;
import com.example.webapp.service.UserHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/user-management")
public class RequestController {
    private final UserHandler userHandler;

    @Autowired
    public RequestController(UserHandler userHandler) {
        this.userHandler = userHandler;
    }

    @PostMapping("/user")
    public ResponseEntity<ServerResponse<String>> getAgeByName(@RequestBody ServerRequest request) {
        String userName = request.getUserName();
        if (userName == null) return new ResponseEntity<>(new ServerResponse<>("invalid name"), HttpStatus.BAD_REQUEST);
        int age = userHandler.getUserAgeByName(userName);
        if (age < 0) return new ResponseEntity<>(new ServerResponse<>("invalid name"), HttpStatus.BAD_REQUEST);

        String jsonObject = createJsonObject(userName, age);
        return new ResponseEntity<>(new ServerResponse<>(jsonObject), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<ServerResponse<String>> findMaxAge() {

        User userWithMaxAge = userHandler.getUserWithMaxAge();
        if (userWithMaxAge == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        String jsonObject = createJsonObject(userWithMaxAge.getName(), userWithMaxAge.getAge());
        return new ResponseEntity<>(new ServerResponse<>(jsonObject), HttpStatus.OK);
    }

    private String createJsonObject(String name, int age) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(new User(age, name));
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            return null;
        }

    }

    @PostMapping("/info")
    public ResponseEntity<ServerResponse<String>> getInfoAboutUser(@RequestBody ServerRequest serverRequest) {
        String userName = serverRequest.getUserName();
        if (userName == null) return new ResponseEntity<>(new ServerResponse<>("invalid name"), HttpStatus.BAD_REQUEST);
        List<Request> requestList = userHandler.getInfo(userName);
        if (requestList.size() == 0) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return new ResponseEntity<>(new ServerResponse<>(mapper.writeValueAsString(requestList)), HttpStatus.OK);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            return null;
        }


    }

}
