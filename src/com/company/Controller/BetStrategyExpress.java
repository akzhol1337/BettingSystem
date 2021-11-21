package com.company.Controller;

import com.company.Model.Entities.User;
import com.company.Model.Repository.IPostgreRepository;

import java.util.ArrayList;
import java.util.Map;

public class BetStrategyExpress implements BetStrategy{

    private final IPostgreRepository repo;

    public BetStrategyExpress(IPostgreRepository repo) {
        this.repo = repo;
    }

    @Override
    public void makeBet(int amount, User user, ArrayList<Integer> eventsID, Map<Integer, Short> mapPick) throws Exception {
        if(amount > user.getBalance()){
            System.out.println("Not enough money on balance!");
            return;
        }
        boolean outcome = repo.makeExpressBet(amount, user.getID(), eventsID, mapPick);
        user.setTotalBets(user.getTotalBets()+1);
        if(outcome){
            double coeff = repo.getCoefficientExpress(eventsID, mapPick);
            int profit = (int)(amount * (coeff-1));
            user.setBalance(user.getBalance() + profit);
            repo.changeBalance(profit, true, user.getID());
            user.setBetsWon(user.getBetsWon()+1);
            user.setProfit(user.getProfit() + profit);

            repo.changeBetStatistics(user.getID(), profit, true);
        } else {
            user.setBalance(user.getBalance() - amount);
            repo.changeBalance(amount, false, user.getID());


            repo.changeBetStatistics(user.getID(), 0, false);
        }
    }
}
