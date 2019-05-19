package app;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JDialog;
import java.net.URL;



/**
 * <h3>HelpWindow class</h3>
 * <p>HelpWindow class is responsible for initializing and creating the help window context.</p>
 * <p>Creating a window and GUI take place in appriopriate methods. Class is supported by events, which are triggered by user activity.</p>
 *
 * 
 * @author Paweł Szeląg
 * @version 3.0.0
 * @since   2019-05-16
 */
public class HelpWindow extends JDialog {
	/** Handler url to html help context*/
	URL url;
	
	/** Displaying container*/
	JEditorPane editor;
	private static final long serialVersionUID = 1L;

	
	/** Class constructor. Responsible for creating GUI of the Help Window <br>
	 * */
	public HelpWindow() {
		url = null;
		this.setTitle("Pomoc");
		this.setResizable(true);
		this.setSize(800,600);
		this.addWindowListener	(new WindowAdapter(){ 
			public void windowClosing(WindowEvent e){  
				setVisible(false);				
			}	
		});
		
		editor = new JEditorPane();
		editor.setEditable(false);
		url = app.HelpWindow.class.getResource("help/index.html");
		setURLPage();
		this.add(new JScrollPane(editor), BorderLayout.CENTER);
	}
	
	/**
	 * Method responsible for setting appriopriate url path to html help context
	 */
	private void setURLPage() {
		try {
			editor.setPage(url);
		}
		catch(Exception e) {
			editor.setText("Nie mozna otworzy plikow z pomoca " + url);
		} 
	}
}
