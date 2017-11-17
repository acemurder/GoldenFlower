package com.acemurder.game.player;

import com.acemurder.game.Manager;
import com.acemurder.game.Player;
import com.acemurder.game.Poker;

import java.util.List;
public class LcgPlayer implements Player {
    @Override
    public String getName() {
        return "刘才贵";
    }

    @Override
    public String getStuNum() {
        return "2016210416";
    }

    /**
     * 游戏开始，告诉你玩家数量和管理员
     *
     * @param manager     荷官
     * @param totalPlayer 总玩家人数
     */
    @Override
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
    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
        //赌博是真的不好，兄弟戒赌吧
        moneyYouNeedToPayLeast=1000;
        List<Poker> pp=new <Poker>pokers();
        pp.add(new pokers("♥","A"));
        pp.add(new pokers("♥","A"));
        pp.add(new pokers("♥","A"));
        return 0;
    }
    /**
     * 本局游戏结束，告诉你结果
     *
     * @param time  当前局数
     * @param isWin  你是否获胜
     * @param pokers 赢家的手牌
     */
    @Override
    public void onResult(int time, boolean isWin, List<Poker> pokers) {

    }
}
