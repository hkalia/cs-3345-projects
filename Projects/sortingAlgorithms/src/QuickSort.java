
public class QuickSort {
    public static long totalComparisons = 0;
    public static long totalMovements = 0;

    public static void quickSort(int[] list) {
        quickSort(list, 0, list.length - 1);
    }

    private static void quickSort(int[] list, int first, int last) {
        if (last > first) {
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, last);
        }
    }

    /**
     * Partition the array list[first..last]
     */
    private static int partition(int[] list, int first, int last) {
        int pivot = list[first]; // Choose the first element as the pivot
        int low = first + 1; // Index for forward search
        int high = last; // Index for backward search

        while (high > low) {
            // Search forward from left
            while (low <= high && list[low] <= pivot)
                low++;

            // Search backward from right
            while (low <= high && list[high] > pivot)
                high--;

            // Swap two elements in the list
            if (high > low) {
                int temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }

        while (high > first && list[high] >= pivot)
            high--;

        // Swap pivot with list[high]
        if (pivot > list[high]) {
            list[first] = list[high];
            list[high] = pivot;
            return high;
        } else {
            return first;
        }
    }

    public static void quickSortMod(int[] list) {
        totalComparisons = 0;
        totalMovements = 0;
        quickSortMod(list, 0, list.length - 1);
    }

    private static void quickSortMod(int[] list, int first, int last) {
        if (last > first) {
            totalComparisons++;
            int pivotIndex = partitionMod(list, first, last);
            totalMovements++;
            quickSortMod(list, first, pivotIndex - 1);
            quickSortMod(list, pivotIndex + 1, last);
        }
    }

    /**
     * Partition the array list[first..last]
     */
    private static int partitionMod(int[] list, int first, int last) {
        int pivot = list[first]; // Choose the first element as the pivot
        int low = first + 1; // Index for forward search
        int high = last; // Index for backward search
        totalMovements += 3;

        while (high > low) {
            totalComparisons++;
            // Search forward from left
            while (low <= high && list[low] <= pivot) {
                totalComparisons += 2;
                low++;
            }

            // Search backward from right
            while (low <= high && list[high] > pivot) {
                totalComparisons += 2;
                high--;
            }

            // Swap two elements in the list
            if (high > low) {
                totalComparisons++;
                int temp = list[high];
                list[high] = list[low];
                list[low] = temp;
                totalMovements += 3;
            }
        }

        while (high > first && list[high] >= pivot) {
            totalComparisons += 2;
            high--;
        }

        // Swap pivot with list[high]
        if (pivot > list[high]) {
            totalComparisons++;
            list[first] = list[high];
            list[high] = pivot;
            totalMovements += 2;
            return high;
        } else {
            return first;
        }
    }

    /**
     * A test method
     */
    public static void main(String[] args) {
        int[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
        quickSort(list);
        for (int i = 0; i < list.length; i++)
            System.out.print(list[i] + " ");
    }
}