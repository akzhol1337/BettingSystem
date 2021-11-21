package com.company.Controller;

import com.company.Model.Entities.User;

import java.util.ArrayList;
import java.util.Map;

public interface BetStrategy {


    void makeBet(int amount, User user, ArrayList<Integer> eventsID, Map< Integer, Short > mapPick) throws Exception;
}
