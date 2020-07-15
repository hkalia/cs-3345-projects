public class AVLNode<K extends Comparable<? super K>, V> {
    K key;
    V value;
    int height;
    AVLNode<K, V> left, right;

    public AVLNode(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
