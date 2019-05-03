package operations;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHelper {
	
	public String activePath;
	public String fileName;
    private BufferedWriter _writer;
    private BufferedReader _reader;
    private String[] list;
	public boolean saveState;
	
	Object data[][];
	public FileHelper() {
		setNewFile();
		data = new String[5][5];
		
	}
	
	public void setNewFile() {
		activePath = null;
		fileName = "bezNazwy";
		saveState = false;
		 _writer = null;
		
		
	}
	
	
	public boolean getSaveState() {
		return saveState;
	}
	
	public void setSaveState(boolean state) {
		saveState = state;
	}
	
	//Zapis do pliku
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
