package clueGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
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
		roomMap = new HashMap<Character, Room>();
		
		try {
			loadSetupConfig();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (BadConfigFormatException e) {
			System.out.println(e.getMessage());
		}
		
		
		try {
			loadLayoutConfig();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (BadConfigFormatException e) {
			System.out.println(e.getMessage());
		}
		
//		calcAdjLists();
		
	}
	
	public void loadSetupConfig() throws FileNotFoundException, BadConfigFormatException {
		File setupFile = new File(setupConfigFile);
		//System.out.println("Absolute path of setupConfigFile: " + setupFile.getAbsolutePath());

		Scanner reader = new Scanner(setupFile);
		while (reader.hasNextLine()) {
			String data = reader.nextLine();
			String[] arr = data.split(", ", 3);
			if (arr[0].equals("Room") || arr[0].equals("Space")) {
				Room newRoom = new Room(arr[1]);
				roomMap.put(arr[2].charAt(0), newRoom);
//				for (String s: arr) {
//					System.out.println(s);
//				}
			}
			
			//System.out.println(data);
		}
		for (Map.Entry<Character, Room> entry : roomMap.entrySet()) {
		    System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
		reader.close();
		
	}
	
	public void loadLayoutConfig() throws FileNotFoundException, BadConfigFormatException {
		try {
			File layoutFile = new File(layoutConfigFile);
			Scanner reader1 = new Scanner(layoutFile);
			boolean firstRun = true;
			while (reader1.hasNextLine()) {
				String data = reader1.nextLine();
				String[] cols = data.split("\\s*,\\s*"); // This regex will split on commas surrounded by any amount of whitespace
				numColumns = cols.length;
				System.out.println();
				numRows++;
				
			}
			reader1.close();

			
			grid = new BoardCell[numRows][numColumns];
			int tempRow = 0;
			int counter = 0;
			Scanner reader2 = new Scanner(layoutFile);
			while (reader2.hasNextLine()) {
				String data = reader2.nextLine();
				String[] splitRow = data.split("\\s*,\\s*");
				for (int tempCol = 0; tempCol < numColumns; tempCol++) {
					String splitCol = splitRow[tempCol];
					counter++;
					//System.out.println(splitCol);
					grid[tempRow][tempCol] = new BoardCell(tempRow, tempCol, splitCol);
				}
				tempRow++;
					
			}
			reader2.close();	
			System.out.println(counter);
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
		return numRows;
	}

	public int getNumColumns() {
		// TODO Auto-generated method stub
		return numColumns;
	}

	public BoardCell getCell(int i, int j) {
		// TODO Auto-generated method stub
		return grid[i][j];
	}
	
	public Room getRoom(char c) {
		return roomMap.get(c);
	}

	public Room getRoom(BoardCell cell) {
		// TODO Auto-generated method stub
		return room;
	}
     
    
     

}
