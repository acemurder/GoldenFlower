package com.acemurder.game;

import java.util.*;

/**
 * Created by : AceMurder
 * Created on : 2017/11/6
 * Created for : Games.
 * Enjoy it !!!!
 */
public class Manager {

    public static final int MIN_MONEY_PER_TIME = 100;
    public static final int INIT_MONEY = 200000;

    private static final int SAME_POINT = 60 * 100000;
    private static final int STRAIGHT = 20 * 100000;
    private static final int SAME_COLOR = 30 * 100000;
    private static final int SAME_COLOR_STRAIGHT = STRAIGHT + SAME_COLOR;
    private static final int PAIR = 10 * 100000;

    //以上你们都不用管

    private final List<Player> players;
    private HashMap<Player, Integer> bank = new HashMap<>();
    private HashMap<Player, List<Poker>> handPoker = new HashMap<>();
    private HashMap<Player, Integer> playTimeMap = new HashMap<>();
    private int totalTime;


    public Manager(List<Player> players) {
        this.players = players;
        for (Player player : players) {
            bank.put(player, INIT_MONEY); //设置每人初始筹码
        }
    }


    /**
     * 游戏开始
     *
     * @param totalTime 总局数
     */
    void startGame(int totalTime) {
        this.totalTime = totalTime;
        for (Player player : players) {
            try {
                playTimeMap.put(player, totalTime);
                player.onGameStart(this, players.size());
            } catch (Exception e) {
                //总有刁民想害朕
            }
        }
        for (int i = 0; i < totalTime; i++) {
            play(i);
        }
    }

    /**
     * 每局游戏开始
     *
     * @param time 当前局数
     */
    private void play(int time) {
        int moneyOnDesk = 0;
        int minMoneyNeedToPay = MIN_MONEY_PER_TIME;
        //每局游戏没人底注 minMoneyNeedToPay = 100
        List<Player> tempPlayers = new LinkedList<>();
        List<Player> tempPlayersHold = new ArrayList<>();

        for (Player p : players) {
            if (bank.get(p) >= minMoneyNeedToPay) {
                bank.put(p, bank.get(p) - minMoneyNeedToPay);
                tempPlayers.add(p);
                moneyOnDesk += minMoneyNeedToPay;
            } else{
                if (playTimeMap.get(p) == totalTime)
                    playTimeMap.put(p, time + 1);
            }
        }
        tempPlayersHold.addAll(tempPlayers);
        if (tempPlayers.size() == 1) {
            bank.put(tempPlayers.get(0), bank.get(tempPlayers.get(0)) + moneyOnDesk);
            return;
        }

        List<Poker> pokers = createPokers(tempPlayers.size()); //总有人想搞事情,每局我都要换新牌
        Collections.shuffle(pokers); //洗牌
        handPoker.clear();
        for (int i = 0; i < tempPlayers.size(); i++) {
            List<Poker> singleHandPoker = new ArrayList<>();
            singleHandPoker.add(pokers.get(3 * i));
            singleHandPoker.add(pokers.get(3 * i + 1));
            singleHandPoker.add(pokers.get(3 * i + 2));
            handPoker.put(tempPlayers.get(i), singleHandPoker);
        }


        Collections.shuffle(tempPlayers);

        int round = 0;
        System.out.println("第" + time + "局游戏开始：游戏人数：" + tempPlayers.size());
        while (tempPlayers.size() > 1 && round < 5 && moneyOnDesk < tempPlayersHold.size() * 5000) {
            System.out.println("   第" + round + "轮开始：");
            Iterator<Player> iterator = tempPlayers.iterator();
            while (iterator.hasNext()) {
                try {
                    Player p = iterator.next();
                    List<Poker> t = new ArrayList<>();
                    t.addAll(handPoker.get(p));
                    int money = p.bet(time, round, tempPlayers.size(), moneyOnDesk, minMoneyNeedToPay, t);
                    if (money > bank.get(p))
                        money = bank.get(p);
                    if (money < minMoneyNeedToPay || money > 3 * minMoneyNeedToPay) {
                        //下注数小于最小下注数 或者 超过最小下注数的三倍都是无效下注，放弃本局游戏
                        iterator.remove();
                        System.out.println("     玩家 " + p.getInformation() + " 弃牌");
                    } else {
                        bank.put(p, bank.get(p) - money);
                        System.out.println("     玩家 " + p.getInformation() + " 下注" + money);
                        moneyOnDesk += money;
                        minMoneyNeedToPay = money;
                    }
                    if (tempPlayers.size() == 1)
                        break;
                } catch (Exception e) {
                    //总有刁民想害朕
                    e.printStackTrace();
                }
            }
            round++;
        }
        int maxPoint = 0;
        Player winner = null;
        for (Player p : tempPlayers) {
            int point = judge(handPoker.get(p));
            if (point >= maxPoint) {
                winner = p;
                maxPoint = point;
            }
        }
        System.out.println(" 本局游戏结束:");
        for (Player p : tempPlayersHold) {
            if (p == winner) {
                bank.put(winner, bank.get(winner) + moneyOnDesk);
                List<Poker> t = new ArrayList<>();
                t.addAll(handPoker.get(winner));
                try {
                    p.onResult(time, true, t);
                } catch (Exception e) {
                    //刁民
                }
            } else {
                List<Poker> t = new ArrayList<>();
                t.addAll(handPoker.get(winner));
                try {
                    p.onResult(time, false, t);
                } catch (Exception e) {
                    //总有人想搞事情
                }
            }

            System.out.println("  " + p.getInformation() + " 的手牌是" +
                    handPoker.get(p).get(0) + " "
                    + handPoker.get(p).get(1) + " " +
                    handPoker.get(p).get(2) + "   余额：" + bank.get(p));

        }
        System.out.println(" " + winner.getInformation() + "获得了" + moneyOnDesk +
                "筹码，手牌是" + handPoker.get(winner).get(0) + " "
                + handPoker.get(winner).get(1) + " " + handPoker.get(winner).get(2));
        System.out.println();
    }


