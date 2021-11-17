package com.company.Model.Repository;

import com.company.Model.Entities.User;

import java.util.ArrayList;

public interface IPostgreRepository {
    ArrayList<User> getAllUsers() throws Exception;
    boolean login(String email, String password) throws Exception;
    boolean register(String email, String password, int age) throws Exception;


}
