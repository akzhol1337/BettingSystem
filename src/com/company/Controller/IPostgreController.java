package com.company.Controller;

import com.company.Model.Entities.User;
import com.company.Model.Repository.IPostgreRepository;

import java.util.ArrayList;

public interface IPostgreController {
    ArrayList<User> getAllUsers() throws Exception;
}
