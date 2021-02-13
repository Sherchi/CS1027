/**
 * Position object for the snake and board elements 
 * @author Darwin
 *
 */
public class Position {

	private int positionRow;
	private int positionCol;
	
	/**
	 * Contsructor for Position with Row and Col
	 * @param positionRow
	 * @param positionCol
	 */
	public Position(int positionRow, int positionCol) {
		this.positionRow = positionRow;
		this.positionCol = positionCol;
		
	}
	/**
	 * returns the Row
	 * @return
	 */
	public int getRow() {
		return positionRow;
	
	}
	/**
	 * returns the Column
	 * @return
	 */
	public int getCol() {
		return positionCol;
		
	}
	/**
	 * sets the Row
	 * @param newRow
	 */
	public void setRow(int newRow) {
		this.positionRow = newRow;
		
	}
	/**
	 * Sets the Column
	 * @param newCol
	 */
	public void setCol(int newCol) {
		this.positionCol = newCol;
	}
	
	/**
	 * Checks if the position is equal to another position instance
	 * @param otherPosition
	 * @return
	 */
	public boolean equals(Position otherPosition) {
		if(this.getRow() == otherPosition.getRow() & this.getCol() == otherPosition.getCol()){
			return true;
			
		}else {
			return false;
			
		}
		
	}
}
