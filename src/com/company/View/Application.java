package com.company.View;

import com.company.Controller.IPostgreController;
import com.company.Controller.PostgreController;
import com.company.Model.Entities.Bet;
import com.company.Model.Entities.Event;
import com.company.Model.Entities.User;
import com.company.Model.Repository.IPostgreRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Application {
    private final IPostgreController controller;
    Scanner in = new Scanner(System.in);

    public Application(IPostgreRepository adapter) {
        controller = new PostgreController(adapter);
    }

    public void start() throws Exception {
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
        try {
            String email = in.next(), pass = in.next();
            User successfully_login = controller.login(email, pass);
            System.out.println( successfully_login != null ? "SUCCESS" : "FAIL" );

            if(successfully_login == null) return;
            events(successfully_login);

        } catch(Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }
    void register() throws Exception {
        try {
            String name = in.next(), email = in.next(), pass = in.next();
            int age = in.nextInt();
            System.out.println(controller.register(name, email, pass, age) ? "SUCCESS" : "FAIL");
        } catch(Exception e){
            System.out.println(e.getMessage());
            throw e;
        }

    }

    void events(User user) throws Exception {
        while(true) {
            try {
                System.out.println("-----------------------");
                System.out.println("1: My profile");
                System.out.println("2: Show all");
                System.out.println("3: Show by Category");
                System.out.println("#: Back");

                int choice = in.nextInt();

                if(choice == 1){
                    myProfile(user);
                } else if(choice == 2){
                    showAllEvents();
                    System.out.println("1: Make a bet");
                    System.out.println("#: Return");

                    int choice1 = in.nextInt();

                    if(choice1 == 1){
                         makeBet(user);
                    }

                } else if(choice == 3){
                    String category = in.next();
                    showByCategory(category);
                    System.out.println("1: Make a Bet");
                    System.out.println("2: Filter by league");
                    System.out.println("#: Return");
                    int choice1 = in.nextInt();

                    switch (choice1) {
                        case 1 -> makeBet(user);
                        case 2 -> {
                            showByLeague(category);
                            System.out.println("1: Make a Bet");
                            System.out.println("# Return");

                            int choice2 = in.nextInt();

                            if(choice2 == 1) makeBet(user);
                        }
                    }
                } else {
                    break;
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

    void makeBet(User user) throws Exception {
        System.out.println("1: Ordinary");
        System.out.println("2: Express");
        System.out.println("#: Return");

        int choice;
        choice = in.nextInt();

        if(choice == 1){

            System.out.println("Enter amount of money you bet");
            int amount = in.nextInt();
            int eventID = in.nextInt();
            String pickStr = in.next();

            short pick;
            if(pickStr.equals("W1")){
                pick = 0;
            } else if(pickStr.equals("W2")){
                pick = 1;
            } else {
                pick = 2;
            }

            controller.makeOrdinaryBet(amount, user, eventID, pick);
        } else if(choice == 2){
            System.out.println("Enter amount of money you bet");
            int amount = in.nextInt();
            System.out.println("Enter count of events");
            int count = in.nextInt();

            ArrayList<Integer> eventsID = new ArrayList<Integer>();
            Map< Integer, Short > mapPick = new HashMap<Integer, Short>();

            for(int i = 0; i < count; i++){
                int id = in.nextInt();
                String pick = in.next();

                if(pick.equals("W1")){
                    mapPick.put(id, (short) 0);
                } else if(pick.equals("W2")){
                    mapPick.put(id, (short) 1);
                } else {
                    mapPick.put(id, (short) 2);
                }

                eventsID.add(id);
            }
            controller.makeExpressBet(amount, user, eventsID, mapPick);
        }
    }

    void myProfile(User user) throws Exception {
        while(true) {
            System.out.println("--------------------------------");
            System.out.println("Hello, " + user.getName());
            System.out.println("Rank: " + user.getRanking());
            System.out.println("Balance: " + user.getBalance());
            System.out.println("Profit: " + user.getProfit());
            System.out.println();
            System.out.println();
            System.out.println("Total Bets: " + user.getTotalBets() + " Won: " + user.getBetsWon() + " Lose: " + (user.getTotalBets() - user.getBetsWon()));

            System.out.println("1: Change password");
            System.out.println("2: See leaderboard");
            System.out.println("3: My bets history");
            System.out.println("# Return");

            int choice = in.nextInt();
            if(choice == 1){
                changePassword(user);
            } else if(choice == 2){
                leaderboard();
            } else if(choice == 3){
                betHistory(user);
            } else {
                break;
            }
        }
    }

    void changePassword(User user) throws Exception {
        String newPassword = in.next();
        controller.changePassword(user.getID(), newPassword);
    }

    void leaderboard() throws Exception {
        System.out.println("-------LEADERBOARD--------");
        int top = 0;
        for(User user : controller.leaderboard()){
            System.out.print("#" + top + ": ");
            System.out.println(user.toString());
            top++;
        }

        System.out.println("--------------------------");
    }

    void betHistory(User user) throws Exception {
        System.out.println("-------BET HISTORY--------");
        int cur = 0;

        ArrayList<Bet> bets = controller.getBetHistory(user.getID());

        boolean expressStatus = true;
        double expressCoeff = 1.0;
        int expressAmount = 0;
        int expressCount = 0;

        for(int i = 0; i < bets.size(); i++){

            Bet currentBet = bets.get(i);

            if(i != 0 && currentBet.getID() == bets.get(i-1).getID()){
                System.out.println(bets.get(i).toStringExpress());
                if(!currentBet.isStatus()) expressStatus = false;
                expressCoeff *= currentBet.getCoeff();
                expressCount++;
                expressAmount += currentBet.getAmount();

                if(i + 1 == bets.size() || bets.get(i+1).getID() != currentBet.getID()){
                    System.out.println("EXPRESS " + expressCount + " matches : " + expressCoeff + ", " + (currentBet.isStatus() ? "WON " : "LOSE ") + (expressAmount * expressCoeff));
                    System.out.println("------------------------------------");
                    expressStatus = true;
                    expressCoeff = 1.0;
                    expressAmount = 0;
                    expressCount = 0;
                }
            } else {
                System.out.println(bets.get(i).toStringOrdinary());
                System.out.println("-");
            }
        }
    }

}
