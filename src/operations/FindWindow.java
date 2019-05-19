package operations;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import app.ContentPanel;
import operations.OperationManager;

/**
 * <h3>FindWindow class</h3>
 * <p>FindWindow class is responsible for initializing and creating the find window context.</p>
 * <p>Creating a window and GUI take place in appriopriate methods. Class is supported by events, which are triggered by user activity.</p>
 *
 * 
 * @author Paweł Szeląg
 * @version 3.0.0
 * @since   2019-05-16
 */
public class FindWindow extends JDialog {

	private static final long serialVersionUID = 1L;

	/** Field which contains value to find provided by user*/
	JTextField findValue;
	
	/**Button instance for searching the appriopriate value*/
	JButton findButton;
	
	/*** Logging object for events triggered by user.*/
	final static Logger logger = Logger.getLogger("logger");

	/**
	 * Class constructor. Responsible for creating GUI and handling events truggered by user.
	 * @param panel Handler co ContentPanel instance
	 */
	public FindWindow(ContentPanel panel) {

		
		this.setTitle("Wyszukiwanie wartości");
		setLayout(new BorderLayout());
		setBounds(850, 50, 400, 120);
		setResizable(false);
		JPanel separatorPanel = new JPanel();
		separatorPanel.setSize(new Dimension(getWidth(), 20));

		JPanel contentPanel = new JPanel();
		contentPanel.setPreferredSize(new Dimension(getWidth(), 50));
		contentPanel.setLayout(new FlowLayout());
		JLabel findInfo = new JLabel("Znajdź: ");
		findInfo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		contentPanel.add(findInfo);
		findValue = new JTextField();
		findValue.setPreferredSize(new Dimension(150, 30));
		findValue.setFont(new Font("Verdana", Font.PLAIN, 18));
		contentPanel.add(findValue);
		findButton = new JButton("Znajdź");
		contentPanel.add(findButton);

		add(contentPanel, BorderLayout.PAGE_START);
		JPanel lastPanel = new JPanel();
		lastPanel.setPreferredSize(new Dimension(getWidth(), 100));
		JButton cancelButton = new JButton("Wyjdź");
		lastPanel.add(cancelButton);
		add(lastPanel);

		// Events
		
		findButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] tabela = new int[2];
				
				tabela = ((OperationManager) panel.getTable().getModel()).findValue(Double.valueOf(findValue.getText()));
				logger.info("Próba znalezienia wartości: " + Double.valueOf(findValue.getText()));
				
				panel.highlightCell(tabela[0], tabela[1]);
				
				panel.getTable().repaint();
			}
		});

		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	}
}
