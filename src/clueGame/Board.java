package clueGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.io.FileReader;


public class Board {
	private BoardCell[][] grid;
	private int numRows;
	private int numColumns;
	private String layoutConfigFile;
	private String setupConfigFile;
	private Map<Character, Room> roomMap;
    private static Board theInstance = new Board();
	private Room room;
	private BoardCell cell;
  
    // constructor is private to ensure only one can be created
    private Board() {
    	super() ;
    }
    
    // this method returns the only Board
    public static Board getInstance() {
    	return theInstance;
    }
    /*
     * initialize the board (since we are using singleton pattern)
     */
	public void initialize() {
		try {
			loadSetupConfig();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (BadConfigFormatException e) {
			System.out.println(e.getMessage());
		}
		//loadSetupConfig();
		loadLayoutConfig();
		
		cell = new BoardCell(0,0);
		numRows = 25;
		numColumns = 24;
		grid = new BoardCell[numRows][numColumns];
		room = new Room();
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numColumns; col++) {
				grid[row][col] = new BoardCell(row, col);
			}
		}
//		
//		calcAdjLists();
		
	}
	
	public void loadSetupConfig() throws FileNotFoundException, BadConfigFormatException {
		File setupFile = new File(setupConfigFile);
		System.out.println("Absolute path of setupConfigFile: " + setupFile.getAbsolutePath());

		Scanner reader = new Scanner(setupFile);
		while (reader.hasNextLine()) {
			String data = reader.nextLine();
			System.out.println(data);
		}
		reader.close();
		
	}
	
	public void loadLayoutConfig() {
		try {
			File layoutFile = new File(layoutConfigFile);
			Scanner reader = new Scanner(layoutFile);
			while (reader.hasNextLine()) {
				String data = reader.nextLine();
				System.out.println(data);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("error: ");
			e.printStackTrace();
		}
	}

	public void setConfigFiles(String string, String string2) {
		// TODO Auto-generated method stub
		layoutConfigFile = string;
		setupConfigFile = string2;
		
	}
	
	public int getNumRows() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getNumColumns() {
		// TODO Auto-generated method stub
		return 0;
	}

	public BoardCell getCell(int i, int j) {
		// TODO Auto-generated method stub
		return grid[i][j];
	}
	
	public Room getRoom(char c) {
		return room;
	}

	public Room getRoom(BoardCell cell) {
		// TODO Auto-generated method stub
		return room;
	}
     
    
     

}
