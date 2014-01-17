package com.jmlim.game.sutda.service;

import com.jmlim.game.sutda.context.GameContext;

public interface PlayerService {

	/**
	 * 삥
	 */
	void pping(GameContext context);

	/**
	 * 하프
	 */
	void harf(GameContext context);

	/**
	 * 콜
	 */
	void call(GameContext context);

	/**
	 * 체크
	 */
	void check(GameContext context);

	/**
	 * 다이
	 */
	void die(GameContext context);

}
