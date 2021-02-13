
/**
 * @author Darwin
 *Circular Array 
 * @param <T>
 */
public class OrderedCircularArray<T> implements SortedListADT<T> {

	/**Init Variables
	 * 
	 */
	private CellData[] list;
	private int front = 1;
	private int rear = 0;
	private int count;
	
	public OrderedCircularArray() {
		list = (CellData<T>[]) new CellData[5];

	}
	
	/* (non-Javadoc)
	 * @see SortedListADT#insert(java.lang.Object, int)
	 * 
	 *	 Inserts data cell with a value into the ordered array with and Id
	 *	if the list is empty, puts the cell as the front value
	 *	if the cell is the smallest, move all the other cells over by 1 then insert as the front
	 *	if they are in between, move all cells over by one starting from rear to index where it should be put in
	 */
	public void insert(T id, int value) {
		// TODO Auto-generated method stub
		if (size() == list.length) {
			expandCapacity();
			
		}
		CellData tempCell = new CellData(id,value);
		
		if (count == 0) {
			list[front] = tempCell;

		}else {
			for (int i = front; i < (list.length + front); i++) {
				if (list[i%list.length] != null) {
					if (value > list[i%list.length].getValue() && list[(i+1)%list.length] == null) {
						list[(i+1)%list.length] = tempCell;
						break;
	
					}
					//(i-1)%list.length
					else if (value < list[i%list.length].getValue() && list[(i-1)%list.length] == null) {
						for (int n = list.length; n > i-1; n--) {
							list[Math.abs(n%list.length)] = list[Math.abs((n-1)%list.length)];

						}
						for (int n = front-1; n > 0 ; n--) {
							list[Math.abs(n%list.length)] = list[Math.abs((n-1)%list.length)];
							
						}
						list[(i)%list.length] = tempCell;
						break;
						
					}else if (value >= list[i%list.length].getValue() && value <= list[i+1%list.length].getValue()) {
						if (rear > front) {
							for (int n = rear + 1; n > i; n--) {
								list[Math.abs(n%list.length)] = list[Math.abs((n-1)%list.length)];
	
							}
						}else {
							for (int n = rear + 1; n > 0 ; n--) {
								list[Math.abs(n%list.length)] = list[Math.abs((n-1)%list.length)];
								
							}
							for (int n = list.length; n > i-1; n--) {
								list[Math.abs(n%list.length)] = list[Math.abs((n-1)%list.length)];
	
							}

						}
						list[(i+1)%list.length] = tempCell;
						break;
					}
				}
			}
		}
		rear = (rear + 1)%list.length;
		count++;	
	}

	/**
	 * @param id
	 * @throws InvalidDataItemException
	 * 
	 * Removes the datacell with ID
	 */
	public void remove(T id) throws InvalidDataItemException{
		
		for (int i = 0; i < list.length;i++) {
			if (list[i] != null) {
				if (list[i].getId().equals(id)) {
					list[i]= null;
					for (int x = i; x < list.length;x++) {
						list[Math.abs(x%list.length)] = list[Math.abs((x+1)%list.length)];
						
					}
					for (int x = 0; x < front; x++) {
						list[Math.abs(x%list.length)] = list[Math.abs((x+1)%list.length)];

					}
					list[front-1] = null;
					rear = (rear - 1)%list.length;
	
					count--;
					break;
					
				}
			}
			if (i == list.length -1) {
				throw new InvalidDataItemException("No ID");
				
			}
		}
	}

	
	/* (non-Javadoc)
	 * @see SortedListADT#changeValue(java.lang.Object, int)
	 * changes value of the dataCell with ID to newValue
	 */
	public void changeValue(T id, int newValue) throws InvalidDataItemException {
		try {
			remove(id);
			insert(id, newValue);
			
		}catch (Exception e){
			throw new InvalidDataItemException("Invalid Data Item");
			
		}
	}

	/* (non-Javadoc)
	 * @see SortedListADT#getSmallest()
	 * gets the smallest value from the list
	 */
	public T getSmallest() throws EmptyListException{
		if (count == 0) {
			throw new EmptyListException("List is empty");
		}
		
		CellData temp = new CellData(list[front].getId(),list[front].getValue());
		
		list[front] = null;
		
		for (int i = front; i <list.length + front; i++) {
			if (list[Math.abs(i%list.length)] != null) {
				front = Math.abs(i%list.length);
				break;
			}
		}
		count --;
		return (T) temp.getId();
		
	}
	/* (non-Javadoc)
	 * @see SortedListADT#getValue(java.lang.Object)
	 * gets the value of ID
	 */
	public int getValue(T id) throws InvalidDataItemException {
		for (int i = 0; i < list.length;i++) {
			if (list[i] != null) {
				if (list[i].getId().equals(id)) {
					return list[i].getValue();
					
				}
			}
		}
		throw new InvalidDataItemException("No Id");
	}

	
	/* (non-Javadoc)
	 * @see SortedListADT#isEmpty()
	 * checks if the list is empty
	 */
	public boolean isEmpty() {
		if  (count == 0) {
			return true;
		}else {
			return false;

		}
	}

	
	/* (non-Javadoc)
	 * @see SortedListADT#size()
	 * returns the amount of datacells
	 */
	public int size() {
		return count;
	}

	
	/* (non-Javadoc)
	 * @see SortedListADT#getFront()
	 * 
	 * returns the front value
	 */
	public int getFront() {
		return front;
	}

	
	/* (non-Javadoc)
	 * @see SortedListADT#getRear()
	 * 
	 * returns the rear value
	 */
	public int getRear() {
		return rear;
	}
	
	/**
	 * Expands the capacity of the list. Doubles it and then rearrages the values in the list. 
	 */
	private void expandCapacity() {
		T[] larger = (T[])(new CellData[list.length * 2]);
		
		for (int i = 1; i < count; i++) 
			larger[i] = (T)list[i];
		larger[list.length] = (T)list[rear];
		
		rear = list.length;
		list = (CellData[]) larger;
	}
}
