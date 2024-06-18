package experiments;

import java.util.HashSet;
import java.util.Set;

// represents one cell in your grid
public class TestBoardCell {
	TestBoard board;
	int r;
	int c;
	Set<TestBoardCell> adjList;
	boolean isRoom;
	boolean isOccupied;
	
	public TestBoardCell(int r, int c) {
		adjList = new HashSet<TestBoardCell>();
		
	}
	
	void addAdjacency(TestBoardCell cell) {
		
	}
	
	public Set<TestBoardCell> getAdjList() {
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
