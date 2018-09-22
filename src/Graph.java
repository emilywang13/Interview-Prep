import java.util.*;


import java.lang.reflect.Array;
	
public class Graph {
	public static ArrayList<Node> visitedNodes = new ArrayList<Node>();
	
	public class Node {
		public int level = 0;
		
		public String name;
		public ArrayList<Node> children = new ArrayList<Node>();
		public Node (String s) {
			name = s;
		}
	}
	
	void addEdge(Node a, Node b) {
		a.children.add(b);
	}
	
	public class BST {
		public BSTNode root = null;
		public class BSTNode {
			public int val;
			public BSTNode left;
			public BSTNode right;
			public BSTNode parent;
			
			public BSTNode(int input) {
				val = input;
				left = null;
				right = null;
				parent = null;
			}
			
		}
		
		public void printNodes(BSTNode node) {
			if (node == null) return;
			System.out.print(node.val + " ");
			printNodes(node.left);
			printNodes(node.right);
		}
		
		public void insert(BSTNode node) {
			if (root == null) root = node;
			//insert node as leaf node 
			else {
				BSTNode curr = root;
				while (curr != null) {
					//node is smaller than current node
					if (node.val < curr.val) {
						//if reach leaf node, add curr as left child
						if (curr.left == null) {
							curr.left = node;
							node.parent = curr;
							curr = null;
						}
						//else, keep moving along left subtree
						else
							curr = curr.left;
					}
					//node is larger than current node
					else {
						if (curr.right == null) {
							curr.right = node;
							node.parent = curr;
							curr = null;
						}
						else {
							curr = curr.right;
						}
					}
				}
			}
				
		}
		
	}
	
	
	
	//4.1: Implement BFS to return true if there is a path between two nodes
	void BFS(Node root) {
		LinkedList<Node> q = new LinkedList<Node>();
		q.add(root);
		visitedNodes.add(root); //keep track of visited nodes to prevent being stuck in cycles
		root.level = 1;
		while (q.size() != 0) {
			Node curr = q.poll();
						
			if (curr.children != null) {
				for(Node n : curr.children) {
					if (!visitedNodes.contains(n)) {
						visitedNodes.add(n);
						q.add(n);
						n.level = curr.level++; //children's level is parent's level + 1
					}
					
				}
			}
		}
	}
	
	//Depth-First Search
	void DFS(Node root) {
		if (root == null) return;
		visitedNodes.add(root);
		for (Node n: root.children) {
			if (!visitedNodes.contains(n))
				DFS(n);
		}
	}
	
	public boolean routeBetweenNodes(Node a, Node b) {
		BFS(a);
		return (visitedNodes.contains(b));
	}
	
	//4.2 Minimal Tree: Given sorted unique array, create a BST with minimal height
	public void minTree(int[] a) {
		BST tree = new BST();
		BST.BSTNode n = buildTree(a);
		tree.printNodes(n);
	
	}
	
	public BST.BSTNode buildTree(int[] a) {		
		BST tree = new BST();
		
		if (a.length == 0) return null;
		int index = a.length/2; 
		
		int nodeValue = a[index];
		//Set root to be the middle element in the array
		BST.BSTNode n = tree.new BSTNode(nodeValue);
		
		
		int[] leftArray = new int[index];
		int[] rightArray = new int[a.length-index-1];
		
		for (int i = 0; i < index; i++) {
			leftArray[i] = a[i];
		}
		
		for (int j = 0; j < rightArray.length; j++) {
			rightArray[j] = a[j + index + 1];
		}
		
		//Repeat recursively for elements to the left and right of root
		n.left = buildTree(leftArray);
		n.right = buildTree(rightArray);
		return n;
	}
	
	//4.3 Each depth in the tree is a linked list
	ArrayList<LinkedList<BST.BSTNode>> createLinkedLists(BST.BSTNode root) {
		ArrayList<LinkedList<BST.BSTNode>> result = new ArrayList<LinkedList<BST.BSTNode>>();
		LinkedList<BST.BSTNode> curr = new LinkedList<BST.BSTNode>();
		if (root != null) 
			curr.add(root);
		while (curr.size() > 0) {
			result.add(curr);
			LinkedList<BST.BSTNode> parents = curr;
			curr = new LinkedList<BST.BSTNode>();
			for (BST.BSTNode parent : parents) {
				//visit children
				if (parent.left != null)
					curr.add(parent.left);
				if (parent.right != null)
					curr.add(parent.right);
			}
		}
		return result;
			
	}
	
	void printLinkedLists(ArrayList<LinkedList<BST.BSTNode>> input) {
			for (LinkedList<BST.BSTNode> l: input) {
				for (BST.BSTNode n: l) {
					System.out.print(n.val + " ");
				}
				System.out.println("next linked list");
				
		
			}
		
	}
	
