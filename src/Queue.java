import java.util.NoSuchElementException;
import java.util.LinkedList;


public class Queue<T> {
	private static class QueueNode<T> {
		private T data;
		private QueueNode<T> next;
		
		public QueueNode(T input) {
			data = input;
		}		
	}
	
	private QueueNode<T> first;
	private QueueNode<T> last;
	
	public void add(T item) {
		QueueNode<T> n = new QueueNode<T>(item);
		if (first == null) { //if queue is empty
			first = n;
			first.next = last;
		}
		else if (last == null) //if queue contains 1 node
			last = n;
		else { //if queue contains more than 1 node
			last.next = n;
			last = last.next;
		}
	}
	
	public T remove() {
		if (first == null) {
			throw new NoSuchElementException();
		}
		QueueNode<T> n = first;
		first = first.next;
		return n.data;
	}
	
	public T peek() {
		if (first == null) {
			throw new NoSuchElementException();
		}
		return first.data;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public static class MyQueue<T>{
		Stack<T> s = new Stack<T>();
		Stack<T> tempStack = new Stack<T>();
		public MyQueue(Stack<T> nodes) {
			s = nodes;
		}
		public void push(T input) {
			s.push(input); //push onto current stack of nodes
		}
		public T pop() {
			T node = null;
			while (!s.isEmpty()) {
				node = s.pop();
				if (s.isEmpty()) //if popped first node 
					break;
				else {
					tempStack.push(node); 
				}
			}
			//put back nodes from tempStack to s
			while (!tempStack.isEmpty()) {
				T tempNode = tempStack.pop();
				s.push(tempNode);
			}
			return node;
		}
	}
	
	public class AnimalShelter {
		private LinkedList<String> dogs = new LinkedList<String>();
		private LinkedList<String> cats = new LinkedList<String>();
		private LinkedList<String> allPets = new LinkedList<String>();
		
		public void enqueue(String pet, String type) {
			allPets.add(pet);
			if (type == "dog")
				dogs.add(pet);
			if (type == "cat")
				cats.add(pet);
		}
		
		public String dequeueAny() {
			
			String s = allPets.removeFirst();
			if (dogs.contains(s))
				dogs.remove(s);
			else if (cats.contains(s))
				cats.remove(s);
			return s;
			
		}
		
		public String dequeueDog() {
			String s = dogs.removeFirst();
			allPets.remove(s);
			return s;
		}
		public String dequeueCat() {
			String s = cats.removeFirst();
			allPets.remove(s);
			return s;
		}
		
		public void printPets() {
			for (String s: allPets) {
				System.out.println(s + " ");
			}
		}
	}
		
	
	public static void main(String[] args) {
		Stack<Integer> s = new Stack<Integer>();
		s.push(1);
		s.push(2);
		s.push(3);
		s.printNodes();
		Queue<Integer> q = new Queue<Integer>();
		Queue<Integer>.AnimalShelter a = q.new AnimalShelter();
		a.enqueue("first", "dog");
		a.enqueue("second", "cat");
		a.enqueue("third", "dog");
		System.out.println("Shelter contains: ");
		a.printPets();
		a.dequeueAny();
		System.out.println("Updated shelter: ");
		a.printPets();
		a.dequeueDog();
		System.out.println("Updated shelter: ");
		a.printPets();
		

		
		LinkedList<Integer> l = new LinkedList<Integer>();
	}
}
