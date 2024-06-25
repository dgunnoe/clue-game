package experiments;

import java.util.HashSet;
import java.util.Set;

import experiments.TestBoardCell;

public class TestBoard {
	final static int COLS = 4;
	final static int ROWS = 4;
	private TestBoardCell[][] grid;
	TestBoardCell cell;
	private Set<TestBoardCell> targets;
	private Set<TestBoardCell> visited;
	
	
	
	public TestBoard() {
		targets = new HashSet<TestBoardCell>();
		cell = new TestBoardCell(0,0);
		grid = new TestBoardCell[ROWS][COLS];
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				grid[row][col] = new TestBoardCell(row, col);
			}
		}
		
		
	}
	
	public void calcAdjLists(TestBoardCell cell) {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (i - 1 >= 0 && i - 1 <= ROWS) { // looking at upper neighbor
					grid[i][j].addAdjacency(getCell(i-1, j));
				} else if (i + 1 >= 0 && i + 1 <= ROWS) { // looking at lower neighbor
					grid[i][j].addAdjacency(getCell(i+1, j));
				} else if (j - 1 >= 0 && j - 1 <= COLS) { // looking at left neighbor
					grid[i][j].addAdjacency(getCell(i, j-1));
				} else if (j + 1 >= 0 && j + 1 <= COLS) { // looking at right neighbor
					grid[i][j].addAdjacency(getCell(i, j+1));
				}
					
			}
		}
	}
	// calculates legal targets for a move from startCell of length path length
	public void calcTargets(TestBoardCell startCell, int pathLength) {
		
	}
	
	// returns the cell from the board at row, col
	public TestBoardCell getCell(int row, int col) {
		return cell;
	}
	
	public Set<TestBoardCell> getTargets() {
		return targets;
	}
	

}
