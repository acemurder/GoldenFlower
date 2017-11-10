package com.acemurder.game;

import java.util.List;

/**
 * Created by : AceMurder
 * Created on : 2017/11/6
 * Created for : Games.
 * Enjoy it !!!!
 */
public interface Player {

    /**
     * 实现这个方法，返回你的姓名，eg:王尼
     * @return
     */
    String getName();

    /**
     * 实现这个方法，返回你的学号 eg：2015211876
     * @return
     */
    String getStuNum();

    default String getInformation() {
        return getName() + getStuNum();
    }

    /**
     * 游戏开始，告诉你玩家数量和管理员
     *
     * @param manager     荷官
     * @param totalPlayer 总玩家人数
     */
    void onGameStart(Manager manager, int totalPlayer);

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
    int bet(final int time, final int round, final int lastPerson, final int moneyOnDesk,
            final int moneyYouNeedToPayLeast,
            List<Poker> pokers);

    /**
     * 本局游戏结束，告诉你结果
     *
     * @param time  当前局数
     * @param isWin  你是否获胜
     * @param pokers 赢家的手牌
     */
    void onResult(int time, boolean isWin, List<Poker> pokers);


}