	//4.4 Check if tree is balanced-subtree heights cannot differ by more than one
	int checkHeight(BST.BSTNode n) {
		if (n==null) return 0;
		else return (Math.max(checkHeight(n.left)+1, checkHeight(n.right)+1));
	}
	
	boolean isBalanced(BST.BSTNode n) {
		if (n==null) return true;
		if (Math.abs(checkHeight(n.left)-checkHeight(n.right)) > 1) {
			return false;
		}
		else {
			return (isBalanced(n.left) && isBalanced(n.right));
		}
	}
	
	//4.5 Validate BST: Return true if tree is a BST. Make sure largest child in left subtree < node < 
	//smallest child in right subtree
	boolean validateBST(BST.BSTNode n) {
		if (n==null) return true;
		else if (n.left == null && n.right == null) return true;
		else if (n.left == null) return (n.val < minChild(n.right)); //node only has a right subtree
		else if (n.right == null) return (maxChild(n.left)<= n.val); //node only has a left subtree
		else return (maxChild(n.left) <= n.val && n.val < minChild(n.right)); 
	}
	
	int maxChild(BST.BSTNode n) {
		if (n==null) return 0;
		else return (Math.max(maxChild(n.left),Math.max(n.val, maxChild(n.right))));
	}
	
	int minChild(BST.BSTNode n) {
		if (n==null) return 0;
		else return (Math.min(minChild(n.left), Math.min(n.val, minChild(n.right))));
	}
	
	//4.6 Successor: Find the "next" node (in-order successor) of a given node in a BST
	BST.BSTNode successor(BST.BSTNode cur) {
		if (cur == null) return null;
		if (cur.right != null) return leftMostChild(cur.right);
		//node does not have right child. if left child, return parent. else, return root if bigger than cur node.
		else {
			//if left child
			if (cur.parent.left == cur) {
				return cur.parent;
			}
			BST.BSTNode root = cur;
			//if on right subtree and root is bigger, that's the successor. Else there is no successor.
			while (root != null) {
				root = root.parent;
				//done traversing parents and did not find larger node
				if (root == null) 
					break;
				if (root.val > cur.val) {
					return root;
				}
				
			}
			
		}
		return null;
	}
		
	BST.BSTNode leftMostChild(BST.BSTNode cur) {
		while (cur.left != null)
			cur = cur.left;
		return cur;
	}
	
	//4.7 Build Order: Given list of projects and dependencies, output the build order.
	Node[] buildOrder(Node[] projects, ArrayList<Node[]> a) {
		Node[] output = new Node[projects.length];
		Graph g = new Graph();
		//set up the graph. add edge from project to its dependency
		for (Node[] n: a) {
			g.addEdge(n[0], n[1]);
			System.out.println("Adding edge from " + n[0].name + "to " + n[1].name);
		}
		
		//explore the first project, moving along outgoing edges until the end of the path
		traverse(projects[0], g);
		
		return output;
	}
	
	void traverse(Node n, Graph g) {
		
	}
	
	
	public static void main(String[] args) {
		Graph g = new Graph();
		/*int[] input = {1,2,3};
		BST.BSTNode root = g.buildTree(input);
		System.out.println("root parent is" + root.parent);
		System.out.println("root.left parent is" + root.left.parent);
		System.out.println("root.right parent is" + root.right.parent);*/
		Graph.BST tree = g.new BST();
		BST.BSTNode n = tree.new BSTNode(5);
		BST.BSTNode n2 = tree.new BSTNode(3);
		BST.BSTNode n3 = tree.new BSTNode(7);
		BST.BSTNode n4 = tree.new BSTNode(1);
		BST.BSTNode n5 = tree.new BSTNode(4);
		BST.BSTNode n6 = tree.new BSTNode(6);


		tree.insert(n);
		tree.insert(n2);
		tree.insert(n3);
		tree.insert(n4);
		tree.insert(n5);
		tree.insert(n6);

		Node a = g.new Node("a");
		Node b = g.new Node("b");
		Node c = g.new Node("c");
		Node d = g.new Node("d");
		Node e = g.new Node("e");
		Node f = g.new Node("f");

		Node[] input = {a,b,c,d,e,f};
		ArrayList<Node[]> dep = new ArrayList<Node[]>();
		Node[] x = {d, a};
		Node[] x2 = {b,f};
		Node[] x3 = {d, b};
		Node[] x4 = {a, f};
		Node[] x5 = {c, d};
		dep.add(x);
		dep.add(x2);
		dep.add(x3);
		dep.add(x4);
		dep.add(x5);
	
		Node[] output = g.buildOrder(input,dep);
		
	
		
	}

}
