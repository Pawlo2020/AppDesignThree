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

public class FindWindow extends JDialog {
	/**
	 * @author Paweł Szeląg
	 */
	private static final long serialVersionUID = 1L;

	JTextField findValue;
	JButton findButton;
	final static Logger logger = Logger.getLogger("logger");

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
