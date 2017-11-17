package com.acemurder.game.player;

import com.acemurder.game.Manager;
import com.acemurder.game.Player;
import com.acemurder.game.Poker;

import java.util.Collections;
import java.util.List;

public class LeoPlayer implements Player {
    int totalPlayer;

    @Override
    public String getName() {
        return "罗子曦1  ";
    }

    @Override
    public String getStuNum() {
        return "2017210828";
    }

    @Override
    public void onGameStart(Manager manager, int totalPlayer) {
        this.totalPlayer = totalPlayer;
    }

    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
        if (lastPerson >= 5) {
            if (isSamePoint(pokers)) {
                if (round <= 1) return 2 * moneyYouNeedToPayLeast;
                else return 3 * moneyYouNeedToPayLeast;
            }
            if (isSameColor(pokers))
                return (int) ((2 + (round / 10f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) ((2 + (round / 10f)) * moneyYouNeedToPayLeast) : 3 * moneyOnDesk - 1;
            if ((isSameColorStraight(pokers) || isSamePoint(pokers)))
                return (int) ((2 + (round / 10f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) ((2 + (round / 10f)) * moneyYouNeedToPayLeast) : 3 * moneyOnDesk - 1;
            if (isPair(pokers)) {
                if (moneyYouNeedToPayLeast <= 1800)
                    return (int) (1.3 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (1.3 * moneyYouNeedToPayLeast) : moneyYouNeedToPayLeast;
                else return 0;
            }
            if (isStraight(pokers))
                return (int) (1.7 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (1.7 * moneyYouNeedToPayLeast) : moneyYouNeedToPayLeast;
            for (Poker p : pokers) {
                if (moneyYouNeedToPayLeast <= 600) {
                    if (p.getPoint().getNum() >= 12)
                        return moneyYouNeedToPayLeast;
                } else return 0;
            }
            return 0;
        }
        else {
            if (isSamePoint(pokers)) {
                return 3 * moneyYouNeedToPayLeast;
            }
            if (isSameColor(pokers))
                return   moneyYouNeedToPayLeast*3;
            if ((isSameColorStraight(pokers) || isSamePoint(pokers)))
                return moneyYouNeedToPayLeast*3;

            return 0;
        }


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
