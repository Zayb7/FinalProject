package game;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import java.awt.Color;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BoardTests {
	public static Board testBoard;

	@BeforeClass
	public static void setUpBeforeClass() {
		testBoard = new Board(false);

		// load the testboard for passing tests
		final String fileName = "TEST_BOARD.csv";
	}

	

	@Test
	public void test3bubbles() {
		// test for vertical
		ArrayList<Bubble> temp = testBoard.detectHelper(testBoard.getBubble(0, 0));
		assertTrue(temp != null);
		assertTrue(temp.size() == 2);
		assertTrue(temp.contains(testBoard.getBubble(1, 0)));
		assertTrue(temp.contains(testBoard.getBubble(2, 0)));
		// test for horizontal
		temp = testBoard.detectHelper(testBoard.getBubble(6, 11));
		assertTrue(temp.size() == 3);
		assertTrue(temp.contains(testBoard.getBubble(6, 12)));
		assertTrue(temp.contains(testBoard.getBubble(6, 13)));
		assertTrue(temp.contains(testBoard.getBubble(6, 14)));

		// test for L-shape
		temp = testBoard.detectHelper(testBoard.getBubble(5, 2));
		assertTrue(temp.size() == 2);
		assertTrue(temp.contains(testBoard.getBubble(5, 3)));
		assertTrue(temp.contains(testBoard.getBubble(6, 3)));
	}

	@Test
	public void testPopulatesBoard() {

		// test for size
		assertTrue(testBoard.getCols() == 20);
		assertTrue(testBoard.getRows() == 10);

		// test for randomness
		Board testBoardOne = new Board(true), testBoardTwo = new Board(true);

		// corners
		assertFalse(testBoardOne.getBubble(0, 0).equals(
				testBoardTwo.getBubble(0, 0)));
		assertFalse(testBoardOne.getBubble(9, 19).equals(
				testBoardTwo.getBubble(9, 19)));
		assertFalse(testBoardOne.getBubble(0, 19).equals(
				testBoardTwo.getBubble(0, 19)));
		assertFalse(testBoardOne.getBubble(9, 0).equals(
				testBoardTwo.getBubble(9, 0)));

		// off by one from left
		assertFalse(testBoardOne.getBubble(5, 2).equals(
				testBoardTwo.getBubble(5, 2)));

		// centers
		assertFalse(testBoardOne.getBubble(4, 10).equals(
				testBoardTwo.getBubble(4, 10)));
		// test bubbles on board not empty
		assertTrue(testBoard.getBubbleBoard() != null);

	}

	@Test
	public void testDrop() {
		// Copy the reference of this bubble
		Bubble testBubble = testBoard.getBubble(0, 7);
		// pop a stack of bubbles
		testBoard.fallHelper(testBoard.getBubble(5, 7));
		// Check the bubbles fell
		assertTrue(testBoard.getBubble(5, 7).getBubbleColor() == Color.GREEN);
		// Check that the bubble is not in the previous spot
		assertFalse(testBoard.getBubble(0, 7) != testBubble);
	}

	@Test
	public void testSwap() {
		Bubble bubbleOne = testBoard.getBubble(4, 5);
		Bubble bubbleTwo = testBoard.getBubble(4, 4);
		bubbleOne.setCol(4);
		bubbleTwo.setCol(5);
		// swap the bubbles
		testBoard.swap(testBoard.getBubble(4, 5), testBoard.getBubble(4, 4));
		// check that they swapped
		System.out.println(testBoard.getBubble(4, 4).getRow() + " " + testBoard.getBubble(4, 4).getCol() + " " +testBoard.getBubble(4, 4).getBubbleColor());
		System.out.println(bubbleOne.getRow() + " " + bubbleOne.getCol() + " " +bubbleOne.getBubbleColor());
		assertTrue(testBoard.getBubble(4, 4).equals(bubbleOne));
		assertTrue(testBoard.getBubble(4, 5).equals(bubbleTwo));
	}

	@Test
	public void testColorEquality() {
		// test to make sure two bubbles are same
		// test for inequality
		assertTrue(testBoard.getBubble(0, 0).colorEquals(testBoard.getBubble(1, 0)));
		assertFalse(testBoard.getBubble(0, 0).colorEquals(testBoard.getBubble(0, 1)));
	}

	// FOR GUI TESTING
	// @Test
	// public void testMoveReticle(){
	// //test move up, down, left, right
	// }

	@Test
	public void testEndCondition() {
		// test that timer ends
		testBoard.setIntTimer(0);
		// the main function  will set the game to end
		// check that the game has ended
		assertTrue(testBoard.isEnd());
		// test that player can't swap after time ends
		Bubble bubbleOne = testBoard.getBubble(4, 5);
		Bubble bubbleTwo = testBoard.getBubble(4, 4);
		// swap the bubbles
		testBoard.swap(testBoard.getBubble(4, 5), testBoard.getBubble(4, 4));
		assertFalse(testBoard.getBubble(4, 4) == bubbleOne);
		assertFalse(testBoard.getBubble(4, 5) == bubbleTwo);
	}
}
