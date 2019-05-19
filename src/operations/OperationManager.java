package operations;


import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;


/**
 * <h3>OperationListModel class</h3>
 * <p>OperationListModel class is responsible for defining the way of displaying and managing operations in operation List.</p>
 * <p>Class defines working selected behaviours with the calculated operations in the application </p>
 *
 * 
 * @author Paweł Szeląg
 * @version 3.0.0
 * @since   2019-05-16
 */

public class OperationManager extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	/**Table instance*/
	JTable _table;
	
	/**Defining column names array.*/
	String[] columnNames = { "A", "B", "C", "D", "E" };
	
	/**Initialize object data array for entry values.*/
	static Object[][] data = { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 } };
	
	/**Chart data array*/
	static double[] chartData;
	
	/**Represents sum value.*/
	double sum;
	
	/**Represents minimum value.*/
	double min;
	
	/**Represents maximum value.*/
	double max;
	
	/**Coordinates table values array.*/
	int[] coordinates;
	
	/**Supported iterator.*/
	int iterator;
	
	/**Supported iterator X.*/
	int iteratorX; 
	
	/**Supported iterator Y.*/
	int iteratorY;

	
	/**
	 * Constructor class. Assigning values for iterators and initialize chart data array.
	 */
	public OperationManager() {
		iteratorX = 0;
		iteratorY = 0;
		chartData = new double[25];
	}
	
	
	/**
	 * Calculate sum operation.
	 * @return Sum of values in table.
	 */
	public double sumOperation() {
		sum = 0;

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				sum = sum + Double.valueOf(data[i][j].toString());
			}
		}
		return sum;
	}

	/**
	 * Calculate average operation.
	 * @return Average value in table
	 */
	public double averageOperation() {
		return sumOperation() / 25;
	}
	
	
	/**
	 * Gets chart data
	 * @return Chart data double array
	 */
	public double[] getChartData() {
		iterator=0;
		for(int i=0;i<5;i++) {
			for(int j=0; j<5;j++) {
				chartData[iterator] = Double.valueOf(data[j][i].toString());
				iterator++;
			}
		}
		return chartData;
	}

	/**
	 * Perform minimum operation
	 * @return Minimum value
	 */
	public double minOperation() {
		min = Double.valueOf(data[0][0].toString());

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (min > Double.valueOf(data[i][j].toString())) {
					min = Double.valueOf(data[i][j].toString());
				}
			}
		}
		return min;
	}

	/**
	 * Perform maximum operation
	 * @return Maximum value
	 */
	public double maxOperation() {
		max = Double.valueOf(data[0][0].toString());

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (max < Double.valueOf(data[i][j].toString())) {
					max = Double.valueOf(data[i][j].toString());
				}
			}
		}
		return max;
	}

	/**
	 * Gets data object array
	 * @return Data object array
	 */
	public Object[][] getData() {
		return data;
	}
	
	/**
	 * Sets data into the model
	 * @param data Actual table data
	 */
	public void setData(Object[][] data) {
		this.data = data;
	}

	
	/**
	 * Filling with zeros
	 */
	public void fillWithZeros() {

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				
				data[i][j] = data[i][j].toString();
				data[i][j] = "0";
			}
		
		}
	}

	/**
	 * Finds value in the table
	 * @param value Value to find
	 * @return Coordinates in the table
	 */
	public int[] findValue(Double value) {
		System.out.println(value);
		coordinates = new int[2];
		
		for (int i=0; i < 5; i++) {
			for (int j=0; j < 5; j++) {
				if (value.equals(Double.valueOf(data[i][j].toString()))) {
					coordinates[0] = i;
					coordinates[1] = j;

				}

			}

		}

		return coordinates;
	}

	/**
	 * Overridden method. Adds table model listener.
	 */
	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

	/**
	 * Overridden method. Gets column class.
	 */
	@Override
	public Class<?> getColumnClass(int c) {
		// TODO Auto-generated method stub
		return getValueAt(0, c).getClass();
	}

	
	/**
	 * Overridden method. Gets number of columns.
	 */
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	
	/**
	 * Gets column name by column index.
	 * @param columnIndex Column index
	 */
	@Override
	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get number of rows.
	 * @return Number of rows
	 */
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.length;
	}

	/**
	 * Gets value at specific indexes
	 * @return Value at specific indexes
	 */
	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		return data[row][col];
	}

	/**
	 * Check if cell at specific indexes is editable
	 * @return Boolean flag
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Remove table model listener
	 */
	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

	/**
	 * Sets value at specific index
	 * @param aValue Entry value
	 * @param rowIndex - Row index
	 * @param columnIndex Column index
	 */
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

		data[rowIndex][columnIndex] = aValue;
		// fireTableDataChanged();

	}

}
