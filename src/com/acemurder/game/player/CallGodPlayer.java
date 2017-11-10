package com.acemurder.game.player;

import com.acemurder.game.Manager;
import com.acemurder.game.Player;
import com.acemurder.game.Poker;

import java.util.List;

/**
 * Created by : AceMurder
 * Created on : 2017/11/10
 * Created for : Games.
 * Enjoy it !!!!
 */
public class CallGodPlayer implements Player {
    @Override
    public String getName() {
        return "跟注帝";
    }

    @Override
    public String getStuNum() {
        return "2015211876";
    }

    @Override
    public void onGameStart(Manager manager, int totalPlayer) {

    }

    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
        return moneyYouNeedToPayLeast;
    }

    @Override
    public void onResult(int time, boolean isWin, List<Poker> pokers) {

    }
}
