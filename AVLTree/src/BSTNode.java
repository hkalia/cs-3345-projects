public class BSTNode<K extends Comparable<? super K>, V> {
    K key;
    V value;
    int height;
    BSTNode<K, V> left, right;

    public BSTNode(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