    /**
     * 比牌
     *
     * @param handCards
     * @return 根据牌型以及牌面返回一个int
     */
    private int judge(List<Poker> handCards) {
        Collections.sort(handCards);
        int value = 0;
        if (isPair(handCards)) {
            if (handCards.get(0).getPoint().num == handCards.get(1).getPoint().num)
                value += handCards.get(0).getPoint().num * 10000
                        + handCards.get(0).getPoint().num * 100
                        + handCards.get(2).getPoint().num;
            else
                value += handCards.get(1).getPoint().num * 10000
                        + handCards.get(1).getPoint().num * 100
                        + handCards.get(0).getPoint().num;

        } else {
            value += handCards.get(0).getPoint().num * 10000
                    + handCards.get(1).getPoint().num * 100
                    + handCards.get(2).getPoint().num;
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


    /**
     * 生成牌，当 3 * 玩家数 > 52 时使用两幅牌，> 104时使用三副牌，以此类推
     *
     * @return 一副或者多副牌
     */
    private List<Poker> createPokers(int playerCount) {
        List<Poker> pokers = createSinglePokers();
        for (int i = 0; i < playerCount * 3 / 52; i++) {
            pokers.addAll(createSinglePokers());
        }
        return pokers;
    }

    /**
     * 生词单副牌
     *
     * @return 一副牌
     */
    private List<Poker> createSinglePokers() {
        Poker.Point[] points = {Poker.Point._2, Poker.Point._3,
                Poker.Point._4, Poker.Point._5, Poker.Point._6, Poker.Point._7, Poker.Point._8,
                Poker.Point._9, Poker.Point._10, Poker.Point.J, Poker.Point.Q, Poker.Point.K, Poker.Point.A};
        Poker.Suit[] suits = {Poker.Suit.SPADE, Poker.Suit.CLUB, Poker.Suit.DIAMOND, Poker.Suit.HEART};

        List<Poker> pokers = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 4; j++) {
                Poker p = new Poker(suits[j], points[i]);
                pokers.add(p);
            }
        }
        return pokers;
    }

    public int getBalance(Player player) {
        return bank.get(player) == null ? 0 : bank.get(player);
    }

    HashMap<Player, Integer> getBank() {
        return bank;
    }

    HashMap<Player, Integer> getPlayTime() {
        return playTimeMap;
    }
}
