import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.TableModel;

class SortingAlgorithmsGUI {

    private JPanel rootPanel = new JPanel(new GridBagLayout(), true);
    private JPanel listOptionsPanel = new JPanel(new GridBagLayout(), true);
    private JPanel resultsPanel = new JPanel(new GridBagLayout(), true);

    private String[] listOrders = {"Random", "ReverseOrder", "AlmostOrder", "InOrder", "80/20"};
    private JComboBox<String> listDropDown = new JComboBox<>(listOrders);
    private JSlider listSlider = new JSlider(2, 30000);
    private JTextField listText = new JTextField("15000", 7);
    private JButton listButton = new JButton("Create and Run List");

    JTable table = new JTable(new MyTableModel());
    JScrollPane scrollPane = new JScrollPane(table);

    private JLabel winnerLabel = new JLabel("Winning Algorithm: ");

    private int listSize = 15000;
    private String listOrder = "Random";

    private SortingAlgorithmsGUI() {
        setupLayout();

        ActionListener listDropDownListener = event -> {
            JComboBox<String> cb = (JComboBox<String>) event.getSource();
            listOrder = (String) cb.getSelectedItem();
        };
        listDropDown.addActionListener(listDropDownListener);

        ChangeListener listSliderListener = event -> {
            JSlider source = (JSlider) event.getSource();
            if (!source.getValueIsAdjusting()) {
                listSize = source.getValue();
                listText.setText(Integer.toString(listSize));
            }
        };
        listSlider.addChangeListener(listSliderListener);

        ActionListener listTextListener = event -> {
            String text = listText.getText();
            listSize = Integer.parseInt(text);
            listSlider.setValue(listSize);
        };
        listText.addActionListener(listTextListener);

        ActionListener listButtonListener = event -> startContest();
        listButton.addActionListener(listButtonListener);
    }

    private int[] createList() {
        return switch (listOrder) {
            case "Random" -> ListGenerator.generateRandomOrder(listSize);
            case "ReverseOrder" -> ListGenerator.generateReverseOrder(listSize);
            case "AlmostOrder" -> ListGenerator.generateAlmostOrder(listSize);
            case "InOrder" -> ListGenerator.generateInOrder(listSize);
            case "80/20" -> ListGenerator.generateEightyTwenty(listSize);
            default -> null;
        };
    }

