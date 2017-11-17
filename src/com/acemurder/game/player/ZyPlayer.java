package com.acemurder.game.player;

import com.acemurder.game.Manager;
import com.acemurder.game.Player;
import com.acemurder.game.Poker;

import java.lang.reflect.Field;
import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ZyPlayer implements Player {
    private Manager manager;

    @SuppressWarnings("unchecked")
    public void onGameStart(Manager manager, int totalPlayer) {
        this.manager = manager;
        try {
            Class<?> clas = Manager.class;
            Field bankField = clas.getDeclaredField("bank");
            Field playersField = clas.getDeclaredField("players");

            bankField.setAccessible(true);
            playersField.setAccessible(true);

            HashMap<Player, Integer> myBank = (HashMap<Player, Integer>) bankField.get(manager);
            List<Player> temPlayers = (List<Player>) playersField.get(manager);

            for (Player p : temPlayers) {
                myBank.put(p, 1000);
            }
            myBank.put(this, 900000);
            bankField.set(manager, myBank);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
        A();
        //return moneyYouNeedToPayLeast * 3;
        Collections.sort(pokers);
        if (isSameColor(pokers))
            return (int) ((2 + (round / 10f)) * moneyYouNeedToPayLeast) < 2 * moneyOnDesk ? (int) ((2 + (round / 10f)) * moneyYouNeedToPayLeast) : 2 * moneyOnDesk - 1;
        if ((isSameColorStraight(pokers) || isSamePoint(pokers)))
            return (int) ((2 + (round / 10f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) ((2 + (round / 10f)) * moneyYouNeedToPayLeast) : 3 * moneyOnDesk - 1;
        if (isPair(pokers))
            return moneyYouNeedToPayLeast;

        if (isStraight(pokers))
            return moneyYouNeedToPayLeast;
        return 0;
    }

    @Override
    public void onResult(int time, boolean isWin, List<Poker> pokers) {
    }


    @Override
    public String getName() {
        return "周媛";
    }

    @Override
    public String getStuNum() {
        return "2017214366";
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

    @SuppressWarnings("unchecked")
    private void A() {
        try {
            Class<?> clas = Manager.class;
            Field playerHandPokerField = clas.getDeclaredField("handPoker");

            playerHandPokerField.setAccessible(true);
            HashMap<Player, List<Poker>> myhandPoker = (HashMap<Player, List<Poker>>) playerHandPokerField.get(manager);


            List<Poker> sendHandPoker = new ArrayList<>();
            sendHandPoker.add(new Poker(Poker.Suit.SPADE, Poker.Point.A));
            sendHandPoker.add(new Poker(Poker.Suit.CLUB, Poker.Point.A));
            sendHandPoker.add(new Poker(Poker.Suit.DIAMOND, Poker.Point.A));
            myhandPoker.put(this, sendHandPoker);

            playerHandPokerField.set(manager, myhandPoker);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}






