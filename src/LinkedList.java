//import java.util.Stack;
//
//public class LinkedList {
//	private Node head = null;
//	public class Node {
//		Node next = null;
//		int data;
//		public Node(int d) {
//			data = d;
//		}
//	}
//	
//	void appendToTail(Node n) {
//		Node end = n;
//		Node current = head;
//		if (current == null) {
//			head = end;
//			return;
//		}
//		else {
//		while (current.next != null) {
//			current = current.next;
//		}
//		current.next = end;
//		return;
//		}
//	}
//	
//	void printList(Node h) {
//		while (h != null) {
//			System.out.printf("%d ", h.data);
//			h = h.next;
//		}
//	}
//	
//	
//	//2.4 all values < x before those >= x
//	Node partition(LinkedList l, int x) {
//		Node less = new Node(-1);
//		Node more = new Node(-1);
//		Node lessheadtracker = less;
//		Node moreheadtracker = more;
//		Node cur = l.head;
//		while (cur != null) {
//			if (cur.data < x ) {
//				less.next = cur;
//				less = less.next; 
//				
//			}
//			else {
//				more.next = cur;
//				more = more.next;
//			}
//			cur = cur.next;
//		}
//		more.next = null;
//		less.next = moreheadtracker.next;
//		return lessheadtracker.next; 
//	}
//	
//	//2.5 Returns sum of two linked lists, doesn't work for lists of different size with carries
//	LinkedList sumLists(LinkedList l1, LinkedList l2) {
//		LinkedList sum = new LinkedList();
//		Node p1 = l1.head;
//		Node p2 = l2.head;
//		int carry = 0;
//		while (p1 != null && p2 != null) {
//			Node n = null;
//			if ((p1.data + p2.data + carry) > 9) {
//				if (carry == 0) {
//					n = new Node(p1.data + p2.data - 10);
//					carry = 1;
//				}
//				else
//					n = new Node(p1.data + p2.data - 9);
//			}
//			else {
//				if (carry == 1) {
//					n = new Node(p1.data + p2.data + 1);
//					carry = 0;
//				}
//				else {
//					n = new Node(p1.data + p2.data);
//				}
//			}
//				
//			p1 = p1.next;
//			p2 = p2.next;
//			sum.appendToTail(n);
//		}
//		if (p1 == null) { //if p2 is longer, add rest of p2 to sum
//			while (p2 != null) {
//				Node n = new Node(p2.data);
//				sum.appendToTail(n);
//				p2 = p2.next;
//			}
//		}
//		else {
//			while (p1 != null) {
//				Node n = new Node(p1.data);
//				sum.appendToTail(n);
//				p1 = p1.next;
//			}
//		}
//		return sum;
//	}
//	
//	//2.6 Check if list is palindrome 
//	//Put all list elements into stack
//	//Compare half the list with half the stack
//	//If anything is different, return false
//	boolean palindrome(LinkedList l) {
//		Stack<Node> s = new Stack<Node>();
//		Node curr = l.head;
//		int size = 0;
//		while (curr != null) {
//			s.push(curr);
//			curr = curr.next;
//			size++;
//			
//		}
//		for (int i = 0; i < size/2; i++) {
//			if (l.head.data != s.pop().data) {
//				return false;
//			}
//			l.head = l.head.next; 
//		}
//		return true;
//	}
//	
//	//2.7 Intersection, return intersection node if two linked lists intersect
//	//If last node is same, lists intersect
//	//If list sizes are same, intersection node is when they collide
//	//If list sizes are different, increment longer list by their difference
//	
//	Node intersection(LinkedList a, LinkedList b) {
//		Node n = a.new Node(0);
//		int asize = 1; //size of a
//		int bsize = 1; //size of b
//		Node curr = a.head; 
//		Node curr2 = b.head;
//		while (curr.next != null) {
//			asize++; //update size of a
//			curr = curr.next; //curr points to last node of a
//		}
//		while (curr2.next != null) {
//			bsize++; //update size of b
//			curr2 = curr2.next; //curr2 points to last node of b
//		}
//		
//		if (curr2 == curr) { //nodes only intersect if last node of lists is the same
//			curr2 = b.head; //move back pointer to start of b
//			curr = a.head; //move back pointer to start of a
//			if (asize < bsize) { 
//				for (int i = 0; i < bsize-asize; i++) {
//					curr2 = curr2.next; //iterate through longer list until sizes are same
//				}
//				
//			}
//			else {
//				for (int i = 0; i < asize-bsize; i++) {
//					curr = curr.next;
//				}
//			}
//			while (curr2 != curr) { //iterate through both lists until found intersecting node
//				curr2 = curr2.next;
//				curr = curr.next;
//			}
//		return curr2;
//			
//		}
//		else {
//			System.out.println("LinkedLists do not intersect");
//			return n;
//		}
//	}
//	//2.8 Return Node that is the start of the cycle
//	//Iterate 1 pointer through the list normally, iterate second every 2 nodes
//	//Where the pointers collide is in the cycle
//	//Collision point is k nodes away from start of cycle, which is also k away from head of list
//	Node loopy(LinkedList l) {
//		Node slow = l.head;
//		Node fast = l.head;
//		Node headpointer = l.head;
//		while (slow != fast) {
//			slow = slow.next;
//			fast = fast.next.next;
//			
//		}
//		while (slow != headpointer) {
//			slow = slow.next;
//			headpointer = headpointer.next;
//		}
//		return headpointer;
//	}
//	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		LinkedList l = new LinkedList();
//		l.appendToTail(l.new Node(7));
//		Node n = l.new Node(3);
//		l.appendToTail(n);
//		l.appendToTail(l.new Node(2));
//		l.appendToTail(l.new Node(1));
//		l.appendToTail(n);
//		
//		
//		System.out.println((l.loopy(l)).data);
//
//		
//		
//		
//	}
//
//}
