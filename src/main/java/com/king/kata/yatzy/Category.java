package com.king.kata.yatzy;

public enum Category {

	CHANCE,
	YATZY,
	ONES(1),
	TWOS(2),
	THREES(3),
	FOURS(4),
	FIVES(5),
	SIXES(6),
	PAIR,
	TWO_PAIRS,
	THREE_OF_A_KIND(3),
	FOUR_OF_A_KIND(4),
	SMALL_STRAIGHT(1),
	LARGE_STRAIGHT(2),
	FULL_HOUSE,
	GENERIC;

	private int value;
	Category(int value) {
		this.value = value;
	}

	Category() {
	}

	public int getValue() {
		return value;
	}
}
