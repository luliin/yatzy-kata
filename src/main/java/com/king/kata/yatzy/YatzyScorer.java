package com.king.kata.yatzy;

import java.util.*;


public class YatzyScorer {

	public int calculateScore(Category category, YatzyRoll roll) {
		return switch (category) {
			case CHANCE -> roll.getSum();
			case YATZY -> evaluateYatzy(roll);
			case ONES, TWOS, THREES, FOURS, FIVES, SIXES -> getNumberScores(category, roll);
			case PAIR -> getBiggestPairScore(roll);
			case TWO_PAIRS -> getTwoPairScore(roll);
			case THREE_OF_A_KIND, FOUR_OF_A_KIND -> getXOfAKindPairScore(category, roll);
			case SMALL_STRAIGHT, LARGE_STRAIGHT -> getStraightScore(category, roll);
			case FULL_HOUSE -> evaluateFullHouse(roll);
			default -> 0;
		};
	}

	private int evaluateYatzy(YatzyRoll roll) {
		return roll.getMap().containsValue(5) ? 50 : 0;
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

		faceValue = evaluate(category.getValue(), entrySet);

		return category.getValue() * faceValue;
	}

	private int evaluate(int categoryValue, Set<Map.Entry<Integer, Integer>> entrySet) {
		int faceValue = 0;
		for (Map.Entry<Integer, Integer> entry : entrySet) {
			if (entry.getValue() >= categoryValue) {
				faceValue = entry.getKey();
			}
		}
		return faceValue;
	}

	public int getNumberScores(Category category, YatzyRoll yatzyRoll) {
		return switch (category) {
			case ONES, TWOS, THREES, FOURS, FIVES, SIXES -> evaluateNumberScores(category.getValue(), yatzyRoll);
			default -> 0;
		};
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
			result = roll.getSortedStream()
					.reduce(first, (a, b) -> (a == b) ? a + 1 : 0);
		}
		return result != 0 ? roll.getSum() : result;
	}

	public int evaluateFullHouse(YatzyRoll roll) {
		return roll.getMap().containsValue(3) && roll.getMap().containsValue(2) ?
				roll.getSum() : 0;
	}
}
