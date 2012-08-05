/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.genidea.ofac.web.jsf.managedBean;

import ch.genidea.checknames.model.ListEntry;
import ch.genidea.checknames.model.PEPEntry;
import ch.genidea.checknames.model.SearchLog;
import ch.genidea.checknames.search.SearchBean;
import ch.genidea.checknames.search.SearchBeanPEP;
import ch.genidea.checknames.service.SearchLogService;
import ch.genidea.checknames.service.SimpleQuery;
import ch.genidea.checknames.utility.PerformanceTest;

import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.HttpRequest;
import org.apache.poi.hssf.record.formula.functions.Search;

/**
 * 
 * @author marcomolteni
 */

public class PersonMB {
	private String firstName;
	private String familyName;
	private SearchBean searchBean;
	private SearchBeanPEP searchBeanPEP;
	private List<ListEntry> resultList;
	private List<PEPEntry> resultListPEP;
	private SimpleQuery simpleQuery;
	private boolean fuzzySearch = false;
	private float timeToSearch = 0f;
	private float requestsPerSecond = 0f;
	private int resultTotal = 0;
	private int resultShowed = 0;
	private boolean resultShowedLimited=false;
	private SearchLogService slog;
	private String ipRequester; 
	private boolean pepSearch = false;
	
	public boolean getPepSearch() {
		return pepSearch;
	}

	public void setPepSearch(boolean pepSearch) {
		this.pepSearch = pepSearch;
	}

	/** Creates a new instance of PersonMB */
	public PersonMB() {
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the LastName
	 */
	public String getFamilyName() {
		return familyName;
	}

	/**
	 * @param LastName
	 *            the LastName to set
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	@SuppressWarnings("unchecked")
	public String searchResult() {
		ipRequester = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
		

		
		timeToSearch = 0f;
		int maxResults = 3;
		String queryString = "";
		if (firstName != null) {
			
			queryString = correctString(firstName);
			PerformanceTest pt = new PerformanceTest();
			pt.startTime();
			searchBean = simpleQuery.HibernateSearch(queryString, 0, maxResults);
			
			if (pepSearch){
				searchBeanPEP = simpleQuery.HibernateSearchPEP(queryString, 0, maxResults);
				setResultListPEP((List<PEPEntry>)searchBeanPEP.getListResults());
			}
			timeToSearch = (float)pt.stopTime();
			
			resultList = (List<ListEntry>)searchBean.getListResults();
			
			resultShowed = searchBean.getRecordsShowed();
			resultTotal = searchBean.getRecordsTotal();
		
			//TODO: parametrize!!!
			if (true){
			
		
			SearchLog sl = new SearchLog();
			sl.setStringInserted(queryString);
			sl.setTimeToAnswer((int)timeToSearch);
			sl.setIpRequester(ipRequester);
			slog.save(sl);
		}	
		} else
			resultList = null;

		return "null";
	}

	/**
	 * @return the resultList
	 */
	public List<ListEntry> getResultList() {
		return resultList;
	}

	public int getResultNumber() {
		if (resultList != null)
			return resultList.size();
		return 0;
	}

	/**
	 * @param simpleQuery
	 *            the simpleQuery to set
	 */
	public void setSimpleQuery(SimpleQuery simpleQuery) {
		this.simpleQuery = simpleQuery;
	}

	private String correctString(String query) {
		String[] strings = query.trim().split(" ");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < strings.length; i++) {
			// if the first char is a space then is a void string
			if (strings[i].length() > 0 && strings[i].charAt(0) != ' '){
			sb.append(" +" + strings[i].trim());
			if (isFuzzySearch())
				sb.append("~");
			}
		}

		return sb.toString();
	}

	/**
	 * @return the fuzzySearch
	 */
	public boolean isFuzzySearch() {
		return fuzzySearch;
	}

	/**
	 * @param fuzzySearch
	 *            the fuzzySearch to set
	 */
	public void setFuzzySearch(boolean fuzzySearch) {
		this.fuzzySearch = fuzzySearch;
	}

	public float getTimeToSearch() {
		if (timeToSearch > 0)
		  return timeToSearch/1000;
		return timeToSearch;
	}

	public float getRequestsPerSecond() {
		if (timeToSearch > 0)
			return 1000/timeToSearch;
		return 0;
	}


	public void setResultTotal(int resultTotal) {
		this.resultTotal = resultTotal;
	}

	public int getResultTotal() {
		return resultTotal;
	}

	public void setResultShowed(int resultShowed) {
		this.resultShowed = resultShowed;
	}

	public int getResultShowed() {
		return resultShowed;
	}

	public void setResultShowedLimited(boolean resultShowedLimited) {
		this.resultShowedLimited = resultShowedLimited;
	}

	public boolean isResultShowedLimited() {
		if (resultShowed < resultTotal)
			return true;
		return false;
	}

	public void setSlog(SearchLogService slog) {
		this.slog = slog;
	}

	public String getIpRequest() {
		return ipRequester;
	}

	public void setResultListPEP(List<PEPEntry> resultListPEP) {
		this.resultListPEP = resultListPEP;
	}

	public List<PEPEntry> getResultListPEP() {
		return resultListPEP;
	}

}
