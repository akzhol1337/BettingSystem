package com.company;

import com.company.Model.DB.IPostgresAdapter;
import com.company.Model.DB.PostgresAdapter;
import com.company.Model.Repository.IPostgreRepository;
import com.company.Model.Repository.PostgreRepository;
import com.company.View.Application;

public class Main {

    public static void main(String[] args) throws Exception {

        IPostgresAdapter adapter = new PostgresAdapter();
        IPostgreRepository repo = new PostgreRepository(adapter);


        Application app = new Application(repo);
        app.start();
    }
}
