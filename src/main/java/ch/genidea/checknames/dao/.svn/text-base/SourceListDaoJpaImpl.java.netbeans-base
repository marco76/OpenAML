/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.genidea.checknames.dao;

import ch.genidea.checknames.model.SourceList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marcomolteni
 */
@Transactional
public class SourceListDaoJpaImpl implements SourceListDao{


    private EntityManager em;

    @PersistenceContext(name="XMLReaderPU")
     public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    
    public void save(SourceList entry) {
        em.persist(entry);
    }

    public SourceList update(SourceList entry) {
        return em.merge(entry);
    }

    public void delete(SourceList entry) {
       em.remove(entry);
    }

    public int getNumberOfEntries() {
       Query qry = em.createNativeQuery("Select count(*) from SourceList");
       Float result = (Float) qry.getSingleResult();
       return result.intValue();
    }

    public List<SourceList> findAll() {
        return em.createQuery("Select o from SourceList o").getResultList();
    }

    public SourceList findById(int id){
        return em.find(SourceList.class, id);
    }


	public String getFileName(SourceList sourceList) {
		return "" + sourceList.getId() + "_" + sourceList.getName();
	}


}
