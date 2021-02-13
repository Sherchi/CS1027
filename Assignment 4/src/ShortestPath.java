
/**
 * @author Darwin
 *
 */
public class ShortestPath {
	CityMap cityMap;
	MapCell currentCell;
	MapCell testCell;
	OrderedCircularArray<MapCell> OArray = new OrderedCircularArray<MapCell>();
	
	
	
	public ShortestPath(CityMap theMap) {
		cityMap = theMap;
		
	}
		
	public void findShortestPath() {
		MapCell currentCell = cityMap.getStart();
		currentCell.markInList();
		OArray.insert(currentCell, 0);
		
		int distance;
		int predDist;
		currentCell = OArray.getSmallest();
		currentCell.markOutList();
		
		distance = 1 + testCell.getDistanceToStart();
		testCell = nextCell(currentCell);
		
		if (testCell.getDistanceToStart() > distance) {
			testCell.setDistanceToStart(distance);
			testCell.setPredecessor(currentCell);
			
		}
		predDist = currentCell.getDistanceToStart();
		if (testCell.isMarkedInList() && predDist < OArray.getValue(testCell)) {
			OArray.changeValue(testCell, predDist);
		}else if(!testCell.isMarkedInList()) {
			OArray.insert(testCell, predDist);
			testCell.markInList();
		}
	}
	
	public MapCell nextCell(MapCell cell) {
		if (cell.isNorthRoad()) {
			MapCell temp = cell.getNeighbour(0);
			if (!temp.isMarked()) {
				if(temp.isDestination() || temp.isNorthRoad() || temp.isIntersection()) {
					temp.markInList();
					return temp;
				}
			}
			return null;
		}
		if (cell.isSouthRoad()) {
			MapCell temp = cell.getNeighbour(2);
			if (!temp.isMarked()) {
				if(temp.isDestination() || temp.isSouthRoad() || temp.isIntersection()) {
					temp.markInList();
					return temp;
				}
			}
			return null;
		}
		if (cell.isEastRoad()) {
			MapCell temp = cell.getNeighbour(1);
			if (!temp.isMarked()) {
				if(temp.isDestination() || temp.isEastRoad() || temp.isIntersection()) {
					temp.markInList();
					return temp;
				}
			}
			return null;
		}
		if (cell.isWestRoad()) {
			MapCell temp = cell.getNeighbour(3);
			if (!temp.isMarked()) {
				if(temp.isDestination() || temp.isWestRoad() || temp.isIntersection()) {
					temp.markInList();
					return temp;
				}
			}
			return null;
		}
		
		
		for (int i = 0; i < 4; i++) {
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
			
			if (!cell.getNeighbour(i).isMarked() && !cell.getNeighbour(i).isBlock()) {
				MapCell temp = cell.getNeighbour(i);
				return temp;
				
			}
		}
		return null;
				
	}
}
