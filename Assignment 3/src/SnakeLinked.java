
/**
 * @author Darwin
 * Linked list for the snake's body
 * Uses linked nodes for the co-ordinates of the snake's body
 */
public class SnakeLinked {
	int snakeLength;
	
	DoubleList<Position> snakeBody;
	
	/**
	 * Init position of the snake
	 * @param row
	 * @param col
	 */
	public SnakeLinked(int row, int col) {
		snakeLength = 1;
		Position newPosition = new Position(row,col);
		snakeBody = new DoubleList<Position>();
		snakeBody.addData(0, newPosition);
		
	}
	
	/**
	 * 
	 * @return the length of the snake/how many nodes are being used
	 */
	public int getLength() {
		return snakeLength;
	}
	
	/**
	 * @param index
	 * @return returns the data at the index
	 */
	public Position getPosition(int index) {
		if (index < 0 || index > snakeLength-1) {
			return null;
			
		}else {
		return snakeBody.getData(index);
		
		}
	}
	/**
	 * checks if pos part of the snake
	 * @param pos
	 * @return
	 */
	public boolean snakePosition(Position pos) {
		DoubleNode<Position> current = snakeBody.getNode(0);
		for (int i = 0; i < snakeLength; i++) {
			current = snakeBody.getNode(i);
			if (current.getData().getRow() == pos.getRow() && current.getData().getCol() == pos.getCol()) {
				return true;
			}	

		}return false;
	}
	
	/**
	 * @param direction
	 * @return The new direction of the head after going up/down/left/right
	 */
	public Position newHeadPosition(String direction) {
		int tempRow = snakeBody.getData(0).getRow();
		int tempCol = snakeBody.getData(0).getCol();
		
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
	 * @param direction
	 * moves the snake and adds the new head location while removing the tail/last node
	 */
	public void moveSnakeLinked(String direction) {
		snakeBody.addData(0, newHeadPosition(direction));
		snakeBody.removeData(snakeLength);
	}
	
	/**
	 * Removes last node
	 */
	public void shrink() {
		snakeBody.removeData(snakeLength-1);
		snakeLength -= 1;
	}
	
	/**
	 * Same as the movement but doesn't remove last node.
	 * @param direction
	 */
	public void grow(String direction) {
		snakeBody.addData(0, newHeadPosition(direction));
		snakeLength += 1;
	}
	
	
}
