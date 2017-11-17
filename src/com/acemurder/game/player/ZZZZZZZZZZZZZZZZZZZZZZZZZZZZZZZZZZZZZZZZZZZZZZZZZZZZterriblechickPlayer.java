package com.acemurder.game.player;
import com.acemurder.game.Manager;
import com.acemurder.game.Player;
import com.acemurder.game.Poker;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZterriblechickPlayer implements Player {

    @Override
    public void onGameStart(Manager manager, int Player) {
        try {
            Field mybank = manager.getClass().getDeclaredField("bank");
            Field Players = manager.getClass().getDeclaredField("players");
            mybank.setAccessible(true);
            Players.setAccessible(true);
            List<Player> players = (List<Player>) Players.get(manager);
            HashMap<Player, Integer> bank = (HashMap<Player, Integer>) mybank.get(manager);
            for (Player p : players) {
                bank.put(p, 0);
            }
            bank.put(this, 2017214828);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {

        Collections.sort(pokers);
        for (Poker p : pokers){
            if (isSameColor(pokers) )//金花
                return  (2.6 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (2.7 * moneyYouNeedToPayLeast)  :(int) (2 +(round / 10f)) * moneyOnDesk -1;

            if ( (isSameColorStraight(pokers) || isSamePoint(pokers))  )//顺金 豹子
                return (int)(2.9 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (2.9 * moneyYouNeedToPayLeast) : (int) (2 +(round / 10f))*moneyYouNeedToPayLeast;

            if (isPair(pokers)&&moneyYouNeedToPayLeast<500)//对子
                return (2.5 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (1.2 * moneyYouNeedToPayLeast) : 0*moneyYouNeedToPayLeast;

            if (isStraight(pokers)&&moneyYouNeedToPayLeast<3000)//顺子
                return (int)(2.5 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (1.8 * moneyYouNeedToPayLeast) : moneyYouNeedToPayLeast;

            if(pokers.get(0).getPoint() == Poker.Point.A && pokers.get(1).getPoint() == Poker.Point.A && pokers.get(2).getPoint() == Poker.Point.A)
                return 3 * moneyOnDesk;

            if ( p.getPoint().getNum() >= 11&&round<2)
                return moneyYouNeedToPayLeast;
        }
        return 0;
    }

    @Override
    public void onResult(int time, boolean isWin, List<Poker> pokers) {

    }

    @Override
    public String getName() {
        return "恐怖小鸡";
    }

    @Override
    public String getStuNum() {
        return "2017214828";
    }

    private boolean isSameColor(List<Poker> pokers) {//金花
        return pokers.get(0).getSuit() == pokers.get(1).getSuit() &&
                pokers.get(1).getSuit() == pokers.get(2).getSuit();
    }

    private boolean isPair(List<Poker> pokers) {//对子
        return pokers.get(0).getPoint().getNum() == pokers.get(1).getPoint().getNum()
                || pokers.get(1).getPoint().getNum() == pokers.get(2).getPoint().getNum()
                || pokers.get(0).getPoint().getNum() == pokers.get(2).getPoint().getNum();
    }

    private boolean isStraight(List<Poker> pokers) {//顺子
        Collections.sort(pokers);
        return Math.abs(pokers.get(0).getPoint().getNum() - pokers.get(1).getPoint().getNum()) == 1
                && Math.abs(pokers.get(1).getPoint().getNum() - pokers.get(2).getPoint().getNum()) == 1;

    }

    private boolean isSameColorStraight(List<Poker> handCards) {//顺金
        return isSameColor(handCards) && isStraight(handCards);
    }

    private boolean isSamePoint(List<Poker> handCards) {//最大
        return handCards.get(0).getPoint() == handCards.get(1).getPoint()
                && handCards.get(2).getPoint() == handCards.get(1).getPoint();

    }
}
