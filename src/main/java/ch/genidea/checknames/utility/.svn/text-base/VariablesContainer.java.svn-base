package ch.genidea.checknames.utility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class VariablesContainer {
	Properties properties = null;
	String filePath = null;
	public void loadProperties(){
		java.io.FileInputStream fis;
		try {
			fis = new java.io.FileInputStream (new java.io.File(filePath));
			try {
				properties.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	public String getProperty(String propertyName){
		return properties.getProperty(propertyName);
	}
	public void setFilePath(String filePath){
		this.filePath = filePath;
	}
	public String getFilePath()
	{
		return filePath;
	}
}
