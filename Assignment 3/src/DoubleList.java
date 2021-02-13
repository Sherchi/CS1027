
/**
 * @author Darwin
 *	DoubleList Class: Used for linking nodes together and removing links
 * @param <T>
 */
public class DoubleList<T> {
	
	private DoubleNode<T> head;
	private DoubleNode<T> rear;
	private int numDataItems;
	
	
	public DoubleList(){
		numDataItems = 0;
		head = null;
		rear = null;
		
	}
	
	/**
	 * @param index "index" of the linked list. Points to the node at this index
	 * @param newData	the data that is going to be placed into the node.
	 * @throws InvalidPositionException if the index is out of bounds
	 * 
	 * Creates a new node "newNode" that contains the data then links the node to other pre-existing nodes
	 * 		based off of the index given. 
	 * It goes through the nodes by starting at the "head", a node that points to the last indexing node and has no value.
	 * 		Additionally, the head and rear should are only used to make accessing the other node easier.
	 * 		the Link list should be something like this visually:
	 * 		
	 * 		REAR -> [] <-> [] ... [] <-> [] <- HEAD
	 * 
	 * But it goes through the list from the head.
	 * (I had to change which direction it went through due to the test harness given. Originally went from rear to head)
	 * 
	 */
	public void addData(int index, T newData) throws InvalidPositionException{
		if (index < 0 || index > numDataItems) {
			throw new InvalidPositionException("Invalid Index");
		}
		
		DoubleNode<T> current;
		DoubleNode<T> temp;
		
		DoubleNode<T> newNode = new DoubleNode<T>();
		newNode.setData(newData);
		
		/*
		 * Checks if there are any nodes that exist, if there are, then it will link 
		 * the nodes to the ones beside it based off of location. 
		 * 
		 * index 0 will insert the node at the beginning, replacing current links to the first node.
		 * index = numDataItems will insert at the end
		 * 
		 * else, it will go through from the head, until it finds the index and replace the links.
		 */
		if (numDataItems > 0){
			if (index == 0) {
				rear.getNext().setPrev(newNode);
				newNode.setNext(rear.getNext());
				newNode.setPrev(rear);
				rear.setNext(newNode);
				numDataItems += 1;
			}
			else if (index == numDataItems) {
				head.getPrev().setNext(newNode);
				newNode.setPrev(head.getPrev());
				newNode.setNext(head);
				head.setPrev(newNode);
				numDataItems += 1;
			}else {
				current = head.getPrev();	
				for (int i = index; i >= 0; i--) {			///Test Harness goes other way. Need to fix it.
					temp = current.getPrev();
					current = temp;
				
				}
				newNode.setPrev(current);
				newNode.setNext(current.getNext());
				current.getNext().setPrev(newNode);
				current.setNext(newNode);
				numDataItems += 1;
			}
			/*
			 * This is for if there are no nodes created yet, and it will set one up and make it be able to be 
			 * 	pointed at by both the head and rear.
			 */
		}else if (numDataItems == 0) {
			
			if (head == null) {
				head = new DoubleNode<T>();
				head.setPrev(newNode);
				if (numDataItems == 0) {
					numDataItems += 1;
				}
				
			}
			if (rear == null) {
				rear = new DoubleNode<T>();
				rear.setNext(newNode);
				if(numDataItems == 0) {
					numDataItems += 1;
				}
			}
		}
	}

	/**
	 * @param index
	 * @return the Node at index
	 * @throws InvalidPositionException
	 * 
	 * Find the node at index and returns it
	 */
	public DoubleNode<T> getNode(int index) throws InvalidPositionException{
		if (index < 0 || index > numDataItems -1) {
			throw new InvalidPositionException("Invalid Index");
		}
		DoubleNode<T> current;
		
		if (index == 0) {
			return rear.getNext();
		}
		
		if (index == numDataItems -1) {
			return head.getPrev();
		}
		current = rear.getNext();
		
		for (int i = 0; i <= index-1; i++) {
			current = current.getNext();
		}
		return current;
		
	}
	


	/**
	 * @param index
	 * @throws InvalidPositionException
	 * 
	 * This will remove a node by removing all of its links and setting the data inside the node to null.
	 * 
	 */
	public void removeData(int index) throws InvalidPositionException{
		if (index < 0 || index > numDataItems-1) {
			throw new InvalidPositionException("Invalid Index");
		}
		DoubleNode<T> temp;
		DoubleNode<T> current;
		/**
		 * Checks if there are more than 1 object in the linked List, if there is then it will set next and
		 * 	prev the node that is after the node being removed to the node before it and vice versa.
		 * 	Will then remove the node's links and set the data to null.
		 */
		if (numDataItems > 1) {
			if (index == 0) {
				temp = rear.getNext().getNext();
				rear.getNext().getNext().setPrev(rear);
				rear.getNext().setNext(null);
				rear.getNext().setPrev(null);
				rear.getNext().setData(null);
				rear.setNext(temp);
				
			}else if (index == numDataItems-1) {
				temp = head.getPrev().getPrev();
				head.getPrev().getPrev().setNext(head);
				head.getPrev().setNext(null);
				head.getPrev().setPrev(null);
				head.getPrev().setData(null);
				head.setPrev(temp);
				
			}else {
				current = rear;
				for (int i = 0; i <= index; i++) {
					current = current.getNext();
				}
				current.getNext().setPrev(current.getPrev());
				current.getPrev().setNext(current.getNext());
				current.setNext(null);
				current.setPrev(null);
				
			}
			/**
			 * If there is only one node, just remove the head/rear links to it and remove the data as well.
			 */
		}else {
			rear.getNext().setNext(null);
			rear.getNext().setPrev(null);
			rear.getNext().setData(null);
			rear.setNext(null);
			head.setPrev(null);
		}
		numDataItems -=1;
		
	}
	/**
	 * @param index
	 * @return the data at the node at index
	 * @throws InvalidPositionException
	 * 
	 * Simply returns the data in the node at the given index.
	 */
	public T getData(int index) throws InvalidPositionException{
		if (index < 0 || index > numDataItems) {
			throw new InvalidPositionException("Invalid Index");
		}
		
		DoubleNode<T> current;
		
		if (index == 0) {
			return rear.getNext().getData();
			
		}else if (index == numDataItems - 1) {
			return head.getPrev().getData();
			
		}else {
			current = rear;
			for (int i = 0; i <= index; i++) {
				current = current.getNext();

			}
			return current.getData();
		}
	}
	
	/**
	 * @param index
	 * @param newData
	 * @throws InvalidPositionException
	 * 
	 * Sets the data in the node at the given index to be newData
	 */
	public void setData(int index,T newData) throws InvalidPositionException{
		if (index < 0 || index > numDataItems) {
			throw new InvalidPositionException("Invalid Index");
		}
		
		DoubleNode<T> current;
		
		if (index == 0) {
			rear.getNext().setData(newData);
			
		}
		else if (index == numDataItems -1) {
			head.getPrev().setData(newData);
			
		}else {
			current = rear.getNext();
			for(int i = 0; i <= index; i++) {
				current = current.getNext();
				
			}
			current.setData(newData);
			
		}
		
		
	}

}
