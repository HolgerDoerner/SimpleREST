package com.example;

import java.util.List;
import java.util.Optional;

import com.example.bean.UserBean;

public interface UserService {
    public List<UserBean> getUsers();
    public Optional<UserBean> getUserById(String id);
}