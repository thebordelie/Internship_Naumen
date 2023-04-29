package com.example.webapp.service;

public interface Generator <T, V>{
    V generateObject(T value);
}
