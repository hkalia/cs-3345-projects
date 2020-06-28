
public class SelectionSort {
    /**
     * The method for sorting the numbers
     */
    public static int totalComparisons = 0;
    public static int totalMovements = 0;

    public static void selectionSort(double[] list) {
        for (int i = 0; i < list.length - 1; i++) {

            // Find the minimum in the list[i..list.length-1]
            double currentMin = list[i];
            int currentMinIndex = i;

            for (int j = i + 1; j < list.length; j++) {
                if (currentMin > list[j]) {
                    currentMin = list[j];
                    currentMinIndex = j;
                }
            }

            // Swap list[i] with list[currentMinIndex] if necessary;
            if (currentMinIndex != i) {
                list[currentMinIndex] = list[i];
                list[i] = currentMin;
            }
        }
        totalComparisons++;
        totalMovements++;
    }

    public static void selectionSortMod(double[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            totalComparisons++;
            totalMovements++;
            // Find the minimum in the list[i..list.length-1]
            double currentMin = list[i];
            totalMovements++;
            int currentMinIndex = i;
            totalMovements++;

            for (int j = i + 1; j < list.length; j++) {
                if (currentMin > list[j]) {
                    currentMin = list[j];
                    currentMinIndex = j;
                    totalMovements += 2;
                }
                totalComparisons += 2;
                totalMovements++;
            }
            totalMovements++;
            totalComparisons++;

            // Swap list[i] with list[currentMinIndex] if necessary;
            if (currentMinIndex != i) {
                list[currentMinIndex] = list[i];
                list[i] = currentMin;
                totalMovements += 2;
            }
            totalComparisons++;
        }
        totalComparisons++;
        totalMovements++;
    }

    public static void main(String[] args) {
        double[] list = {-2, 4.5, 5, 1, 2, -3.3};
        selectionSort(list);
        for (int i = 0; i < list.length; i++)
            System.out.print(list[i] + " ");
    }
}