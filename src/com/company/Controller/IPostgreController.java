package com.company.Controller;

import com.company.Model.Entities.User;
import com.company.Model.Repository.IPostgreRepository;

import java.util.ArrayList;

public interface IPostgreController {
    ArrayList<User> getAllUsers() throws Exception;
    boolean login(String email, String password) throws Exception;
    boolean register(String email, String password, int age) throws Exception;
}
