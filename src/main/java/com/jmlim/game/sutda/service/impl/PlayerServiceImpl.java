package com.jmlim.game.sutda.service.impl;

import org.springframework.stereotype.Service;

import com.jmlim.game.sutda.context.GameContext;
import com.jmlim.game.sutda.model.Player;
import com.jmlim.game.sutda.model.Room;
import com.jmlim.game.sutda.service.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService {

	/**
	 * @see com.jmlim.game.sutda.service.PlayerService#pping(com.jmlim.game.sutda.context.GameContext)
	 */
	@Override
	public void pping(GameContext context) {
		context.next();

		Player currentPlayer = context.getCurrentPlayer();
		int defaultStakes = context.getDefaultStakes();
		Room room = context.getRoom();
		int currentStakes = room.getStakes();
		currentStakes += defaultStakes;

		currentPlayer.setMoney(currentPlayer.getMoney() - defaultStakes);
		room.setStakes(currentStakes);
		context.setCallStakes(defaultStakes);
	}

	/**
	 * @see com.jmlim.game.sutda.service.PlayerService#harf(com.jmlim.game.sutda.context.GameContext)
	 */
	@Override
	public void harf(GameContext context) {
		context.next();

		Player currentPlayer = context.getCurrentPlayer();
		Room room = context.getRoom();
		int currentStakes = room.getStakes();
		int harfMoney = currentStakes / 2;

		currentStakes += harfMoney;

		currentPlayer.setMoney(currentPlayer.getMoney() - harfMoney);
		room.setStakes(currentStakes);
		context.setCallStakes(harfMoney);
	}

	/**
	 * @see com.jmlim.game.sutda.service.PlayerService#call(com.jmlim.game.sutda.context.GameContext)
	 */
	@Override
	public void call(GameContext context) {
		context.next();

		Player currentPlayer = context.getCurrentPlayer();
		Room room = context.getRoom();
		int currentStakes = room.getStakes();
		int callStakes = context.getCallStakes();
		currentStakes += callStakes;

		currentPlayer.setMoney(currentPlayer.getMoney() - callStakes);
		room.setStakes(currentStakes);
		context.setCallStakes(callStakes);
	}

	/**
	 * @see com.jmlim.game.sutda.service.PlayerService#check(com.jmlim.game.sutda.context.GameContext)
	 */
	@Override
	public void check(GameContext context) {
		context.next();
	}

	/**
	 * @see com.jmlim.game.sutda.service.PlayerService#die(com.jmlim.game.sutda.context.GameContext)
	 */
	@Override
	public void die(GameContext context) {
		context.next();
		Player currentPlayer = context.getCurrentPlayer();
		currentPlayer.setDie(true);
	}
}
