package app;

import operations.FileHelper;
import operations.FindWindow;
import operations.OperationListModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import org.apache.log4j.Logger;
import org.freixas.jcalendar.JCalendarCombo;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.SlidingCategoryDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;

import com.l2fprod.common.swing.JLinkButton;
import com.l2fprod.common.swing.JTaskPane;
import com.l2fprod.common.swing.JTaskPaneGroup;
import com.l2fprod.common.swing.JTipOfTheDay;
import com.l2fprod.common.swing.TipModel;
import com.l2fprod.common.swing.plaf.JTipOfTheDayAddon;

import operations.OperationManager;

public class ContentPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger("logger");
	JLabel[] numbersLabel, numbersLabelTwo;
	JTextField entryArea;
	JButton addEntryButton;
	JList operationList;
	JButton calculateButton;
	JButton zeroButton;
	JButton saveButton;
	OperationListModel listModel;
	int row, col;
	JTable table;
	String format;
	JTextArea resultArea;
	DecimalFormat df;
	Object value;
	private String tempPath;
	JScrollPane resPane;
	JCalendarCombo calendar;
	JTextArea dateArea;
	JPanel conPane;
	ContentPanel mainContent;
	JFreeChart chart;
	ChartPanel chartPanel;
	public DefaultTableCellRenderer renderer;

	public ContentPanel(FileHelper helper, StatusBar state, FileDialog saveDialog, FileDialog openDialog,MainWindow frame) {
		super();
		mainContent = this;
		
		FlowLayout boxLayoutTwo = new FlowLayout(FlowLayout.LEFT,0,0);
		setLayout(boxLayoutTwo);
		JPanel navPanel = new JPanel();
		navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
		navPanel.setBackground(new Color(48, 51, 107));
		navPanel.setPreferredSize(new Dimension(220,580));
		JTaskPane taskPane = new JTaskPane();
		taskPane.setBackground(new Color(48, 51, 107));
		JTaskPaneGroup g1 = new JTaskPaneGroup();
		g1.setTitle("Zadania");
		JLinkButton newLink = new JLinkButton("Nowy");
		JLinkButton openLink = new JLinkButton("Otwórz");
		JLinkButton saveLink = new JLinkButton("Zapisz");
		JLinkButton findLink = new JLinkButton("Znajdź");
		g1.add(newLink);
		g1.add(openLink);
		g1.add(saveLink);
		g1.add(findLink);
		
		
		
		
		taskPane.add(g1);
		JTaskPaneGroup g2 = new JTaskPaneGroup();
		g2.setTitle("Operacje");
		taskPane.add(g2);
		JLinkButton sumLink = new JLinkButton("Suma");
		JLinkButton avgLink = new JLinkButton("Średnia");
		JLinkButton minmaxLink = new JLinkButton("Min/Max");
		JLinkButton zeroLink = new JLinkButton("Wyzerowanie tablicy");
		g2.add(sumLink);
		g2.add(avgLink);
		g2.add(minmaxLink);
		g2.add(zeroLink);
		
		JTaskPaneGroup g3 = new JTaskPaneGroup();
		g3.setTitle("Wykres");
		taskPane.add(g3);
		
		JLinkButton savePngLink = new JLinkButton("Zapisz jako PNG");
		JLinkButton refreshLink = new JLinkButton("Odśwież");
		g3.add(savePngLink);
		g3.add(refreshLink);
		navPanel.add(taskPane);
		add(navPanel);
	
		conPane = new JPanel();
		conPane.setLayout(new BoxLayout(conPane, BoxLayout.Y_AXIS));
		conPane.setBackground(Color.GREEN);
		conPane.setPreferredSize(new Dimension(970,580));
		conPane.setLayout(new BorderLayout());
		
		JPanel calculationPanel = new JPanel();
		calculationPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		calculationPanel.setPreferredSize(new Dimension(920,330));
		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
		tablePanel.setPreferredSize(new Dimension(700,350));
		//Sliders and table
		
		JSlider xSlider = new JSlider(0, 100, 0);
		renderer = new DefaultTableCellRenderer();
		xSlider.setMaximumSize(new Dimension(520, 35));
		xSlider.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		// xSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
		xSlider.setMajorTickSpacing(30);
		numbersLabel = new JLabel[] { new JLabel("A"), new JLabel("B"), new JLabel("C"), new JLabel("D"),
				new JLabel("E") };
		numbersLabelTwo = new JLabel[] { new JLabel("1"), new JLabel("2"), new JLabel("3"), new JLabel("4"),
				new JLabel("5") };

		for (int i = 0; i < numbersLabel.length; i++) {
			numbersLabel[i].setFont(new Font("Segoe UI", Font.PLAIN, 12));
		}
		for (int i = 0; i < numbersLabelTwo.length; i++) {
			numbersLabelTwo[i].setFont(new Font("Segoe UI", Font.PLAIN, 12));
		}
		xSlider.setMajorTickSpacing(25);

		Hashtable<Integer, JLabel> labels = new Hashtable<>();
		labels.put(0, numbersLabel[0]);
		labels.put(25, numbersLabel[1]);
		labels.put(50, numbersLabel[2]);
		labels.put(75, numbersLabel[3]);
		labels.put(100, numbersLabel[4]);

		Hashtable<Integer, JLabel> labelsTwo = new Hashtable<>();
		labelsTwo.put(0, numbersLabelTwo[4]);
		labelsTwo.put(25, numbersLabelTwo[3]);
		labelsTwo.put(50, numbersLabelTwo[2]);
		labelsTwo.put(75, numbersLabelTwo[1]);
		labelsTwo.put(100, numbersLabelTwo[0]);
		xSlider.setLabelTable(labels);

		xSlider.setPaintLabels(true);
		xSlider.setSnapToTicks(true);
		tablePanel.add(xSlider);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		centerPanel.setMaximumSize(new Dimension(800, 260));
		
		
		
		JSlider ySlider = new JSlider(JSlider.VERTICAL);
		ySlider.setPreferredSize(new Dimension(35, 210));
		centerPanel.add(ySlider);
		JPanel panelTable = new JPanel();
		panelTable.setLayout(new BorderLayout());
		centerPanel.add(panelTable);

		ySlider.setLabelTable(labelsTwo);
		ySlider.setMajorTickSpacing(25);
		ySlider.setPaintLabels(true);
		ySlider.setSnapToTicks(true);

		TableModel model = new OperationManager();
		table = new JTable(model) {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component comp = super.prepareRenderer(renderer, row, col);
				((JLabel) comp).setHorizontalAlignment(JLabel.RIGHT);
				return comp;
			}
		};
		table.getColumnModel().getColumn(0).setPreferredWidth(130);
		table.getColumnModel().getColumn(1).setPreferredWidth(130);
		table.getColumnModel().getColumn(2).setPreferredWidth(130);
		table.getColumnModel().getColumn(3).setPreferredWidth(130);
		table.getColumnModel().getColumn(4).setPreferredWidth(130);
		table.setRowHeight(0, 48);
		table.setRowHeight(1, 48);
		table.setRowHeight(2, 48);
		table.setRowHeight(3, 48);
		table.setRowHeight(4, 48);
		panelTable.add(table, BorderLayout.SOUTH);
		tablePanel.add(centerPanel);
		
		calculationPanel.add(tablePanel);
		
		
		JPanel operationPane = new JPanel();
		operationPane.setPreferredSize(new Dimension(220,350));
		operationPane.setLayout(new BoxLayout(operationPane,BoxLayout.Y_AXIS));
		JLabel operationInfo = new JLabel("Operacja: ");
		operationInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
		operationInfo.setFont(new Font("Verdana", Font.PLAIN, 16));
		operationPane.add(operationInfo);
		JPanel sepopOne = new JPanel();
		sepopOne.setMaximumSize(new Dimension(220,5));
		sepopOne.setAlignmentX(Component.CENTER_ALIGNMENT);
		operationPane.add(sepopOne);
		listModel = new OperationListModel();
		operationList = new JList();
		operationList.setModel(listModel);
		operationList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		operationList.setLayoutOrientation(JList.VERTICAL_WRAP);
		operationList.setPreferredSize(new Dimension(80, 60));
		operationList.repaint();
		operationList.setFont(new Font("SegoeUI", Font.PLAIN,14));
		JScrollPane sp = new JScrollPane(operationList);
		sp.setViewportView(operationList);
		sp.setMaximumSize(new Dimension(120, 62));
		sp.setAlignmentX(Component.CENTER_ALIGNMENT);
		operationPane.add(sp);
		
		JPanel sepopTwo = new JPanel();
		sepopTwo.setMaximumSize(new Dimension(220,5));
		operationPane.add(sepopTwo);
		JPanel calPane = new JPanel();
		calPane.setMaximumSize(new Dimension(220,35));
		calPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		operationPane.add(calPane);
		calculateButton = new JButton();
		calculateButton.setText("Oblicz");
		operationPane.add(calculateButton);
		calPane.add(calculateButton);
		JPanel sepopThree = new JPanel();
		sepopThree.setMaximumSize(new Dimension(220,10));
		operationPane.add(sepopThree);

		
		JLabel resultInfo = new JLabel("Wynik operacji: ");
		resultInfo.setFont(new Font("Verdana", Font.PLAIN, 16));
		resultInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		operationPane.add(resultInfo);
		
		JPanel resultPanel = new JPanel();
		resultPanel.setMaximumSize(new Dimension(150,50));
		
		resultArea = new JTextArea();
		resultArea.setPreferredSize(new Dimension(200, 25));
		resultArea.setEnabled(true);
		resultArea.setEditable(false);
		resultArea.setForeground(Color.black);
		resPane = new JScrollPane(resultArea);
		resPane.setViewportView(resultArea);
		resPane.setSize(new Dimension(400,50));
		resPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		resPane.setPreferredSize(new Dimension(150, 45));
		resPane.setBounds(250, 250, 400, 50);
		resultPanel.add(resPane);
		saveButton = new JButton("Zapisz");
		zeroButton = new JButton("Wyzeruj");
		
		
		operationPane.add(resultPanel);
		
		
		JPanel optionPanel = new JPanel();

		optionPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		optionPanel.setMaximumSize(new Dimension(220,50));
		optionPanel.add(saveButton);
		optionPanel.add(zeroButton);
		
		operationPane.add(optionPanel);
		
		
		
		JPanel datePanel = new JPanel();
		datePanel.setMaximumSize(new Dimension(220, 30));
		JLabel dateLabel = new JLabel("Data:");
		datePanel.add(dateLabel);
		calendar = new JCalendarCombo();
		calendar.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		datePanel.add(calendar);
		operationPane.add(datePanel);
		dateArea = new JTextArea();
		dateArea.setMaximumSize(new Dimension(100,20));
		

		dateArea.setForeground(new Color(255,255,255));
		dateArea.repaint();
		datePanel.add(dateArea);
		calculationPanel.add(operationPane);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		dateArea.setText(df.format(calendar.getDate()));
		dateArea.setForeground(Color.BLACK);
		dateArea.setEditable(false);
		
		conPane.add(calculationPanel,BorderLayout.PAGE_START);
		JPanel entryPanel = new JPanel();
		entryPanel.setLayout(new BoxLayout(entryPanel,BoxLayout.X_AXIS));
		entryPanel.setMaximumSize(new Dimension(280, 30));
		JLabel entryInfo = new JLabel("Wartość: ");
		entryPanel.add(entryInfo);
		entryArea = new JTextField();
		entryArea.setPreferredSize(new Dimension(100, 25));
		entryPanel.add(entryArea);
		addEntryButton = new JButton("Dodaj wartość");
		entryPanel.add(addEntryButton);
		tablePanel.add(entryPanel);
		
		
		
		updateChart(true);
		
	
		add(conPane);
		
		
		
		
		// Events

		//Kalendarz
		calendar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				dateArea.setText(df.format(calendar.getDate()));
				logger.info("Wybrano datę"+ df.format(calendar.getDate()));
				
			}
		});
		
		
		
		// Zdarzenie wpisywania wartości do tablicy
		addEntryButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				char cell = '_';
				if (table.isEditing()) {
					table.getCellEditor().stopCellEditing();
				}
				
				switch (xSlider.getValue()) {
				case 0:
					col = 0;
					cell = 'A';
					break;
				case 25:
					col = 1;
					cell = 'B';
					break;
				case 50:
					col = 2;
					cell = 'C';
					break;
				case 75:
					col = 3;
					cell = 'D';
					break;
				case 100:
					col = 4;
					cell = 'E';
					break;
				}

				switch (ySlider.getValue()) {
				case 0:
					row = 4;
					break;
				case 25:
					row = 3;
					break;
				case 50:
					row = 2;
					break;
				case 75:
					row = 1;
					break;
				case 100:
					row = 0;
					break;
				}

				
				if(getEntryValue()) {
				table.getModel().setValueAt(value, row, col);
				helper.setSaveState(false);
				state.setSaveStatus(helper.getSaveState());
				table.repaint();
				logger.info("Wprowadzono wartość: "+ value + " " + "do komórki: " + cell+(row+1));
				updateChart(false);
				}
				
				
				
			}

		});
		
		

		// Zdarzenie zerowania tablicy
		zeroButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int value = JOptionPane.showOptionDialog(null, "Czy na pewno chcesz wyzerować tablicę?", "Uwaga",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						new String[] { "Tak", "Nie" }, "Tak");
				if (value == JOptionPane.YES_OPTION) {

					//Operacja zerowania modelu
					((OperationManager) table.getModel()).fillWithZeros();
					
					helper.setSaveState(false);
					table.repaint();
					state.setSaveStatus(false);
					updateChart(true);
				} else {

				}

			}

		});

		// Zdarzenie obliczania wyniku
		calculateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switch (operationList.getSelectedIndex()) {
				case 0:
					resultArea.setText(String.valueOf(((OperationManager) table.getModel()).sumOperation()));
					logger.info("Przeprowadzono operację suma");
					break;
				case 1:
					resultArea.setText(String.valueOf(((OperationManager) table.getModel()).averageOperation()));
					logger.info("Przeprowadzono operację średnia");
					break;
				case 2:
					resultArea.setText("Min: " + String.valueOf(((OperationManager) table.getModel()).minOperation())
							+ " Max: " + String.valueOf(((OperationManager) table.getModel()).maxOperation()));
					logger.info("Przeprowadzono operację min/max");
					break;
				default:
					break;

				}
				resPane.setViewportView(resultArea);
				resPane.repaint();
				
			}

		});

		//New file navbar
		newLink.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
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
							helper.saveFile(tempPath, ((OperationManager) getTable().getModel()).getData());
							logger.info("Zapisano plik: " + saveDialog.getFile());
							helper.saveState = true;
							frame.statusbar.setSaveStatus(helper.getSaveState());
							frame.statusbar.setFileName(helper.fileName);
							}
						} catch (IOException arg2) {
							// TODO Auto-generated catch block
							logger.error("Błąd zapisu pliku");
						}

						frame.statusbar.setSaveStatus(helper.getSaveState());

					}
					} else {
						setEnabled(true);
					}
					
				
				
				helper.saveState = false;
				helper.setNewFile();
				((OperationManager) getTable().getModel()).fillWithZeros();
				getTable().repaint();
				frame.statusbar.setSaveStatus(helper.saveState);
				frame.statusbar.setFileName(helper.fileName);
				logger.info("Utworzono nowy arkusz");
				updateChart(true);
				}
		});
		
		//Open navbar
		openLink.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openDialog.setVisible(true);

				tempPath = openDialog.getDirectory() + openDialog.getFile();
				
				System.out.println(tempPath);
				tempPath = tempPath.replace("\\", "\\\\");
				System.out.println(tempPath);
				if(openDialog.getDirectory()!=null && openDialog.getFile() !=null) {
					helper.activePath = tempPath;
					helper.fileName = openDialog.getFile();
					}
				else {
					return;
				}
				
				
				try {
					((OperationManager) table.getModel()).setData(helper.openFile(tempPath));
					table.repaint();
					renderer.setHorizontalAlignment(JLabel.RIGHT);
					helper.saveState = true;
					frame.statusbar.setSaveStatus(helper.getSaveState());
					frame.statusbar.setFileName(helper.fileName);
					logger.info("Załadowano plik: " + openDialog.getFile());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					logger.error("Błąd załadowania pliku");
				}
				
			}
		});
		
		
		//Save navbar
		saveLink.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
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
				
				
				System.out.println(helper.fileName);
				try {
					if(!helper.activePath.equals(null)) {
						System.out.println("NIE JESTEM NULLEM");
					helper.saveFile(tempPath, ((OperationManager) getTable().getModel()).getData());
					helper.saveState = true;
					state.setSaveStatus(helper.getSaveState());
					state.setFileName(helper.fileName);
					logger.info("Zapisano plik: " + saveDialog.getFile());
					return;
					}
				} catch (IOException arg0) {
					// TODO Auto-generated catch block
					logger.error("Błąd zapisu pliku");
					
				}

			}});
		
		//Find navbar
		findLink.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FindWindow findWindow = new FindWindow(mainContent);
				findWindow.setVisible(true);
				
			}
		});
		
		//Sum navbar
		sumLink.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setResult("Suma: " +String.valueOf(((OperationManager) getTable().getModel()).sumOperation()));
				logger.info("Przeprowadzono operację suma");
				
			}
		});

		//Avg navbar
		avgLink.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setResult("Średnia: " + String.valueOf(((OperationManager) getTable().getModel()).averageOperation()));
				logger.info("Przeprowadzono operację średnia");
				
			}
		});

		//Min/Max navbar
		minmaxLink.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setResult("Min: "+ String.valueOf(((OperationManager) getTable().getModel()).minOperation())
						+ " Max: "+ String.valueOf(((OperationManager) getTable().getModel()).maxOperation()));
				logger.info("Przeprowadzono operację min/max");
				
			}
		});
		
		//Zero navbar
		zeroLink.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int value = JOptionPane.showOptionDialog(null, "Czy na pewno chcesz wyzerować tablicę?", "Uwaga",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						new String[] { "Tak", "Nie" }, "Tak");
				if (value == JOptionPane.YES_OPTION) {

					//Operacja zerowania modelu
					((OperationManager) table.getModel()).fillWithZeros();
					logger.info("Wyzerowano tablicę");
					helper.setSaveState(false);
					table.repaint();
					state.setSaveStatus(false);
					updateChart(true);
				} else {

				}
				
			}
		});
		
		//Save as png file
		savePngLink.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					saveDialog.setVisible(true);
					tempPath = saveDialog.getDirectory() + saveDialog.getFile();
					tempPath = tempPath.replace("\\", "\\\\");
					OutputStream out = new FileOutputStream(new File(tempPath));
					
					
					if(saveDialog.getDirectory()!=null && saveDialog.getFile() !=null) {
						ChartUtilities.writeChartAsPNG(out, chart, chartPanel.getWidth(), chartPanel.getHeight());
						logger.info("Wyeksportowano wykres do pliku: "+ saveDialog.getFile());
						out.close();
						}
					else {
						return;
					}
				    
				    

				} catch (IOException ex) {
					logger.error("Błąd eksportu wykresu do pliku .png");
				}
				
			}
		});
		
		
		
		//Refresh chart
		refreshLink.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateChart(false);
				logger.info("Odświeżono wykres");
				
			}
		});
		
		//Save
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
				
				
				System.out.println(helper.fileName);
				try {
					if(!helper.activePath.equals(null)) {
						System.out.println("NIE JESTEM NULLEM");
					helper.saveFile(tempPath, ((OperationManager) getTable().getModel()).getData());
					helper.saveState = true;
					state.setSaveStatus(helper.getSaveState());
					state.setFileName(helper.fileName);
					logger.info("Zapisano plik: " + saveDialog.getFile());
					return;
					}
				} catch (IOException arg0) {
					// TODO Auto-generated catch block
					logger.error("Błąd zapisu pliku");
					
				}

			}

		});
	}

	public void setResult(String result) {
		resultArea.setText(result);
	}

	//Tworzenie odpowiedniego formatu (liczby po przecinku)
	public String getFormat(String text) {
		format = "#";
		for (int i = 0; i < text.length(); i++) {
			format = format + "#";
		}
		return format;
	}
	
	public void updateChart(boolean initialFlag) {
		
		HistogramDataset dataset = new HistogramDataset();
		if(initialFlag==false) {
		dataset.addSeries("Histogram", (((OperationManager) table.getModel()).getChartData()), 30);
		}else {
			dataset.addSeries("Histogram", (((OperationManager) table.getModel()).getChartData()), 30,-1000,1000);
		}
		JPanel bottomPanel = new JPanel();
		bottomPanel.setMaximumSize(new Dimension(800,100));
		bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT,40,5));
		

		PlotOrientation orientation = PlotOrientation.VERTICAL; 
		int max = (int)((OperationManager) table.getModel()).maxOperation();
		

		chart = ChartFactory.createHistogram("Histogram pionowy", "Wartość", "Ilość", dataset, orientation, false, false, false);
		chart.setBorderVisible(true);
		chart.setTitle(new TextTitle("Histogram pionowy", new Font("Verdana", Font.PLAIN, 18)));
		chartPanel = new ChartPanel(chart);
		chartPanel.setBackground(Color.GRAY);
		chartPanel.setPreferredSize(new Dimension(880,230));
		
		
	
		bottomPanel.add(chartPanel);
		
		
		
		conPane.add(bottomPanel);
	}

	public void highlightCell(int row, int col) {
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setColumnSelectionAllowed(true);
		table.setRowSelectionAllowed(true);
		table.changeSelection(row, col, false, false);
	}

	public JTable getTable() {
		return table;
	}
	
	

	// Pobieranie wartości
	public boolean getEntryValue() {
		//Zastapienie przecinka kropka
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
		otherSymbols.setDecimalSeparator('.');
		//Pobieranie i tworzenie odpowiedniego formatu
		df = new DecimalFormat("0." + getFormat(entryArea.getText()), otherSymbols);
		try {

			if (entryArea.getText() == "0") {
				value = 0;
				return true;
			} else {
				//Przypisywanie formatu do liczby decimal format i konwersja na double
				value = df.format(Double.valueOf(entryArea.getText()));
				
				return true;
			}
			//Lapanie wyjatku
		} catch (NumberFormatException e) {
			JOptionPane.showOptionDialog(null, "Wartość musi być liczbą!", "Uwaga",
						JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null,
						new String[] {"OK"}, "OK");
			logger.error("Wprowadzono nieprawidłową wartość");
			return false;
		}

	}

}

