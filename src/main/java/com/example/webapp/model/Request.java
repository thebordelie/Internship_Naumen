package com.example.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class Request {
    private String name;
    private String timeOfRequest;
}
