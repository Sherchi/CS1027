
/**
 * @author Darwin
 *
 */
public class Path {
	
	Map cityMap;
	MapCell currentCell;
	MapCell testCell;
	ArrayStack cellStack = new ArrayStack(10,5,5);
	boolean found = false;
	
	/**
	 * @param theMap
	 * sets the map as theMap using map.java
	 */
	public Path (Map theMap) {
		cityMap = theMap;
		
	}
	
	/**
	 * Calls the algorithm that finds if a path is possible and marks previously visited squares
	 * as well as checks if the destination has been reached.
	 * @param testCell is the cell being checked and will usually be entered into the nextCell function to return the
	 * 			most optimal cell
	 * @param currentCell is the current cell we are residing in
	 */
	public void findPath() {
		/**
		 * Sets the testCell and currentCell to the starting location and adds that location to the stack
		 */
		testCell = cityMap.getStart();
		currentCell = testCell;
		cellStack.push(testCell);
		/**
		 * Will continue to try and find a path until destination is reached, or if there are no moves left.
		 */
		while (!currentCell.isDestination()) {	
			
			/**
			 * If the next possible move exists, push it onto the stack and mark it
			 * if there is no possible move (null value), pop the top cell off and mark it out
			 */
			if(nextCell(testCell) != null) {
				cellStack.push(nextCell(testCell));
				nextCell(testCell).markInStack();	
			}else {				
				cellStack.pop();
				testCell.markOutStack();
			}
			
			/**
			 * If there is no path being recorded, and there are no movements possible, set the possibility of
			 * reaching the destination to false and break out of the loop
			 */
			if (cellStack.isEmpty() && nextCell(currentCell) == null) {
				found = false;
				break;
			}
			/**
			 * if there still is a possible path, set the testCell to the topmost cell then
			 * move our location to that cell
			 */
			testCell = (MapCell) cellStack.peek();
			currentCell = testCell;
			
			/**
			 * if we are standing on the destination, set found to true
			 */
			if (currentCell.isDestination()) {
				found = true;
			}
			
			
		}
		/**
		 * prints out the result of the path + distance based off of condition
		 */
		if (found || currentCell.isDestination()) {
			System.out.println("Path was found with " + (cellStack.size() - 2) + " cells from the starting location");
		}else {
			System.out.println("No Path was Found");

		}
	}
	
	/**
	 * Algorithm to find a path that can reach the destination.
	 * It first checks if we are on a directional road, and if the next cell is able to be traversed
	 * then it goes through the priority of Destination, Intersection, and available Directional road 
	 * and chooses one based off of a clockwise order starting from the north.
	 * @param cell
	 * @return
	 */
	private MapCell nextCell(MapCell cell) {
		
		/**
		 * Checks which directional road it is and if the next move is possible, then return that next move
		 * else return null
		 * 
		 * Should have probably done this in a switch statement but ¯\_(|||)_/¯
		 */

		if (cell.isNorthRoad()) {
			MapCell temp = cell.getNeighbour(0);
			if (!temp.isMarked()) {
				if(temp.isDestination() || temp.isNorthRoad() || temp.isIntersection()) {
					return temp;
				}
			}
			return null;
		}
		if (cell.isSouthRoad()) {
			MapCell temp = cell.getNeighbour(2);
			if (!temp.isMarked()) {
				if(temp.isDestination() || temp.isSouthRoad() || temp.isIntersection()) {
					return temp;
				}
			}
			return null;
		}
		if (cell.isEastRoad()) {
			MapCell temp = cell.getNeighbour(1);
			if (!temp.isMarked()) {
				if(temp.isDestination() || temp.isEastRoad() || temp.isIntersection()) {
					return temp;
				}
			}
			return null;
		}
		if (cell.isWestRoad()) {
			MapCell temp = cell.getNeighbour(3);
			if (!temp.isMarked()) {
				if(temp.isDestination() || temp.isWestRoad() || temp.isIntersection()) {
					return temp;
				}
			}
			return null;
		}
		
		/**
		 * This goes checks if the next possible cell is a destination, intersection, or directional road
		 * then chooses the option highest on the list clockwise from the north.
		 * Also checks if that cell has been visited before or not, and if it is a legal square/not the edge of the
		 * map.
		 * 
		 * If none of the moves are legal, return a null value.
		 */
		for (int n = 0; n < 3; n++) {
			for (int i = 0; i < 4; i++) {
				if(cell.getNeighbour(i)!= null){
				if (!cell.getNeighbour(i).isMarked()) {
					/**
					 * Destination check
					 */
					if (n == 0) {
						if (cell.getNeighbour(i).isDestination()) {
							
							return cell.getNeighbour(i);
						}
					}
					/**
					 * Intersection check
					 */
					if (n == 1) {
						if (cell.getNeighbour(i).isIntersection()) {
							return cell.getNeighbour(i);
						}
					}
					/**
					 * Directional road check, and seeing if it is legal for us to move onto that road.
					 */
					if (n == 2) {
						if (cell.getNeighbour(i).isNorthRoad() && i == 0) {
							return cell.getNeighbour(i);
						}
						if (cell.getNeighbour(i).isSouthRoad() && i == 2) {
							return cell.getNeighbour(i);
							
						}
						if (cell.getNeighbour(i).isEastRoad() && i == 1) {
							return cell.getNeighbour(i);
								
						}
						if (cell.getNeighbour(i).isWestRoad() && i == 3){
							return cell.getNeighbour(i);
						}
					}
				}
			}
			}
		}
		return null;
		
		
	}
}
