

package app;
import java.io.FilenameFilter;

import operations.FileHelper;
import operations.FindWindow;
import about.AboutWindow;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.*;

import com.l2fprod.common.swing.JTipOfTheDay;
import com.l2fprod.common.swing.plaf.windows.WindowsLookAndFeelAddons;
import com.l2fprod.common.swing.tips.DefaultTip;
import com.l2fprod.common.swing.tips.DefaultTipModel;

import operations.OperationManager;
/**
 * <h3>MainWindow class</h3>
 * Application was created for needs Application Design classes
 * <p>MainWindow class is responsible for initializing and creating the window.</p>
 * <p>Creating a window and GUI take place in appriopriate methods. Class is supported by events, which are triggered by user activity.</p>
 *
 * 
 * @author Paweł Szeląg
 * @since   2019-05-16
 */
public class MainWindow extends JFrame {
	

	private static final long serialVersionUID = 1L;
	
	/**
	  * Access field {@link app#Logger}
	  */
	
	
	
	final static Logger logger = Logger.getLogger("logger");	
	JMenu fileMenu, editMenu, viewMenu, helpMenu, windowItem, operationMenu;
	JMenuItem newItem, loadItem, saveItem, saveAsItem, exitItem, helpItem, aboutItem, valuesItem, avgItem, sumItem,
			findItem,tipItem;
	JMenuBar menuBar;
	JCheckBoxMenuItem alwaysOnTop, hideToolbar, hideStatusbar;
	JToolBar toolbar;
	JPanel topPanel;
	ContentPanel contentPanel;
	AboutWindow dialog;
	ToolBarButton[] toolbarButtons;
	boolean alwaysOnTopFlag = false;
	boolean hideStatusBarFlag = false;
	boolean hideToolBarFlag = false;
	String[] fileNames;
	String[] toolBarButtonNames;
	FileDialog openDialog;
	FileDialog saveDialog;
	String tempPath;
	public StatusBar statusbar;
	FileHelper helper;
	DefaultTipModel tips;
	static JTipOfTheDay totd;
	JFrame frame;
	MainWindow() {
		super("Aplikacja");
		frame = this;
		tips = new DefaultTipModel();
		tips.add(new DefaultTip("Reliable software for enterprises","3 billion devices run on java."));
		tips.add(new DefaultTip("Java fight with cancer","400 thousand people have cancer."));
		totd = new JTipOfTheDay(tips);
		
		
		
		
		helper = new FileHelper();
		openDialog = new FileDialog(this, "Choose a file", FileDialog.LOAD);
		saveDialog = new FileDialog(this, "Choose a file", FileDialog.SAVE);
		saveDialog.setFilenameFilter(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return false;
			}
		});

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setEnabled(false);
				int value = JOptionPane.showOptionDialog(null, "Czy chcesz zamknąć aplikację?", "Uwaga",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						new String[] { "Tak", "Nie" }, "Tak");
				if (value == JOptionPane.YES_OPTION) {
					logger.info("Zamknięcie aplikacji");
					dispose();
					System.exit(0);
				} else {
					setEnabled(true);
				}
			}
		});

		BorderLayout boxLayout = new BorderLayout();
		setLayout(boxLayout);

		// Global size and bounds of the frame
		setBounds(40, 30, 1200, 720);

		// Exclusion on resizeable features
		setResizable(false);

		initGUI();
		createAbout();
	}

	JButton but;

	public void initGUI() {
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.setBackground(Color.DARK_GRAY);
		topPanel.setMaximumSize(new Dimension(800, 100));
		createMenu();

		createToolbarButtons();
		createToolbar();
		statusbar = new StatusBar();
		add(topPanel, BorderLayout.NORTH);
		contentPanel = new ContentPanel(helper, statusbar, saveDialog,openDialog ,this);
		add(contentPanel, BorderLayout.CENTER);

		add(statusbar, BorderLayout.PAGE_END);

		statusbar.setSaveStatus(helper.getSaveState());
		statusbar.setFileName(helper.fileName);

		
		
		// Events

		// Okno pomocy
		aboutItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setAlwaysOnTop(false);
				alwaysOnTop.setSelected(false);
				alwaysOnTopFlag = false;
				dialog.setVisible(true);
				dialog.lockMainWindow();
				logger.info("Otworzono okno pomocy");
			}
		});

		// Menu otwórz
		loadItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				open();
				
			}
		});

		// Menu wyjście
		exitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setEnabled(false);
				int value = JOptionPane.showOptionDialog(null, "Czy chcesz zamknąć aplikację?", "Uwaga",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						new String[] { "Tak", "Nie" }, "Tak");
				if (value == JOptionPane.YES_OPTION) {
					logger.info("Wyjście z aplikacji");
					dispose();
					System.exit(0);
				} else {
					setEnabled(true);
				}
			}
		});

		// Menu widok
		alwaysOnTop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (alwaysOnTopFlag == false) {

					setAlwaysOnTop(true);
					alwaysOnTopFlag = true;
					logger.info("Wybrano opcję zawsze na wierzchu");
				} else {
					setAlwaysOnTop(false);
					alwaysOnTopFlag = false;
					logger.info("Odznaczono opcję zawsze na wierzchu");
				}
			}

		});
		
		
		//Toolbar new
		toolbarButtons[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//if not saved
				if(helper.saveState==false) {
					int value = JOptionPane.showOptionDialog(null, "Czy chcesz zapisać plik?", "Uwaga",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
							new String[] { "Tak", "Nie" }, "Tak");
					if (value == JOptionPane.YES_OPTION) {
						if (helper.activePath == null) {
							saveDialog.setVisible(true);
							tempPath = saveDialog.getDirectory() + saveDialog.getFile();
							tempPath = tempPath.replace("\\", "\\\\");
							if(saveDialog.getDirectory()!=null && saveDialog.getFile() !=null) {
								helper.activePath = tempPath;
								helper.fileName = saveDialog.getFile();
								}
							else {
								return;
							}
						} else {
							

						}

						try {
							if(helper.activePath!=null) {
							helper.saveFile(tempPath, ((OperationManager) contentPanel.getTable().getModel()).getData());
							helper.saveState = true;
							statusbar.setSaveStatus(helper.getSaveState());
							statusbar.setFileName(helper.fileName);
							}
						} catch (IOException arg2) {
							// TODO Auto-generated catch block
							
						}

						statusbar.setSaveStatus(helper.getSaveState());

					}
					} else {
						setEnabled(true);
					}
				
				
				helper.saveState = false;
				helper.setNewFile();
				((OperationManager) contentPanel.getTable().getModel()).fillWithZeros();
				contentPanel.getTable().repaint();
				statusbar.setSaveStatus(helper.saveState);
				statusbar.setFileName(helper.fileName);
				logger.info("Utworzono nowy arkusz");
				}
				
			
	});

		// Toolbar otworz
		toolbarButtons[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				open();
			}
		});

		// Toolbar znajdz
		toolbarButtons[3].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FindWindow findWindow = new FindWindow(contentPanel);
				findWindow.setVisible(true);
			}
		});

		// Edycja znajdz
		findItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FindWindow findWindow = new FindWindow(contentPanel);
				findWindow.setVisible(true);

			}
		});

		// Ukryj pasek statusu
		hideStatusbar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (hideStatusBarFlag == false) {
					statusbar.setVisible(false);
					hideStatusBarFlag = true;
					logger.info("Ukryto pasek statusu");
				} else {
					statusbar.setVisible(true);
					hideStatusBarFlag = false;
					logger.info("Przywrócono pasek narzędziowy");
				}
			}
		});

		// Ukryj pasek narzedziowy
		hideToolbar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (hideToolBarFlag == false) {
					toolbar.setVisible(false);
					hideToolBarFlag = true;
					logger.info("Ukryto pasek narzędziowy");
				} else {
					toolbar.setVisible(true);
					hideToolBarFlag = false;
					logger.info("Przywrócono pasek narzędziowy");
				}

			}
		});

		// Toolbar suma
		toolbarButtons[4].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				contentPanel.setResult(
						String.valueOf(((OperationManager) contentPanel.getTable().getModel()).sumOperation()));
				logger.info("Przeprowadzono operację suma");
			}
		});

		// Toolbar srednia
		toolbarButtons[5].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				contentPanel.setResult(
						String.valueOf(((OperationManager) contentPanel.getTable().getModel()).averageOperation()));
				logger.info("Przeprowadzono operację średnia");
			}
		});

		// MINMAX
		toolbarButtons[6].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				contentPanel.setResult("Min: "
						+ String.valueOf(((OperationManager) contentPanel.getTable().getModel()).minOperation())
						+ " Max: "
						+ String.valueOf(((OperationManager) contentPanel.getTable().getModel()).maxOperation()));
				logger.info("Przeprowadzono operację min/max");
			}

		});

		// Save menu
		saveItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				save();

			}
		});

		//Save as
		saveAsItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				saveDialog.setVisible(true);
				tempPath = saveDialog.getDirectory() + saveDialog.getFile();
				tempPath = tempPath.replace("\\", "\\\\");
				if(saveDialog.getDirectory()!=null && saveDialog.getFile() !=null) {
					helper.activePath = tempPath;
					helper.fileName = saveDialog.getFile();
					}
				else {
					return;
				}
				
			
				
				try {
					helper.saveFile(tempPath, ((OperationManager) contentPanel.getTable().getModel()).getData());
					helper.saveState = true;
					statusbar.setSaveStatus(helper.getSaveState());
					statusbar.setFileName(helper.fileName);
					logger.info("Zapisano plik: " + tempPath);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					logger.error("Błąd zapisu pliku");
					
				}
			}
		});
	
		//Toolbar save
		toolbarButtons[2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				save();
			
		}});

		
		//Help Window
		helpItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				HelpWindow helpWindow = new HelpWindow();
				helpWindow.setVisible(true);
				
			}
		});
		
		//Menu Tip
		tipItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				totd.showDialog(frame);
				logger.info("Wybrano opcję porada dnia");
				
			}
		});
		
		
		//Menu new
		newItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//if not saved
				if(helper.saveState==false) {
					int value = JOptionPane.showOptionDialog(null, "Czy chcesz zapisać plik?", "Uwaga",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
							new String[] { "Tak", "Nie" }, "Tak");
					if (value == JOptionPane.YES_OPTION) {
						if (helper.activePath == null) {
							saveDialog.setVisible(true);
							tempPath = saveDialog.getDirectory() + saveDialog.getFile();
							tempPath = tempPath.replace("\\", "\\\\");
							if(saveDialog.getDirectory()!=null && saveDialog.getFile() !=null) {
								helper.activePath = tempPath;
								helper.fileName = saveDialog.getFile();
								}
							else {
								return;
							}
						} else {
							

						}

						try {
							if(helper.activePath!=null) {
							helper.saveFile(tempPath, ((OperationManager) contentPanel.getTable().getModel()).getData());
							helper.saveState = true;
							statusbar.setSaveStatus(helper.getSaveState());
							statusbar.setFileName(helper.fileName);
							}
						} catch (IOException arg2) {
							// TODO Auto-generated catch block
							
						}

						statusbar.setSaveStatus(helper.getSaveState());

					}
					} else {
						setEnabled(true);
					}
				
				
				helper.saveState = false;
				helper.setNewFile();
				((OperationManager) contentPanel.getTable().getModel()).fillWithZeros();
				contentPanel.getTable().repaint();
				statusbar.setSaveStatus(helper.saveState);
				statusbar.setFileName(helper.fileName);
				}
				
			
		});}

	public void createAbout() {
		dialog = new AboutWindow(this);
	}

	public void createToolbarButtons() {

		toolbarButtons = new ToolBarButton[7];
		fileNames = new String[] { "new.png", "open.png", "save.png", "find.png", "sum.png", "avg.png", "value.png" };

		toolBarButtonNames = new String[] { "Nowy", "Otwórz", "Zapisz", "Znajdź", "Suma", "Średnia", "Min/Max" };

		for (int i = 0; i < fileNames.length; i++) {
			String name = "graphics/" + fileNames[i];
			ImageIcon icon = new ImageIcon(getClass().getResource(name));
			ToolBarButton button = new ToolBarButton(toolBarButtonNames[i], new JLabel(icon));
			toolbarButtons[i] = button;
		}

	}

	public void createMenu() {

		menuBar = new JMenuBar();
		menuBar.setPreferredSize(new Dimension(getWidth(), 25));
		fileMenu = new JMenu("Plik");
		fileMenu.setForeground(Color.WHITE);
		editMenu = new JMenu("Edycja");
		editMenu.setForeground(Color.WHITE);
		viewMenu = new JMenu("Widok");
		viewMenu.setForeground(Color.WHITE);
		helpMenu = new JMenu("Pomoc");
		helpMenu.setForeground(Color.WHITE);
		windowItem = new JMenu("Okno");
		windowItem.setForeground(Color.WHITE);
		operationMenu = new JMenu("Operacja");
		operationMenu.setForeground(Color.WHITE);
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(viewMenu);
		menuBar.add(operationMenu);
		menuBar.add(windowItem);
		menuBar.add(helpMenu);

		newItem = new JMenuItem("Nowy");
		loadItem = new JMenuItem("Otwórz...");
		saveItem = new JMenuItem("Zapisz");
		saveAsItem = new JMenuItem("Zapisz jako...");
		exitItem = new JMenuItem("Wyjdź z aplikacji");
		findItem = new JMenuItem("Znajdź wartość...");
		sumItem = new JMenuItem("Sumowanie wartości");
		avgItem = new JMenuItem("Wartość średnia");
		valuesItem = new JMenuItem("Wartość minimalna i maksymalna");
		helpItem = new JMenuItem("Kontekst pomocy");
		tipItem = new JMenuItem("Porada dnia");
		aboutItem = new JMenuItem("Informacje o aplikacji");
		alwaysOnTop = new JCheckBoxMenuItem("Zawsze na wierzchu");
		hideToolbar = new JCheckBoxMenuItem("Ukryj pasek narzędziowy");
		hideStatusbar = new JCheckBoxMenuItem("Ukryj pasek statusu");

		fileMenu.add(newItem);
		fileMenu.add(loadItem);
		fileMenu.add(new JSeparator());
		fileMenu.add(saveItem);
		fileMenu.add(saveAsItem);
		fileMenu.add(new JSeparator());
		fileMenu.add(exitItem);
		editMenu.add(findItem);

		viewMenu.add(hideToolbar);
		viewMenu.add(hideStatusbar);
		operationMenu.add(sumItem);
		operationMenu.add(avgItem);
		operationMenu.add(valuesItem);

		windowItem.add(alwaysOnTop);

		helpMenu.add(helpItem);
		helpMenu.add(tipItem);
		helpMenu.add(aboutItem);

		menuBar.setBorder(null);
		menuBar.setBackground(new Color(45, 152, 218));

		topPanel.add(menuBar, BorderLayout.PAGE_START);

	}

	public void createToolbar() {
		javax.swing.JPanel[] panelArray = new javax.swing.JPanel[7];

		for (int i = 0; i < panelArray.length; i++) {
			panelArray[i] = new JPanel();
			panelArray[i].setMaximumSize(new Dimension(6, 70));
			panelArray[i].setBackground(new Color(69, 170, 242));
		}

		toolbar = new JToolBar();
		toolbar.setPreferredSize(new Dimension(getWidth(), 70));
		toolbar.setFloatable(false);
		toolbar.setBackground(new Color(69, 170, 242));
		toolbar.add(panelArray[0]);
		toolbar.add(toolbarButtons[0]);
		toolbar.add(panelArray[1]);
		toolbar.add(toolbarButtons[1]);
		toolbar.add(panelArray[2]);
		toolbar.add(toolbarButtons[2]);
		panelArray[3].setMaximumSize(new Dimension(18, 70));
		toolbar.add(panelArray[3]);
		toolbar.add(toolbarButtons[3]);
		panelArray[4].setMaximumSize(new Dimension(18, 70));
		toolbar.add(panelArray[4]);
		toolbar.add(toolbarButtons[4]);
		toolbar.add(panelArray[5]);
		toolbar.add(toolbarButtons[5]);
		toolbar.add(panelArray[6]);
		toolbar.add(toolbarButtons[6]);

		topPanel.add(toolbar, BorderLayout.LINE_END);

	}

	public void save() {
		
		if (helper.activePath == null) {
			saveDialog.setVisible(true);
			tempPath = saveDialog.getDirectory() + saveDialog.getFile();
			tempPath = tempPath.replace("\\", "\\\\");
			
			if(saveDialog.getDirectory()!=null && saveDialog.getFile() !=null) {
				helper.activePath = tempPath;
				helper.fileName = saveDialog.getFile();
				}
			else {
				return;
			}
			
		} else {
			

		}
		
		
		try {
			if(!helper.activePath.equals(null)) {
			helper.saveFile(tempPath, ((OperationManager) contentPanel.getTable().getModel()).getData());
			helper.saveState = true;
			statusbar.setSaveStatus(helper.getSaveState());
			statusbar.setFileName(helper.fileName);
			logger.info("Zapisano plik: " + saveDialog.getFile());
			return;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Błąd zapisu pliku");
		}
	}
		
	public void open() {
		openDialog.setVisible(true);

		tempPath = openDialog.getDirectory() + openDialog.getFile();
		
		tempPath = tempPath.replace("\\", "\\\\");
		if(openDialog.getDirectory()!=null && openDialog.getFile() !=null) {
			helper.activePath = tempPath;
			helper.fileName = openDialog.getFile();
			
			}
		else {
			return;
		}
		
		
		try {
			((OperationManager) contentPanel.table.getModel()).setData(helper.openFile(tempPath));
			contentPanel.table.repaint();
			contentPanel.renderer.setHorizontalAlignment(JLabel.RIGHT);
			helper.saveState = true;
			statusbar.setSaveStatus(helper.getSaveState());
			statusbar.setFileName(helper.fileName);
			logger.info("Załadowano plik: " + openDialog.getFile());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.error("Błąd załadowania pliku");
		}
	}
	
	public static void main(String[] args) {

		MainWindow Window = new MainWindow();
		Window.setVisible(true);
		totd.showDialog(Window);
		PropertyConfigurator.configure("mainLog.properties");
		logger.info("Start aplikacji");
		
		

	}

}
