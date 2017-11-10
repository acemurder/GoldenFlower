package com.acemurder.game.player;

import com.acemurder.game.Manager;
import com.acemurder.game.Player;
import com.acemurder.game.Poker;

import java.util.List;

/**
 * Created by : AceMurder
 * Created on : 2017/11/7
 * Created for : Games.
 * Enjoy it !!!!
 */
public class RaiseFatherPlayer implements Player {
    @Override
    public String getName() {
        return "加注狗";
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
        //爸爸给我说玩牌要霸气，不要看牌，直接加倍
        return 3 * moneyYouNeedToPayLeast;
    }

    @Override
    public void onResult(int time, boolean isWin, List<Poker> pokers) {

    }
}
