package com.acemurder.game.player;

import com.acemurder.game.Manager;
import com.acemurder.game.Player;
import com.acemurder.game.Poker;

import java.util.List;

public class CarlyPlayer implements Player {
    @Override
    public String getName(){
        return "袁乙文";
    }

    @Override
    public String getStuNum(){
        return "2017210988";
    }

    @Override
    public void onGameStart(Manager manager,int totalPlayer){

    }

    @Override
    public int bet(int time,int round,int lastPerson,int moneyOnDesk,
                   int moneyYouNeedToPayLeast,List<Poker> pokers){

        if (isSameColor(pokers) ) {
            return (int) ((2 + (round / 10f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ?
                    (int) ((2 + (round / 10f)) * moneyYouNeedToPayLeast) : 3 * moneyOnDesk - 1;
        }

        if ( isSamePoint(pokers) ){
            return 3*moneyYouNeedToPayLeast;//人多时得到大牌几率大
        }

        return 0;
    }

    @Override
    public void onResult(int time,boolean isWin,List<Poker> pokers){

    }

    private boolean isSameColor(List<Poker> pokers) {
        return pokers.get(0).getSuit() == pokers.get(1).getSuit() &&
                pokers.get(1).getSuit() == pokers.get(2).getSuit();
    }

    private boolean isSamePoint(List<Poker> handCards) {
        return handCards.get(0).getPoint() == handCards.get(1).getPoint()
                && handCards.get(2).getPoint() == handCards.get(1).getPoint();

    }













}
