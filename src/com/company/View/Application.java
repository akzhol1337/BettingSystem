package com.company.View;

import com.company.Controller.IPostgreController;
import com.company.Controller.PostgreController;
import com.company.Model.Entities.Event;
import com.company.Model.Repository.IPostgreRepository;

import java.util.ArrayList;
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
        boolean successfully_login = controller.login(email, pass);
        System.out.println( successfully_login ? "SUCCESS" : "FAIL" );

        if(!successfully_login) return;

        while(true) {
            try {
                events();
            } catch(Exception e){
                System.out.println(e.getMessage());
                throw e;
            }
        }

    }
    void register() throws Exception {
        String email = in.next(), pass = in.next();
        int age = in.nextInt();
        System.out.println( controller.register(email, pass, age) ? "SUCCESS" : "FAIL" );

    }

    void events() throws Exception {
        System.out.println("-----------------------");
        System.out.println("1: Show all");
        System.out.println("2: Show by Category");
        System.out.println("#: Back");

        int choice = in.nextInt();

        switch (choice){
            case 1:
                showAllEvents();
                break;
            case 2:
                showByCategory();
                break;
            default:

        }

    }

    void showAllEvents() throws Exception {
        for(Event event : controller.getAllEvents()){
            System.out.println(event.toString());
        }
    }

    void showByCategory() throws Exception {
        String category = in.next();
        for(Event event : controller.getEventsByCategory(category)){
            System.out.println(event.toString());
        }
    }
}
