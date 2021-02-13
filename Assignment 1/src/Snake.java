/**
 * Snake Class, contains snake's length and positions of the different parts of a snake's body
 * as well as growing/shrinking the snake.
 * @author Darwin
 *
 */
public class Snake {
	/**
	 * Init for the actual length of the snake visibly seen
	 * and the length of an array of positions that will contain the information on where the snake's body will be.
	 */
	private int snakeLength;
	private Position[] snakeBody;
	
	/**
	 * Initial position of the snake, with length 1.
	 * @param row
	 * @param col
	 */
	public Snake(int row, int col) {
		snakeLength = 1;
		snakeBody = new Position[5];
		snakeBody[0] = new Position(row,col);
	}
	/**
	 * gets the length of the snake
	 * @return
	 */
	public int getLength(){
		return snakeLength;
		
	}
	/**
	 * gets the position of a certain segment of the snake
	 * @param index
	 * @return
	 */
	public Position getPosition(int index) {
		if (index < 0 || index >snakeLength) {
			return null;
		}
		else {
			return snakeBody[index];
		}
	}
	/**
	 * reduces snake length by 1
	 */
	public void shrink() {
		snakeLength -= 1;

	}
	/**
	 * checks if the snake's body segment is equal to the position entered in "pos"
	 * @param pos
	 * @return
	 */
	public boolean snakePosition(Position pos) {
		for (int i = 0; i <= snakeLength -1 ; i++) {
			if (snakeBody[i].equals(pos)) {
				return true;

			}
		}
		return false;
		
	}
	/**
	 * returns a new co-ordinate for the head based off of the direction given
	 * @param direction
	 * @return
	 */
	public Position newHeadPosition(String direction) {	
		int tempRow = snakeBody[0].getRow();
		int tempCol = snakeBody[0].getCol();

		switch (direction) {
		case "right":
			tempCol += 1;
			return new Position(tempRow,tempCol);
			
		case "left":
			tempCol -= 1;
			return new Position(tempRow,tempCol);
			
		case "up":
			tempRow -= 1;
			return new Position(tempRow,tempCol);
			
		case "down":
			tempRow += 1;
			return new Position(tempRow,tempCol);
			
		default:
			return null;
		}

	}
	/**
	 * Moves the snake by shifting the snake's body positions in the array "snakeBody" up till the 2nd last index
	 * then adds the new head co-ordinates as the position in the first index.
	 * @param direction
	 */
	public void moveSnake(String direction) {
		for(int i = 0; i <= snakeLength - 2; i++ ) {
			snakeBody[snakeLength - i - 1] = snakeBody[snakeLength - i - 2];
			
		}
		snakeBody[0] = newHeadPosition(direction);
	}
	/**
	 * Grows the snake by shifting the snake's body positions in the array "snakeBody" up till the last index
	 * then adds the new head co-ordinates as the position in the first index.
	 * @param direction
	 */
	public void grow(String direction) {	
		snakeLength += 1;
		if (snakeLength >= snakeBody.length) {
			increaseArraySize();

		}
		for(int i = 0; i <= snakeLength - 1 ; i++ ) {
			snakeBody[snakeLength - i] = snakeBody[snakeLength - i - 1];
			
		}

		snakeBody[0] = newHeadPosition(direction);
		
	/**
	 * doubles the possible size of the snake when the length is equal to the length of the array that 
	 * contains the positions of the segments of the snake for memory purposes.
	 */
	}
	public void increaseArraySize(){
		Position[] tempArray = snakeBody;
		snakeBody = new Position[snakeBody.length *2];
		for (int i = 0; i< tempArray.length-1; i++) {
			snakeBody[i] = tempArray[i];
		}
	}
	


}
