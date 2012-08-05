package ch.genidea.checknames.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import ch.genidea.checknames.model.ListEntry;
import ch.genidea.checknames.model.PEPEntry;
import ch.genidea.checknames.model.SearchLog;
import ch.genidea.checknames.search.SearchBean;
import ch.genidea.checknames.search.SearchCriteria;
import ch.genidea.checknames.utility.PerformanceTest;

public class SearchResultServiceImpl implements SearchResultService{
 SimpleQuery simpleQuery;
 SearchBean searchBean;
  
	public List<ListEntry> getResults(String name) {
		
		
		searchBean = simpleQuery.HibernateSearch(name);
		return searchBean.getListResults();
	}

	public void setSimpleQuery(SimpleQuery simpleQuery) {
		this.simpleQuery = simpleQuery;
	}
	
	public List<ListEntry> getResult(SearchCriteria searchCriteria){
		int maxResults = 3;
		String queryString = searchCriteria.getSearchString();
		List<ListEntry> result = null;
		if (!searchCriteria.getSearchString().equals("")) {
			queryString = correctString(queryString, searchCriteria.isFuzzy());
			searchBean = simpleQuery.HibernateSearch(queryString, 0, maxResults);
			// sublist needed because bug in hibernate  	 HHH-199
			List<ListEntry> sublist = searchBean.getListResults();
			List<ListEntry> tmp = new ArrayList<ListEntry> ();
			for(int i = 0; i < sublist.size() ; i++)
			{
					tmp.add(sublist.get(i));
			}
			result = tmp;
			
	}
		return result;
	}
	
	//Because a bug between webflow and RichFaces!!!!
	public Vector<ListEntry> getVectorResult(SearchCriteria searchCriteria){
		List<ListEntry> le =  getResult(searchCriteria);
		Vector<ListEntry> vct = new Vector<ListEntry>(5);
		if (le!=null)
		for(int i= 0; i<le.size(); i++)
		vct.add(le.get(i));
		
		return vct;
	}
	
	String correctString(String query, boolean isFuzzy) {
		String[] strings = query.trim().split(" ");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < strings.length; i++) {
			// if the first char is a space then is a void string
			if (strings[i].length() > 0 && strings[i].charAt(0) != ' '){
			sb.append(" +" + strings[i].trim());
			if (isFuzzy)
				sb.append("~");
			}
		}

		return sb.toString();
	}


}
