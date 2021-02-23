package com.king.kata.yatzy;

import java.util.*;
import java.util.stream.IntStream;

public class YatzyRoll {
	private final int[] dice;

	public YatzyRoll(int d1, int d2, int d3, int d4, int d5) {
		dice = new int[]{d1, d2, d3, d4, d5};
	}

	public int[] getDice() {
		return dice;
	}

	public IntStream getAsStream() {
		return Arrays.stream(dice);
	}

	public Map<Integer, Integer> getMap(){
		Map<Integer, Integer> numberAndCount = new HashMap<>();
		for (int i : getDice()) {
			Integer count = numberAndCount.get(i);
			if (count == null) {
				numberAndCount.put(i, 1);
			} else {
				numberAndCount.put(i, ++count);
			}
		}
		return numberAndCount;
	}

	public Set<Map.Entry<Integer, Integer>> getEntrySet() {
		return getMap().entrySet();
	}

	public IntStream getSortedStream() {
		Arrays.sort(dice);
		return Arrays.stream(dice);
	}

	public int getSum() {
		return getAsStream().sum();
	}

	public static YatzyRoll create(int d1, int d2, int d3, int d4, int d5) {
		YatzyRoll yatzyRoll = new YatzyRoll(d1, d2, d3, d4, d5);
		boolean isValid =yatzyRoll.getAsStream()
				.allMatch(value -> value<=6 && value>=0);
		if(!isValid) throw new InputMismatchException();
		else return yatzyRoll;
	}
}
