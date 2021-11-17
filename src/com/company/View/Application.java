package com.company.View;

import com.company.Controller.IPostgreController;
import com.company.Controller.PostgreController;
import com.company.Model.DB.IPostgresAdapter;
import com.company.Model.Entities.User;
import com.company.Model.Repository.IPostgreRepository;

import java.util.ArrayList;

public class Application {
    private final IPostgreController controller;

    public Application(IPostgreRepository adapter) {
        controller = new PostgreController(adapter);
    }

    public void start() throws Exception {
        ArrayList<User> users = controller.getAllUsers();

        for(User user : users){
            System.out.println(user.getEmail());
        }


    }
}
