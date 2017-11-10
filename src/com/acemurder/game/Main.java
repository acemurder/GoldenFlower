package com.acemurder.game;


import java.io.File;
import java.util.*;

/**
 * Created by : AceMurder
 * Created on : 2017/11/6
 * Created for : Games.
 * Enjoy it !!!!
 */
public class Main {
    private static final String FILE_PATH = "src/com/acemurder/game/player/";
    private static final String PACKEGE_NAME_PREFIX = "com.acemurder.game.player.";


    public static void main(String[] args) {
        File file = new File(FILE_PATH);
        File[] files = file.listFiles();
        if (files == null)
            return;
        List<Player> players = new ArrayList<>();
        for (File f : files) {
            String className = f.getName().replace(".java", "");
            try {
                Class<? extends Player> clazz = (Class<? extends Player>) Class.forName(PACKEGE_NAME_PREFIX + className);
                Player p = clazz.newInstance();
                players.add(p);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Manager manager = new Manager(players);
        manager.startGame(Manager.INIT_MONEY / Manager.MIN_MONEY_PER_TIME * 3  / 2);
        HashMap<Player, Integer> bank = manager.getBank();
        List<Map.Entry<Player, Integer>> scores = new ArrayList<>(bank.entrySet());
        scores.sort(Comparator.comparingInt(Map.Entry::getValue));
        System.out.println("游戏结束：");
        scores.forEach(playerIntegerEntry -> {
            System.out.println(" "+playerIntegerEntry.getKey().getInformation() +
                    " : " + playerIntegerEntry.getValue() +
                    " 剩余筹码, 挣扎了" + (manager.getPlayTime().get(playerIntegerEntry.getKey())) + "局");
        });
    }

}
