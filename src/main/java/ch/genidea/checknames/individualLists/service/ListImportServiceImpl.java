/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.genidea.checknames.individualLists.service;

import ch.genidea.checknames.importer.OFACParser;

import ch.genidea.checknames.model.ListEntry;
import ch.genidea.checknames.model.PEPEntry;
import ch.genidea.checknames.model.SourceList;
import ch.genidea.checknames.service.IndexService;
import ch.genidea.checknames.service.ListEntryService;
import ch.genidea.checknames.service.PEPEntryService;
import ch.genidea.checknames.service.SourceListService;
import ch.genidea.checknames.utility.ParseExcelFileForUkList;
import ch.genidea.checknames.utility.ParseOFACImpl;
import ch.genidea.checknames.utility.ParseUSACongressImpl;
import ch.genidea.checknames.utility.ParseUSASenateImpl;
import ch.genidea.checknames.utility.UploadFileUtility;
import ch.genidea.checknames.utility.VariablesContainer;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author marcomolteni
 */
public class ListImportServiceImpl implements ListImportService {
    private ListEntryService service;
    PEPEntryService pepService;
    OFACParser ofacParser;
   
    public void setPepService(PEPEntryService pepService) {
		this.pepService = pepService;
	}

	SourceListService sourceListService;
    UploadFileUtility upload;
    VariablesContainer variables;
    private IndexService indexService;
    
    // load the file
    // parse the file
  
    public void setUpload(UploadFileUtility upload) {
		this.upload = upload;
	}

	/**
     * @param service the service to set
     */
    public void setService(ListEntryService service) {
        this.service = service;
    }

    /**
     * @param fileName the fileName to set
     */
  
    public void parseUKFile() {
    	SourceList sl = sourceListService.findSourceList(2);
    	String fileSource = variables.getFilePath()+ "/" + sourceListService.getFileName(sl);
    	upload.uploadFile(fileSource, sl.getWebSource());
    	    
    	ParseExcelFileForUkList parse = new ParseExcelFileForUkList();
        parse.setFileSource(fileSource);
        List<ListEntry> list = parse.parseFile(sl);
        insertIntoDB(sl.getId(), list);
      
       updateListInformations(sl.getId(), list.size());
    }


    public void parseOFACXml() {
    	SourceList sl = sourceListService.findSourceList(1);
    	String fileSource = variables.getFilePath()+ "/" + sourceListService.getFileName(sl);
  //  	upload.uploadFile(fileSource, sl.getWebSource());
    	
    	
    	ofacParser.setFilename(fileSource);
    	ofacParser.setSourceList(sl);
    	List<ListEntry> list = ofacParser.parse();
    	
    	/*
    	ParseOFACImpl pe = new ParseOFACImpl();
        pe.setFileName(fileSource);
    	pe.setService(sourceListService);
        
    	List<ListEntry> list = pe.getOfacList();
        */
        insertIntoDB(sl.getId(), list);
        updateListInformations(sl.getId(), list.size());
        list = null;
    }

    public UploadFileUtility getUpload() {
		return upload;
	}

	void updateListInformations(int listId, int size){
        SourceList sl = sourceListService.findSourceList(listId);
        sl.setLastImported(new Date());
        sl.setNoOfRecordsImported(size);
        sourceListService.update(sl);

    }

    public void insertIntoDB(int listNumber, List<ListEntry> list){
    	/*   for (Iterator<ListEntry> it = list.iterator(); it.hasNext();) {
            ListEntry listEntry = it.next();
            if (listEntry != null)
            service.insert(listEntry);
*/
    	service.updateList(listNumber, list);
        }
    

   
    /**
     * @param sourceListService the sourceListService to set
     */
    public void setSourceListService(SourceListService sourceListService) {
        this.sourceListService = sourceListService;
    }
    // delete db
    // insert db

	
	public void setVariables(VariablesContainer variables) {
		this.variables = variables;
	}

	public VariablesContainer getVariables() {
		return variables;
	}

	public String reloadLists() {
		parseOFACXml();
		parseUKFile();
		indexService.manualReIndex();
		return null;
		
		
	}

	public void setIndexService(IndexService indexService) {
		this.indexService = indexService;
	}

	public void parseUSASenate() {
		SourceList sl = sourceListService.findSourceList(3);
    	String fileSource = variables.getFilePath()+ "/" + sourceListService.getFileName(sl);
    	upload.uploadFile(fileSource, sl.getWebSource());
    	   	
    	ParseUSASenateImpl pe = new ParseUSASenateImpl();
        pe.setFileName(fileSource);
        pe.setSL(sl);
        List<PEPEntry> list = pe.parseUSASenateist();
       
        pepService.updateList(sl.getId(), list);
        updateListInformations(sl.getId(), list.size());
		
	}
	
	public void parseUSACongress(){
		SourceList sl = sourceListService.findSourceList(4);
    	String fileSource = variables.getFilePath()+ "/" + sourceListService.getFileName(sl);
    	upload.uploadFile(fileSource, sl.getWebSource());
    	   	
    	ParseUSACongressImpl pe = new ParseUSACongressImpl();
        pe.setFileName(fileSource);
        pe.setSL(sl);
        List<PEPEntry> list = pe.parse();
       
        pepService.updateList(sl.getId(), list);
        updateListInformations(sl.getId(), list.size());
		
	}
	
		public void setOfacParser(OFACParser ofacParser) {
			this.ofacParser = ofacParser;
		}

		
	    


}
