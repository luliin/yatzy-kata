package com.king.kata.yatzy;

import java.util.*;


public class YatzyScorer {

	public int calculateScore(Category category, YatzyRoll roll) {
		switch (category) {
			case CHANCE:
				return getChanceSum(roll);
			case YATZY:
				return evaluateYatzy(roll);
			case ONES:
			case TWOS:
			case THREES:
			case FOURS:
			case FIVES:
			case SIXES:
				return getNumberScores(category, roll);
			case PAIR:
				return getBiggestPairScore(roll);
			case TWO_PAIRS:
				return getTwoPairScore(roll);
			case THREE_OF_A_KIND:
			case FOUR_OF_A_KIND:
				return getXOfAKindPairScore(category, roll);
			case SMALL_STRAIGHT:
			case LARGE_STRAIGHT:
				return getStraightScore(category, roll);
			case FULL_HOUSE: return evaluateFullHouse(roll);
			default: return 0;
		}
	}

	private int getChanceSum(YatzyRoll roll) {
		return roll.getAsStream()
				.sum();
	}

	private int evaluateYatzy(YatzyRoll roll) {
		Set<Map.Entry<Integer, Integer>> entrySet = roll.getEntrySet();
		for (Map.Entry<Integer, Integer> entry : entrySet) {
			if (entry.getValue() == 5) return 50;
		}
		return 0;
	}


	public int getBiggestPairScore(YatzyRoll roll) {
		int highestValueInPair = 0;
		Set<Map.Entry<Integer, Integer>> entrySet = roll.getEntrySet();
		for (Map.Entry<Integer, Integer> entry : entrySet) {
			if (entry.getValue() > 1) {
				highestValueInPair = highestValueInPair < entry.getKey() ? entry.getKey() : highestValueInPair;
			}
		}
		return highestValueInPair * 2;
	}

	public int getTwoPairScore(YatzyRoll roll) {
		Set<Map.Entry<Integer, Integer>> entrySet = roll.getEntrySet();
		int valueOne = 0;
		int valueTwo = 0;

		for (Map.Entry<Integer, Integer> entry : entrySet) {
			if (entry.getValue() > 3) {
				valueOne = entry.getKey();
				valueTwo = entry.getKey();
			} else if (entry.getValue() > 1) {
				if (valueTwo == 0) {
					if (valueOne == 0) {
						valueOne = entry.getKey();
					} else {
						valueTwo = entry.getKey();
					}
				}
			}
		}
		return 2 * (valueOne + valueTwo);
	}

	public int getXOfAKindPairScore(Category category, YatzyRoll roll) {
		int faceValue = 0;
		Set<Map.Entry<Integer, Integer>> entrySet = roll.getEntrySet();
		while (faceValue == 0) {
			for (Map.Entry<Integer, Integer> entry : entrySet) {
				if (entry.getValue() > 2) {
					faceValue = entry.getKey();
				}
			}
		}
		return category.getValue() * faceValue;
	}

	public int getNumberScores(Category category, YatzyRoll yatzyRoll) {

		switch (category) {
			case ONES:
			case TWOS:
			case THREES:
			case FOURS:
			case FIVES:
			case SIXES:
				return evaluateNumberScores(category.getValue(), yatzyRoll);
			default:
				return 0;
		}

	}

	public int evaluateNumberScores(int value, YatzyRoll yatzyRoll) {

		return (int)yatzyRoll.getAsStream()
				.filter(diceValue ->  diceValue == value)
				.count() * value;
	}

	public int getStraightScore(Category category, YatzyRoll roll) {

		int result = 0;
		int first = roll.getSortedStream()
				.findFirst().orElse(0);
		if (first == category.getValue()) {
			result = roll.getAsStream()
					.sorted()
					.reduce(first-1, (a, b) -> (a == (b - 1)) ? b : 0);
		}
		return result != 0 ? roll.getSum() : result;
	}

	public int evaluateFullHouse(YatzyRoll roll) {
		if(roll.getMap().containsValue(3) && roll.getMap().containsValue(2)) {
			return roll.getSum();
		} else return 0;
	}
}
