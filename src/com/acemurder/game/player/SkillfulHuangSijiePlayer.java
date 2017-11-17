package com.acemurder.game.player;

import com.acemurder.game.Manager;
import com.acemurder.game.Player;
import com.acemurder.game.Poker;

import java.util.Collections;
import java.util.List;

public class SkillfulHuangSijiePlayer implements Player {
    private static final int SAME_POINT = 60 * 100000;
    private static final int STRAIGHT = 20 * 100000;
    private static final int SAME_COLOR = 30 * 100000;
    private static final int SAME_COLOR_STRAIGHT = STRAIGHT + SAME_COLOR;
    private static final int PAIR = 10 * 100000;
    private int personLeft;
    private int lastBetLeast;
    @Override
    public String getName() {
        return "黄思杰";
    }

    @Override
    public String getStuNum() {
        return "2017211803";
    }

    @Override
    public void onGameStart(Manager manager, int totalPlayer) {

    }

    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
        int value = judge(pokers);
        int forBet=0;
        if(round==0){
            this.personLeft=lastPerson;
            this.lastBetLeast=100;
        }
        //if (this.personLeft>10) {
            if (value > 6000000) {
                forBet= moneyYouNeedToPayLeast * 3;
            }else if (value > 5000000){
                forBet= moneyYouNeedToPayLeast/lastBetLeast<3?moneyYouNeedToPayLeast*3:moneyYouNeedToPayLeast*2;
            } else if (value > 3010000&&personLeft<10){
                forBet= moneyYouNeedToPayLeast/lastBetLeast<3?moneyYouNeedToPayLeast*3:moneyYouNeedToPayLeast;
            }else if(round<2&&moneyYouNeedToPayLeast<1000){
                forBet=moneyYouNeedToPayLeast;
            }
//       }



        this.lastBetLeast=moneyYouNeedToPayLeast;
        return forBet;
    }

    @Override
    public void onResult(int time, boolean isWin, List<Poker> pokers) {

    }

    private int judge(List<Poker> handCards) {
        Collections.sort(handCards);
        int value = 0;
        if (isPair(handCards)) {
            if (handCards.get(0).getPoint().getNum() == handCards.get(1).getPoint().getNum())
                value += handCards.get(0).getPoint().getNum() * 10000
                        + handCards.get(0).getPoint().getNum() * 100
                        + handCards.get(2).getPoint().getNum();
            else
                value += handCards.get(1).getPoint().getNum() * 10000
                        + handCards.get(1).getPoint().getNum() * 100
                        + handCards.get(0).getPoint().getNum();

        } else {
            value += handCards.get(0).getPoint().getNum() * 10000
                    + handCards.get(1).getPoint().getNum() * 100
                    + handCards.get(2).getPoint().getNum();
        }

        if (isSamePoint(handCards))
            value += SAME_POINT;
        else if (isSameColorStraight(handCards))
            value += SAME_COLOR_STRAIGHT;
        else if (isSameColor(handCards))
            value += SAME_COLOR;
        else if (isStraight(handCards))
            value += STRAIGHT;
        else if (isPair(handCards)) {
            value += PAIR;
        } else {

        }
        return value;
    }

    private boolean isSameColor(List<Poker> handCards) {
        return handCards.get(0).getSuit() == handCards.get(1).getSuit() &&
                handCards.get(1).getSuit() == handCards.get(2).getSuit();
    }

    private boolean isPair(List<Poker> handCards) {
        return handCards.get(0).getPoint().getNum() == handCards.get(1).getPoint().getNum()
                || handCards.get(1).getPoint().getNum() == handCards.get(2).getPoint().getNum()
                || handCards.get(0).getPoint().getNum() == handCards.get(2).getPoint().getNum();
    }

    private boolean isStraight(List<Poker> handCards) {
        Collections.sort(handCards);
        return Math.abs(handCards.get(0).getPoint().getNum() - handCards.get(1).getPoint().getNum()) == 1
                && Math.abs(handCards.get(1).getPoint().getNum() - handCards.get(2).getPoint().getNum()) == 1;

    }

    private boolean isSameColorStraight(List<Poker> handCards) {
        return isSameColor(handCards) && isStraight(handCards);
    }

    private boolean isSamePoint(List<Poker> handCards) {
        return handCards.get(0).getPoint() == handCards.get(1).getPoint()
                && handCards.get(2).getPoint() == handCards.get(1).getPoint();

    }
}
