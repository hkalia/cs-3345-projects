import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.TableModel;

class SortingAlgorithmsGUI {

	private JPanel rootPanel = new JPanel(new GridBagLayout(), true);
	private JPanel listOptionsPanel = new JPanel(new GridBagLayout(), true);
	private JPanel resultsPanel = new JPanel(new GridBagLayout(), true);

	private String[] listOrders = { "Random", "ReverseOrder", "AlmostOrder", "InOrder" };
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

	private void startContest() {
		ArrayList<Integer> list = null;
		switch (listOrder) {
		case "Random":
			list = ListGenerator.generateRandomOrder(listSize);
		case "ReverseOrder":
			list = ListGenerator.generateReverseOrder(listSize);
		case "AlmostOrder":
			list = ListGenerator.generateAlmostOrder(listSize);
		case "InOrder":
			list = ListGenerator.generateInOrder(listSize);
		}
		
		int[] ints = list.stream().mapToInt(Integer::intValue).toArray();
		
		//run the sorting algorithms on the list here
		
		// How to update table
		for (int i = 0; i < table.getRowCount(); i++) {
			for (int j = 1; j < table.getColumnCount(); j++) {
				table.setValueAt("data", i, j);
			}
		}
		// How to update winner
		winnerLabel.setText("Winning Algorithm: Insertion Sort");
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