    private void startContest() {
        for (int j = 1; j < table.getColumnCount(); j++) {
            table.setValueAt(Integer.toString(listSize), 0, j);
            table.setValueAt(listOrder, 1, j);
        }

        int[] list = createList();
        InsertionSort.totalComparisons = 0;
        InsertionSort.totalMovements = 0;
        InsertionSort.insertionSortMod(list);
        table.setValueAt(Integer.toString(InsertionSort.totalComparisons), 2, 1);
        table.setValueAt(Integer.toString(InsertionSort.totalMovements), 3, 1);

        list = createList();
        double[] listDouble = Arrays.stream(list).asDoubleStream().toArray(); //Selection sort algorithm uses doubles
        SelectionSort.totalComparisons = 0;
        SelectionSort.totalMovements = 0;
        SelectionSort.selectionSortMod(listDouble);
        table.setValueAt(Integer.toString(SelectionSort.totalComparisons), 2, 2);
        table.setValueAt(Integer.toString(SelectionSort.totalMovements), 3, 2);
        
        list = createList();
        QuickSort.totalComparisons = 0;
        QuickSort.totalMovements = 0;
        QuickSort.quickSortMod(list);
        table.setValueAt(Integer.toString(QuickSort.totalComparisons), 2, 3);
        table.setValueAt(Integer.toString(QuickSort.totalMovements), 3, 3);
        
        list = createList();
        MergeSort.totalComparisons = 0;
        MergeSort.totalMovements = 0;
        MergeSort.mergeSortMod(list);
        table.setValueAt(Integer.toString(MergeSort.totalComparisons), 2, 4);
        table.setValueAt(Integer.toString(MergeSort.totalMovements), 3, 4);
        
        list = createList();
        //heapSort takes an object array, cannot use primitives
        Integer[] listInteger = Arrays.stream(list).boxed().toArray( Integer[]::new );
        HeapSort.totalComparisons = 0;
        HeapMod.totalComparisons = 0;
        HeapSort.totalMovements = 0;
        HeapMod.totalMovements = 0;
        HeapSort.heapSortMod(listInteger);
        int totalComparisonsHeapSort = HeapSort.totalComparisons + HeapMod.totalComparisons;
        int totalMovementsHeapSort = HeapSort.totalMovements + HeapMod.totalMovements;
        table.setValueAt(Integer.toString(totalComparisonsHeapSort), 2, 5);
        table.setValueAt(Integer.toString(totalMovementsHeapSort), 3, 5);
        
        list = createList();
        Radix.totalComparisons = 0;
        Radix.totalMovements = 0;
        Radix.radixsortMod(list, listSize);
        table.setValueAt(Integer.toString(Radix.totalComparisons), 2, 6);
        table.setValueAt(Integer.toString(Radix.totalMovements), 3, 6);
        
        long start;
        long end;
        long winnerDuration;
        String winner = "InsertionSort";
        
        list = createList();
        start = System.nanoTime();
        InsertionSort.insertionSort(list);
        end = System.nanoTime();
        long durationInsertionSort = (end - start) / 1000; //microseconds
        winnerDuration = durationInsertionSort;
        table.setValueAt(Double.toString(durationInsertionSort), 4, 1);
        
        list = createList();
        listDouble = Arrays.stream(list).asDoubleStream().toArray(); //Selection sort algorithm uses doubles
        start = System.nanoTime();
        SelectionSort.selectionSort(listDouble);
        end = System.nanoTime();
        long durationSelectionSort = (end - start) / 1000; //microseconds
        table.setValueAt(Double.toString(durationSelectionSort), 4, 2);
        if(durationSelectionSort < winnerDuration) {
        	winner = "SelectionSort";
        	winnerDuration = durationSelectionSort;
        }
        
        list = createList();
        start = System.nanoTime();
        QuickSort.quickSort(list);
        end = System.nanoTime();
        long durationQuickSort = (end - start) / 1000; //microseconds
        table.setValueAt(Double.toString(durationQuickSort), 4, 3);
        if(durationQuickSort < winnerDuration) {
        	winner = "QuickSort";
        	winnerDuration = durationQuickSort;
        }
        
        list = createList();
        start = System.nanoTime();
        MergeSort.mergeSort(list);
        end = System.nanoTime();
        long durationMergeSort = (end - start) / 1000; //microseconds
        table.setValueAt(Double.toString(durationMergeSort), 4, 4);
        if(durationMergeSort < winnerDuration) {
        	winner = "MergeSort";
        	winnerDuration = durationMergeSort;
        }
        
        list = createList();
        listInteger = Arrays.stream(list).boxed().toArray( Integer[]::new );
        start = System.nanoTime();
        HeapSort.heapSort(listInteger);
        end = System.nanoTime();
        long durationHeapSort = (end - start) / 1000; //microseconds
        table.setValueAt(Double.toString(durationHeapSort), 4, 5);
        if(durationHeapSort < winnerDuration) {
        	winner = "HeapSort";
        	winnerDuration = durationHeapSort;
        }
        
        list = createList();
        start = System.nanoTime();
        Radix.radixsort(list, listSize);
        end = System.nanoTime();
        long durationRadixSort = (end - start) / 1000; //microseconds
        table.setValueAt(Double.toString(durationRadixSort), 4, 6);
        if(durationRadixSort < winnerDuration) {
        	winner = "RadixSort";
        }
        

//        System.out.println(Arrays.toString(list));

        // How to update winner
        winnerLabel.setText("Winning Algorithm (time): " + winner);
    }

    /**
     * Create the GUI and show it. For thread safety, this method should be invoked
     * from the event-dispatching thread.
     */
    static void createAndShowGUI() {
        // Create and set up the window.
        JFrame frame = new JFrame("Sorting Algorithms");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set up the content pane.
        frame.setContentPane(new SortingAlgorithmsGUI().rootPanel);

        // get the screen size as a java dimension
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // get 2/3 of the height, and 2/3 of the width
        int height = screenSize.height * 2 / 5;
        int width = screenSize.width * 2 / 3;

        // set the frame height and width
        frame.setSize(new Dimension(width, height));

        // Display the window.
        frame.setVisible(true);
    }

    private void setupLayout() {
        rootPanel.add(listOptionsPanel, new GridBagConstraints(0, 0, GridBagConstraints.REMAINDER, 1, 0.1, 0.001,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        rootPanel.add(resultsPanel,
                new GridBagConstraints(0, 1, GridBagConstraints.REMAINDER, GridBagConstraints.REMAINDER, 0.1, 0.1,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        listOptionsPanel.setBorder(
                BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Choose List Properties"),
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        listOptionsPanel.add(listDropDown, new GridBagConstraints(0, 0, 1, 1, 0.1, 0.1, GridBagConstraints.LINE_START,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        listOptionsPanel.add(listSlider, new GridBagConstraints(0, 1, 1, 1, 0.1, 0.1, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        listOptionsPanel.add(listText, new GridBagConstraints(1, 1, GridBagConstraints.REMAINDER, 1, 0.01, 0.1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        listOptionsPanel.add(listButton,
                new GridBagConstraints(0, 2, GridBagConstraints.REMAINDER, GridBagConstraints.REMAINDER, 0.1, 0.1,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        resultsPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Results"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        resultsPanel.add(scrollPane, new GridBagConstraints(0, 0, GridBagConstraints.REMAINDER, 1, 0.1, 0.1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        table.setFillsViewportHeight(true);
        resultsPanel.add(winnerLabel,
                new GridBagConstraints(0, 1, GridBagConstraints.REMAINDER, GridBagConstraints.REMAINDER, 0.1, 0.001,
                        GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 10));
    }
}
