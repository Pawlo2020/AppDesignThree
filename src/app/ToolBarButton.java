package app;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * <h3>ToolBarButton class</h3>
 * <p>ToolBarButton class is model of Toolbar button.</p>
 * <p>This class defines GUI of button.</p>
 *
 * 
 * @author Paweł Szeląg
 * @version 3.0.0
 * @since   2019-05-16
 */


public class ToolBarButton extends JButton {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the class. Defines GUI of toolbar button
	 * @param name Button name
	 * @param icon Button icon
	 */
	public ToolBarButton(String name, JLabel icon) {
		setLayout(new FlowLayout());
		setMaximumSize(new Dimension(55, 55));
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(241, 242, 246));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		icon.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanel.add(icon);

		JLabel label = new JLabel(name);

		buttonPanel.add(label);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		setBackground(new Color(241, 242, 246));
		setBorder(null);
		add(buttonPanel);

//		
	}
}
