package com.jmlim.game.sutda.option;

public enum CardType {
	ThreeEightGwangDdang(38000, 38000, "삼팔광땡"),

	GwangDdang(18000, 18000, "광땡"),

	JangDdang(10000, 10000, "장땡"),

	NineDdang(9900, 9900, "구땡"),

	EightDdang(8800, 8800, "필땡"),

	SevenDdang(7700, 7700, "칠땡"),

	SixDdang(6600, 6600, "육땡"),

	FiveDdang(5500, 5500, "오땡"),

	FourDdang(4400, 4400, "사땡"),

	ThreeDdang(3300, 3300, "삼땡"),

	TwoDdang(2200, 2200, "이땡"),

	OneDdang(1100, 1100, "일땡"),

	Alri(1021, 1021, "알리"),

	Doksa(1014, 1014, "독사"),

	GuPping(901, 901, "구삥"),

	JangPping(110, 110, "장삥"),

	JangSa(104, 104, "장사"),

	Saelyuk(64, 64, "세륙"),

	FourNine(49, 49, "사구"),

	StupidFourNine(49, 49, "멍텅구리 사구"),

	DdangHunter(37, 37, "땡잡이"),

	GapO(9, 9, "갑오"),

	EightGGuk(8, 8, "여덟끗"),

	SevenGGuk(7, 7, "일곱끗"),

	SixGGuk(6, 6, "여섯끝"),

	FiveGGuk(5, 5, "다섯끝"),

	FourGGuk(4, 4, "네끗"),

	ThreeGGuk(3, 3, "세끗"),

	TwoGGuk(2, 2, "두끗"),

	OneGGuk(1, 1, "한끗"),

	MangTong(0, 0, "망통"),

	None(-1, -1, "None");

	private int typeCode;

	private int level;

	private String label;

	private CardType(int typeCode, int level, String label) {
		this.typeCode = typeCode;
		this.level = level;
		this.label = label;
	}

	public int getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(int typeCode) {
		this.typeCode = typeCode;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
