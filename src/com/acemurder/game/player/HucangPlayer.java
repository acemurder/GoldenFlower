package com.acemurder.game.player;

import com.acemurder.game.Manager;
import com.acemurder.game.Player;
import com.acemurder.game.Poker;

import java.util.List;

public class HucangPlayer implements Player {

    public String getName() {
        return "胡仓";
    }

    public String getStuNum() {
        return "2017211573";
    }

    public void onGameStart(Manager manager, int totalPlayer) {

    }
    /**
     * 下注
     *
     * @param time                   第几局
     * @param round                  每局游戏会有多轮，当前轮数，最多会有5轮
     * @param lastPerson             还没有弃牌的玩家数
     * @param moneyOnDesk            桌上的筹码数量
     * @param moneyYouNeedToPayLeast 你本次最小需要下注的数量，
     * @param pokers                 你的手牌，三张
     * @return 你的下注数量，小于这个最小下注数量或者大于最小下注数量的三倍，都会被当作弃牌处理。
     */
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
        if(isEqual(pokers)!=-1){
            return (int)(moneyYouNeedToPayLeast*isEqual(pokers));
        }
        if(isSame(pokers)!=-1){
            return (int)(moneyYouNeedToPayLeast*isEqual(pokers));
        }
        return 0;
    }

    public void onResult(int time, boolean isWin, List<Poker> pokers) {

    }

    public double isEqual(List<Poker> pokers){
        if(pokers.get(0).getSuit()==pokers.get(1).getSuit()&&pokers.get(2).getSuit()==pokers.get(1).getSuit()){
            for (Poker p : pokers) {
                if(p.getPoint()== Poker.Point._9||p.getPoint()== Poker.Point._10||p.getPoint()== Poker.Point.J||p.getPoint()== Poker.Point.Q||p.getPoint()== Poker.Point.K||p.getPoint()== Poker.Point.A)
                    return 3;
            }
            return 2.5;
        }
        return -1;
    }

    public double isSame(List<Poker> pokers){
        if(pokers.get(0).getPoint()==pokers.get(1).getPoint()&&pokers.get(2).getPoint()==pokers.get(1).getPoint()){
            return 3;
        }
        for(int i=0;i<3;i++){
            for(int j=i+1;j<3;j++){
                if(pokers.get(i).getPoint()==pokers.get(j).getPoint()){
                    if(pokers.get(i).getPoint()== Poker.Point._9||pokers.get(i).getPoint()== Poker.Point._10||pokers.get(i).getPoint()== Poker.Point.J||pokers.get(i).getPoint()== Poker.Point.Q||pokers.get(i).getPoint()== Poker.Point.K||pokers.get(i).getPoint()== Poker.Point.A)
                        return 1.5;
                    else
                        return 1;
                }
            }
        }
        return -1;
    }

}
