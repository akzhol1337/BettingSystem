package com.company.View;

import com.company.Controller.IPostgreController;
import com.company.Controller.PostgreController;
import com.company.Model.Repository.IPostgreRepository;

import java.util.Scanner;

public class Application {
    private final IPostgreController controller;
    Scanner in = new Scanner(System.in);

    public Application(IPostgreRepository adapter) {
        controller = new PostgreController(adapter);
    }

    public void start() throws Exception {
        /*
        ArrayList<User> users = controller.getAllUsers();

        for(User user : users){
            System.out.println(user.getEmail());
        }

         */




        while(true){
            System.out.println("---------------------------------------");
            System.out.println("!!! Welcome to our Betting Service !!!");
            System.out.println("1: Login");
            System.out.println("2: Register");
            System.out.println("3: Admin");
            System.out.println("#: Quit");

            int choice = in.nextInt();

            try {
                switch (choice) {
                    case 1 -> login();
                    case 2 -> register();
                    default -> System.exit(0);
                }
            } catch(Exception e){
                System.out.println(e.getMessage());
                throw e;
            }


        }
    }

    void login() throws Exception {
        String email = in.next(), pass = in.next();
        System.out.println( controller.login(email, pass) ? "SUCCESS" : "FAIL" );

    }
    void register() throws Exception {
        String email = in.next(), pass = in.next();
        int age = in.nextInt();
        System.out.println( controller.register(email, pass, age) ? "SUCCESS" : "FAIL" );

    }
}
