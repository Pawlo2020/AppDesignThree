package operations;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 * <h3>FileHelper class</h3>
 * <p>FileHelper class is responsible for managing and working with the data of actual handling file.</p>
 * <p>Class defines working behaviours with the file </p>
 *
 * 
 * @author Paweł Szeląg
 * @version 3.0.0
 * @since   2019-05-16
 */



public class FileHelper {
	
	/** Variable which contains active file path*/
	public String activePath;
	
	/** Variable which contains active file path*/
	public String fileName;
    
	/** Writer module used in save modes*/
	private BufferedWriter _writer;
    
	/** Loader module used in opening modes*/
	private BufferedReader _reader;
    
	/**Array of strings represents single row*/
	private String[] list;
	
	/**Bolean variable stores information about saving state*/
	public boolean saveState;
	
	/**Data object array*/
	Object data[][];
	
	/**
	 * Constructor of the class. It is responsible for initializing the array of data and setting save status
	 */
	public FileHelper() {
		setNewFile();
		data = new String[5][5];
		
	}
	
	/**
	 * Method responsible for setting new file handler
	 */
	public void setNewFile() {
		activePath = null;
		fileName = "bezNazwy";
		saveState = false;
		 _writer = null;
		
		
	}
	
	
	/**
	 * Gets the save state.
	 * @return Saving state
	 */
	public boolean getSaveState() {
		return saveState;
	}
	
	/**
	 * Sets current state of saving the file
	 * @param state Save state
	 */
	public void setSaveState(boolean state) {
		saveState = state;
	}
	
	//Zapis do pliku
	
	/**
	 * Method responsible for saving data into the file
	 * @param path Path of file
	 * @param data Passed data array
	 * @throws IOException In case of path error
	 */
	public void saveFile(String path, Object[][] data) throws IOException {
		activePath = path;
		//System.out.println(path);
		if(path!= null) {
		 _writer = new BufferedWriter(new FileWriter(path));
		}else {
			return;
		}
		String line = "";
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				line = line + String.valueOf(data[i][j]) + ";";
			}
			System.out.println(line);
			_writer.write(line);
			_writer.flush();
	        //Providing new line for future writings
	        _writer.newLine();
			line = "";
		}
        _writer.close();
		saveState = true;
	}

	/**
	 * Method responsible for saving a data into the file with same path as previously
	 * @param data Data array object
	 * @throws IOException In case of wrong path
	 */
	public void saveFile(Object[][] data) throws IOException {
		
		//System.out.println(path);
		if(activePath!= null) {
			 _writer = new BufferedWriter(new FileWriter(activePath));
			}else {
				return;
			}
		String line = "";
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				line = line + String.valueOf(data[i][j]) + ";";
			}
			System.out.println(line);
			_writer.write(line);
			_writer.flush();
	        //Providing new line for future writings
	        _writer.newLine();
			line = "";
		}
        _writer.close();
		saveState = true;
	}
	
	//Zadalowanie pliku
	/**
	 * Method responsible for loading data from file into the table model
	 * @param path Path of current file
	 * @return Data object array
	 * @throws IOException In case of wrong path of file
	 */
	public Object[][] openFile(String path) throws IOException {
		activePath = path;
		_reader = new BufferedReader(new FileReader(path));
		list = new String[4];
		for(int i=0;i<5;i++) {
		list = (_reader.readLine()).split(";");
		for(int j=0;j<5;j++) {
			data[i][j] = list[j];
		}
		}
		saveState = true;
		_reader.close();
		
		return data;
	}
}
