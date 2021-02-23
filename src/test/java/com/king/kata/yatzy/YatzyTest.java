package com.king.kata.yatzy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.*;

public class YatzyTest {

	private YatzyScorer yatzyScorer;

	@BeforeEach
	public void setUp() {
		yatzyScorer = new YatzyScorer();
	}

	@Test
	public void rollIsFiveDice() {
		assertEquals(5, new YatzyRoll(5, 2, 1, 4, 6).getDice().length);
	}

	@Test
	public void rollMustBeCorrectDiceValues() {
		assertThrows(InputMismatchException.class, () ->
				yatzyScorer.calculateScore(Category.GENERIC, YatzyRoll.create(-1, 0, 1, 4, 6)));
	}

	@Test
	public void rollMustBeCorrectDiceValues2() {
		assertThrows(InputMismatchException.class, () ->
				yatzyScorer.calculateScore(Category.GENERIC, YatzyRoll.create(7, 7, 1, 4, 6)));
	}

	@Test
	public void genericCategoryReturnsZero() {
		int score = yatzyScorer.calculateScore(Category.GENERIC, YatzyRoll.create(5, 2, 1, 4, 6));
		assertEquals(0, score);
	}
	@Test
	public void getNumberScoresGenericTest() {
		int score = yatzyScorer.getNumberScores(Category.GENERIC, YatzyRoll.create(5, 2, 1, 4, 6));
		assertEquals(0, score);
	}

	@Test
	public void chanceScoresCorrectly() {
		int score = yatzyScorer.calculateScore(Category.CHANCE, YatzyRoll.create(5, 2, 1, 4, 6));
		assertEquals(18, score);
	}

	@Test
	public void chanceScoresCorrectly2() {
		int score = yatzyScorer.calculateScore(Category.CHANCE, YatzyRoll.create(1, 2, 5, 4, 6));
		assertEquals(18, score);
	}

	@Test
	public void onesTest() {
		int score = yatzyScorer.calculateScore(Category.ONES, YatzyRoll.create(1, 2, 4, 4, 6));
		assertEquals(1, score);
	}

	@Test
	public void onesTest2() {
		int score = yatzyScorer.calculateScore(Category.ONES, YatzyRoll.create(1, 1, 4, 4, 6));
		assertEquals(2, score);
	}
	@Test
	public void onesTest3() {
		int score = yatzyScorer.calculateScore(Category.ONES, YatzyRoll.create(1, 1, 4, 4, 1));
		assertNotEquals(2, score);
	}

	@Test
	public void twosTest() {
		int score = yatzyScorer.calculateScore(Category.TWOS, YatzyRoll.create(1, 2, 4, 4, 2));
		assertEquals(4, score);
	}

	@Test
	public void twosTest2() {
		int score = yatzyScorer.calculateScore(Category.TWOS, YatzyRoll.create(2, 2, 2, 2, 2));
		assertEquals(10, score);
	}
	@Test
	public void twosTest3() {
		int score = yatzyScorer.calculateScore(Category.TWOS, YatzyRoll.create(1, 1, 4, 4, 1));
		assertNotEquals(5, score);
	}

	@Test
	public void threesTest() {
		int score = yatzyScorer.calculateScore(Category.THREES, YatzyRoll.create(1, 2, 3, 4, 2));
		assertEquals(3, score);
	}

	@Test
	public void threesTest2() {
		int score = yatzyScorer.calculateScore(Category.THREES, YatzyRoll.create(2, 2, 2, 2, 2));
		assertEquals(0, score);
	}
	@Test
	public void threesTest3() {
		int score = yatzyScorer.calculateScore(Category.THREES, YatzyRoll.create(3, 3, 3, 3, 1));
		assertNotEquals(3, score);
	}

	@Test
	public void foursTest() {
		int score = yatzyScorer.calculateScore(Category.FOURS, YatzyRoll.create(4, 3, 3, 3, 1));
		assertNotEquals(0, score);
	}
	@Test
	public void foursTest2() {
		int score = yatzyScorer.calculateScore(Category.FOURS, YatzyRoll.create(4, 3, 3, 3, 1));
		assertEquals(4, score);
	}

	@Test
	public void fivesTest() {
		int score = yatzyScorer.calculateScore(Category.FIVES, YatzyRoll.create(3, 3, 5, 3, 1));
		assertNotEquals(0, score);
	}
	@Test
	public void sixesTest() {
		int score = yatzyScorer.calculateScore(Category.SIXES, YatzyRoll.create(3, 3, 3, 3, 1));
		assertNotEquals(6, score);
	}

