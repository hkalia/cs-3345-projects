import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Main {
    public static int sizeOfRandomTree = 100;

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
            for (int i = 0; i < sizeOfRandomTree; i++)
                randomTree.insert(books.get(i).ISBN, books.get(i));
            books.clear();
            sc.close();
            randomTree.printInOrder(randomTree.root);
            System.out.println("Random Tree size: " + sizeOfRandomTree);
            if (randomTree.isBSTOrder(randomTree.root, Long.MIN_VALUE, Long.MAX_VALUE)) {
                System.out.println("BST order: true");
                if (randomTree.isAVLStructure(randomTree.root))
                    System.out.println("AVL structure: true");

            } else
                System.out.println("BST order: false\nAVL structure: false");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
