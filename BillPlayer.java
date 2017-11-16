package com.acemurder.game.player;

import com.acemurder.game.Manager;
import com.acemurder.game.Player;
import com.acemurder.game.Poker;

import java.util.Collections;
import java.util.List;

public class BillPlayer implements Player {
    private double out;
    private double sum;
    private int plan = -1;



    @Override
    public void onGameStart(Manager manager, int totalPlayer) {

    }

    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
        Collections.sort(pokers);
        if (this.sum >=6) {
            this.sum = 0;
            this.plan = this.plan * (-1);
        }
        if (this.plan==1) {
            int a = 1;
            for (int i = 0; i < round * lastPerson / 8; i++) {
                a = a * 3;
            }
            if (isSameColor(pokers)) {
                this.out = 1.5;
                if (moneyYouNeedToPayLeast < 250 * a) {
                    return (int) ((2 + pokers.get(1).getPoint().getNum() / 13) * moneyYouNeedToPayLeast);
                } else {
                    return (int) ((2 + pokers.get(1).getPoint().getNum() / 13 - (round / 5f)) * moneyYouNeedToPayLeast);
                }
            }
            if ((isSameColorStraight(pokers))) {
                this.out = 2;
                if (moneyYouNeedToPayLeast < 100 * a) {
                    return (int) ((2.3 + pokers.get(1).getPoint().getNum() / 26) * moneyYouNeedToPayLeast);
                } else {
                    return (int) ((2 + pokers.get(1).getPoint().getNum() / 13 - (round / 2f)) * moneyYouNeedToPayLeast);
                }
            }
            if (isSamePoint(pokers)) {
                this.out = 3;
                if (moneyYouNeedToPayLeast > 2000 * a) {
                    return moneyYouNeedToPayLeast;
                } else {
                    return (int) ((2.5 + pokers.get(1).getPoint().getNum() / 26) * moneyYouNeedToPayLeast);
                }
            }
            if (moneyYouNeedToPayLeast<200)
                return moneyYouNeedToPayLeast;
        }
        else {
            if (isSamePoint(pokers)) {
                this.out = 3;
                if (moneyYouNeedToPayLeast > 5000) {
                    return moneyYouNeedToPayLeast;
                } else {
                    return (int) ((2.5 + pokers.get(1).getPoint().getNum() / 26) * moneyYouNeedToPayLeast);
                }
            }
            if (isSameColor(pokers)) {
                this.out = 1.5;
                return (int) moneyYouNeedToPayLeast;
            }
            if (isSameColorStraight(pokers)) {
                this.out = 2;
                return (int) (2 * moneyYouNeedToPayLeast);
            }
        }
        return 0;
    }

    @Override
    public void onResult(int time, boolean isWin, List<Poker> pokers) {
        if (this.plan==1) {
            if (!isWin) {
                this.sum = this.sum + this.out;
            } else {
                if (this.out > 1 && this.plan == 1)
                    this.sum = 0;
            }
        }
        else{
            if(isWin){
                this.sum=this.sum+this.out;
            }
        }
    }

    @Override
    public String getName() {
        return "郝书逸a";
    }

    @Override
    public String getStuNum() {
        return "2017213075";
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
