package operations;


import javax.swing.AbstractListModel;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class OperationManager extends AbstractTableModel {
	JTable _table;
	String[] columnNames = { "A", "B", "C", "D", "E" };
	static Object[][] data = { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 } };
	static double[] chartData;
	double sum;
	double min;
	double max;
	int[] coordinates;
	int iterator;
	int iteratorX, iteratorY;

	public OperationManager() {
		iteratorX = 0;
		iteratorY = 0;
		chartData = new double[25];
	}

	public double sumOperation() {
		sum = 0;

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				sum = sum + Double.valueOf(data[i][j].toString());
			}
		}
		return sum;
	}

	public double averageOperation() {
		return sumOperation() / 25;
	}
	
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

	public Object[][] getData() {
		return data;
	}
	
	public void setData(Object[][] data) {
		this.data = data;
	}

	public void fillWithZeros() {

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				
				data[i][j] = data[i][j].toString();
				data[i][j] = "0";
			}
		
		}
	}

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

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public Class<?> getColumnClass(int c) {
		// TODO Auto-generated method stub
		return getValueAt(0, c).getClass();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		return data[row][col];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

		data[rowIndex][columnIndex] = aValue;
		// fireTableDataChanged();

	}

}
