package com.acemurder.game.player;

import com.acemurder.game.Manager;
import com.acemurder.game.Player;
import com.acemurder.game.Poker;

import java.util.Collections;
import java.util.List;

/**
 * Created by : Ruokbb
 * Created on : 2017/11/15
 * Created for : Games.
 * Enjoy it !!!!
 */
public class RuokbbPlayer implements Player {
    @Override
    public String getName() {
        return "石其鑫";
    }

    @Override
    public String getStuNum() {
        return "2017210238";
    }

    @Override
    public void onGameStart(Manager manager, int totalPlayer) {

    }

    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
        Collections.sort(pokers);
        //豹子就是干
        if (isSamePoint(pokers))
            return 3* moneyYouNeedToPayLeast;
        //剩下的牌型看着跟
        if((isSameColor(pokers)||isSameColorStraight(pokers))&&moneyYouNeedToPayLeast<=15000)
            return 2*moneyYouNeedToPayLeast;
        if (isPair(pokers)&&moneyYouNeedToPayLeast<=3000)
            return moneyYouNeedToPayLeast;
        if (isStraight(pokers)&&moneyYouNeedToPayLeast<=8000)
            return moneyYouNeedToPayLeast;
        //一堆人打，我不信单牌能赢
        return 0;

    }

    @Override
    public void onResult(int time, boolean isWin, List<Poker> pokers) {

    }
    /*
    判断牌型
     */
    private boolean isSameColor(List<Poker> pokers) {
        return pokers.get(0).getSuit() == pokers.get(1).getSuit() &&
                pokers.get(0).getSuit() == pokers.get(2).getSuit();
    }

    private boolean isPair(List<Poker> pokers) {
        return pokers.get(0).getPoint().getNum() == pokers.get(1).getPoint().getNum()
                || pokers.get(1).getPoint().getNum() == pokers.get(2).getPoint().getNum()
                || pokers.get(0).getPoint().getNum() == pokers.get(2).getPoint().getNum();
    }

    private boolean isStraight(List<Poker> pokers) {
        Collections.sort(pokers);
        return Math.abs(pokers.get(0).getPoint().getNum() - pokers.get(1).getPoint().getNum()) == 1
                && Math.abs(pokers.get(1).getPoint().getNum() - pokers.get(2).getPoint().getNum()) == 1;

    }

    private boolean isSameColorStraight(List<Poker> handCards) {
        return isSameColor(handCards) && isStraight(handCards);
    }

    private boolean isSamePoint(List<Poker> handCards) {
        return handCards.get(0).getPoint() == handCards.get(1).getPoint()
                && handCards.get(2).getPoint() == handCards.get(1).getPoint();

    }
}