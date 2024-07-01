/**
 * @author Desiree
 * @date 6/27/2024
 */
package experiments;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import experiments.TestBoardCell;

public class TestBoard {
	final static int COLS = 4;
	final static int ROWS = 4;
	private TestBoardCell[][] grid;
	TestBoardCell cell;
	private Set<TestBoardCell> targets;
	private Set<TestBoardCell> visited;
	private Map<TestBoardCell, Set<TestBoardCell>> adjMtx;

	
	
	
	public TestBoard() {
		
		cell = new TestBoardCell(0,0);
		grid = new TestBoardCell[ROWS][COLS];
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				grid[row][col] = new TestBoardCell(row, col);
			}
		}
		
		calcAdjLists();
		
	}
	
	public void calcAdjLists() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				TestBoardCell currentCell = grid[i][j];
				//System.out.print("i: " + i + "j: " + j);
				Set <TestBoardCell> tempAdjList = new HashSet <TestBoardCell>();
				
				if (i - 1 >= 0 && i - 1 <= ROWS) { // looking at upper neighbor
					tempAdjList.add(grid[i-1][j]);
				} 
				
				if (i + 1 >= 0 && i + 1 < ROWS) { // looking at lower neighbor
					tempAdjList.add(grid[i+1][j]);
				} 
				
				if (j - 1 >= 0 && j - 1 <= COLS) { // looking at left neighbor
					tempAdjList.add(grid[i][j-1]);
				} 
				
				if (j + 1 >= 0 && j + 1 < COLS) { // looking at right neighbor
					tempAdjList.add(grid[i][j+1]);
				}
				//System.out.println("temp size " + tempAdjList.size());
				currentCell.setAdjList(tempAdjList);
			}
		}
	}
	
	// calculates legal targets for a move from startCell of length path length
	public void calcTargets(TestBoardCell thisCell, int pathLength) {
		targets = new HashSet<TestBoardCell>();
		visited = new HashSet<TestBoardCell>();
		visited.add(thisCell); 
		findAllTargets(thisCell, pathLength);
		
		
	}
	
	public void findAllTargets(TestBoardCell thisCell, int pathLength) {
		Set <TestBoardCell> tempAdjList = thisCell.getAdjList();
		
		//System.out.println("here" + tempAdjList.size());
		for (TestBoardCell adjCell: tempAdjList) {
			// System.out.println()
			if (visited.contains(adjCell)) {
				continue;
			} else {
				visited.add(adjCell);
				if (pathLength == 1 && !(adjCell.getIsOccupied())) {
					targets.add(adjCell);
				} else if (adjCell.getIsRoom()){
					targets.add(adjCell);
				} else {
					findAllTargets(adjCell, pathLength - 1);
				}
				visited.remove(adjCell);
			}
		}
	
	}
	
	// returns the cell from the board at row, col
	public TestBoardCell getCell(int row, int col) {
		return grid[row][col];
	}
	
	public Set<TestBoardCell> getTargets() {
		return targets;
	}
	

}
