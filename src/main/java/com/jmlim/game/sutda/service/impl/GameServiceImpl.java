package com.jmlim.game.sutda.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.jmlim.game.sutda.context.GameContext;
import com.jmlim.game.sutda.model.Card;
import com.jmlim.game.sutda.model.Player;
import com.jmlim.game.sutda.model.Room;
import com.jmlim.game.sutda.option.CardType;
import com.jmlim.game.sutda.option.GameStatus;
import com.jmlim.game.sutda.service.GameService;
import com.jmlim.game.sutda.utils.CardUtils;

@Service
public class GameServiceImpl implements GameService {

	/**
	 * @see com.jmlim.game.sutda.service.GameService#init(com.jmlim.game.sutda.context.GameContext)
	 */
	@Override
	public void init(GameContext context) {

	}

	/**
	 * @see com.jmlim.game.sutda.service.GameService#next(com.jmlim.game.sutda.context.GameContext)
	 */
	@Override
	public Player next(GameContext context) {
		return context.next();
	}

	/**
	 * @see com.jmlim.game.sutda.service.GameService#distributeCard(com.jmlim.game.sutda.context.GameContext)
	 */
	@Override
	public void distributeCard(GameContext context) {
		Room room = context.getRoom();
		List<Player> players = room.getPlayers();
		List<Card> cards = room.getCards();

		for (Player player : players) {
			Random ran = new Random();
			int giveCardPosition = ran.nextInt(cards.size());
			if (!player.isDie()) {
				player.addCard(cards.remove(giveCardPosition));
			}
		}
	}

	/**
	 * @see com.jmlim.game.sutda.service.GameService#checkWinner(com.jmlim.game.sutda.context.GameContext)
	 */
	@Override
	public Player checkWinner(GameContext context) {
		Room room = context.getRoom();
		List<Player> players = room.getPlayers();
		List<CardType> types = new ArrayList<>(GameContext.MAX_PLAYER_NUM);

		CardType cardType = CardType.None;
		for (Player player : players) {
			types.add(CardUtils.returnCardType(player.getCards()));
		}

		if (types.contains(CardType.FourNine)) {
			boolean regame = true;
			for (CardType type : types) {
				if (type.getLevel() > CardType.Alri.getLevel()) {
					regame = false;
					break;
				}
			}

			if (regame) {
				context.setWinnerCardType(CardType.FourNine);
				context.changeGameStatus(GameStatus.REROUND);
				return null;
			}
		}

		if (types.contains(CardType.StupidFourNine)) {
			context.setWinnerCardType(CardType.StupidFourNine);
			context.changeGameStatus(GameStatus.REROUND);
			return null;
		}

		int hasDdangHunterPlayerIndex = types.indexOf(CardType.DdangHunter);
		if (hasDdangHunterPlayerIndex != -1) {
			boolean successHunting = false;
			for (CardType type : types) {
				if (type.getLevel() > CardType.Alri.getLevel()) {
					successHunting = true;
					break;
				}
			}
			if (successHunting) {
				context.setWinnerCardType(CardType.DdangHunter);
				return players.get(hasDdangHunterPlayerIndex);
			}
		}

		int winPlayerIndex = 0;
		int index = 0;
		for (CardType type : types) {
			if (cardType.getLevel() < type.getLevel()) {
				winPlayerIndex = index;
				cardType = type;
			}

			index++;
		}

		context.setWinnerCardType(cardType);
		return players.get(winPlayerIndex);
	}

	/**
	 * @see com.jmlim.game.sutda.service.GameService#reRound(com.jmlim.game.sutda.context.GameContext)
	 */
	@Override
	public void reRound(GameContext context) {
		if (context.getGameStatus() == GameStatus.REROUND) {
			List<Player> players = context.getRoom().getPlayers();
			for (Player player : players) {
				player.clearCards();
			}

			distributeCard(context);
			context.changeGameStatus(GameStatus.PLAYING);
		}
	}

	/**
	 * @see com.jmlim.game.sutda.service.GameService#start(com.jmlim.game.sutda.context.GameContext)
	 */
	@Override
	public void start(GameContext context) {
		Room room = context.getRoom();
		List<Player> players = new ArrayList<>(GameContext.MAX_PLAYER_NUM);
		List<Card> cards = new ArrayList<>(20);

		for (int i = 0; i < GameContext.MAX_PLAYER_NUM; i++) {
			Player player = new Player();
			player.setMoney(10000);
			player.setName("정묵" + i);
			player.setSeq(i);
			players.add(player);
		}

		room.setPlayers(players);

		for (int cardNum = 0; cardNum <= 19; cardNum++) {
			Card card = new Card(cardNum, cardNum >= 10 ? true : false,
					(cardNum == 13 && cardNum == 18) ? true : false);
			cards.add(card);
		}

		room.setCards(cards);
	}

	/**
	 * @see com.jmlim.game.sutda.service.GameService#receivedStakes(com.jmlim.game.sutda.model.Room,
	 *      com.jmlim.game.sutda.model.Player)
	 */
	@Override
	public Player receivedStakes(Room room, Player winnerPlayer) {
		int stakes = room.getStakes();
		room.setStakes(100);
		winnerPlayer.setMoney(winnerPlayer.getMoney() + stakes);
		return winnerPlayer;
	}

	/**
	 * @see com.jmlim.game.sutda.service.GameService#end(com.jmlim.game.sutda.context.GameContext)
	 */
	@Override
	public void end(GameContext context) {

	}
}