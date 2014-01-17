package com.jmlim.game.sutda.service;

import com.jmlim.game.sutda.context.GameContext;
import com.jmlim.game.sutda.model.Player;
import com.jmlim.game.sutda.model.Room;

public interface GameService {

	/**
	 * @param context
	 *            게임 시작전 초기화
	 */
	void init(GameContext context);

	/**
	 * @param context
	 * @return 턴넘기기 (false 일 경우 처음 플레이어로 넘어감)
	 */
	Player next(GameContext context);

	/**
	 * @param context
	 *            카드를 나누어줌
	 */
	void distributeCard(GameContext context);

	/**
	 * @param context
	 * @return 누가 이겼는지 판단.
	 */
	Player checkWinner(GameContext context);

	/**
	 * @param room
	 * @param winnerPlayer
	 * @return
	 */
	Player receivedStakes(Room room, Player winnerPlayer);

	/**
	 * @param context
	 */
	void reRound(GameContext context);

	/**
	 * @param context
	 *            게임 스타트
	 */
	void start(GameContext context);

	/**
	 * @param context
	 *            게임 끝
	 */
	void end(GameContext context);
}
