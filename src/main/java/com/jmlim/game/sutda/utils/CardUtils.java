package com.jmlim.game.sutda.utils;

import java.util.List;

import com.jmlim.game.sutda.model.Card;
import com.jmlim.game.sutda.option.CardType;

public class CardUtils {
	public static CardType returnCardType(List<Card> cards) {
		if (cards.size() != 2) {
			return CardType.None;
		}

		Card card1 = cards.get(0);
		Card card2 = cards.get(1);

		int cardNumber1 = card1.getCardNum();
		int cardNumber2 = card2.getCardNum();

		boolean gwang1 = card1.isGwang();
		boolean gwang2 = card2.isGwang();

		boolean ten1 = card1.isTensDigit();
		boolean ten2 = card2.isTensDigit();

		CardType type = CardType.None;

		int level = 0;

		// 광땡
		if (gwang1 && gwang2) {
			// 38광땡
			type = CardType.GwangDdang;
			if (cardNumber1 % 10 == 3 && cardNumber2 % 10 == 8
					|| cardNumber1 % 10 == 8 && cardNumber2 % 10 == 3) {
				type = CardType.ThreeEightGwangDdang;
			}
		}
		// 땡
		else if (cardNumber1 % 10 == cardNumber2 % 10) {
			level = (cardNumber1 + cardNumber2) * 100;
			for (CardType cardType : CardType.values()) {
				if (level == cardType.getLevel()) {
					type = cardType;
				}
			}
			// 장땡
			if (type == CardType.None) {
				type = CardType.JangDdang;
			}
		} else {
			// 알리
			if (cardNumber1 % 10 == 1 && cardNumber2 % 10 == 2
					|| cardNumber1 % 10 == 2 && cardNumber2 % 10 == 1) {
				type = CardType.Alri;
			}
			// 독사
			else if (cardNumber1 % 10 == 1 && cardNumber2 % 10 == 4
					|| cardNumber1 % 10 == 4 && cardNumber2 % 10 == 1) {
				type = CardType.Doksa;
			}
			// 구삥
			else if (cardNumber1 % 10 == 1 && cardNumber2 % 10 == 9
					|| cardNumber1 % 10 == 9 && cardNumber2 % 10 == 1) {
				type = CardType.GuPping;
			}
			// 장삥
			else if (cardNumber1 % 10 == 1 && cardNumber2 % 10 == 10
					|| cardNumber1 % 10 == 10 && cardNumber2 % 10 == 1) {
				type = CardType.JangPping;
			}
			// 장사
			else if (cardNumber1 % 10 == 4 && cardNumber2 % 10 == 10
					|| cardNumber1 % 10 == 10 && cardNumber2 % 10 == 4) {
				type = CardType.JangSa;
			}
			// 세륙
			else if (cardNumber1 % 10 == 4 && cardNumber2 % 10 == 6
					|| cardNumber1 % 10 == 6 && cardNumber2 % 10 == 4) {
				type = CardType.Saelyuk;
			}
			// 사구
			else if (cardNumber1 % 10 == 4 && cardNumber2 % 10 == 9
					|| cardNumber1 % 10 == 9 && cardNumber2 % 10 == 4) {
				type = CardType.FourNine;
				if (ten1 && ten2) {
					type = CardType.StupidFourNine;
				}
			}
			// 땡잡이
			else if (cardNumber1 % 10 == 3 && cardNumber2 % 10 == 7
					|| cardNumber1 % 10 == 7 && cardNumber2 % 10 == 3) {
				type = CardType.DdangHunter;
			}
			// 끗
			else {
				level = (cardNumber1 + cardNumber2) % 10;
				for (CardType cardType : CardType.values()) {
					if (level == cardType.getLevel()) {
						type = cardType;
					}
				}
			}
		}

		return type;
	}
}
