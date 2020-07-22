import java.util.Random;

public class BinarySearchTree<K extends Comparable<? super K>, V> {
    public long complexity = 0;
    public BSTNode<K, V> root;


    public void insert(K key, V value) {
        if (value == null) return;

        BSTNode<K, V> newNode = new BSTNode<>(key, value);
        if (root == null) {
            root = newNode;
            return;
        }
        root = insertRecur(root, newNode);
    }

    private BSTNode<K, V> insertRecur(BSTNode<K, V> cur, BSTNode<K, V> newNode) { //le recursion has arrived
        if (cur == null)
            return newNode;
        Random rand = new Random();
        int oneOrZero = rand.nextInt(2);
        if (oneOrZero == 1)
            cur.left = insertRecur(cur.left, newNode);
        else
            cur.right = insertRecur(cur.right, newNode);
        return cur;
    }

    public boolean isBSTOrder(BSTNode<K, V> cur, K min, K max) {
        if (cur == null) return true;
        if (cur.key.compareTo(min) < 0) {
            System.out.println("Not BST order: " + cur.key + " is smaller than its left child");
            return false;
        }
        if (cur.key.compareTo(max) > 0) {
            System.out.println("Not BST order: " + cur.key + " is larger than its right child");
            return false;
        }

        // min and max redefined by parent node key
        return isBSTOrder(cur.left, min, cur.key) && isBSTOrder(cur.right, cur.key, max);
    }

    public boolean isAVLStructure(BSTNode<K, V> cur) {
        if (cur == null) return true;
        int leftHeight;
        int rightHeight;
        if (cur.left == null)
            leftHeight = -1;
        else
            leftHeight = cur.left.height;
        if (cur.right == null)
            rightHeight = -1;
        else
            rightHeight = cur.right.height;
        int difference = Math.abs(leftHeight - rightHeight);
        if (difference < 2 && isAVLStructure(cur.left) && isAVLStructure(cur.right))
            return true;
        else {
            System.out.println("Not AVL structure: " + cur.key + " is not height balanced");
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
