package com.example.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServerResponse<T> {
    private T response;
}
