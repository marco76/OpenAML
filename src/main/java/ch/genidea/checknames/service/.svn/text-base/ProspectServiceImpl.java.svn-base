package ch.genidea.checknames.service;

import java.util.Collection;

import ch.genidea.checknames.dao.ProspectDao;
import ch.genidea.checknames.model.Prospect;

public class ProspectServiceImpl implements ProspectService {

	private ProspectDao dao;
	
	public void setDao(ProspectDao dao) {
		this.dao = dao;
	}

	public Collection<Prospect> findAll() {
		return dao.findAll();
	}

	public void save(Prospect entry) {
		dao.saveProspect(entry);

	}

}
