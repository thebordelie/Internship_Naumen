package com.example.webapp.reader;


import java.io.IOException;
import java.io.InputStreamReader;

public interface InputDataReader<T> {
    T readDataFromStream(InputStreamReader reader) throws IOException;
}
