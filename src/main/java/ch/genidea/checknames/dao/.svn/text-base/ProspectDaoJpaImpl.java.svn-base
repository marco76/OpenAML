package ch.genidea.checknames.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import ch.genidea.checknames.model.Prospect;

@Transactional
public class ProspectDaoJpaImpl implements ProspectDao {

	private EntityManager em;

    @PersistenceContext
     public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	@SuppressWarnings("unchecked")
	public Collection<Prospect> findAll() {
		return em.createQuery("Select p from Prospect p").getResultList();
		
	}

	public void saveProspect(Prospect entry) {
		em.persist(entry);

	}

}
