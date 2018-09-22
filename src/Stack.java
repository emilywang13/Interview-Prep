import java.util.*;
import java.lang.Exception;

public class Stack<T> {
	
	private static class StackNode<T>{
		private T data;
		private StackNode<T> next;
		private int min;
		public StackNode(T data) {
			this.data = data;
		}
	}
	protected StackNode<T> top;
	public void printNodes() {
		StackNode<T> point = top;
		System.out.println("Stack contains: ");
		while (point != null) {
			System.out.print(point.data + " ");
			point = point.next;
		}
		System.out.println("\n");
	}
	
	public T pop() {
		if (top == null) throw new EmptyStackException();
		T item = top.data;
		top = top.next;
		return item;
	}
	
	public void push(T item) {
		StackNode<T> node = new StackNode<T>(item);
		if (top != null) 
			node.next = top;
		top = node;
	}
	
	public T peek() {
		if (top == null) throw new EmptyStackException();
		return top.data;
	}
	
	public boolean isEmpty() {
		return top == null;
	}
	
	
	// 3.1 Use a single array to implement three stacks
	private static class ThreeStacks {
		private int numberOfStacks = 3;
		private int stackCapacity;
		private int[] values;
		private int[] sizes;
		
		public ThreeStacks(int stackSize) {
			stackCapacity = stackSize;
			values = new int[stackSize * numberOfStacks]; //array to hold all stack values
			sizes = new int[numberOfStacks]; //holds size of each stack
		}
		
		public void push(int stackNum, int value) throws Exception {
			if (sizes[stackNum] == stackCapacity) {
				throw new Exception("Stack out of bounds");
			}
			else {
				sizes[stackNum]++;
				values[indexOfTop(stackNum)] = value;
			}
		}
		
		public int pop(int stackNum) {
			if (isEmpty(stackNum)) {
				throw new EmptyStackException();
			}
			int value = values[indexOfTop(stackNum)];
			values[indexOfTop(stackNum)] = 0;
			sizes[stackNum]--;
			return value;
		}
		
		public int peek(int stackNum) {
			if (isEmpty(stackNum)) {
				throw new EmptyStackException();
			}
			return values[indexOfTop(stackNum)];
		}
		
		public boolean isEmpty(int stackNum) {
			return sizes[stackNum] == 0;
		}
		
		public int indexOfTop(int stackNum) {
			int offset = stackNum * stackCapacity; //move over by stack number * capacity
			int size = sizes[stackNum]; //move an additional size of the stack to get to end of stack which is top
			return offset + size - 1; //subtract 1 for correct index of array
		}
		
	}
	
	//3.2 Stack Min: Return minimum element in stack in O(1) time
	public void push2(T item) {
		StackNode<T> node = new StackNode<T>(item);
		if (top == null) //this is the first node being added
			node.min = (int) node.data;
		else if ((int)item < top.min) { //the node added is the new minimum 
			node.min = (int)item; 
		}
		else { //the node added is not the new minimum
			node.min = top.min;
		}
		node.next = top;
		top = node;
	}
	
	public void pop2() {
		//top.min = top.next.min;
		top = top.next;
	}
	
	public int getMin() {
		return top.min;
	}
	
	//3.3 Stack of Plates: If stack gets too high, create a new stack
	private static class SetOfStacks<T> {
		Stack<T> topStack;
		Stack<T> nextStack;
		private int stackCap;
		private int curSize;
		
		public SetOfStacks(int cap) {
			stackCap = cap; //stack size initialized to user's input
		}
		
		public void push(T item) {
			if (topStack == null) { //if no stacks are initialized, set topStack to a new stack
				Stack<T> s = new Stack<T>();
				StackNode<T> n = new StackNode<T>(item);
				s.top = n;
				topStack = s;
				curSize = 1;
			}
			else if (curSize < stackCap) { //if top stack can fit more nodes
				topStack.push(item);
				curSize++;
			}
			else { //if top stack is full
				//create a new stack
				Stack<T> s = new Stack<T>();
				StackNode<T> n = new StackNode<T>(item);
				s.top = n;
				
				//update new topStack
				nextStack = topStack;
				topStack = s;
				curSize = 1; //reset current stack size
			}
		}
		
		public int pop() {
			int value = (int)topStack.pop();
			curSize--;
			if (topStack == null) //if top stack is empty after removing node
				topStack = nextStack; //update the new top stack
			return value;
			
		}	
	}
	
	//3.5 Sort Stack: smallest items on top
	public void sortStack() {
		this.printNodes();
		Stack<T> tempStack = new Stack<T>(); //holds elements in decreasing order
		while (!this.isEmpty()) {
			StackNode<T> tempNode = new StackNode<T>(this.pop());
			while (!tempStack.isEmpty() && (int)tempNode.data < (int)tempStack.peek()) {
				this.push(tempStack.pop());
			}
			tempStack.push(tempNode.data); 
		}
		while (!tempStack.isEmpty()) { //push back all nodes from tempStack in reverse order
			this.push(tempStack.pop());
		}
		this.printNodes();
		
	}
	
	
	
	
	
	public static void main(String[] args) throws Exception {
		Stack<Integer> s = new Stack<Integer>();
		s.push(1);
		s.push(2);
		s.push(5);
		s.push(3);
		s.sortStack();
		
	}

}
