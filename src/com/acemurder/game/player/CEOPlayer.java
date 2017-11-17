package com.acemurder.game.player;
import com.acemurder.game.Manager;
import com.acemurder.game.Player;
import com.acemurder.game.Poker;
import java.util.Collections;
import java.util.List;

public class CEOPlayer implements Player {
    @Override
    public String getName() {
        return "岑金富";
    }

    @Override
    public String getStuNum() {
        return "2017213276";
    }

    @Override
    public void onGameStart(Manager manager, int totalPlayer) {
    }

    @Override
    public void onResult(int time, boolean isWin, List<Poker> pokers) {
    }

    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
        if (isSameColor(pokers)) {
            return 3*moneyYouNeedToPayLeast;
        }
        if (isStraight(pokers)) {
            return (int) (1.5 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (1.2* moneyYouNeedToPayLeast) : moneyYouNeedToPayLeast;
        }
        if (isPair(pokers)) {
            return (time > 1500 ? 3*moneyYouNeedToPayLeast : moneyYouNeedToPayLeast);
        }
        if (isSameColorStraight(pokers)) {
            return 3 * moneyYouNeedToPayLeast;
        }
        if (isSamePoint(pokers)) {
            return 3 * moneyYouNeedToPayLeast;
        }
        if (time > 2000 && isJQKA(pokers)){
            return 2*moneyYouNeedToPayLeast;
        }
        return 0;

    }


    private boolean isSameColor(List<Poker>pokers) {
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
    private boolean isJQKA(List<Poker>handCards){
        return handCards.get(0).getPoint() == Poker.Point.A
                || handCards.get(0).getPoint() == Poker.Point.Q
                || handCards.get(0).getPoint() == Poker.Point.K
                || handCards.get(0).getPoint() == Poker.Point.J
                || handCards.get(1).getPoint() == Poker.Point.A
                || handCards.get(1).getPoint() == Poker.Point.Q
                || handCards.get(1).getPoint() == Poker.Point.K
                || handCards.get(1).getPoint() == Poker.Point.J;
    }

}