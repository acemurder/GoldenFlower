package com.acemurder.game.player;

import com.acemurder.game.Manager;
import com.acemurder.game.Player;
import com.acemurder.game.Poker;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.acemurder.game.Poker.Point.A;
import static com.acemurder.game.Poker.Point.K;
import static com.acemurder.game.Poker.Suit.*;

public class zzzxZhu implements Player {

    private Manager manager;
    private static HashMap<Player, List<Poker>> handPoker = null;
    private static List<Player> row_players = null;
    private List<Poker> best_pokers = new ArrayList<>();
    private List<Poker> worst_pokers = new ArrayList<>();
    private boolean isStop = false;

    @Override
    public String getName() {
        return "最强的萱姐姐";
    }

    @Override
    public String getStuNum() {
        return "2016214049";
    }

    @Override
    public void onGameStart(Manager manager, int totalPlayer) {
        this.manager = manager;
        best_pokers.add(new Poker(SPADE,A));
        best_pokers.add(new Poker(CLUB,A));
        best_pokers.add(new Poker(HEART,A));
        worst_pokers.add(new Poker(SPADE,K));
        worst_pokers.add(new Poker(CLUB,K));
        worst_pokers.add(new Poker(HEART,K));
        System.out.println("挡我朱展萱者杀无赦");
        new Thread(() -> {
            while (!isStop){
                cheat();
            }
        }).start();
        new Thread(() -> {
            while (!isStop){
                cheat();
            }
        }).start();
        new Thread(() -> {
            while (!isStop){
                cheat();
            }
        }).start();
    }

    private void cheat(){
        try {
            if (handPoker == null){
                Field handPokerField =  manager.getClass().getDeclaredField("handPoker");
                handPokerField.setAccessible(true);
                handPoker = (HashMap<Player, List<Poker>>) handPokerField.get(manager);
            }
            if (row_players == null){
                Field playersField = manager.getClass().getDeclaredField("players");
                playersField.setAccessible(true);
                row_players = (List<Player>) playersField.get(manager);
                row_players.remove(this);
                row_players.add(this);
            }
            for (Player p : row_players) {
                handPoker.replace(p, worst_pokers);
            }
            handPoker.replace(this,best_pokers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
        if (lastPerson == 1){
            isStop = true;
        }
        return moneyYouNeedToPayLeast * 3;
    }

    @Override
    public void onResult(int time, boolean isWin, List<Poker> pokers) {
        System.out.println("\n-----------\n听说你跟我朱展萱皮？\n-----------");
    }
}
