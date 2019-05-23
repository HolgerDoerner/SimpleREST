package com.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.bean.UserBean;

public class MockUserService implements UserService {
    private List<UserBean> userList;
    
    public MockUserService() {
        try {
            userList = Files.lines(Paths.get("MOCK_DATA.csv"), StandardCharsets.UTF_8).map(line -> line.split(","))
                    .map(array -> new UserBean(array[0], array[1], array[2])).collect(Collectors.toList());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public List<UserBean> getUsers() {
        return userList;
    }

    @Override
    public Optional<UserBean> getUserById(String id) {
        return userList.stream().filter(user -> user.getId().equals(id)).findAny();
    }

}