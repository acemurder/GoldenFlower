package com.acemurder.game.player;

import com.acemurder.game.Manager;
import com.acemurder.game.Player;
import com.acemurder.game.Poker;

import java.util.List;

public class JayPlayer implements Player {
    @Override
    public String getName() {
        return "李吉";
    }

    @Override
    public String getStuNum() {
        return "2016210409";
    }

    @Override
    public void onGameStart(Manager manager, int totalPlayer) {

    }

    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
        return 0;
    }

    @Override
    public void onResult(int time, boolean isWin, List<Poker> pokers) {

    }
}
