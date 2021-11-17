package com.company.Model.Repository;

import com.company.Model.Entities.User;

import java.util.ArrayList;

public interface IPostgreRepository {
    ArrayList<User> getAllUsers() throws Exception;


}
