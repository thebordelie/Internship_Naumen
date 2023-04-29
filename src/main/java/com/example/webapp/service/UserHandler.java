package com.example.webapp.service;

import com.example.webapp.model.Request;
import com.example.webapp.model.User;

import java.util.List;


public interface UserHandler {
    int getUserAgeByName(String name);

    List<Request> getInfo(String name);

    User getUserWithMaxAge();
}
