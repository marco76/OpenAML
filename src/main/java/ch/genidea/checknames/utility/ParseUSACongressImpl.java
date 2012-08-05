package ch.genidea.checknames.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ch.genidea.checknames.model.PEPEntry;
import ch.genidea.checknames.model.SourceList;

public class ParseUSACongressImpl {
	private String fileName = null;
	private int listNumber;
	private SourceList sl;

	public void setSL (SourceList sl) {
		this.sl = sl;
	}

	public void setListNumber(int listNumber) {
		this.listNumber = listNumber;
	}
	
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}
	
	public List<PEPEntry> parse()
	{
		String file = null;
		
		file = readFile(fileName);
		
		List <PEPEntry> resultList = new ArrayList<PEPEntry> ();
		PEPEntry pep = null;
		
		int recordPos = file.indexOf("Bodylink");
		int namePos = 0;
		int endNamePos = 0;
		String names[] = null;
		String familyName, firstName = null;
		while(recordPos > 0)
		{
		 pep = new PEPEntry();
		 namePos = file.indexOf('>', recordPos) + 1;
		 endNamePos = file.indexOf('<', recordPos);
		 names = file.substring(namePos, endNamePos).split(",");
		 familyName = names[0].trim();
		 firstName = names[1].trim();
		
		int beginRegionPos = file.indexOf(',', endNamePos) + 1;
		int endRegionPos = file.indexOf('<', beginRegionPos);
		String region = file.substring(beginRegionPos, endRegionPos);
		
		pep.setFamilyName(familyName);
		pep.setFirstName(firstName);
		pep.setAllNames(firstName);
		pep.setCounty(region);
		recordPos = file.indexOf("Bodylink", endRegionPos);
		pep.setSourceList(sl);
		resultList.add(pep);
		}
		return resultList;
		
	}

	private String readFile(String filename){
		StringBuilder contents = new StringBuilder();
	    
	    try {
	       BufferedReader input =  new BufferedReader(new FileReader(fileName));
	      try {
	        String line = null; //not declared within while loop
	        while (( line = input.readLine()) != null){
	          contents.append(line);
	          contents.append(System.getProperty("line.separator"));
	        }
	      }
	      finally {
	        input.close();
	      }
	    }
	    catch (IOException ex){
	      ex.printStackTrace();
	    }
	    
	    return contents.toString();

	}
}
