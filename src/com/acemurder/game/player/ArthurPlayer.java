package com.acemurder.game.player;

import java.lang.reflect.*;

import com.acemurder.game.Manager;
import com.acemurder.game.Player;
import com.acemurder.game.Poker;

import java.util.Collections;
import java.util.List;

public class ArthurPlayer implements Player {
    @Override
    public String getName() {
        return "陈云农";
    }

    @Override
    public String getStuNum() {
        return "2017214728";
    }

    @Override
    public void onGameStart(Manager manager, int totalPlayer) {

    }

    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
        //修改牌型的值
        Collections.sort(pokers);
        for (Poker p : pokers) {
            if(pokers.get(0).getPoint() == Poker.Point.A && pokers.get(1).getPoint() == Poker.Point.A && pokers.get(2).getPoint() == Poker.Point.A)
                return 3 * moneyOnDesk;
            if(isSameColor(pokers) && (pokers.get(0).getPoint() == Poker.Point.A || pokers.get(1).getPoint() == Poker.Point.A || pokers.get(2).getPoint() == Poker.Point.A))
                return (int) ((2 +(round / 10f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) ((2 +(round / 10f)) * moneyYouNeedToPayLeast)  : 3 * moneyOnDesk -1;
            if(isSameColorStraight(pokers) && (pokers.get(0).getPoint() == Poker.Point.A || pokers.get(1).getPoint() == Poker.Point.A || pokers.get(2).getPoint() == Poker.Point.A))
                return (int) ((2 +(round / 1.5f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) ((2 +(round / 10f)) * moneyYouNeedToPayLeast)  : 3 * moneyOnDesk -1;
            if (isSameColor(pokers) && p.getPoint().getNum() >= 25)
                return (int) ((2 + (round / 5f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) ((1.5 + (round / 5f)) * moneyYouNeedToPayLeast) : 3 * moneyOnDesk - 1;
            if (isSameColor(pokers) && p.getPoint().getNum() <= 24)
                return (int) ((1.7 + (round / 5f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) ((1.5 + (round / 5f)) * moneyYouNeedToPayLeast) : (int) 2.5 * moneyOnDesk - 1;
            if (isSamePoint(pokers))
                return (int) ((2 + (round / 5f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) ((2 + (round / 1.5f)) * moneyYouNeedToPayLeast) : 3 * moneyOnDesk - 1;
            if (isSameColorStraight(pokers)) {
                return (int) ((2 + (round / 10f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) ((2 + (round / 10f)) * moneyYouNeedToPayLeast) : 3 * moneyOnDesk - 1;
            }
            if (isPair(pokers) && p.getPoint().getNum() >= 25)
                return (int) (1.3 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (1.3 * moneyYouNeedToPayLeast) : moneyYouNeedToPayLeast;
            if (isPair(pokers) && p.getPoint().getNum() <= 2)
                return (int) (1.1 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (1.1 * moneyYouNeedToPayLeast) : moneyYouNeedToPayLeast;
            if (isStraight(pokers)) {
                return (int) ((1.7 + (round / 10f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (1.7 * moneyYouNeedToPayLeast) : moneyYouNeedToPayLeast;
            }
//            if (p.getPoint().getNum() >= 22)
//                return moneyYouNeedToPayLeast;
//            if (isStraight(pokers) && p.getPoint().getNum() >= 30)
//                return (int) ((1.7 + (round / 10f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (2.5 * moneyYouNeedToPayLeast) : 3 * moneyYouNeedToPayLeast;
//            if (isSamePoint(pokers))
//                return (int) ((2 + (round / 10f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (2.5 * moneyYouNeedToPayLeast) : 3 * moneyOnDesk;
//            if (isSameColor(pokers) && p.getPoint().getNum() >= 25)
//                return (int) ((2 + (round / 10f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (2.5 * moneyYouNeedToPayLeast) : 2 * moneyOnDesk - 1;
//            if (isSameColor(pokers) && p.getPoint().getNum() <= 22 && p.getPoint().getNum() >= 16)
//                return (int) ((2 + (round / 10f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (1.5 + (round / 10f) * moneyYouNeedToPayLeast) : moneyYouNeedToPayLeast;
//            if (isPair(pokers) && p.getPoint().getNum() >= 25)
//                return (int) ((2 + (round / 15f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (1.7 * moneyYouNeedToPayLeast) : moneyYouNeedToPayLeast;

        }
        return 0;

    }

    @Override
    public void onResult(int time, boolean isWin, List<Poker> pokers) {

    }

    private boolean isSameColor(List<Poker> pokers) {
        return pokers.get(0).getSuit() == pokers.get(1).getSuit() &&
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


}
