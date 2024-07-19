/**
 * @author Desiree
 * @date 6/27/2024
 */
package tests;

import java.util.Set;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;

import clueGame.Board;
import clueGame.BoardCell;

//import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.*;

import experiments.*;

public class GameSetupTests {
	private static Board board;
	
	@BeforeAll
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("data/ClueLayout.csv", "data/ClueSetup.txt");
		// Initialize will load config files
		board.initialize();
	}
	
	
	@Test
	//Load people and weapons from ClueSetup.txt and ensure the data was loaded properly. [20pts]
	//Create Player class with human and computer child classes.   Use people data to instantiate 6 players (1 human and 5 computer) [20pts]
	public void loadPeopleAndWeapons() {
		// Test (0,0) - top left 
		BoardCell cell = board.getCell(0,0);
		//board.calcAdjLists();
		Set<BoardCell> testList = cell.getAdjList();
		//System.out.println("size" + testList.size());
		Assert.assertTrue(testList.contains(board.getCell(1, 0)));
		Assert.assertTrue(testList.contains(board.getCell(0, 1)));
		Assert.assertEquals(2, testList.size());
	
		// Test (0,3) - top right
		cell = board.getCell(0,3);
		testList = cell.getAdjList();
		Assert.assertTrue(testList.contains(board.getCell(1, 3)));
		
	}
	

}
