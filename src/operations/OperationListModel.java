package operations;

import javax.swing.AbstractListModel;
/**
 * <h3>OperationListModel class</h3>
 * <p>OperationListModel class is responsible for defining the way of displaying and managing operations in operation List.</p>
 * <p>Class defines working selected behaviours with the calculated operations in the applications </p>
 *
 * 
 * @author Paweł Szeląg
 * @version 3.0.0
 * @since   2019-05-16
 */
public class OperationListModel extends AbstractListModel<String> {

	private static final long serialVersionUID = 1L;
	
	/**String array which stores operation names*/
	String[] operationList;
	
	/**
	 * Class constructor. It initialize operation list array.
	 */
	public OperationListModel(){
		operationList = new String[] {"Suma", "Średnia","Min/Max"};
	}
	
	/**
	 * Overridden method. Responsible to getting appriopriate operation index.
	 */
	@Override
	public String getElementAt(int arg0) {
		// TODO Auto-generated method stub
		return operationList[arg0];
	}

	/**
	 * Overridden method. Responsible to getting number of operations.
	 */
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return operationList.length;
	}
	
}
