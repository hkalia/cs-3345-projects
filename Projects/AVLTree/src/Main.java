import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	
    public static void main(String[] args) {
        AVLTree<Long, Book> avlTree = new AVLTree<>();
        BinarySearchTree<Long, Book> randomTree = new BinarySearchTree<>();
        ArrayList<Book> books = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("listISBN.txt");
            Scanner sc = new Scanner(fis);

            while (sc.hasNextLine()) {
                Book book = new Book(sc.nextLine());
                avlTree.insert(book.ISBN, book);
                books.add(book);
            }
            
            System.out.println("height: " + avlTree.root.height);
            System.out.println("complexity: " + avlTree.complexity);
            // shuffle list for Random binary tree
            Collections.shuffle(books);		
            for (Book b : books) 
            	randomTree.insert(randomTree.root, b.ISBN, b);
            books.clear();
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
