package about;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.ToolBarButton;
/**
 * <h3>AboutWindow class</h3>
 * <p>AboutWindow class is responsible for initializing and creating the About window context.</p>
 * <p>Creating a window and GUI take place in appriopriate methods. Class is supported by events, which are triggered by user activity.</p>
 *
 * 
 * @author Paweł Szeląg
 * @version 3.0.0
 * @since   2019-05-16
 */
public class AboutWindow extends JDialog {

	private static final long serialVersionUID = 1L;
	
	/** Handler to MainWindow frame object*/
	JFrame window;
	
	/** Title pane panel instance*/
	JPanel titlePanel;
	
	/**
	 * Constructor class. Responsible for creating a size of window.
	 * @param window MainWindow handler passed argument
	 */
	public AboutWindow(JFrame window) {
		this.setTitle("Informacje o aplikacji");
		this.window = window;
		setBounds(450, 260, 400, 300);
		setResizable(false);
		setAlwaysOnTop(true);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				window.setEnabled(true);
			}
		});
		
		
		initGUI();
		
	}
	
	
	/**
	 * Initiation GUI method for About window. It creates a content.
	 */
	private void initGUI() {
		setLayout(new BorderLayout());
		titlePanel = new JPanel();
		
		titlePanel.setSize(new Dimension(getWidth(), 80));
		JLabel titleLabel = new JLabel("Aplikacja dydaktyczna");
		titleLabel.setFont(new Font("Segoe UI", Font.PLAIN,22));
		JLabel versionLabel = new JLabel("Wersja: 1.0.0");
		versionLabel.setFont(new Font("Segoe UI", Font.ITALIC,16));
		
		titlePanel.setLayout(new BoxLayout(titlePanel,BoxLayout.Y_AXIS));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		titlePanel.add(titleLabel);
		titlePanel.add(versionLabel);
		add(titlePanel, BorderLayout.PAGE_START);
		JPanel mainContentPanel = new JPanel();
		
		mainContentPanel.setLayout(new BoxLayout(mainContentPanel,BoxLayout.X_AXIS));
		mainContentPanel.setPreferredSize( new Dimension(getWidth(), 200));
		add(mainContentPanel, BorderLayout.SOUTH);
		
		JPanel leftContent = new JPanel();
		leftContent.setMaximumSize(new Dimension(getWidth()/2,200));
		mainContentPanel.add(leftContent);
		ImageIcon icon = new ImageIcon(getClass().getResource("logo/logo.png"));
		JLabel logo = new JLabel(icon);
		leftContent.add(logo);
		
		JPanel rightContent = new JPanel();
		rightContent.setMaximumSize(new Dimension(getWidth()/2,200));
		mainContentPanel.add(rightContent);
		rightContent.setLayout(new BoxLayout(rightContent, BoxLayout.Y_AXIS));
		JPanel separator = new JPanel();
		separator.setMaximumSize(new Dimension(rightContent.getWidth(), 40));
		rightContent.add(separator);
		JLabel authorLabel = new JLabel("Paweł Szeląg");
		JLabel licenceLabel = new JLabel("Licencja GNU GPL");
		JLabel uniLabel = new JLabel("Politechnika Koszalińska");
		JLabel emailLabel = new JLabel("pawlodev@outlook.com");
		rightContent.add(authorLabel);
		rightContent.add(licenceLabel);
		rightContent.add(uniLabel);
		rightContent.add(emailLabel);
		
		
	}

	/**
	 * Method responsible for locking MainWindow instance.
	 */
	public void lockMainWindow() {
		window.setEnabled(false);
	}
	
	
}
