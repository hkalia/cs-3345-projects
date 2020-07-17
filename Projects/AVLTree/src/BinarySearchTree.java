public class BinarySearchTree<K extends Comparable<? super K>, V> {
    public long complexity = 0;
    public BSTNode<K, V> root;

    public void insert(BSTNode<K, V> root, K key, V value) { //le recursion has arrived
        if (value == null) return;
        BSTNode<K, V> newNode = new BSTNode<>(key, value);
        if (root == null) {
            root = newNode;
            return;
        }
        else if(key==root.key) {
        	root.value = value;
        }
        else if(key.compareTo(root.key) < 0)  {
        	insert(root.left, key, value);
        }
        else {
        	insert(root.right, key, value);
        }
    }

    private int getBalance(BSTNode<K, V> node) {
        int leftHeight = node.left == null ? -1 : node.left.height;
        int rightHeight = node.right == null ? -1 : node.right.height;

        return leftHeight - rightHeight;
    }

	public boolean isBSTOrder(BSTNode<K, V> cur, K min, K max) { 
		if (cur == null) return true;
		if (cur.key.compareTo(min) < 0) {
			System.out.println("Not BST order: " + cur.key + "is smaller than its left child");
			return false;
		}		
		if (cur.key.compareTo(max) > 0) {
			System.out.println("Not BST order: " + cur.key + "is larger than its right child");
			return false;	
		}
		
		// min and max redefined by parent node key
		return isBSTOrder(cur.left, min, cur.key) && isBSTOrder(cur.right, cur.key, max);  
	}
	
	public boolean isAVLStructure(BSTNode<K, V> cur) {
		if (cur == null) return true;
		
		int difference = Math.abs(cur.left.height - cur.right.height);
		if (difference < 2 && isAVLStructure(cur.left) && isAVLStructure(cur.right)) 
			return true;
		else {
			System.out.println("Not AVL structure: " + cur.key + "is not height balanced");
			return false;
		}
	}


    public void printPreOrder(BSTNode<K, V> tree) {
        if (tree != null) {
            System.out.format("current: %13s | left: %13s | right %13s\n",
                    tree.key,
                    tree.left == null ? "null" : tree.left.key,
                    tree.right == null ? "null" : tree.right.key
            );
            printPreOrder(tree.left);
            printPreOrder(tree.right);
        }
    }

    public void printInOrder(BSTNode<K, V> tree) {
        if (tree != null) {
            printInOrder(tree.left);
            System.out.format("current: %13s | left: %13s | right %13s\n",
                    tree.key,
                    tree.left == null ? "null" : tree.left.key,
                    tree.right == null ? "null" : tree.right.key
            );
            printInOrder(tree.right);
        }
    }

    public void printLevelOrder(BSTNode<K, V> tree) {
        for (int i = 1; i < tree.height; i++)
            printGivenLevel(tree, i);
    }

    public void printGivenLevel(BSTNode<K, V> tree, int level) {
        if (tree == null) return;
        if (level == 1)
            System.out.format("current: %13s | left: %13s | right %13s\n",
                    tree.key,
                    tree.left == null ? "null" : tree.left.key,
                    tree.right == null ? "null" : tree.right.key
            );
        else if (level > 1) {
            printGivenLevel(tree.left, level - 1);
            printGivenLevel(tree.right, level - 1);
        }
    }


}
