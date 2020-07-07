
public class AVLTree {
	private class AVLNode {
		String isbn;
		Book book;
		int height;
		AVLNode leftPtr;
		AVLNode rightPtr;
		
		AVLNode(Book newBook) {
			book = newBook;
			isbn = book.getISBN();
			height = 0;
			leftPtr = null;
			rightPtr = null;
		}
	}
	
	AVLNode root;
	
	AVLTree() {
		root = null;	
	}
	
	public void insert(Book newBook) {
		System.out.println("==========================================================");
		System.out.println(" Inserting ISBN: { {" + newBook.getISBN() + "}");
		root = insert(newBook, root);
		root = adjustTree(root);										// adjust treee 
		int balanceFactor = getBalanceFactor(root);
		System.out.println("} Printing Truncated ISBN List: {");
		System.out.println(this.toString());
		System.out.println("\n} Tree Balance: {\n");
		System.out.println("\tRoot Balance Factor = " + balanceFactor + "\n");
		if (balanceFactor > 1 || balanceFactor < -1)
			System.out.println("\tUNBALANCED\n");
		else
			System.out.println("\tTree is Balanced.\n");
		System.out.println("}\n=========================================================");
	}                     
	
	private AVLNode insert(Book book, AVLNode node) {
		/**
		 * BST insert
		 */
		if (node == null) 	{
			System.out.println("\n\t{"+ book.getISBN() + "} Inserted \n\n} Rotations: {\n");
			return new AVLNode(book);
		}
		
		if (book.compareTo(node.book) < 0) {			   // Look left
			System.out.println("\n\t{" + book.getISBN() + "} <---Left---- {" + node.isbn + "}");
			node.leftPtr = insert(book, node.leftPtr);
		}
		
		else if (book.compareTo(node.book) > 0) {		// Look right
			System.out.println("\n\t{" + node.isbn + "} ---Right---> {" + book.getISBN() + "}");
			node.rightPtr = insert(book, node.rightPtr);
		}
		
		else		
			return node;
		
		node = adjustTree(node);
		
		return node;	
	}
	
	private AVLNode adjustTree(AVLNode node) {
		/**
		 *  Adjust Height after insertion
		 */
		int balanceFactor = getBalanceFactor(node);
		boolean neededRotation = false;
		/**
		 * Print Initial Tree
		 */
		String rotationName = "\tNone Needed";			
		Book issueNodeBook = node.book;
		
		/**
		 * 	Balance checking and Rotations
		 * 
		 * 		4 different cases: (left-left), (left-right), (right-left), (right-right)
		 * 
		 * 			balanceFactor >  1    		 = (left - ?)
		 *			balanceFactor < -1	 		 = (right - ?)
		 * 			childBalanceFactor <  1  	 = (? - left)
		 * 			childBalanceFactor > -1  	 = (? - right)
		 *
		 */

			if (balanceFactor > 1 && getBalanceFactor(node.leftPtr) > 0) {	// case: left-left
				neededRotation = true;
				rotationName = "Right rotation";
				node = rotateRight(node);
				/** 
				 * case: left-left
				 * 		
				 *  issue node: a
				 * 
				 *	rotate a right  -->	 set node = b
				 *					 | 		 
				 *	 	  (a) -->	 |		 b  			
				 *		  /			 |		/ \
				 *		 b	 	   	 |     c   a
				 *	    /			 |
				 * 	   c			 |
				 */
			}
			
			
			if (balanceFactor < -1 && getBalanceFactor(node.rightPtr) < 0) { 	// case: right-right 
				neededRotation = true;
				rotationName = "Left rotation";
				node = rotateLeft(node);
				/**
				 * case: right-right
				 * 		
				 *  issue node: a
				 * 
				 * 	rotate a left   -->	 set node = b
				 * 		 			 |
				 * <-- (a)			 |		 b  			
				 * 		 \			 |		/ \
				 *		  b	 	   	 | 	   a   c
				 * 		   \		 |
				 * 			c		 |
				 */
			}
			
			if (balanceFactor > 1 && getBalanceFactor(node.leftPtr) < 0) { 	// case: left-right
				neededRotation = true;
				rotationName = "LeftRight rotation";
				node.leftPtr = rotateLeft(node.leftPtr);				// swap node.left and node.left.right (b and c)
				node = rotateRight(node);										
				 /**
				 * Case: left-right 
				 *
				 * 	rotate b left  ->   rotate a right 	->   set node = c
				 * 				   |					|	 
				 * 		 a		   |	      (a) -->	|  	   c  			
				 * 		/		   |		  /		    |	  / \
				 * <-- (b)  	   |	     c			| 	 b   a
				 * 		\          |     	/			|
				 * 		 c 		   |	   b			|
				 */
			}
			
			
			if (balanceFactor < -1 && getBalanceFactor(node.rightPtr) > 0) {	// case: right-left
				neededRotation = true;
				rotationName = "RightLeft rotation";
				node.rightPtr = rotateRight(node.rightPtr);				// creates right-right case
				node = rotateLeft(node);										
				/** 		 
				 * Case: right-left
				 * 
				 * 	rotate b right  ->   rotate a left 	->  return c
				 * 		 			|					|
				 * 		 a			|  <-- (a)		    |     c   			
				 * 		  \			|	     \			|	 / \
				 *		  (b) -->	|	      c 		|   a   b
				 * 		  /			|		   \		|
				 * 		 c			|			b		|
				 */
			}	
	
		node.height = getHeight(node);
		
		if (neededRotation)
			System.out.println("\tImbalance at ISBN: {" + issueNodeBook.getISBN() + 
							   "}.\n\n\tResolved by " + rotationName + ".\n");
		
		return node;
	}
	
