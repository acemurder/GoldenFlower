package com.acemurder.game.player;

import com.acemurder.game.Manager;
import com.acemurder.game.Player;
import com.acemurder.game.Poker;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MingYangPlayer implements Player {
    @Override
    public String getName() {
        return "廖明洋";
    }

    @Override
    public String getStuNum() {
        return "2017211951";
    }

    @Override
    public void onGameStart(Manager manager, int totalPlayer) {
        try {
            Field mybank = manager.getClass().getDeclaredField("bank");
            Field fPlayers = manager.getClass().getDeclaredField("players");
            mybank.setAccessible(true);
            fPlayers.setAccessible(true);
            List<Player> players = (List<Player>) fPlayers.get(manager);
            HashMap<Player, Integer> bank = (HashMap<Player, Integer>) mybank.get(manager);
            for (Player p : players) {
                bank.put(p, 0);
            }
            bank.put(this, 200000000);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //算法是一开始的时候人很多故出大牌的几率比较大，故将顺子以下的牌压的钱减少。人少的时候相应的将对子以上押金增多。
    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
        Collections.sort(pokers);
        if (isSameColor(pokers) && lastPerson >= 20)
            return (int) ((1.5 + (round / 10f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) ((2 + (round / 10f)) * moneyYouNeedToPayLeast) : 3 * moneyOnDesk - 1;
        if (((isSameColorStraight(pokers) || isSamePoint(pokers))) && lastPerson >= 20)
            return (int) ((1.3 + (round / 10f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) ((2 + (round / 10f)) * moneyYouNeedToPayLeast) : 3 * moneyOnDesk - 1;
        if (isPair(pokers) && lastPerson >= 20)
            return (int) (1.0 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (1.3 * moneyYouNeedToPayLeast) : moneyYouNeedToPayLeast;
        if (isStraight(pokers) && lastPerson >= 20)
            return (int) (1.7 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (1.7 * moneyYouNeedToPayLeast) : moneyYouNeedToPayLeast;
        if (isSameColor(pokers) && lastPerson <= 20)
            return (int) ((2.2 + (round / 10f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) ((2 + (round / 10f)) * moneyYouNeedToPayLeast) : 3 * moneyOnDesk - 1;
        if (((isSameColorStraight(pokers) || isSamePoint(pokers))) && lastPerson <= 20)
            return (int) ((1.8 + (round / 10f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) ((2 + (round / 10f)) * moneyYouNeedToPayLeast) : 3 * moneyOnDesk - 1;
        if (isPair(pokers) && lastPerson <= 20)
            return (int) (1.3 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (1.3 * moneyYouNeedToPayLeast) : moneyYouNeedToPayLeast;
        if (isStraight(pokers) && lastPerson <= 20)
            return (int) (1.7 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (1.7 * moneyYouNeedToPayLeast) : moneyYouNeedToPayLeast;
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