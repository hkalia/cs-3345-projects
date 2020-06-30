import javax.swing.table.AbstractTableModel;

class MyTableModel extends AbstractTableModel {

    private String[] columnNames = {"", "Insertion Sort", "Selection Sort", "Quick Sort", "Merge Sort", "Heap Sort", "Radix Sort"};
    private Object[][] data = {
            {"List Size: ", "", "", "", "", "", ""},
            {"List Type: ", "", "", "", "", "", ""},
            {"Comparisons: ", "", "", "", "", "", ""},
            {"Movements: ", "", "", "", "", "", ""},
            {"Total Time (microsec): ", "", "", "", "", "", ""},
    };

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    @Override
    public Class<?> getColumnClass(int c) {
        return String.class;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
}