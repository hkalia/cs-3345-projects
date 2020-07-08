import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Driver {
	public static void main (String[] args) {
		/**
		 * Menu
		 */
		boolean userChoice = consoleMenu();				// true -> AVL Tree; false -> Random Binary Tree
		if (userChoice) {
			/**
			 * AVL Tree
			 */
			String filePath = "C:\\Users\\jayay\\OneDrive\\Eclipse Java\\Java Workspace\\AVLTree\\src\\books.txt";
			AVLTree avlTree = new AVLTree();
			LinkedList<Book> books = findBooks(filePath);
			while(!books.isEmpty())
				avlTree.insert(books.pop());
			books.clear();
		}
		else {
			/**
			 * Random Binary Tree
			 */
			// User can pick how many elements go in each tree and how many trees
			Scanner input = new Scanner(System.in);
			System.out.println("\n=========================================================");
			System.out.print("\n\tHow many elements in each tree\n\n\t: ");
			int sizeOfEachTree = input.nextInt();
			System.out.print("\n\tHow many trees\n\n\t: ");
			int repeatNum = input.nextInt();
			System.out.println("\n=========================================================");
			if (sizeOfEachTree < 0 || repeatNum < 0) {
				System.out.println("\n\tInvalid input");
				input.close();
				return;
			}
			input.close();
			int balancedTreeNum = 0;				// count number of balanced trees
			int bSTOrderTreeNum = 0;				// count number of ordered trees
			int aVLTreeNum = 0;						// count number of AVL trees
 			for (int i = 0; i < repeatNum; i++) {
				RandomBinaryTree<Integer> randomTree = new RandomBinaryTree<Integer>(); 
				LinkedList<Integer> intObjs = createRandomTree(sizeOfEachTree);
				while(!intObjs.isEmpty()) {
					randomTree.insert(intObjs.pop());
				}
				System.out.println("\nTree " + (i+1) + ": { \n" + randomTree.toString() + "}");
				if (randomTree.isBalanced)
					balancedTreeNum++;
				if (randomTree.isAscOrder)
					bSTOrderTreeNum++;
				if (randomTree.isBalanced && randomTree.isAscOrder)
					aVLTreeNum++;
				intObjs.clear();
			}
 			double percent = (double) balancedTreeNum / repeatNum * 100;
 			System.out.println("\n=========================================================");
 			System.out.println("\n Number of balanced trees : " + balancedTreeNum + "/" + repeatNum + " : % " + Math.round(percent));
 			percent = (double) bSTOrderTreeNum / repeatNum * 100;
 			System.out.println("\n Number of BST order trees: " + bSTOrderTreeNum + "/" + repeatNum + " : % " + Math.round(percent));
 			percent = (double) aVLTreeNum / repeatNum * 100;
 			System.out.println("\n Number of AVL like trees : " + aVLTreeNum + "/" + repeatNum + " : % " + Math.round(percent));
		}
	}
	
	/**
	 * Menu
	 */
	public static boolean consoleMenu() {
		Scanner input = new Scanner(System.in);
		String choice = "";
		boolean isOne = false;
		boolean isTwo = false;
		do {
			System.out.println("\n\n\t1. AVL Tree\n");
			System.out.print("\t2. Random Binary Tree\n\n\t:");
			choice = input.nextLine();
			isOne = choice.contains("1");
			isTwo = choice.contains("2");
		} while((isOne && isTwo) || !(isOne || isTwo));
		
		return isOne;			// true = 1, false = 2
	}
	
	/**
	 * Part 1.
	 */
	public static LinkedList<Book> findBooks(String filePath) {
		LinkedList<Book> books = new LinkedList<Book>();
		LinkedList<String> elementList = new LinkedList<String>();
		try {
			File file = new File(filePath);
			Scanner fileScan = new Scanner(file);
			
			while(fileScan.hasNext()) {
				String element = fileScan.next();			// element = { ISBN , Title , Author }
				if (element.charAt(0) == '"') {
						while (element.charAt(element.length()-1) != '"' && fileScan.hasNext()) 		
							element += " " + fileScan.next();
						elementList.add(element.substring(1,element.length()-1));
				}
				if (element.compareTo(" ") == 0) 
					fileScan.next();
			}
			
			fileScan.close();
			while (!elementList.isEmpty() && elementList.size() % 3 == 0) {
				System.out.println(elementList.peek());
				String currentISBN = elementList.pop();
				System.out.println(elementList.peek());
				String currentTitle = elementList.pop();
				System.out.println(elementList.peek());
				String currentAuthor = elementList.pop();
				books.push(new Book(currentISBN, currentTitle, currentAuthor));
			}
			elementList.clear();	
			
		} catch(IOException e) {
			e = new IOException("FILE DID NOT OPEN CORRECTLY.");
		}
		Collections.shuffle(books);							// randomizes book insert order
		return books;
	}
	
	/**
	 * Part 2. 
	 */
	public static LinkedList<Integer> createRandomTree(int sizeOfTree) {
		LinkedList<Integer> randomInts = new LinkedList<Integer>();
		if (sizeOfTree <= 0) 
			return randomInts;
			
		for (int i = 0; i < sizeOfTree * 2; i++) {	// pushes integers in order for 2x size of tree
			randomInts.push(i);
		}
		Collections.shuffle(randomInts);			// shuffles list
		for (int i = 0; i < sizeOfTree; i++) {		// deletes half the list
			randomInts.pop();
		}
		return randomInts;							// return random Integer List
	}
}




