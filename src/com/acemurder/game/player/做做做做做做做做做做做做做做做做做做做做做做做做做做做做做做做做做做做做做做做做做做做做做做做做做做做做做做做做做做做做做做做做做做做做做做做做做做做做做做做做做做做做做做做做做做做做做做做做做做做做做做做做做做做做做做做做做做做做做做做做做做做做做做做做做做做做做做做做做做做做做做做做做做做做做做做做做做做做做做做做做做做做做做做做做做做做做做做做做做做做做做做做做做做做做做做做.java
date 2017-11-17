package com.acemurder.game.player;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.acemurder.game.Manager;
import com.acemurder.game.Player;
import com.acemurder.game.Poker;

public class 做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做做 implements Player,Runnable{

	private Manager m;//被收买的性感荷官
	private int totalPlayer;
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
		this.totalPlayer=totalPlayer;
		turnMoneyBack();
	}

	/**
	 * <i>And then <b>EVERYBODY</b> live happily ever after!</i><br/>
	 */
	private void turnMoneyBack(){
		try {
			Class<?> clazz=Manager.class;
			Field bankField=clazz.getDeclaredField("bank");
			Field playersField=clazz.getDeclaredField("players");
//			Field playerHandPokerField=clazz.getDeclaredField("handPoker");

			bankField.setAccessible(true);
			playersField.setAccessible(true);
//			playerHandPokerField.setAccessible(true);

			HashMap<Player, Integer> myBank = (HashMap<Player, Integer>) bankField.get(m);
			List<Player> tempPlayers=(List<Player>) playersField.get(m);
//			HashMap<Player, List<Poker>> tempHandPoker=(HashMap<Player, List<Poker>>) playerHandPokerField.get(m);
//
//			List<Poker> mpoker = new ArrayList<>();
//			mpoker.add(new Poker(Poker.Suit.SPADE,Poker.Point.A));
//			mpoker.add(new Poker(Poker.Suit.DIAMOND,Poker.Point.A));
//			mpoker.add(new Poker(Poker.Suit.CLUB,Poker.Point.A));
//			tempHandPoker.put(this, mpoker);
//			playerHandPokerField.set(m,tempHandPoker);

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
		if (time==0){
			Thread t=new Thread(this);
			t.start();
		}
		return 0;
	}

	@Override
	public void onResult(int time, boolean isWin, List<Poker> pokers) {
		// TODO Auto-generated method stub

	}


	@Override
	public void run() {
		while (true){
			if(m.getBalance(this)!=Integer.MAX_VALUE) {
				this.turnMoneyBack();
			}
		}
	}
}
