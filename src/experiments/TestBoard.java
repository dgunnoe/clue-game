package experiments;

import java.util.HashSet;
import java.util.Set;

public class TestBoard {
	TestBoardCell cell;
	Set<TestBoardCell> targets; 
	
	public TestBoard() {
		targets = new HashSet<TestBoardCell>();
		cell = new TestBoardCell(0,0);
		
		
	}
	
	// calculates legal targets for a move from startCell of length pathlength
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
