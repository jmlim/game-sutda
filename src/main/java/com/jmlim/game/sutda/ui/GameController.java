package com.jmlim.game.sutda.ui;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jmlim.game.sutda.context.GameContext;
import com.jmlim.game.sutda.context.GameContextImpl;
import com.jmlim.game.sutda.model.Player;
import com.jmlim.game.sutda.model.Room;
import com.jmlim.game.sutda.option.Batting;
import com.jmlim.game.sutda.option.GameStatus;
import com.jmlim.game.sutda.service.GameService;
import com.jmlim.game.sutda.service.PlayerService;

@Controller
public class GameController {

	@Autowired
	private GameService gameService;

	@Autowired
	private PlayerService playerService;

	@RequestMapping(value = "{roomName}/game-start", method = RequestMethod.POST)
	public @ResponseBody
	GameContext gameStart(HttpSession session, @PathVariable String roomName) {
		GameContext context = new GameContextImpl();

		Room room = new Room();
		context.setRoom(room);

		gameService.start(context);
		gameService.distributeCard(context);
		context.changeGameStatus(GameStatus.START);

		session.setAttribute(roomName, context);
		return context;
	}

	@RequestMapping(value = "{roomName}/batting/{batting}", method = RequestMethod.POST)
	public @ResponseBody
	GameContext batting(HttpSession session, @PathVariable String roomName,
			@PathVariable String batting) {
		GameContext context = (GameContext) session.getAttribute(roomName);

		if (context.getGameStatus() != GameStatus.PLAYING) {
			context.changeGameStatus(GameStatus.PLAYING);
		}

		if (Batting.PPING.getName().equals(batting)) {
			playerService.pping(context);
		} else if (Batting.HARF.getName().equals(batting)) {
			playerService.harf(context);
		} else if (Batting.CALL.getName().equals(batting)) {
			playerService.call(context);
		} else if (Batting.CHECK.getName().equals(batting)) {
			playerService.check(context);
		} else if (Batting.DIE.getName().equals(batting)) {
			playerService.die(context);
		} else {
			System.out.println("exception");
		}

		if (context.getCurrentSeq() == 0
				&& context.getGameStatus() == GameStatus.PLAYING) {
			if (context.getRound() == 1) {
				context.changeGameStatus(GameStatus.DISTRIBUTE);
			}

			if (context.getRound() > 2) {
				context.changeGameStatus(GameStatus.STOP);
			}
		}

		return context;
	}

	@RequestMapping(value = "{roomName}/distribute-card", method = RequestMethod.POST)
	public @ResponseBody
	GameContext distributeCard(HttpSession session,
			@PathVariable String roomName) {
		GameContext context = (GameContext) session.getAttribute(roomName);
		gameService.distributeCard(context);
		return context;
	}

	@RequestMapping(value = "{roomName}/check-winner", method = RequestMethod.POST)
	public @ResponseBody
	GameContext checkWinner(HttpSession session, @PathVariable String roomName) {
		GameContext context = (GameContext) session.getAttribute(roomName);
		context.setWinnerPlayer(gameService.checkWinner(context));
		return context;
	}

	@RequestMapping(value = "{roomName}/received-stakes", method = RequestMethod.POST)
	public @ResponseBody
	GameContext receivedStakes(HttpSession session,
			@PathVariable String roomName) {
		GameContext context = (GameContext) session.getAttribute(roomName);
		Room room = context.getRoom();
		Player winnerPlayer = context.getWinnerPlayer();
		gameService.receivedStakes(room, winnerPlayer);

		return context;
	}

	@RequestMapping(value = "{roomName}/re-round", method = RequestMethod.POST)
	public @ResponseBody
	GameContext reRound(HttpSession session, @PathVariable String roomName) {
		GameContext context = (GameContext) session.getAttribute(roomName);
		gameService.reRound(context);
		return context;
	}

	@RequestMapping(value = "{roomName}/game-end", method = RequestMethod.POST)
	public @ResponseBody
	GameContext gameEnd(HttpSession session, @PathVariable String roomName) {
		GameContext context = (GameContext) session.getAttribute(roomName);
		gameService.end(context);
		return context;
	}
}
