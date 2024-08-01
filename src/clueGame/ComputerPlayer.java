package clueGame;

public class ComputerPlayer extends Player {

	public ComputerPlayer(String name, int startRow, int startColumn, String color) {
		super(name, startRow, startColumn, color);
		// TODO Auto-generated constructor stub
	}
	
	public Solution createSuggestion() {
		return null;
	}

	public BoardCell selectTarget() {
		return null;
	}
}
