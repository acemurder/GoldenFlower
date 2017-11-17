package com.acemurder.game.player;

import com.acemurder.game.Manager;
import com.acemurder.game.Player;
import com.acemurder.game.Poker;

import java.util.Collections;
import java.util.List;

public class LiuPlayer implements Player {

    @Override
    public String getName() {
        return "刘俊";
    }

    @Override
    public String getStuNum() {
        return "2017210611";
    }

    @Override
    public void onGameStart(Manager manager, int totalPlayer) {

    }

    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
        Collections.sort(pokers);
        if (lastPerson > 13) {
            if (isSamePoint(pokers) || isSameColorStraight(pokers))
                return 3 * moneyYouNeedToPayLeast;

            if (isSameColor(pokers) && isSuperPoint(pokers)) {
                if (round <= 1)
                    return moneyYouNeedToPayLeast;
                else if (round == 2) {
                    if (moneyOnDesk / lastPerson <= 400)
                        return 3 * moneyYouNeedToPayLeast;
                    else if (moneyOnDesk / lastPerson <= 3600 && moneyOnDesk / lastPerson > 400)
                        return (int) (2.5 * moneyYouNeedToPayLeast);
                    else
                        return moneyYouNeedToPayLeast;
                } else
                    return moneyOnDesk / lastPerson / (round + 1) > 8100 ? moneyYouNeedToPayLeast : 2 * moneyYouNeedToPayLeast;
            }
            return 0;
        }


        if ((isSameColorStraight(pokers) || isSamePoint(pokers)))
            return 3 * moneyYouNeedToPayLeast;

        if (isSameColor(pokers)) {
            if (round <= 1)
                return moneyYouNeedToPayLeast;
            if (round == 2) {
                if (moneyOnDesk / lastPerson <= 400)
                    return 3 * moneyYouNeedToPayLeast;
                else if (moneyOnDesk / lastPerson <= 3000 && moneyOnDesk / lastPerson > 400)
                    return (int) 2.5 * moneyYouNeedToPayLeast;
                else
                    return moneyYouNeedToPayLeast;
            } else
                return moneyOnDesk / lastPerson / (round + 1) > 3000 ? moneyYouNeedToPayLeast : (int) 2.5 * moneyYouNeedToPayLeast;
        }

        if (isStraight(pokers)) {
            if (round <= 1)
                return moneyYouNeedToPayLeast;
            else if (round == 2) {
                if (moneyOnDesk / lastPerson <= 400)
                    return 3 * moneyYouNeedToPayLeast;
                else if (moneyOnDesk / lastPerson <= 2000 && moneyOnDesk / lastPerson > 400)
                    return 2 * moneyYouNeedToPayLeast;
                else
                    return moneyYouNeedToPayLeast;
            } else
                return moneyOnDesk / lastPerson / (round + 1) > 2500 ? moneyYouNeedToPayLeast : 2 * moneyYouNeedToPayLeast;
        }

        if (isPair(pokers)) {
            if (round <= 1)
                return moneyYouNeedToPayLeast;
            else if (round == 2) {
                if (moneyOnDesk / lastPerson <= 400)
                    return (int) (1.5 * moneyYouNeedToPayLeast);
                if (moneyOnDesk / lastPerson <= 2500 && moneyOnDesk / lastPerson > 400)
                    return moneyYouNeedToPayLeast;
                else
                    return 0;
            } else
                return moneyOnDesk / lastPerson / (round + 1) > 1500 ? 0 : (int) (1.2 * moneyYouNeedToPayLeast);
        }

        if (isSuperPoint(pokers)) {
            if (round <= 1)
                return moneyYouNeedToPayLeast;
            if (round == 2) {
                if (moneyOnDesk / lastPerson <= 400)
                    return (int) (1.5 * moneyYouNeedToPayLeast);
                else if (moneyOnDesk / lastPerson <= 1500 && moneyOnDesk / lastPerson > 400)
                    return moneyYouNeedToPayLeast;
                else
                    return 0;
            } else
                return moneyOnDesk / lastPerson / (round + 1) > 500 ? 0 : moneyYouNeedToPayLeast;
        }
        return 0;
    }

    @Override
    public void onResult(int time, boolean isWin, List<Poker> pokers) {

    }

    private boolean isSameColor(List<Poker> handCards) {
        return handCards.get(0).getSuit() == handCards.get(1).getSuit() &&
                handCards.get(1).getSuit() == handCards.get(2).getSuit();
    }

    private boolean isPair(List<Poker> handCards) {
        return (handCards.get(0).getPoint().getNum() == handCards.get(1).getPoint().getNum()
                || handCards.get(1).getPoint().getNum() == handCards.get(2).getPoint().getNum()
                || handCards.get(0).getPoint().getNum() == handCards.get(2).getPoint().getNum());
    }

    private boolean isStraight(List<Poker> handCards) {
        Collections.sort(handCards);
        return Math.abs(handCards.get(0).getPoint().getNum() - handCards.get(1).getPoint().getNum()) == 1
                && Math.abs(handCards.get(1).getPoint().getNum() - handCards.get(2).getPoint().getNum()) == 1;

    }

    private boolean isSameColorStraight(List<Poker> handCards) {
        return (isSameColor(handCards)) && isStraight(handCards);
    }

    private boolean isSamePoint(List<Poker> handCards) {
        return handCards.get(0).getPoint() == handCards.get(1).getPoint()
                && handCards.get(2).getPoint() == handCards.get(1).getPoint();
    }

    private boolean isSuperPoint(List<Poker> handCards) {
        return handCards.get(0).getPoint().getNum() > 12;
    }
}
