package com.acemurder.game.player;

import com.acemurder.game.Manager;
import com.acemurder.game.Player;
import com.acemurder.game.Poker;

import java.util.Collections;
import java.util.List;

public class Cynthia implements Player
{
    @Override
    public String getName() {
        return "刘彦茹";
    }

    @Override
    public String getStuNum() {
        return "2017210441";
    }

    @Override
    public void onGameStart(Manager manager, int totalPlayer) {

    }

    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
        Collections.sort(pokers);
        if (isSameColor(pokers) )
            return (int) ((2 + (round / 10f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) ((2 + (round / 10f)) * moneyYouNeedToPayLeast) : 3 * moneyOnDesk - 1;

        if (isSameColorStraight(pokers) )
            return (int) ((2 +(round / 20f)) * moneyYouNeedToPayLeast) < 2 * moneyOnDesk ? (int) ((2 +(round / 20f)) * moneyYouNeedToPayLeast)  : 2 * moneyOnDesk ;
        if (isPair(pokers)) {
            if (pokers.get(0).getPoint().getNum() == pokers.get(1).getPoint().getNum()) {
                if (pokers.get(0).getPoint().getNum() >= 10)
                    return  (2 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ?  (2 * moneyYouNeedToPayLeast) : moneyYouNeedToPayLeast;
            }
            if (pokers.get(2).getPoint().getNum() == pokers.get(1).getPoint().getNum()) {
                if (pokers.get(1).getPoint().getNum() >= 10)
                    return (2 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (2 * moneyYouNeedToPayLeast) : moneyYouNeedToPayLeast;
            }
            if (pokers.get(0).getPoint().getNum() == pokers.get(2).getPoint().getNum()) {
                if (pokers.get(0).getPoint().getNum() >= 10)
                    return (2 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (2 * moneyYouNeedToPayLeast) : moneyYouNeedToPayLeast;
            }
            return (int) (1.3 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int)(1.3 * moneyYouNeedToPayLeast) : moneyYouNeedToPayLeast;
        }
        if (isSamePoint(pokers))
            return (3 * moneyOnDesk - 1);
        if (isStraight(pokers))
            return (int) (2.5 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (2.5 * moneyYouNeedToPayLeast) : moneyYouNeedToPayLeast;
        for (Poker p : pokers){
            if ( p.getPoint().getNum() >= 13)
                return moneyYouNeedToPayLeast ;
        }
        return 0;
    }

    @Override
    public void onResult(int time, boolean isWin, List<Poker> pokers) {

    }
    //金花（相同花色
    private boolean isSameColor(List<Poker> pokers) {
        return pokers.get(0).getSuit() == pokers.get(1).getSuit() &&
                pokers.get(1).getSuit() == pokers.get(2).getSuit();
    }
    //对子
    private boolean isPair(List<Poker> pokers) {
        return pokers.get(0).getPoint().getNum() == pokers.get(1).getPoint().getNum()
                || pokers.get(1).getPoint().getNum() == pokers.get(2).getPoint().getNum()
                || pokers.get(0).getPoint().getNum() == pokers.get(2).getPoint().getNum();
    }
    //顺子
    private boolean isStraight(List<Poker> pokers) {
        Collections.sort(pokers);
        return Math.abs(pokers.get(0).getPoint().getNum() - pokers.get(1).getPoint().getNum()) == 1
                && Math.abs(pokers.get(1).getPoint().getNum() - pokers.get(2).getPoint().getNum()) == 1;

    }
    //顺金
    private boolean isSameColorStraight(List<Poker> handCards) {
        return isSameColor(handCards) && isStraight(handCards);
    }
    //豹子
    private boolean isSamePoint(List<Poker> handCards) {
        return handCards.get(0).getPoint() == handCards.get(1).getPoint()
                && handCards.get(2).getPoint() == handCards.get(1).getPoint();

    }
}
