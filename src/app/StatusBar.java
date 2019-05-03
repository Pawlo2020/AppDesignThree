package app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class StatusBar extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JTextArea fileName, state;
	
	public void stateSetText(String text) {
		state.setText(text);
	}
	
	public void fileNameSetText(String text) {
		fileName.setText(text);
	}
	
	
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
	
	public void setSaveStatus(boolean flag) {
		if(flag == true) {
			state.setText("Zapisano");
		}else if(flag==false) {
			state.setText("Niezapisano");
		}
	}
	
	public void setFileName(String name) {
		fileName.setText(name);
	}
}
