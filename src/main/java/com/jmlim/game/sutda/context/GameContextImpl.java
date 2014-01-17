package com.jmlim.game.sutda.context;

import java.util.List;

import com.jmlim.game.sutda.model.Player;
import com.jmlim.game.sutda.model.Room;
import com.jmlim.game.sutda.option.CardType;
import com.jmlim.game.sutda.option.GameStatus;

public class GameContextImpl implements GameContext {

	private Room room;

	private Player currentPlayer;

	private Player winnerPlayer;

	private CardType winnerCardType;

	private int defaultStakes = 100;

	private GameStatus status;

	private int currentSeq = 0;

	private int callStakes;

	private int round;

	/**
	 * @see com.jmlim.game.sutda.context.GameContext#setRoom(com.jmlim.game.sutda.model.Room)
	 */
	@Override
	public void setRoom(Room room) {
		this.room = room;
	}

	/**
	 * @see com.jmlim.game.sutda.context.GameContext#getRoom()
	 */
	@Override
	public Room getRoom() {
		return room;
	}

	/**
	 * @see com.jmlim.game.sutda.context.GameContext#getCurrentPlayer()
	 */
	@Override
	public Player getCurrentPlayer() {
		if (currentPlayer == null) {
			List<Player> players = room.getPlayers();
			currentPlayer = players.get(currentSeq);
		}

		return currentPlayer;
	}

	/**
	 * @see com.jmlim.game.sutda.context.GameContext#getCurrentSeq()
	 */
	@Override
	public int getCurrentSeq() {
		return currentSeq;
	}

	/**
	 * @see com.jmlim.game.sutda.context.GameContext#getRound()
	 */
	@Override
	public int getRound() {
		return round;
	}

	/**
	 * @see com.jmlim.game.sutda.context.GameContext#setDefaultStakes(int)
	 */
	@Override
	public void setDefaultStakes(int money) {
		this.defaultStakes = money;
	}

	/**
	 * @see com.jmlim.game.sutda.context.GameContext#getDefaultStakes()
	 */
	@Override
	public int getDefaultStakes() {
		return defaultStakes;
	}

	/**
	 * @see com.jmlim.game.sutda.context.GameContext#setCallStakes(int)
	 */
	@Override
	public void setCallStakes(int money) {
		this.callStakes = money;
	}

	/**
	 * @see com.jmlim.game.sutda.context.GameContext#getCallStakes()
	 */
	@Override
	public int getCallStakes() {
		return callStakes;
	}

	/**
	 * @see com.jmlim.game.sutda.context.GameContext#changeGameStatus(com.jmlim.game.sutda.option.GameStatus)
	 */
	@Override
	public void changeGameStatus(GameStatus status) {
		this.status = status;
	}

	/**
	 * @see com.jmlim.game.sutda.context.GameContext#getGameStatus()
	 */
	@Override
	public GameStatus getGameStatus() {
		return status;
	}

	/**
	 * @see com.jmlim.game.sutda.context.GameContext#next()
	 */
	@Override
	public Player next() {
		if (room == null) {
			return null;
		}

		List<Player> players = room.getPlayers();
		if (currentSeq < players.size() - 1) {
			currentSeq++;
		} else {
			round++;
			currentSeq = 0;
		}

		currentPlayer = players.get(currentSeq);
		if (currentPlayer.isDie()) {
			next();
		}

		return currentPlayer;
	}

	/**
	 * @see com.jmlim.game.sutda.context.GameContext#setWinnerPlayer(com.jmlim.game.sutda.model.Player)
	 */
	@Override
	public void setWinnerPlayer(Player player) {
		winnerPlayer = player;
	}

	/**
	 * @see com.jmlim.game.sutda.context.GameContext#getWinnerPlayer()
	 */
	@Override
	public Player getWinnerPlayer() {
		return winnerPlayer;
	}

	/**
	 * @see com.jmlim.game.sutda.context.GameContext#setWinnerCardType(com.jmlim.game.sutda.option.CardType)
	 */
	@Override
	public void setWinnerCardType(CardType type) {
		this.winnerCardType = type;
	}

	/**
	 * @see com.jmlim.game.sutda.context.GameContext#getWinnerCardType()
	 */
	@Override
	public CardType getWinnerCardType() {
		return winnerCardType;
	}
}
