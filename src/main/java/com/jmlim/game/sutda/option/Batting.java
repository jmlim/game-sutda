package com.jmlim.game.sutda.option;

public enum Batting {
	PPING("pping"), HARF("harf"), CALL("call"), CHECK("check"), DIE("die");

	private String name;

	private Batting(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
