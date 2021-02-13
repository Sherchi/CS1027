/**
 * BoardGame class for all the information of the board that the snake is played on
 * @author Darwin
 * Std Num: 250959696
 */
public class BoardGame {
	/**
	 * Init vars
	 */
	private int boardLength;
	private int boardWidth;
	
	private String[][] matrix;
	private Snake theSnake;
	/**
	 * Reads in info from a board text file
	 * @param boardFile
	 */
	public BoardGame(String boardFile) {
		MyFileReader in;
		
		//Skips first 2 lines
		in = new MyFileReader(boardFile);
		int num1 = in.readInt();
		int num2 = in.readInt();
		
		//3rd and 4th num set to length and width
		int num3 = in.readInt();
		boardLength = num3;
		int num4 = in.readInt();
		boardWidth = num4;
		
		//5th and 6th numbers set to snake init position
		int num5 = in.readInt();
		int num6 = in.readInt();
		theSnake = new Snake(num5,num6);

		//sets the entire board to be empty
		matrix = new String[boardWidth][boardLength];
		for (int i = 0; i <= boardWidth-1 ; i++) {
			for (int x = 0;x <= boardLength-1; x++) {
				matrix[i][x] = "empty";
				
			}
		}
		
		//reads every 3 lines and changes info on what that square contains
		while (in.endOfFile() == false) {
			int tempNum1 = in.readInt();
			int tempNum2 = in.readInt();
			String tempString = in.readString();
			
			matrix[tempNum1][tempNum2] = tempString;
			
		}

	}
	/**
	 * Gets the matrix that contains info of the board
	 * @param row
	 * @param col
	 * @return
	 */
	public String getObject(int row, int col) {
		return matrix[row][col];
	
	}
	
	/**
	 * sets the matrix
	 * @param row
	 * @param col
	 * @param newObject
	 */
	void setObject(int row, int col, String newObject) {
		matrix[row][col] = newObject;
		
	}
	
	/**
	 * 
	 * @return the snake
	 */
	public Snake getSnake() {
		return theSnake;
	}
	/**
	 * gets the lenght of board
	 * @return
	 */
	public int getLength() {
		return boardLength;
		
	}
	/**
	 * gets width of board
	 * @return
	 */
	public int getWidth() {
		return boardWidth;
		
	}
	/**
	 * gets the type of object in that index of the matrix
	 * @param row
	 * @param col
	 * @return
	 */
	public String getType(int row, int col) {
		return matrix[row][col];
		
	}
}
