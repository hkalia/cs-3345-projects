README.txt

Books.java

Contains book class holding String values: ISBN, bookTitle, authorLastName and lexVal. The lexVal refers to lexicographical value of ISBN string with all non numeric characters and regix removed, ie. 342-345-535 423	-> 342345535423
The compareTo() method overrides Comparable<> interface and returns the compareTo value of lexVal.

AVLTree.java

AVL Tree is a binary tree data structure that balances itself after every input and maintains proper order. It has BST insert with only difference  being the node is passed into adjustTree(AVLNode node) method that determines the balanceFactor of the node it is child node and uses these numbers to determine the type of rotation needed: left, right, left-left or left-right. 
Each of these operations are handled in the adjustTree() method but the two rotation operations are still using either a left or right rotatation so I created just those two rotations. ToString() is overridden and prints the preOrder, inOrder and postOrder of the tree.


RandomBinaryTree.java

Just a modified binary tree that uses pseudorandom number generation to determine whether the node goes left or right and only inserting when pointer leads to null.

books.txt

I made the example before the project specified so I used '"' to seperate different elements. 

Driver.java

The driver runs a console menu that gives two choices to user, AVL or random tree. The method findBooks() takes strings from textfile following the convention I had set forth prior with each element being contained in "parenthesis" and I truncated and added each to the a String LinkedList. Then I use these values to create book items that are then pushed into a book object linked list that is then shuffled and returned.

createRandomTree(int) creates a random list of integers to be pushed into each randomTree.

The main method calles the aformentioned methods and class constructors to create one AVLTree holding every book in the textfile or create a list of random trees and return each of their balances and order.