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
        int lenEighty = (int) Math.floor(x * 0.8);
        int lenTwenty = x - lenEighty;
        int[] numbers = new int[x];

        for (int i = 0; i < lenEighty; i++) {
            numbers[i] = i;
        }

        System.arraycopy(generateRandomOrder(x), lenEighty, numbers, lenEighty, lenTwenty);
        return numbers;
    }


}
