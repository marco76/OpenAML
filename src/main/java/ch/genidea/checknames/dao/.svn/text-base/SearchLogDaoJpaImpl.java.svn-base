package ch.genidea.checknames.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import ch.genidea.checknames.model.SearchLog;

@Transactional
public class SearchLogDaoJpaImpl implements SearchLogDao {
	
	  private EntityManager em;

	    @PersistenceContext
	     public void setEntityManager(EntityManager em) {
	        this.setEm(em);
	    }
	public void save(SearchLog entity) {
		em.persist(entity);
	}
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
}
