
/**
 * @author Darwin
 *	The Stack that will contain all the mapCells.
 *	Contains functions that will push/pop/peek at elements in the stack as well as counting how many
 *		elements there are.
 *
 * @param <T>
 */
public class ArrayStack<T> implements ArrayStackADT<T> {

	
	/**
	 * Init Vars
	 * 
	 * Stack is the stack
	 * top is the length/height of the stack. Set to -1 because stack is initially empty
	 * initial Capacity is maximum amount of elements currently allowed for memory purposes
	 * sizeIncreaze/sizeDecrease are the amounts that the capacity will change when full or below 1/4th maximum
	 * 		capacity.
	 */
	private T[] stack;
	private int top = -1;
	private int initialCapacity;
	private int sizeIncrease;
	private int sizeDecrease;
		
	/**
	 * Init
	 * @param initialCap 	initial capacity
	 * @param sizeInc 		how much you want to increase by when maxed out
	 * @param sizeDec		how much you want to decrease by when excess space
	 */
	public ArrayStack(int initialCap, int sizeInc, int sizeDec) {
		initialCapacity = initialCap;
		sizeIncrease = sizeInc;
		sizeDecrease = sizeDec;
		top = 0;
		
		stack = (T[])(new Object[initialCap]);
		
	}
	
	/* (non-Javadoc)
	 * @see ArrayStackADT#push(java.lang.Object)
	 * 
	 * increaseStack is a private function I added to ArrayStack to increase the capacity
	 */
	public void push(T dataItem) {
		
		if (size() >= stack.length && (stack[stack.length-1] != null)) {
			increaseStack();

		}
		stack[top] = dataItem;
		top++;
		
	}

	/* (non-Javadoc)
	 * @see ArrayStackADT#pop()
	 * 
	 * decreaseStack is the private function I added to ArrayStack to decrease capacity.
	 * IncreaseStack is used here for when the maximum amount is reached but an element wishes to be popped
	 * Will throw an emptyStackException if the stack is empty
	 */
	public T pop() throws EmptyStackException {
		
		if(isEmpty()) {
			throw new EmptyStackException("Stack (Pop)");
			
		}
		if (size() < stack.length) {
			decreaseStack();
		}	
		
		if (size() >= stack.length && (stack[stack.length-1] != null)) {
			increaseStack();

		}
		
		top --;
		T result = stack[top];	
		
		return result;
	}

	/* (non-Javadoc)
	 * @see ArrayStackADT#peek()
	 * 
	 * Will throw an emptyStackException when the stack is empty telling where the issue occured
	 */
	public T peek() {
		if(isEmpty()) {
			throw new EmptyStackException("Stack (Peek)");
			
		}
		return stack[top-1];
		
	}

	/* (non-Javadoc)
	 * @see ArrayStackADT#isEmpty()
	 * 
	 */
	public boolean isEmpty() {
		
		return (top == 0);
	}
	
	/* (non-Javadoc)
	 * @see ArrayStackADT#size()
	 */
	public int size() {
		return top;
		
	}
	/**
	 * @return the current max capacity of the stack
	 */
	public int length() {
		return 	stack.length;
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String tempString = "";
		String result = "";
		for (int i = 0; i < top; i++) {
			tempString += (stack[i] + ", ");
		}
		
		return ("Stack: " + tempString.substring(0,tempString.length() - 2));
	}
	
	
	/**
	 * increases max capacity of the stack by creating a temp stack but with higher capcity then
	 * 	copying elements from the original stack, then replacing the original stack with the new stack
	 */
	private void increaseStack() {
		T[] tempStack = (T[])(new Object[stack.length + sizeIncrease]);
		for (int i = 0; i < stack.length; i++) {
			tempStack[i] = stack[i];
			
		} 
		stack = tempStack;
	}
	
	/**
	 * Decreases max capacity of the stack by creating a temp stack but with lower capcity then
	 * 	copying elements from the original stack, then replacing the original stack with the new stack
	 */
	private void decreaseStack() {
		if (size() <= stack.length/4 && stack.length > initialCapacity) {
			T[] tempStack = (T[])(new Object[stack.length - sizeDecrease]);
			for (int i = 0; i < tempStack.length; i++) {

				tempStack[i] = stack[i];
				
			} 
			stack = tempStack;
		}
	}
}
