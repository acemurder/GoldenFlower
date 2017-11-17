package com.acemurder.game.player;

import com.acemurder.game.Manager;
import com.acemurder.game.Player;
import com.acemurder.game.Poker;

import java.util.List;

public class ShiganPlayer implements Player {
    @Override
    public String getName() {
        return "石干";
    }

    @Override
    public String getStuNum() {
        return "2017211707";
    }

    @Override
    public void onGameStart(Manager manager, int totalPlayer) {

    }

    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
        if((pokers.get(0).getSuit() == pokers.get(1).getSuit() &&
                pokers.get(1).getSuit() == pokers.get(2).getSuit())||(pokers.get(0).getPoint() == pokers.get(1).getPoint() &&
                pokers.get(1).getPoint() == pokers.get(2).getPoint()))
            return moneyYouNeedToPayLeast*3;
        else return 0;
    }

    @Override
    public void onResult(int time, boolean isWin, List<Poker> pokers) {

    }
}
