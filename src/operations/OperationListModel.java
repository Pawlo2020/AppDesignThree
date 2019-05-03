package operations;

import javax.swing.AbstractListModel;

public class OperationListModel extends AbstractListModel<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String[] operationList;
	public OperationListModel(){
		operationList = new String[] {"Suma", "Średnia","Min/Max"};
	}
	
	@Override
	public String getElementAt(int arg0) {
		// TODO Auto-generated method stub
		return operationList[arg0];
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return operationList.length;
	}
	
}
