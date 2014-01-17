package com.jmlim.game.sutda.context;

import com.jmlim.game.sutda.model.Player;
import com.jmlim.game.sutda.model.Room;
import com.jmlim.game.sutda.option.CardType;
import com.jmlim.game.sutda.option.GameStatus;

public interface GameContext {

	int MAX_PLAYER_NUM = 4;

	void setWinnerPlayer(Player player);

	Player getWinnerPlayer();
	
	void setWinnerCardType(CardType type);

	CardType getWinnerCardType();
	
	void setRoom(Room room);

	Room getRoom();

	Player getCurrentPlayer();

	void setDefaultStakes(int money);

	int getDefaultStakes();

	void setCallStakes(int money);

	int getCallStakes();

	int getCurrentSeq();

	int getRound();

	void changeGameStatus(GameStatus status);

	GameStatus getGameStatus();

	Player next();
}
