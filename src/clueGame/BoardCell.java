package clueGame;

import java.util.HashSet;
import java.util.Set;

import experiments.TestBoard;
import experiments.TestBoardCell;

public class BoardCell {
	Board board;
	private int row;
	private int col;
	private char initial;
	private DoorDirection doorDirection;
	private boolean roomLabel;
	private boolean roomCenter;
	private char secretPassage;
	private Set<BoardCell> adjList;
	
	public BoardCell(int r, int c) {
		adjList = new HashSet<BoardCell>();
	}
	
	
	public void addAdjacency(BoardCell cell) {
		adjList.add(cell);
	}

	public boolean isDoorway() {
		// TODO Auto-generated method stub
		return false;
	}

	public Object getDoorDirection() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isLabel() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isRoomCenter() {
		// TODO Auto-generated method stub
		return false;
	}

	public char getSecretPassage() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
