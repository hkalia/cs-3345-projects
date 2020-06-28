public class HeapMod<E extends Comparable<E>> {
    private java.util.ArrayList<E> list = new java.util.ArrayList<>();
    public static int totalComparisons = 0;
    public static int totalMovements = 0;

    /**
     * Create a default heap
     */
    public HeapMod() {
    }

    /**
     * Create a heap from an array of objects
     */
    public HeapMod(E[] objects) {
        for (int i = 0; i < objects.length; i++)
            add(objects[i]);
        totalComparisons++;
        totalMovements += 2;
    }

    /**
     * Add a new object into the heap
     */
    public void add(E newObject) {
        list.add(newObject); // Append to the heap
        totalMovements++;
        int currentIndex = list.size() - 1; // The index of the last node
        totalMovements++;
        while (currentIndex > 0) {
            int parentIndex = (currentIndex - 1) / 2;
            totalMovements++;
            // Swap if the current object is greater than its parent
            if (list.get(currentIndex).compareTo(
                    list.get(parentIndex)) > 0) {
                E temp = list.get(currentIndex);
                totalMovements++;
                list.set(currentIndex, list.get(parentIndex));
                totalMovements++;
                list.set(parentIndex, temp);
                totalMovements++;
            } else
                break; // the tree is a heap now
            totalComparisons++;
            currentIndex = parentIndex;
            totalMovements++;
            totalComparisons++;
        }
        totalComparisons++;
    }

    /**
     * Remove the root from the heap
     */
    public E remove() {
        if (list.size() == 0) return null;
        totalComparisons++;
        E removedObject = list.get(0);
        totalMovements++;
        list.set(0, list.get(list.size() - 1));
        totalMovements++;
        list.remove(list.size() - 1);
        totalMovements++;

        int currentIndex = 0;
        totalMovements++;
        while (currentIndex < list.size()) {
            totalComparisons++;
            int leftChildIndex = 2 * currentIndex + 1;
            totalMovements++;
            int rightChildIndex = 2 * currentIndex + 2;
            totalMovements++;

            // Find the maximum between two children
            if (leftChildIndex >= list.size()) break; // The tree is a heap
            totalComparisons++;
            int maxIndex = leftChildIndex;
            totalMovements++;
            if (rightChildIndex < list.size()) {
                if (list.get(maxIndex).compareTo(
                        list.get(rightChildIndex)) < 0) {
                    maxIndex = rightChildIndex;
                    totalMovements++;
                }
                totalComparisons++;
            }
            totalComparisons++;

            // Swap if the current node is less than the maximum
            if (list.get(currentIndex).compareTo(
                    list.get(maxIndex)) < 0) {
                E temp = list.get(maxIndex);
                totalMovements++;
                list.set(maxIndex, list.get(currentIndex));
                totalMovements++;
                list.set(currentIndex, temp);
                totalMovements++;
                currentIndex = maxIndex;
                totalMovements++;
            } else
                break; // The tree is a heap
            totalComparisons++;
        }
        totalComparisons++;

        return removedObject;
    }

    /**
     * Get the number of nodes in the tree
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Return true if heap is empty
     */
    public boolean isEmpty() {
        totalComparisons++;
        return list.size() == 0;
    }
}