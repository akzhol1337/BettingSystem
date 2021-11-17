package com.company.Controller;

import com.company.Model.Entities.User;
import com.company.Model.Repository.IPostgreRepository;

import java.util.ArrayList;

public class PostgreController implements IPostgreController{
    private final IPostgreRepository repo;

    public PostgreController(IPostgreRepository repo) {
        this.repo = repo;
    }

    public ArrayList<User> getAllUsers() throws Exception {
        return repo.getAllUsers();
    }
}
