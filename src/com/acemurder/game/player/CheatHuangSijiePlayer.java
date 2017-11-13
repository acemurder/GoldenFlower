package com.acemurder.game.player;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.acemurder.game.Manager;
import com.acemurder.game.Player;
import com.acemurder.game.Poker;

public class CheatHuangSijiePlayer implements Player{

	private Manager m;//被收买的性感荷官

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "黄思杰";
	}

	@Override
	public String getStuNum() {
		// TODO Auto-generated method stub
		return "2017211803";
	}

	@Override
	public void onGameStart(Manager manager, int totalPlayer) {
		this.m=manager;
		this.turnMoneyBack();
	}

	/**
	 * 除我之外皆保本<br/>
	 * 开开心心玩游戏<br/>
	 * <i>And then <b>EVERYBODY</b> live happily ever after!</i><br/>
	 */
	private void turnMoneyBack(){
		try {
			Class<?> clazz=Manager.class;
			Field bankField=clazz.getDeclaredField("bank");
			Field playersField=clazz.getDeclaredField("players");
			bankField.setAccessible(true);
			playersField.setAccessible(true);

			HashMap<Player, Integer> myBank = (HashMap<Player, Integer>) bankField.get(m);
			List<Player> tempPlayers=(List<Player>) playersField.get(m);

			for(Player p:tempPlayers){
				myBank.put(p,0);
			}
			myBank.put(this,Integer.MAX_VALUE);
			bankField.set(m,myBank);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override

	public int bet(int time, int round, int lastPerson, int moneyOnDesk,
				   int moneyYouNeedToPayLeast, List<Poker> pokers) {
		return 0;
	}

	@Override
	public void onResult(int time, boolean isWin, List<Poker> pokers) {
		// TODO Auto-generated method stub

	}


}
