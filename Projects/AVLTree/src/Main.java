import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        AVLTree<Long, Book> avlTree = new AVLTree<>();

        try {
            FileInputStream fis = new FileInputStream("listISBN.txt");
            Scanner sc = new Scanner(fis);

            while (sc.hasNextLine()) {
                Book book = new Book(sc.nextLine());
                avlTree.insert(book.ISBN, book);
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // avlTree.printLevelOrder(avlTree.root);
        System.out.println("height: " + avlTree.root.height);
        System.out.println("complexity: " + avlTree.complexity);
        
        //below is for putting random stuff into the BST, not done yet
        ArrayList<String> lines = new ArrayList<String>();
        try {
            FileInputStream fis = new FileInputStream("listISBN.txt");
            Scanner sc = new Scanner(fis);

            while (sc.hasNextLine()) {
            	lines.add(sc.nextLine());
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
