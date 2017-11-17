package com.acemurder.game.player;

import com.acemurder.game.Manager;
import com.acemurder.game.Player;
import com.acemurder.game.Poker;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


/**
 * Created by : Fxymine4ever
 */
public class Fxymine4ever implements Player {

    private static final Integer MAXMONEY = 999999999;

    @Override
    public void onGameStart(Manager manager, int totalPlayer) throws ClassNotFoundException {
        Class<?> clazz =Manager.class;
        try {
            Field playersField = manager.getClass().getDeclaredField("players");
            Field bankField = manager.getClass().getDeclaredField("bank");
            bankField.setAccessible(true);
            playersField.setAccessible(true);
            //解开封装

            HashMap<Player,Integer>afterbank = (HashMap<Player, Integer>)bankField.get(manager);//获得manager类 转换 得到我的银行
            List<Player> playersCheat = (List<Player>)playersField.get(manager);//获取manager类 转换 得到player类

            bankField.set(manager,afterbank);//设置我的银行

            for (Player player : playersCheat) {
                afterbank.put(player, -100);
            }//你们都去欠债吧

            afterbank.put(this,MAXMONEY);//我最有钱

        } catch (NoSuchFieldException e) {

            e.printStackTrace();

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }

    /*  同花顺 48/22100
        同花 1096/22100
        顺子 720/22100
        豹子 52/22100*/
    @Override
    public String getName() {
        return "冯新耀";
    }

    @Override
    public String getStuNum() {
        return "2017211851";
    }


    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
        Collections.sort(pokers);//对扑克进行排序
        if (isSameColor(pokers) )//金花
            return ((int) ((2 +(round / 10.0)) * moneyYouNeedToPayLeast) < 3 * moneyOnDesk) ? (int) ((2 +(round / 10.0)) * moneyYouNeedToPayLeast)  : 3 * moneyOnDesk -1;
        if ( (isSameColorStraight(pokers)))//压一样的倍数气死你
            return (int) (1.5 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (1.2 * moneyYouNeedToPayLeast)  : 3 * moneyOnDesk;
        if (isPair(pokers))//压一样的倍数气死你
            return (int) (1.5 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (1.2 * moneyYouNeedToPayLeast) : moneyYouNeedToPayLeast;
        if (isStraight(pokers))//压一样的倍数气死你
            return (int) (1.5 * moneyYouNeedToPayLeast) < 3 * moneyOnDesk ? (int) (1.2 * moneyYouNeedToPayLeast) : moneyYouNeedToPayLeast;
        if (isSamePoint(pokers)){//豹子无脑压2倍
            return  2 * moneyYouNeedToPayLeast;
        }
        for (Poker p : pokers){
            if ( p.getPoint().getNum() >= 12)
                return 0;
        }
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
