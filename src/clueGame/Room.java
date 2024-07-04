package clueGame;
/**
 * Holds information about rooms
 */
public class Room {
	private String name;
	private BoardCell centerCell;
	private BoardCell labelCell;
	
	public Room(String name) {
		this.name = name;
	}
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public BoardCell getLabelCell() {
		// TODO Auto-generated method stub
		return null;
	}

	public BoardCell getCenterCell() {
		// TODO Auto-generated method stub
		return null;
	}

}
