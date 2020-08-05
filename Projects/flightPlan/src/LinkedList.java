import java.util.Iterator;

public class LinkedList<E> implements Iterable<Node<E>> {
    Node<E> head, tail;

    LinkedList(E head) {
        this.head = this.tail = new Node<>(head);
    }

    void add(E data) {
        Node<E> node = new Node<>(data);
        this.tail.next = node;
        this.tail = node;
    }

    void addFirst(E data) {
        Node<E> node = new Node<>(data);
        node.next = this.head;
        this.head = node;
    }

    @Override
    public Iterator<Node<E>> iterator() {
        return new LinkedListIterator<>(head);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");
        for (Node<E> cur : this) {
            str.append(cur.data.toString());
            str.append(", ");
        }
        str.append("]");
        return str.toString();
    }

    static class LinkedListIterator<E> implements Iterator<Node<E>> {
        Node<E> current;

        public LinkedListIterator(Node<E> head) {
            this.current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Node<E> next() {
            Node<E> tmp = current;
            current = current.next;
            return tmp;
        }
    }
}
