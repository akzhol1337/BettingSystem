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
        while(true) {
            try {
                String email = in.next(), pass = in.next();
                boolean successfully_login = controller.login(email, pass);
                System.out.println( successfully_login ? "SUCCESS" : "FAIL" );

                if(!successfully_login) return;
                events();
            } catch(Exception e){
                System.out.println(e.getMessage());
                throw e;
            }
        }

    }
    void register() throws Exception {
        try {
            String email = in.next(), pass = in.next();
            int age = in.nextInt();
            System.out.println(controller.register(email, pass, age) ? "SUCCESS" : "FAIL");
        } catch(Exception e){
            System.out.println(e.getMessage());
            throw e;
        }

    }

    void events() throws Exception {
        while(true) {
            try {
                System.out.println("-----------------------");
                System.out.println("1: Show all");
                System.out.println("2: Show by Category");
                System.out.println("#: Back");

                int choice = in.nextInt();

                switch (choice) {
                    case 1 -> showAllEvents();
                    case 2 -> {
                        String category = in.next();
                        showByCategory(category);
                        System.out.println("1: Make a Bet");
                        System.out.println("2: Filter by league");
                        System.out.println("#: Return");
                        int choice1 = in.nextInt();

                        switch (choice1) {
                            case 1 -> makeBet();
                            case 2 -> showByLeague(category);
                        }
                    }
                }
            } catch(Exception e){
                System.out.println(e.getMessage());
                throw e;
            }
        }

    }

    void showAllEvents() throws Exception {
        try {
            for (Event event : controller.getAllEvents()) {
                System.out.println(event.toString());
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    void showByCategory(String category) throws Exception {
        try {
            for (Event event : controller.getEventsByCategory(category)) {
                System.out.println(event.toString());
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

    void showByLeague(String category) throws Exception{
        try {
            in.nextLine();
            String league = in.nextLine();
            for (Event event : controller.getEventsByLeague(category, league)) {
                System.out.println(event.toString());
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

    void makeBet(){

    }
}