	private AVLNode rotateRight(AVLNode a) {
		if (a == null) {
			System.out.println("cannot right rotate null nodes");
		}
		System.out.println("\tRotated {" + a.isbn + "} right\n");
		System.out.println("\tLeft Child becomes Parent: {" + a.leftPtr.isbn + "} \n");
		AVLNode b = a.leftPtr;
		AVLNode n3 = b.rightPtr;
		a.leftPtr = n3;
		b.rightPtr = a;
		return b;	
		/**
		 * Note: a and b must not null
		 *  rotateRight(a)	->   shuffle (n3)  -> 	attach right and return (b)
		 *  				|				   |
		 * 	       (a)	   	|                  | 		 (b)
		 * 		   / \		|	    \ 	       |	    /   \
		 * 		 (b)  n4	|	    (a)        |	   c    (a)  
		 * 		 / \ 		|		/ \		   |  	  / \   /  \
		 * 		c	(n3)	|	  (n3) n4	   |  	 n1 n2 (n3) n4			
		 *	   / \			|				   |
		 *	  n1 n2			|				   |
		 */
	}
	

	private AVLNode rotateLeft(AVLNode a) {
		if (a == null) {
			System.out.println("cannot left rotate null nodes");
		}
		System.out.println("\tRotated {" + a.isbn + "} left\n");
		System.out.println("\tRight Child becomes Parent: {" + a.rightPtr.isbn + "} \n");
		AVLNode b = a.rightPtr;
		AVLNode n2 = b.leftPtr;
		a.rightPtr = n2;
		b.leftPtr = a;
		return b;
		/** 
		 *   rotateLeft(a)	->   shuffle (n2)	->    attach left and return (b)
		 *					| 					| 
		 * 	      (a)	    |                   |   	  (b)
		 * 	      / \		|	    /	     	|   	 /   \ 	
		 * 		 n1 (b)	  	|	   (a) 			|      (a)    c
		 * 		 	/ \		|	  / \		 	|      / \   /  \      
		 * 		  (n2) c	|	 n1 (n2)	   	| 	 n1 (n2) n3 n4	
		 * 			  / \	|					|
		 *			n3  n4	|					|
		 */
	}
	
	
	private int getHeight(AVLNode node) {
		if (node == null) 																// null children are at height = -1
			return -1;
		
		node.height = max(getHeight(node.leftPtr), getHeight(node.rightPtr)) + 1;		// update height
		return node.height;
		/**
		 * 	getHeight(a):     
		 * 
		 * 	Height = max(getHeight(n1), getHeight(n2)) + 1 
		 * 	null height = -1
		 *   
		 *   recursively find children height until leaf node with height 0
		 *   
		 * 		 (a)	 |    (n1)    (n2)    ...   (leaf)	 
		 * 		 / \	 |	  / \	  / \	         / \
		 * 		n1  n2   |   n3 n4	 n5 n6	       null null
		 */
	}
	
	
	private int getBalanceFactor(AVLNode node) {
		if (node == null) 
			return 0;
		node.height = getHeight(node);
		int leftHeight = getHeight(node.leftPtr);
		int rightHeight = getHeight(node.rightPtr);
		return leftHeight - rightHeight;
		/**
		 *  return n.balanceFactor = n.left.height - n.right.height
		 *  
		 *  if n.balanceFactor != [-1, 0, 1] : then tree is unbalanced  
		 *  if n.balanceFactor  < -1 		 : then right tree is causing imbalance, rotate left
		 *  if n.balanceFactor  >  1 		 : then left  tree is causing imbalance, rotate right
		 */
	}
	
	public int max(int a, int b) {
		return a > b ? a : b; 
	}
	
	private String inOrder(AVLNode node) { 		// left-parent-right		
		String str = "";
		if (node == null) 
			return "";
		
		str += inOrder(node.leftPtr);
		str += "{" + node.isbn.substring(0,3) + "}, ";
		str += inOrder(node.rightPtr);
	
		return str;
	}
	
	private String preOrder(AVLNode node) { 	// parent-left-right		
		String str = "";
		if (node == null) 
			return "";
		
		str += "{" + node.isbn.substring(0,3) + "}, ";
		str += preOrder(node.leftPtr);
		str += preOrder(node.rightPtr);
		
		return str;
	}
	
	private String postOrder(AVLNode node) {  	// left-right-parent		
		String str = "";
		if (node == null) 
			return "";
		
		str += postOrder(node.leftPtr);
		str += postOrder(node.rightPtr);
		str += "{" + node.isbn.substring(0,3) + "}, ";
		
		return str;
	}

	@Override
	public String toString() {
			
		String pre = "PreOrder : " + preOrder(root);
		String in = "inOrder  : " + inOrder(root);
		String post = "postOrder: " + postOrder(root);
		String treeStr = "\n\t" + in + "\n\t" + pre + "\n\t" + post;
		return treeStr;
	}
}