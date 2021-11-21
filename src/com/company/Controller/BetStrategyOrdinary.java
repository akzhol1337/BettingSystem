package com.company.Controller;

import com.company.Model.Entities.User;
import com.company.Model.Repository.IPostgreRepository;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class BetStrategyOrdinary implements BetStrategy{

    private final IPostgreRepository repo;

    public BetStrategyOrdinary(IPostgreRepository repo) {
        this.repo = repo;
    }

    @Override
    public void makeBet(int amount, User user, ArrayList<Integer> eventsID, Map<Integer, Short> mapPick) throws Exception {
        if(amount > user.getBalance()){
            System.out.println("Not enough money on balance!");
            return;
        }

        Random r = new Random();
        boolean outcome = r.nextBoolean();

        int eventID = eventsID.get(0);
        short pick = mapPick.get(eventsID.get(0));

        double coeff = repo.getCoefficientOrindary(eventID, pick);
        user.setTotalBets(user.getTotalBets()+1);

        System.out.println(coeff);
        repo.makeOrdinaryBet(amount, user.getID(), eventID, outcome, pick);

        if(outcome){
            int profit = (int)(amount * (coeff-1));
            user.setBalance(user.getBalance() + profit);
            user.setBetsWon(user.getBetsWon()+1);

            repo.changeBalance(profit, true, user.getID());
            repo.changeBetStatistics(user.getID(), profit, true);
        } else {
            user.setBalance(user.getBalance() - amount);

            repo.changeBalance(amount, false, user.getID());
            repo.changeBetStatistics(user.getID(), 0, false);
        }
    }
}
