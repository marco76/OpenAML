package ch.genidea.checknames.search;

import java.util.List;

import ch.genidea.checknames.model.ListEntry;

public class SearchBean {
	
	private List<ListEntry> listResults;
	private int recordsTotal;
	private int recordsShowed;
	
	public void setListResults(List<ListEntry> listResults) {
		this.listResults = listResults;
	}
	public List<ListEntry> getListResults() {
		return listResults;
	}
	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public int getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsShowed(int recordsShowed) {
		this.recordsShowed = recordsShowed;
	}
	public int getRecordsShowed() {
		
		return listResults.size();
	}

}
