package com.company.View;

import com.company.Controller.IPostgreController;
import com.company.Controller.PostgreController;
import com.company.Model.Entities.Admin;
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
    Admin admin = new Admin(0, "admin", "admin");

    public Application(IPostgreRepository adapter) throws Exception {
        controller = new PostgreController(adapter);
        for(User user : controller.getAllUsers()){
            admin.addUser(user);
        }
        for(Event event : controller.getAllEvents()){
            admin.addEvent(event);
        }
    }

    public void start() throws Exception {
        while(true){
            System.out.println("---------------------------------------");
            System.out.println("!!! Welcome to our Betting Service !!!");
            System.out.println("1: Login");
            System.out.println("2: Register");
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

            if(email.equals(admin.getLogin()) && pass.equals(admin.getPassword())){
                admin();
            } else {
                User successfully_login = controller.login(email, pass);
                System.out.println(successfully_login != null ? "SUCCESS" : "FAIL");

                if (successfully_login == null) return;

                events(successfully_login);
            }

        } catch(Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }
    void register() throws Exception {
        try {
            String name = in.next(), email = in.next(), pass = in.next();
            int age = in.nextInt();
            boolean success = controller.register(name, email, pass, age);
            if(success){
                admin.addUser(new User(name, email, email, pass, age, 0, 0, "hazik", 0, 0));
                System.out.println("SUCCESS");
            } else {
                System.out.println("FAIL");
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
            throw e;
        }

    }

    void admin() throws Exception {
        while(true) {
            System.out.println("#1: See all users");
            System.out.println("#2: See all events");
            System.out.println("#: Return");

            int choice = in.nextInt();

            if (choice == 1) {
                for (User user : admin.getUsers()) {
                    System.out.println(user.toString());
                }
                System.out.println("#1: Edit user");
                System.out.println("#: Continue");

                int choice1 = in.nextInt();

                if(choice1 == 1){
                    System.out.println("Input id");
                    String id = in.next();

                    User user = admin.findUserById(id);

                    System.out.println("#1: Edit balance");
                    System.out.println("#2: Edit rank");

                    int choice2 = in.nextInt();

                    if(choice2 == 1){
                        System.out.println("Input new balance");
                        int balance = in.nextInt();
                        user.setBalance(balance);
                        controller.changeBalance(id, balance);
                    } else if(choice2 == 2){
                        System.out.println("Input new rank");
                        String rank = in.next();
                        user.setRanking(rank);
                        controller.changeRank(id, rank);
                    }

                }

            } else if (choice == 2) {
                for (Event event : admin.getEventsID()) {
                    System.out.println(event.toString());
                }
                System.out.println("#1: Edit event");
                System.out.println("#2: Add event");
                System.out.println("#: Continue");

                int choice1 = in.nextInt();

                if(choice1 == 1){
                    System.out.println("Input id");
                    int id = in.nextInt();

                    Event event = admin.findEventById(id);

                    System.out.println("#1: Delete event");
                    System.out.println("#2: Edit event info");

                    int choice2 = in.nextInt();

                    if(choice2 == 1){
                        admin.removeEvent(event);
                        controller.removeEvent(id);
                    } else if(choice2 == 2){
                        System.out.println("1: Edit coefficients");
                        System.out.println("2: Edit team names");
                        System.out.println("3: Edit date");
                        System.out.println("4: Edit league");
                        System.out.println("5: Edit category");
                        System.out.println("6: Edit location");

                        int choice3 = in.nextInt();

                        short pick;

                        if(choice3 == 1){
                            System.out.println("1: Win 1");
                            System.out.println("2: Win 2");
                            System.out.println("3: Draw");

                            pick = in.nextShort();

                            System.out.println("Enter new coefficient");
                            double coefficient = in.nextDouble();
                            controller.editEventCoefficient(id, coefficient, pick);

                            if(pick == 1){
                                event.setCoeffWin1(coefficient);
                            } else if(pick == 2){
                                event.setCoeffWin2(coefficient);
                            } else if(pick == 3){
                                event.setCoeffDraw(coefficient);
                            }

                        } else if(choice3 == 2){
                            System.out.println("1: Team 1");
                            System.out.println("2: Team 2");

                            int choice4 = in.nextInt();


                            String teamName;
                            if(choice4 == 1){
                                teamName = in.next();
                                event.setFirstPlayer(teamName);
                                controller.editEventInfo(id, teamName, (short)1);
                            } else if(choice4 == 2){
                                teamName = in.next();
                                event.setSecondPlayer(teamName);
                                controller.editEventInfo(id, teamName, (short)2);
                            }
                        } else if(choice3 == 3){
                            System.out.println("Enter a year");
                            int year = in.nextInt();
                            System.out.println("Enter a month");
                            int month = in.nextInt();
                            System.out.println("Enter a day");
                            int day = in.nextInt();
                            String date = year + "-" + month + "-" + day;
                            //event.setDateOfMatch(date);
                            controller.editEventInfo(id, date, (short)3);
                        } else if(choice3 == 4){
                            System.out.println("Enter a league");
                            String league = in.next();

                            event.setLeague(league);
                            controller.editEventInfo(id, league, (short)4);
                        } else if(choice3 == 5){
                            System.out.println("Enter a category");
                            String category = in.next();
                            event.setCategory(category);
                            controller.editEventInfo(id, category, (short)5);
                        } else if(choice3 == 6){
                            System.out.println("Enter a location");
                            String location = in.next();
                            event.setLocation(location);
                            controller.editEventInfo(id, location, (short)6);
                        }
                    }


                } else if(choice1 == 2){

                }

            } else {
                return;
            }
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
