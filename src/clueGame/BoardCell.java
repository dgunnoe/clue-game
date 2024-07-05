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
	private boolean isRoom;
	private boolean target;


	private Set<BoardCell> adjList;
	
	public BoardCell(int r, int c, String label) {
		adjList = new HashSet<BoardCell>();		
		doorDirection = DoorDirection.NONE; // Assume not a doorway
		
		// Labeling if it's a true room
		if (label.charAt(0) == 'W' || label.charAt(0) == 'X') {
			isRoom = false; 
		} else {
			isRoom = true; 
		}
		
		initial = label.charAt(0); // i.e. 'C', 'W', etc.
		
		//secretPassage = null; // Assume dummy variable, will be overwritten if needed
		roomLabel = false; // Assume false
		roomCenter = false; // Assume false
		doorDirection = DoorDirection.NONE; // Assume not a doorway
		target = false;
		
		// Special label
		if (label.length() == 2) {
			
			char id = label.charAt(1); 
			
			// For room center labels
			if (id == '*') { // * = center
				roomCenter = true;
			} else if (id == '#') { // # = label
				roomLabel = true;
			} else  { 
				
				// Door labels
				if (id == 'v') {
					doorDirection = DoorDirection.DOWN;
				} else if (id == '<') {
					doorDirection = DoorDirection.LEFT;
				} else if (id == '>' ) {
					doorDirection = DoorDirection.RIGHT;
				} else if (id == '^') {
					doorDirection = DoorDirection.UP;
				} else { // Char of room it leads to
					secretPassage = id;
				}
			}
			
		}

	}
	
	
	public void addAdjacency(BoardCell cell) {
		adjList.add(cell);
	}

	public boolean isDoorway() {
		if (doorDirection == DoorDirection.NONE) {
			return false;
		}
		return true;
	}

	public Object getDoorDirection() {
		// TODO Auto-generated method stub
		return doorDirection;
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
