package com.acemurder.game.player;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;

import com.acemurder.game.Manager;
import com.acemurder.game.Player;
import com.acemurder.game.Poker;
import com.acemurder.game.Poker.Point;

import static com.acemurder.game.Poker.Point.A;

public class AlpacaPlayer implements Player {
    static void setFinalStatic(Field field, Object newValue) throws Exception {
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(null, newValue);
    }

    public static void main(String args[]) throws Exception {
        setFinalStatic(Poker.class.getField("Point") ,14);
    }
        @Override
    public String getName() {
        {
            return "丁昊";
        }
    }

    @Override
    public String getStuNum() {
        {
            return "2017214276";
        }
    }

    @Override
    public void onGameStart(Manager manager, int totalPlayer) {

    }

    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {

        if (isSameColor(pokers))
            return (int) ((2 + (round / 10f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) ((2 + (round / 10f)) * moneyYouNeedToPayLeast) : 9999999* moneyOnDesk - 1;

        if ((isSameColorStraight(pokers) || isSamePoint(pokers)))
            return (int) ((2 + (round / 10f)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) ((2 + (round / 10f)) * moneyYouNeedToPayLeast) : 9999 * moneyOnDesk - 1;
        if (isPair(pokers))
            return (int) (1.2 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (3 * moneyYouNeedToPayLeast) :2* moneyYouNeedToPayLeast;

        if (isStraight(pokers))
            return (int) (1.7 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (1.9 * moneyYouNeedToPayLeast) : moneyYouNeedToPayLeast;
        for (Poker p : pokers) {
            if (p.getPoint().getNum() >= 11)
                return  moneyYouNeedToPayLeast;
            if (p.getPoint().getNum() >= 13)
                return  3*moneyYouNeedToPayLeast;
            if (p.getPoint().getNum() >= 14)
                return 5*moneyYouNeedToPayLeast;
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

