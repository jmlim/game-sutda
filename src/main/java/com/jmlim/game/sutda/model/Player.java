package com.jmlim.game.sutda.model;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private Integer id;

	private int seq;

	private Integer money = 0;

	private String name;

	private List<Card> cards = new ArrayList<>();

	private boolean die;

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
	public int getSeq() {
		return seq;
	}

	/**
	 * @param seq
	 */
	public void setSeq(int seq) {
		this.seq = seq;
	}

	/**
	 * @return
	 */
	public Integer getMoney() {
		return money;
	}

	/**
	 * @param money
	 */
	public void setMoney(Integer money) {
		this.money = money;
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
	public List<Card> getCards() {
		return cards;
	}

	/**
	 * @param card
	 */
	public void addCard(Card card) {
		cards.add(card);
	}

	/**
	 * @return
	 */
	public boolean isDie() {
		return die;
	}

	/**
	 * @param die
	 */
	public void setDie(boolean die) {
		this.die = die;
	}

	/**
	 * 
	 */
	public void clearCards() {
		cards.clear();
	}
}
