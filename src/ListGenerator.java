import java.util.ArrayList;
import java.util.Random;
public class ListGenerator {
	
	static ArrayList<Integer> generateInOrder(int x) {
		ArrayList<Integer> numbers = new ArrayList<>(x);
		for(int i = 0; i < x; i++){
		    numbers.add(i);
		}
		
		return numbers;
	}
	
	static ArrayList generateReverseOrder(int x) {
		ArrayList<Integer> numbers = new ArrayList<>(x);
		for(int i = x; i >= 0 ; i--){
		    numbers.add(i);
		}
		
		return numbers;
	}
	
	static ArrayList generateRandomOrder(int x) {
		ArrayList<Integer> numbers = new ArrayList<>(x);
		Random r = new Random(); 
		for(int i = 0; i < x; i++){
		    numbers.add(i);
		}
		
		for(int i = x - 1; i > 0; i--) {
			int randomIndex = r.nextInt(i+1);
			
			//swap the number at i with the one at randomIndex
			int temp = numbers.get(randomIndex);
			numbers.set(i, numbers.get(randomIndex));
			numbers.set(randomIndex, temp);
		}
		
		return numbers;
	}
	
	static ArrayList generateAlmostOrder(int x) {
		ArrayList<Integer> numbers = new ArrayList<>(x);
		Random r = new Random(); 
		for(int i = 0; i < x; i++){
		    numbers.add(i);
		}
		
		for(int i = x - 1; i > 0; i--) {
			int randomIndex = r.nextInt(i+1);
			
			if(r.nextInt(50) == 0) { //only swap them 2% of the time
				//swap the number at i with the one at randomIndex
				int temp = numbers.get(randomIndex);
				numbers.set(i, numbers.get(randomIndex));
				numbers.set(randomIndex, temp);
			}

		}
		
		return numbers;
	}
	
}
