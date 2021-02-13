
/**
 * @author Darwin
 *		Class that contains all the functions for a node that can be doubly linked.
 *
 *		
 * @param <T>
 */
public class DoubleNode<T> {
	
	private DoubleNode<T> next;
	private DoubleNode<T> prev;
	private T data;
	
	/**
	 * Inits by setting all values to null
	 */
	public DoubleNode() {
		data = null;
		next = null;
		prev = null;
		
	}
	
	public DoubleNode(T newData) {
		data = newData;
		
	}
	
	/**
	 * @return returns the node that is linked in "next"
	 */
	public DoubleNode<T> getNext(){
		return next;
	}
	
	/**
	 * @return the node that is linked in "prev"
	 */
	public DoubleNode<T> getPrev(){
		return prev;
		
	}
	
	/**
	 * @return the data stored in "data"
	 */
	public T getData() {
		return data;
	}
	
	/**
	 * @param nextNode
	 * sets what next is
	 */
	public void setNext(DoubleNode<T> nextNode) {
		next = nextNode;
	}
	
	/**
	 * @param prevNode
	 * sets what prev is
	 */
	public void setPrev(DoubleNode<T> prevNode) {
		prev = prevNode;
	}
	
	/**
	 * @param newData
	 * sets what data is.
	 */
	public void setData(T newData) {
		data = newData;
	}

}
