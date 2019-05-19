package app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * <h3>StatusBar class</h3>
 * <p>StatusBar class is responsible for initializing and creating the toolbar.</p>
 * <p>Creating a toolbar and GUI take place in appriopriate methods. Class is supported by events, which are triggered by user activity.</p>
 *
 * 
 * @author Paweł Szeląg
 * @version 3.0.0
 * @since   2019-05-16
 */



public class StatusBar extends JPanel {

	private static final long serialVersionUID = 1L;

	/**Field contains current opened file name.*/
	JTextArea fileName; 
	
	/**Field contains current saving state of file.*/
	JTextArea state;
	
	/**
	 * Method responsible for setting current saving state of file.
	 * @param text Current state status
	 */
	public void stateSetText(String text) {
		state.setText(text);
	}
	
	
	/**Method responsible for setting current name of file.
	 * @param text Current name of file
	 * */
	public void fileNameSetText(String text) {
		fileName.setText(text);
	}
	
	/**
	 * Parameterless class constructor.<br>
	 * Responsible for creating and initializing gui for statusbar.
	 */
	public StatusBar() {
		super();
		setPreferredSize(new Dimension(800, 25));
		setBackground(new Color(127, 143, 166));
		setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel fileNameInfo = new JLabel("Nazwa pliku: ");
		fileNameInfo.setForeground(Color.WHITE);
		fileNameInfo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		fileName = new JTextArea();
		fileName.setPreferredSize(new Dimension(100,15));
		fileName.setBackground(new Color(127, 143, 166));
		fileName.setForeground(Color.WHITE);
		fileName.setEnabled(false);
		add(fileNameInfo);
		add(fileName);
		
		JPanel separatorOne = new JPanel();
		separatorOne.setPreferredSize(new Dimension(100,5));
		separatorOne.setBackground(new Color(127, 143, 166));
		add(separatorOne);
		
		JLabel stateInfo = new JLabel("Stan zapisu: ");
		stateInfo.setForeground(Color.WHITE);
		stateInfo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		add(stateInfo);
		state = new JTextArea();
		state.setPreferredSize(new Dimension(100,15));
		state.setBackground(new Color(127, 143, 166));
		state.setForeground(Color.WHITE);
		state.setEnabled(false);
		add(state);
	}
	
	/**
	 * Method responsible for setting state status.
	 * @param flag Boolean flag. True - saved status, False - unsaved status
	 */
	public void setSaveStatus(boolean flag) {
		if(flag == true) {
			state.setText("Zapisano");
		}else if(flag==false) {
			state.setText("Niezapisano");
		}
	}
	
	/**
	 * Method responsible for setting current name of file.
	 * @param name File name
	 */
	public void setFileName(String name) {
		fileName.setText(name);
	}
}
