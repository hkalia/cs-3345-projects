public class InsertionSort {

    public static long totalComparisons = 0;
    public static long totalMovements = 0;

    /**
     * The method for sorting the numbers
     */
    public static void insertionSort(int[] list) {
        for (int i = 1; i < list.length; i++) {
            /** insert list[i] into a sorted sublist list[0..i-1] so that
             list[0..i] is sorted. */
            int currentElement = list[i];
            int k;
            for (k = i - 1; k >= 0 && list[k] > currentElement; k--) {
                list[k + 1] = list[k];
            }

            // Insert the current element into list[k+1]
            list[k + 1] = currentElement;
        }
    }

    /**
     * The method for sorting the numbers
     */
    public static void insertionSortMod(int[] list) {
        totalComparisons = 0;
        totalMovements = 0;
        for (int i = 1; i < list.length; i++) {
            totalComparisons++;
            /** insert list[i] into a sorted sublist list[0..i-1] so that
             list[0..i] is sorted. */
            int currentElement = list[i];
            totalMovements++;
            int k;
            for (k = i - 1; k >= 0 && list[k] > currentElement; k--) {
                totalComparisons += 2;
                list[k + 1] = list[k];
                totalMovements++;
            }

            // Insert the current element into list[k+1]
            list[k + 1] = currentElement;
            totalMovements++;
        }
    }

    /**
     * A test method
     */
    public static void main(String[] args) {
        int[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
        insertionSort(list);
        for (int i = 0; i < list.length; i++)
            System.out.print(list[i] + " ");
    }
}