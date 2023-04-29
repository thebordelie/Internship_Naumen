package com.example.webapp.reader;

import com.example.webapp.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

@Component
@Data
@NoArgsConstructor
public class InputDataReaderImpl implements InputDataReader<ArrayList<User>> {
    @Value("${separator}")
    private String separator;

    @Override
    public ArrayList<User> readDataFromStream(InputStreamReader reader) throws IOException {
        ArrayList<User> users = new ArrayList<>();
        BufferedReader readerFromStream = new BufferedReader(reader);
        String user = readerFromStream.readLine();
        while (user != null) {
            int separatorIndex = user.indexOf(this.separator);
            if (separatorIndex != -1) {
                String name = user.substring(0, separatorIndex);
                String age = user.substring(separatorIndex + 1);
                try {
                    users.add(new User(Integer.parseInt(age), name));
                } catch (NumberFormatException e) {
                }
            }
            user = readerFromStream.readLine();

        }
        return users;
    }
}
