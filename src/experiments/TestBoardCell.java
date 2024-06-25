package experiments;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// represents one cell in your grid
public class TestBoardCell {
	TestBoard board;
	private int row, col;
	Set<TestBoardCell> adjList;
	boolean isRoom, isOccupied;
	private Map<TestBoardCell, Set<TestBoardCell>> adjMtx;
	
	public TestBoardCell(int r, int c) {
		adjList = new HashSet<TestBoardCell>();
		
	}
	
	// A setter to add a cell to this cells adj list
	public void addAdjacency(TestBoardCell cell) {
		adjList.add(cell);
	}
	
	public Set<TestBoardCell> getAdjList() {
		for (TestBoardCell c: adjList) {
			System.out.println(c);
		}
		return adjList;
	}
	
	
	public void getRoom(boolean isRoom) {
		
	}
	public void setRoom(boolean isRoom) {
		
	}
	public void getIsRoom() {
		
	}
	public void setIsRoom(boolean isRoom) {
		
	}
	public void setOccupied(boolean isOccupied) {
		
	}
	
	

}
