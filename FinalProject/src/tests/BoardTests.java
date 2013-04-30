package tests;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import game.Board;
import game.Bubble;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BoardTests {
	public Board testBoard;

	@Before
	public void setUp() {
		testBoard = new Board(false);

	}

	@Test
	public void test3bubbles() {
		testBoard.detectLinear(false);
		// test for horizontal
		assertTrue(testBoard.getBubbleAt(6, 11).isEmpty());
		assertTrue(testBoard.getBubbleAt(6, 12).isEmpty());
		assertTrue(testBoard.getBubbleAt(6, 13).isEmpty());
		// test for vertical
		assertTrue(testBoard.getBubbleAt(0, 0).isEmpty());
		assertTrue(testBoard.getBubbleAt(1, 0).isEmpty());
		assertTrue(testBoard.getBubbleAt(2, 0).isEmpty());

	}

	@Test
	public void testPopulatesBoard() {

		// test for size
		assertTrue(testBoard.getCols() == 20);
		assertTrue(testBoard.getRows() == 10);

		// test for randomness
		Board testBoardOne = new Board(true);

		// corners
		assertFalse(testBoardOne.getBubbleAt(0, 0) == null);
		assertFalse(testBoardOne.getBubbleAt(9, 19)== null);
		assertFalse(testBoardOne.getBubbleAt(0, 19) == null);
		assertFalse(testBoardOne.getBubbleAt(9, 0) == null);

		// off by one from left
		assertFalse(testBoardOne.getBubbleAt(5, 2) == null);

		// centers
		assertFalse(testBoardOne.getBubbleAt(4, 10) == null);
		// test bubbles on board not empty
		assertTrue(testBoard.getBubbleBoard() != null);

	}

	@Test
	public void testDrop() {
		// Copy the reference of this bubble
		Bubble testBubble = testBoard.getBubbleAt(0, 7);
		//Mimic pop bubbles
		testBoard.getBubbleBoard()[5][7].setEmpty(true);
		testBoard.getBubbleBoard()[4][7].setEmpty(true);
		testBoard.getBubbleBoard()[3][7].setEmpty(true);
		testBoard.getBubbleBoard()[2][7].setEmpty(true);
		// pop a stack of bubbles
		testBoard.fallMaster();
		// Check the bubbles fell
		assertTrue(testBoard.getBubbleAt(5, 7).getBubbleColor() == Color.GREEN);
		// Check that the bubble is not in the previous spot
		assertFalse(testBoard.getBubbleAt(0, 7).equals(testBubble));
	}

	@Test
	public void testSwap() {
		Bubble bubbleOne = testBoard.getBubbleAt(4, 5);
		Bubble bubbleTwo = testBoard.getBubbleAt(4, 4);
		bubbleOne.setCol(4);
		bubbleTwo.setCol(5);
		// swap the bubbles
		testBoard.swap(testBoard.getBubbleAt(4, 5), testBoard.getBubbleAt(4, 4));
		// check that they swapped
		assertTrue(testBoard.getBubbleAt(4, 4).equals(bubbleOne));
		assertTrue(testBoard.getBubbleAt(4, 5).equals(bubbleTwo));
	}

	@Test
	public void testColorEquality() {
		// test to make sure two bubbles are same
		// test for inequality
		assertTrue(testBoard.getBubbleAt(0, 0).colorEquals(testBoard.getBubbleAt(1, 0)));
		assertFalse(testBoard.getBubbleAt(0, 0).colorEquals(testBoard.getBubbleAt(0, 1)));
	}

	@Test
	public void testEndOfGameRules() {
		Bubble bubbleOne = testBoard.getBubbleAt(4, 5);
		Bubble bubbleTwo = testBoard.getBubbleAt(4, 4);
		// swap the bubbles
		testBoard.swap(testBoard.getBubbleAt(4, 5), testBoard.getBubbleAt(4, 4));
		assertFalse(testBoard.getBubbleAt(4, 4) == bubbleOne);
		assertFalse(testBoard.getBubbleAt(4, 5) == bubbleTwo);
	}
}
