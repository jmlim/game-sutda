package com.jmlim.game.sutda.model;

import java.util.List;

public class Room {

	private Integer id;

	private String name;

	private Boolean gaming;

	private List<Player> players;

	private List<Card> cards;

	private int stakes = 100;

	/**
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return
	 */
	public Boolean getGaming() {
		return gaming;
	}

	/**
	 * @param gaming
	 */
	public void setGaming(Boolean gaming) {
		this.gaming = gaming;
	}

	/**
	 * @return
	 */
	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * @param players
	 */
	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	/**
	 * @return
	 */
	public List<Card> getCards() {
		return cards;
	}

	/**
	 * @param cards
	 */
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	/**
	 * @return
	 */
	public int getStakes() {
		return stakes;
	}

	/**
	 * @param stakes
	 */
	public void setStakes(int stakes) {
		this.stakes = stakes;
	}
}
