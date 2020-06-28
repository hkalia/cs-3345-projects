import java.util.ArrayList;
import java.util.Random;

public class ListGenerator {

    static int[] generateInOrder(int x) {
        int[] numbers = new int[x];
        for (int i = 0; i < x; i++) {
            numbers[i] = i;
        }

        return numbers;
    }

    static int[] generateReverseOrder(int x) {
        int[] numbers = new int[x];
        for (int i = 0; i < x; i++) {
            numbers[i] = x - i - 1;
        }

        return numbers;
    }

    static int[] generateRandomOrder(int x) {
        int[] numbers = new int[x];
        Random r = new Random();
        for (int i = 0; i < x; i++) {
            numbers[i] = i;
        }

        for (int i = 0; i < x; i++) {
            int randomIndex = r.nextInt(x);

            //swap the number at i with the one at randomIndex
            int temp = numbers[i];
            numbers[i] = numbers[randomIndex];
            numbers[randomIndex] = temp;
        }

        return numbers;
    }

    static int[] generateAlmostOrder(int x) {
        int[] numbers = new int[x];
        Random r = new Random();
        for (int i = 0; i < x; i++) {
            numbers[i] = i;
        }

        for (int i = 0; i < x; i++) {
            int randomIndex = r.nextInt(x);

            if (r.nextInt(50) == 0) { //only swap them 2% of the time
                //swap the number at i with the one at randomIndex
                int temp = numbers[i];
                numbers[i] = numbers[randomIndex];
                numbers[randomIndex] = temp;
            }

        }

        return numbers;
    }
    
    static int[] generateEightyTwenty(int x) { //
    	int lenEighty = (int) (x * 0.8);
    	int lenTwenty = (int) (x * 0.2);
        int[] numbers = new int[x];
        Random r = new Random();
        for (int i = 0; i < x; i++) {
            numbers[i] = i;
        }
        int[] numbersEighty = new int[lenEighty];
        int[] numbersTwenty = new int[lenTwenty];
        System.arraycopy(numbers, 0, numbersEighty, 0, lenEighty);
        System.arraycopy(numbers, lenEighty, numbersTwenty, 0, lenTwenty);
        for (int i = 0; i < lenTwenty; i++) {
            int randomIndex = r.nextInt(lenTwenty);
            int temp = numbersTwenty[i];
            numbersTwenty[i] = numbersTwenty[randomIndex];
            numbersTwenty[randomIndex] = temp;
        }
        System.arraycopy(numbersEighty, 0, numbers, 0, lenEighty);
        System.arraycopy(numbersTwenty, 0, numbers, lenEighty, lenTwenty);
        return numbers;
    }


}
