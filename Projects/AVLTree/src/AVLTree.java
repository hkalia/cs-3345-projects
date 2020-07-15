public class AVLTree {
    AVLNode root;

    public void insert(Book book) {
        if (book == null) return;

        AVLNode newNode = new AVLNode(book);
        if (root == null) {
            root = newNode;
            return;
        }
        root = insertRecur(root, newNode);
    }

    private AVLNode insertRecur(AVLNode cur, AVLNode newNode) {
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

    private AVLNode rightRotate(AVLNode cur) {
        AVLNode leftChild = cur.left;
        cur.left = cur.left.right;
        leftChild.right = cur;

        updateHeight(cur);
        updateHeight(leftChild);

        return leftChild;
    }

    private AVLNode leftRotate(AVLNode cur) {
        AVLNode rightChild = cur.right;
        cur.right = cur.right.left;
        rightChild.left = cur;

        updateHeight(cur);
        updateHeight(rightChild);

        return rightChild;
    }

    private int getBalance(AVLNode node) {
        int leftHeight = node.left == null ? -1 : node.left.height;
        int rightHeight = node.right == null ? -1 : node.right.height;

        return leftHeight - rightHeight;
    }

    private void updateHeight(AVLNode node) {
        // to get height, find the larger height between the two children and add 1
        int leftHeight = node.left == null ? -1 : node.left.height;
        int rightHeight = node.right == null ? -1 : node.right.height;

        node.height = 1 + Math.max(leftHeight, rightHeight);
    }

    void printPreOrder(AVLNode node) {
        if (node != null) {
            System.out.format("current: %13s | left: %13s | right %13s\n",
                    node.key,
                    node.left == null ? "null" : node.left.key,
                    node.right == null ? "null" : node.right.key
            );
            printPreOrder(node.left);
            printPreOrder(node.right);
        }
    }

    void printInOrder(AVLNode node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.format("current: %13s | left: %13s | right %13s\n",
                    node.key,
                    node.left == null ? "null" : node.left.key,
                    node.right == null ? "null" : node.right.key
            );
            printInOrder(node.right);
        }
    }
}
