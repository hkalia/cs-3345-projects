import java.util.Random;

public class RandomBinaryTree<E extends Comparable<E>> {
	private class Node implements Comparable<E> {
		public E key;
		int height;
		Node left;
		Node right;
		
		Node(E e){
			key = e;
			height = 0;
			left = null;
			right = null;
		}
		
		@Override
		public int compareTo(E o) {
			return key.toString().compareTo(o.toString());
			/**
			 * Regardless of the class of the key object, it can be compared
			 * and the trees order determined
			 */
		}
	}
	
	private Node root;
	private Random randomSeed;
	public boolean isBalanced;						// for driver main method
	public boolean isAscOrder;						// for driver main method
	
	RandomBinaryTree() {
		randomSeed = new Random();
		root = null;
		isBalanced = false;
		isAscOrder = true;
	}
	
	public void insert(E e) {
		root = insert(e, root);
	}
	
	private Node insert(E e, Node node) {
		int zeroOrOne = randomSeed.nextInt(2); 		// zeroOrone = {0,1}
		if (node == null)
			return new Node(e);
		if (zeroOrOne == 0) 						// if 0 go left
			node.left = insert(e,node.left);
		else if (zeroOrOne == 1)					// if 1 go right
			node.right = insert(e,node.right);
		return node;		
	}
	
	private int getBalanceFactor(Node node) {		// same as AVLTree
		if (node == null) 							
			return 0;
		int leftHeight = getHeight(node.left);
		int rightHeight = getHeight(node.right);
		return leftHeight - rightHeight;
	}
	
	private int getHeight(Node node) {				// same as AVLTree
		if (node == null)
			return -1;
		node.height = max(getHeight(node.left),getHeight(node.right)) + 1;
		return node.height;
	}
	
	public int max(int a, int b) {
		return a > b ? a : b;
	}
	
	private String isBalanced() {
		int treeBalance = getBalanceFactor(root);
		if (treeBalance > -2 && treeBalance < 2)
			this.isBalanced = true;					// balance is determined here
		return this.isBalanced == true ? "\n\tBalanced = true\n" : "\n\tBalanced = false\n";
	}
	
	private String inOrder(Node node) {				// same as AVLTree
		String str = "";
		if (node == null) 
			return str;
		str += inOrder(node.left);
		str += node.key.toString() + " ";
		str += inOrder(node.right);
		return str;
	}
	
	private String preOrder(Node node) {
		String str = "";
		if (node == null) 
			return str;
		str += node.key.toString() + " ";
		str += preOrder(node.left);
		str += preOrder(node.right);
		return str;
	}
	
	private String postOrder(Node node) {
		String str = "";
		if (node == null) 
			return str;
		str += postOrder(node.left);
		str += postOrder(node.right);
		str += node.key.toString() + " ";
		return str;
	}
	
	private String isAscendingOrder(String str) {
		String [] nodeStrArr = str.split(" ");
		String min = nodeStrArr[0];
		for (int i = 1; i < nodeStrArr.length; i++) { 
			if (nodeStrArr[i].compareTo(min) <= 0)
				this.isAscOrder = false;			// order is determined here
		}
		return this.isAscOrder == true ? "\n\n\tBST Order = true" : "\n\n\tBST Order = false";
	}
	
	@Override
	public String toString() {
		String inOrderStr = inOrder(root);
		String preOrderStr = "\n\tpreOrder list : " + preOrder(root);
		String postOrderStr = "\n\tpostOrder list: " + postOrder(root);
		String isAscending = isAscendingOrder(inOrderStr);
		return "\n\tInOrder list  : " + inOrderStr + preOrderStr + postOrderStr + isAscending + isBalanced();
	}
}
