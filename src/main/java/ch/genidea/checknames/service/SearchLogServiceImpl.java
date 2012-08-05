package ch.genidea.checknames.service;

import ch.genidea.checknames.dao.SearchLogDao;
import ch.genidea.checknames.model.SearchLog;

public class SearchLogServiceImpl implements SearchLogService {
	private SearchLogDao dao;
	
	public void save(SearchLog entity) {
		dao.save(entity);

	}

	public void setDao(SearchLogDao dao) {
		this.dao = dao;
	}

}
