package app;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JDialog;
import java.net.URL;
public class HelpWindow extends JDialog {
	/**
	 * 
	 */
	URL url;
	JEditorPane editor;
	private static final long serialVersionUID = 1L;

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
	private void setURLPage() {
		try {
			editor.setPage(url);
		}
		catch(Exception e) {
			editor.setText("Nie mozna otworzy plikow z pomoca " + url);
		} 
	}
}
