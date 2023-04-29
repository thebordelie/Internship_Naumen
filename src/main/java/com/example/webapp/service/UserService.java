package com.example.webapp.service;

import com.example.webapp.model.Request;
import com.example.webapp.model.User;
import com.example.webapp.reader.FileHandler;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kong.unirest.HttpResponse;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserHandler {
    private final FileHandler fileHandler;
    private final ArrayList<User> users;
    private final Generator<String, Integer> ageGenerator;
    private final ArrayList<Request> userRequests;
    @Autowired
    public UserService(FileHandler readerFromFile, Generator<String, Integer> ageGenerator) {
        this.fileHandler = readerFromFile;
        this.users = fileHandler.readFromFile();
        this.ageGenerator = ageGenerator;
        this.userRequests = new ArrayList<>();
    }

    @Override
    public int getUserAgeByName(String name) {
        List<User> users = this.users.stream().filter(user -> user.getName().equals(name)).collect(Collectors.toList());
        Date date = new Date();
        userRequests.add(new Request(name, Calendar.getInstance().getTime().toString()));
        if (users.size() == 0) {
            int age = ageGenerator.generateObject(name);
            if (age > 0) this.users.add(new User(age, name));
            return age;
        }
        return users.get(0).getAge();
    }


    @Override
    public List<Request> getInfo(String name) {
        return userRequests.stream().filter(user-> user.getName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public User getUserWithMaxAge() {
        Optional<User> userWithMaxAge = users.stream().max(Comparator.comparingInt(User::getAge));
        return userWithMaxAge.orElse(null);

    }
}
