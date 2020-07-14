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

        return cur;
    }

    void printPreOrder(AVLNode node) {
        if (node != null) {
            node.value.print();
            printPreOrder(node.left);
            printPreOrder(node.right);
        }
    }

    void printInOrder(AVLNode node) {
        if (node != null) {
            printInOrder(node.left);
            node.value.print();
            printInOrder(node.right);
        }
    }
}
