package ch.genidea.checknames.service;

import java.util.List;
import java.util.Vector;

import ch.genidea.checknames.model.ListEntry;
import ch.genidea.checknames.search.SearchCriteria;

public interface SearchResultService {
	public List<ListEntry> getResults (String name);

	public List<ListEntry> getResult(SearchCriteria searchCriteria);

	public Vector<ListEntry> getVectorResult(SearchCriteria searchCriteria);

	
}
