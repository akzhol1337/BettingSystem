package com.company.Controller;

import com.company.Model.Entities.User;

import java.util.ArrayList;
import java.util.Map;

public class BetContext {
    private BetStrategy strategy;

    public BetContext() {
    }

    public void setStrategy(BetStrategy strategy) {
        this.strategy = strategy;
    }
    public void executeStrategy(int amount, User user, ArrayList<Integer> eventsID, Map<Integer, Short> mapPick) throws Exception {
        strategy.makeBet(amount, user,  eventsID, mapPick);
    }
}
