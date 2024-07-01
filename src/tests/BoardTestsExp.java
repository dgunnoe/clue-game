/**
 * @author Desiree
 * @date 6/27/2024
 */
package tests;

import java.util.Set;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
//import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.*;

import experiments.*;

public class BoardTestsExp {
	TestBoard board;
	
	@BeforeEach
	public void setUp() {
		board = new TestBoard();
	}
	
	
	@Test
	//Test for adj list
	public void testAdjacency() {
		// Test (0,0) - top left 
		TestBoardCell cell = board.getCell(0,0);
		//board.calcAdjLists();
		Set<TestBoardCell> testList = cell.getAdjList();
		//System.out.println("size" + testList.size());
		Assert.assertTrue(testList.contains(board.getCell(1, 0)));
		Assert.assertTrue(testList.contains(board.getCell(0, 1)));
		Assert.assertEquals(2, testList.size());
	
		// Test (0,3) - top right
		cell = board.getCell(0,3);
		testList = cell.getAdjList();
		Assert.assertTrue(testList.contains(board.getCell(1, 3)));
		Assert.assertTrue(testList.contains(board.getCell(0, 2)));
		Assert.assertEquals(2, testList.size());
		
		
		// Test (3,3) - bottom right
		cell = board.getCell(3,3);
		testList = cell.getAdjList();
		Assert.assertTrue(testList.contains(board.getCell(2, 3)));
		Assert.assertTrue(testList.contains(board.getCell(3, 2)));
		Assert.assertEquals(2, testList.size());
		
		// Test (2,2) - middle
		cell = board.getCell(2,2);
		testList = cell.getAdjList();
		Assert.assertTrue(testList.contains(board.getCell(1, 2)));
		Assert.assertTrue(testList.contains(board.getCell(3, 2)));
		Assert.assertTrue(testList.contains(board.getCell(2, 1)));
		Assert.assertTrue(testList.contains(board.getCell(2, 3)));
		Assert.assertEquals(4, testList.size());
		
		// Test (1,0) - left edge
		cell = board.getCell(1,0);
		testList = cell.getAdjList();
		Assert.assertTrue(testList.contains(board.getCell(0, 0)));
		Assert.assertTrue(testList.contains(board.getCell(1, 1)));
		Assert.assertTrue(testList.contains(board.getCell(2, 0)));
		Assert.assertEquals(3, testList.size());
		
		// Test (2,3) - right edge
		cell = board.getCell(2,3);
		testList = cell.getAdjList();
		Assert.assertTrue(testList.contains(board.getCell(1, 3)));
		Assert.assertTrue(testList.contains(board.getCell(2, 2)));
		Assert.assertTrue(testList.contains(board.getCell(3, 3)));
		Assert.assertEquals(3, testList.size());
		
	}
	
	@Test
	//Test for creating targets
	public void testTargetsNormal() {
		// Roll a 3 starting at (0,0)
		TestBoardCell cell = board.getCell(0,0);
		//board.calcAdjLists();
		
		board.calcTargets(cell, 3);
		Set<TestBoardCell> targets = board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(3, 0)));
		Assert.assertTrue(targets.contains(board.getCell(2, 1)));
		Assert.assertTrue(targets.contains(board.getCell(0, 1)));
		Assert.assertTrue(targets.contains(board.getCell(1, 2)));
		Assert.assertTrue(targets.contains(board.getCell(0, 3)));
		Assert.assertTrue(targets.contains(board.getCell(1, 0)));
		
		
		// Roll a 2 starting at (1,1)
		cell = board.getCell(1,1);
		board.calcTargets(cell, 2);
		targets = board.getTargets();
		//System.out.println("target size: " + targets.size());
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(0, 0)));
		Assert.assertTrue(targets.contains(board.getCell(0, 2)));
		Assert.assertTrue(targets.contains(board.getCell(2, 0)));
		Assert.assertTrue(targets.contains(board.getCell(3, 1)));
		Assert.assertTrue(targets.contains(board.getCell(2, 2)));
		Assert.assertTrue(targets.contains(board.getCell(1, 3)));
		
		
	}
	
	@Test
	public void testTargetsMixed() {
		// set up occupied cells.
		
		// Start at 1,1
		TestBoardCell cell = board.getCell(1,1);
		board.getCell(0, 0).setOccupied(true);
		board.getCell(0, 2).setIsRoom(true);
		
		
		board.calcTargets(cell, 2);
		Set<TestBoardCell> targets = board.getTargets();
		//System.out.println("target size: " + targets.size());
		Assert.assertEquals(5, targets.size());
		//Assert.assertTrue(targets.contains(board.getCell(0, 0)));
		Assert.assertTrue(targets.contains(board.getCell(0, 2)));
		Assert.assertTrue(targets.contains(board.getCell(2, 0)));
		Assert.assertTrue(targets.contains(board.getCell(3, 1)));
		Assert.assertTrue(targets.contains(board.getCell(2, 2)));
		Assert.assertTrue(targets.contains(board.getCell(1, 3)));
		
		board.getCell(0, 0).setOccupied(false);
		board.getCell(0, 2).setIsRoom(false);
		
		// Start at 0, 3
		board.getCell(0, 2).setOccupied(true);
		board.getCell(2, 2).setIsRoom(true);
		//board.getCell(1, 2).setIsRoom(true);
		cell = board.getCell(0, 3);
		board.calcTargets(cell, 3);
		targets = board.getTargets();
		Assert.assertEquals(5, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(2, 2)));
		Assert.assertTrue(targets.contains(board.getCell(3, 3)));
		
		Assert.assertTrue(targets.contains(board.getCell(1, 1)));
		//Assert.assertTrue(targets.contains(board.getCell(0, 2)));
		Assert.assertTrue(targets.contains(board.getCell(1, 3)));
		Assert.assertTrue(targets.contains(board.getCell(0, 0)));
		
		board.getCell(0, 2).setOccupied(false);
		board.getCell(2, 2).setIsRoom(false);
		


	}
}
