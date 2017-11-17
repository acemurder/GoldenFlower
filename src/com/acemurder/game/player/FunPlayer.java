package com.acemurder.game.player;
import com.acemurder.game.Manager;
import com.acemurder.game.Player;
import com.acemurder.game.Poker;

import java.util.Collections;
import java.util.List;


public class FunPlayer implements Player {

    @Override
    public void onGameStart(Manager manager, int totalPlayer) {

    }

    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
        Collections.sort(pokers);
        if (isbiggest(pokers))
            return (3 * moneyYouNeedToPayLeast);
        if (isSameColor(pokers))
            return ((3) * moneyYouNeedToPayLeast);
        if ((isSameColorStraight(pokers) || isSamePoint(pokers)))
            return ((3) * moneyYouNeedToPayLeast);
        if (isPair(pokers))
            return (int) (1.5 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (1.3 * moneyYouNeedToPayLeast) : moneyYouNeedToPayLeast;

        if (isStraight(pokers))
            return (int) (1.8 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (1.7 * moneyYouNeedToPayLeast) : moneyYouNeedToPayLeast;
        for (Poker p : pokers) {
            if (p.getPoint().getNum() >= 14)
                return moneyYouNeedToPayLeast;
        }
        if (issingle(pokers)) {
            return moneyYouNeedToPayLeast;
        }
        return 0;
    }
    @Override
    public void onResult(int time, boolean isWin, List<Poker> pokers) {

    }

    @Override
    public String getName() {
        return "菜手子";
    }

    @Override
    public String getStuNum() {
        return "2015211876";
    }

    private boolean isSameColor(List<Poker> pokers) {
        return pokers.get(0).getSuit() == pokers.get(2).getSuit() &&
                pokers.get(1).getSuit() == pokers.get(2).getSuit();
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
    private boolean isbiggest(List<Poker> pokers){
        return  pokers.get(0).getPoint().getNum() == pokers.get(1).getPoint().getNum()
                &&pokers.get(1).getPoint().getNum() == pokers.get(2).getPoint().getNum();
    }
    private boolean issingle(List<Poker> pokers){
        Poker.Point[] points;
        return   pokers.get(0).getPoint().getNum() == 14|| pokers.get(1).getPoint().getNum()==14|| pokers.get(2).getPoint().getNum()==14;
    }

}
