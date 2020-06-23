import javax.swing.table.AbstractTableModel;

class MyTableModel extends AbstractTableModel {

    private String[] columnNames = {"", "Insertion Sort", "Selection Sort", "Quick Sort", "Merge Sort", "Heap Sort", "Radix Sort"};
    private Object[][] data = {
            {"List Size: ", "", "", "", "", "", ""},
            {"List Type: ", "", "", "", "", "", ""},
            {"Comparisons: ", "", "", "", "", "", ""},
            {"Movements: ", "", "", "", "", "", ""},
            {"Total Time: ", "", "", "", "", "", ""},
    };

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    public Class<?> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
}