class Node<E> {
    E data;
    Node<E> next;

    Node(E data) {
        this.data = data;
        this.next = null;
    }

    public Node(E data, Node<E> next) {
        this.data = data;
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" + this.data + ", " + this.next + "}";
    }
}
