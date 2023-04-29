package com.example.webapp.reader;

import com.example.webapp.model.User;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

@Getter
@Component
public class FileHandler {
    @Value("${file.name}")
    private String fileName;

    private final InputDataReader<ArrayList<User>> readerFromFile;

    @Autowired
    public FileHandler(InputDataReader<ArrayList<User>> readerFromFile) {
        this.readerFromFile = readerFromFile;
    }

    public ArrayList<User> readFromFile() {
        try {
            return readerFromFile.readDataFromStream(new FileReader(fileName));
        } catch (IOException e) {
            return null;
        }
    }
}
