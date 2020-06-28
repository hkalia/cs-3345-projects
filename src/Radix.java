import java.io.*;
import java.util.*;

class Radix {
	
    public static int totalComparisons = 0;
    public static int totalMovements = 0;
    
    static void radixsort(int arr[], int n) { // Find the maximum number to know number of digits
        int m = getMax(arr, n);
        for (int exp = 1; m / exp > 0; exp *= 10)
            countSort(arr, n, exp);
    }
    
    static int getMax(int arr[], int n) {
        int mx = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > mx)
                mx = arr[i];
        return mx;
    }

    static void countSort(int arr[], int n, int exp) {
        int output[] = new int[n];
        int i;
        int count[] = new int[10];
        Arrays.fill(count, 0);
        for (i = 0; i < n; i++)
            count[(arr[i] / exp) % 10]++;
        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];
        // Build the output array
        for (i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }
        for (i = 0; i < n; i++)
            arr[i] = output[i];
    }

    static void radixsortMod(int arr[], int n) { // Find the maximum number to know number of digits
        int m = getMaxMod(arr, n);
        totalMovements++;
        for (int exp = 1; m / exp > 0; exp *= 10) {
        	countSortMod(arr, n, exp);
        	totalMovements++;
        	totalComparisons++;
        }
        totalMovements++;
        totalComparisons++;
        
            
    }
    
    static int getMaxMod(int arr[], int n) {
        int mx = arr[0];
        totalMovements++;
        for (int i = 1; i < n; i++) {
        	totalMovements++;
        	totalComparisons++;
            if (arr[i] > mx) {
            	mx = arr[i];
            	totalMovements++;
            }
            totalComparisons++;
        }
        totalMovements++;
        totalComparisons++;
        return mx;
    }

    static void countSortMod(int arr[], int n, int exp) {
        int output[] = new int[n];
        int i;
        int count[] = new int[10];
        Arrays.fill(count, 0);
        totalMovements += 11; //the above method does 10 movements, plus the one from creating output[]
        for (i = 0; i < n; i++) {
        	count[(arr[i] / exp) % 10]++;
        	totalMovements += 2;
        	totalComparisons++;
        }
        totalMovements++;
        totalComparisons++;
            
        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < 10; i++) {
        	count[i] += count[i - 1];
        	totalMovements += 2;
        	totalComparisons++;
        }
        totalMovements++;
        totalComparisons++;
        
        // Build the output array
        for (i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
            totalMovements += 3;
        	totalComparisons++;
        }
        totalMovements++;
        totalComparisons++;
        
        for (i = 0; i < n; i++) {
        	arr[i] = output[i];
        	totalMovements += 2;
        	totalComparisons++;
        }
        totalMovements++;
        totalComparisons++;
            
    }

    static void print(int arr[], int n) {
        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
    }

    public static void main(String[] args) {
        int arr[] = {170, 45, 75, 90, 802, 24, 2, 66};
        int n = arr.length;
        radixsort(arr, n);
        print(arr, n);
    }
}