	@Test
	public void isYatzy() {
		int score = yatzyScorer.calculateScore(Category.YATZY, YatzyRoll.create(1, 1, 1, 1, 1));
		assertEquals(50, score);
	}

	@Test
	public void isNotYatzy() {
		int score = yatzyScorer.calculateScore(Category.YATZY, YatzyRoll.create(1, 6, 1, 1, 1));
		assertEquals(0, score);
	}

	@Test
	public void PairScoresCorrectlyWithOnePair() {
		int score = yatzyScorer.calculateScore(Category.PAIR, YatzyRoll.create(5, 2, 5, 4, 6));
		assertEquals(10, score);
	}

	@Test
	public void pairScoresCorrectlyWithFourOfTheSame() {
		int score = yatzyScorer.calculateScore(Category.PAIR, YatzyRoll.create(5, 5, 5, 5, 6));
		assertEquals(10, score);
	}

	@Test
	public void twoPairsTest() {
		int score = yatzyScorer.calculateScore(Category.TWO_PAIRS, YatzyRoll.create(5, 5, 5, 5, 5));
		assertEquals(20, score);
	}

	@Test
	public void twoPairsTest2() {
		int score = yatzyScorer.calculateScore(Category.TWO_PAIRS, YatzyRoll.create(4, 4, 5, 5, 6));
		assertEquals(18, score);
	}

	@Test
	public void twoPairsTest3() {
		int score = yatzyScorer.calculateScore(Category.TWO_PAIRS, YatzyRoll.create(4, 4, 4, 4, 4));
		assertNotEquals(20, score);
	}

	@Test
	public void threeOfAKindTest() {
		int score = yatzyScorer.calculateScore(Category.THREE_OF_A_KIND, YatzyRoll.create(5, 5, 5, 5, 5));
		assertEquals(15, score);
	}

	@Test
	public void threeOfAKindTest2() {
		int score = yatzyScorer.calculateScore(Category.THREE_OF_A_KIND, YatzyRoll.create(4, 4, 5, 5, 4));
		assertEquals(12, score);
	}

	@Test
	public void threeOfAKindTest3() {
		int score = yatzyScorer.calculateScore(Category.THREE_OF_A_KIND, YatzyRoll.create(4, 4, 4, 4, 4));
		assertNotEquals(20, score);
	}

	@Test
	public void fourOfAKindTest() {
		int score = yatzyScorer.calculateScore(Category.FOUR_OF_A_KIND, YatzyRoll.create(5, 5, 5, 5, 5));
		assertEquals(20, score);
	}

	@Test
	public void fourOfAKindTest2() {
		int score = yatzyScorer.calculateScore(Category.FOUR_OF_A_KIND, YatzyRoll.create(4, 4, 4, 5, 4));
		assertEquals(16, score);
	}

	@Test
	public void smallStraightTest() {
		int score = yatzyScorer.calculateScore(Category.SMALL_STRAIGHT, YatzyRoll.create(1, 2, 3, 4, 5));
		assertEquals(15, score);
	}

	@Test
	public void smallStraightTest2() {
		int score = yatzyScorer.calculateScore(Category.SMALL_STRAIGHT, YatzyRoll.create(4, 4, 4, 4, 4));
		assertEquals(0, score);
	}

	@Test
	public void largeStraightTest() {
		int score = yatzyScorer.calculateScore(Category.LARGE_STRAIGHT, YatzyRoll.create(6, 4, 3, 5, 2));
		assertEquals(20, score);
	}

	@Test
	public void largeStraightTest2() {
		int score = yatzyScorer.calculateScore(Category.LARGE_STRAIGHT, YatzyRoll.create(4, 4, 4, 4, 4));
		assertEquals(0, score);
	}

	@Test
	public void fullHouseTest() {
		int score = yatzyScorer.calculateScore(Category.FULL_HOUSE, YatzyRoll.create(4, 4, 4, 3, 3));
		assertEquals(18, score);
	}

	@Test
	public void fullHouseTest2() {
		int score = yatzyScorer.calculateScore(Category.FULL_HOUSE, YatzyRoll.create(1,1,2,2,2));
		assertEquals(8, score);
	}

	@Test
	public void yatzyIsNotFullHouse() {
		int score = yatzyScorer.calculateScore(Category.FULL_HOUSE, YatzyRoll.create(4, 4, 4, 4, 4));
		assertEquals(0, score);
	}
}
