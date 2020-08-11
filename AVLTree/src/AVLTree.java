public class AVLTree<K extends Comparable<? super K>, V> {
    public long complexity = 0;
    public AVLNode<K, V> root;

    public void insert(K key, V value) {
        if (value == null) return;

        AVLNode<K, V> newNode = new AVLNode<>(key, value);
        if (root == null) {
            root = newNode;
            return;
        }
        root = insertRecur(root, newNode);
    }

    private AVLNode<K, V> insertRecur(AVLNode<K, V> cur, AVLNode<K, V> newNode) {
        complexity++;
        if (cur == null) return newNode;

        int cmp = newNode.key.compareTo(cur.key);
        if (cmp < 0)
            cur.left = insertRecur(cur.left, newNode);
        else if (cmp > 0)
            cur.right = insertRecur(cur.right, newNode);
        else
            return cur; // keys must be unique

        updateHeight(cur);
        int balance = getBalance(cur);

        // first L
        if (balance > 1) {
            // LR case
            if (newNode.key.compareTo(cur.left.key) > 0) {
                System.out.println("Imbalance occurred at inserting ISBN " + newNode.key + "; fixed in LeftRight Rotation");
                cur.left = leftRotate(cur.left);
                return rightRotate(cur);
            }
            // LL case
            System.out.println("Imbalance occurred at inserting ISBN " + newNode.key + "; fixed in Right Rotation");
            return rightRotate(cur);
        }
        // first R
        if (balance < -1) {
            // RL case
            if (newNode.key.compareTo(cur.right.key) < 0) {
                System.out.println("Imbalance occurred at inserting ISBN " + newNode.key + "; fixed in RightLeft Rotation");
                cur.right = rightRotate(cur.right);
                return leftRotate(cur);
            }
            // RR case
            System.out.println("Imbalance occurred at inserting ISBN " + newNode.key + "; fixed in Left Rotation");
            return leftRotate(cur);
        }

        return cur;
    }

    private AVLNode<K, V> rightRotate(AVLNode<K, V> cur) {
        AVLNode<K, V> leftChild = cur.left;
        cur.left = cur.left.right;
        leftChild.right = cur;

        updateHeight(cur);
        updateHeight(leftChild);

        return leftChild;
    }

    private AVLNode<K, V> leftRotate(AVLNode<K, V> cur) {
        AVLNode<K, V> rightChild = cur.right;
        cur.right = cur.right.left;
        rightChild.left = cur;

        updateHeight(cur);
        updateHeight(rightChild);

        return rightChild;
    }

    private int getBalance(AVLNode<K, V> node) {
        int leftHeight = node.left == null ? -1 : node.left.height;
        int rightHeight = node.right == null ? -1 : node.right.height;

        return leftHeight - rightHeight;
    }

    private void updateHeight(AVLNode<K, V> node) {
        // to get height, find the larger height between the two children and add 1
        int leftHeight = node.left == null ? -1 : node.left.height;
        int rightHeight = node.right == null ? -1 : node.right.height;

        node.height = 1 + Math.max(leftHeight, rightHeight);
    }

    public void printPreOrder(AVLNode<K, V> tree) {
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

    public void printInOrder(AVLNode<K, V> tree) {
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

    public void printLevelOrder(AVLNode<K, V> tree) {
        for (int i = 1; i < tree.height; i++)
            printGivenLevel(tree, i);
    }

    public void printGivenLevel(AVLNode<K, V> tree, int level) {
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
