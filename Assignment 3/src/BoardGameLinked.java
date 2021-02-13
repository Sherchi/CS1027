
/**
 * @author Darwin
 *	Uses an array of linked lists to contain the board elements and goes through the arrays to compare information with
 *		the snake.
 */
public class BoardGameLinked {
	
	int boardLength;
	int boardWidth;
	SnakeLinked theSnake;
	DoubleList<String>[] board;
	
	public BoardGameLinked(String boardFile) {
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
		theSnake = new SnakeLinked(num5,num6);

		//sets the entire board to be empty
		board = new DoubleList[boardWidth];
		
		for (int i = 0; i <= boardWidth-1 ; i++) {
			for (int x = 0;x <= boardLength-1; x++) {
				board[i] = new DoubleList<String>();
				
			}
			for (int n = 0; n <= boardLength-1 ;n++) {
				board[i].addData(n, "empty");
			}
		}
		/**
		 * sets objects into the array of linked lists.
		 */
		while (in.endOfFile() == false) {
			int tempNum1 = in.readInt();
			int tempNum2 = in.readInt();
			String tempString = in.readString();
			
			board[tempNum1].getNode(tempNum2).setData(tempString);
		}
	}
	
	/**
	 * Gets the object at the row and column.
	 * @param row
	 * @param col
	 * @return
	 * @throws InvalidPositionException
	 */
	public String getObject(int row, int col) throws InvalidPositionException{
		if (row > boardWidth || col > boardLength || row < 0 || col < 0) {
			throw new InvalidPositionException("Invalid Position");
			
		}
		return board[row].getNode(col).getData();

	}
	
	/**
	 * Sets an object at the row and column.
	 * @param row
	 * @param col
	 * @param newObject
	 * @throws InvalidPositionException
	 */
	public void setObject(int row, int col, String newObject) throws InvalidPositionException{
		if (row > boardWidth || col > boardLength || row < 0 || col < 0) {
			throw new InvalidPositionException("Invalid Position");
			
		}
		board[row].getNode(col).setData(newObject);;

	}
	
	/**
	 * @return thesnake
	 */
	public SnakeLinked getSnakeLinked() {
		return theSnake;
	}
	
	/**
	 * @param newSnake the newSnake is set to the snake
	 */
	public void setSnakeLinked(SnakeLinked newSnake) {
		theSnake = newSnake;
		
	}
	
	/**
	 * @return returns the length of the board
	 */
	public int getLength() {
		return boardLength;
	}
	
	/**
	 * @return returns the width of the board.
	 */
	public int getWidth() {
		return boardWidth;
	}
	
}
