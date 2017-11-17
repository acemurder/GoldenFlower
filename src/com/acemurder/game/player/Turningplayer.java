package com.acemurder.game.player;
import com.acemurder.game.Manager;
import com.acemurder.game.Player;
import com.acemurder.game.Poker;

import java.util.Collections;
import java.util.List;

public class Turningplayer implements Player {
    @Override
    public void onGameStart(Manager manager, int totalPlayer) {

    }

    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
        Collections.sort(pokers);
        if (isSamePoint(pokers))
            return 3 * moneyYouNeedToPayLeast;
        if (isSameColorStraight(pokers))
            return 3 * moneyYouNeedToPayLeast;
        if (isSameColor(pokers)) {
            if (moneyYouNeedToPayLeast <= 30000)
                return (int) ((2 + (round / 5f)) * moneyYouNeedToPayLeast);
            else
                return moneyYouNeedToPayLeast;
        }
        if(isPair(pokers)){
            if(isBigPair(pokers)){
                return (int) ((2 + (round / 5f)) * moneyYouNeedToPayLeast);
            }
            else
                return moneyYouNeedToPayLeast;
        }

        if (isStraight(pokers) && moneyYouNeedToPayLeast < 70000){
            if (moneyYouNeedToPayLeast <= 20000)
                return (int) ((1 + (round / 5f)) * moneyYouNeedToPayLeast);
            else
                return moneyYouNeedToPayLeast;
        }
        for (Poker p : pokers) {
            if (p.getPoint().getNum() >= 12 && moneyYouNeedToPayLeast <= 1800 && round <= 2)
                return moneyYouNeedToPayLeast;
            else
                return 0;
        }
        return 0;

    }

    @Override
    public void onResult(int time, boolean isWin, List<Poker> pokers) {

    }

    @Override
    public String getName() {
        return "廖宏昊";
    }

    @Override
    public String getStuNum() {
        return "2017214834";
    }

    private boolean isSameColor(List<Poker> pokers) {
        return pokers.get(0).getSuit() == pokers.get(1).getSuit() &&
                pokers.get(1).getSuit() == pokers.get(2).getSuit();
    }
    private boolean isBigPair(List<Poker> pokers) {
        return (pokers.get(0).getPoint().getNum() == pokers.get(1).getPoint().getNum() &&
                pokers.get(0).getPoint().getNum() >= 11)
                ||
                (pokers.get(1).getPoint().getNum() == pokers.get(2).getPoint().getNum() &&
                        pokers.get(1).getPoint().getNum() >= 11)
                ||
                (pokers.get(0).getPoint().getNum() == pokers.get(2).getPoint().getNum() &&
                        pokers.get(0).getPoint().getNum() >= 11)
                ;
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

