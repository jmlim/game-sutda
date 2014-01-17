package com.jmlim.game.sutda.model;

public class Card {

	private int cardNum;

	private boolean tensDigit;

	private boolean gwang;

	/**
	 * @param cardNum
	 * @param ten
	 * @param gwang
	 */
	public Card(int cardNum, boolean tensDigit, boolean gwang) {
		super();
		this.cardNum = cardNum;
		this.tensDigit = tensDigit;
		this.gwang = gwang;
	}

	/**
	 * @return the cardNum
	 */
	public int getCardNum() {
		return cardNum;
	}

	/**
	 * @param cardNum
	 *            the cardNum to set
	 */
	public void setCardNum(int cardNum) {
		this.cardNum = cardNum;
	}

	/**
	 * @return
	 */
	public boolean isTensDigit() {
		return tensDigit;
	}

	/**
	 * @param tensDigit
	 */
	public void setTensDigit(boolean tensDigit) {
		this.tensDigit = tensDigit;
	}

	/**
	 * @return the gwang
	 */
	public boolean isGwang() {
		return gwang;
	}

	/**
	 * @param gwang
	 *            the gwang to set
	 */
	public void setGwang(boolean gwang) {
		this.gwang = gwang;
	}
}
