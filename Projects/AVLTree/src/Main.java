import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();

        try {
            FileInputStream fis = new FileInputStream("listISBN.txt");
            Scanner sc = new Scanner(fis);

            while (sc.hasNextLine()) {
                avlTree.insert(new Book(sc.nextLine()));
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        avlTree.printInOrder(avlTree.root);
    }
}
