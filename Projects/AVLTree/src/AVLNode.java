public class AVLNode {
    String key;
    Book value;
    int height;
    AVLNode left, right;

    public AVLNode(Book book) {
        this.key = book.ISBN;
        this.value = book;
    }
}
