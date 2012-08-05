/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.genidea.checknames.service;


import ch.genidea.checknames.model.ListEntry;
import ch.genidea.checknames.model.PEPEntry;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marcomolteni
 */
@Transactional
public class IndexServiceImpl implements IndexService {
    @PersistenceContext
    protected EntityManager em;

    public String manualReIndex(){
        FullTextEntityManager ftem = Search.getFullTextEntityManager(em);
       // ftem.getTransaction().begin();

        @SuppressWarnings("unchecked")
        List<ListEntry> entries = em.createQuery("select e from ListEntry e").getResultList();
        int counter = 0;

        for (ListEntry entry : entries){
            ftem.index(entry);
            counter++;
          }

        return null;
       // ftem.getTransaction().commit();
    }

	public String manualReIndexPEP() {
		  FullTextEntityManager ftem = Search.getFullTextEntityManager(em);
	       // ftem.getTransaction().begin();

	        @SuppressWarnings("unchecked")
	        List<PEPEntry> entries = em.createQuery("select e from PEPEntry e").getResultList();
	        int counter = 0;

	        for (PEPEntry entry : entries){
	            ftem.index(entry);
	            counter++;
	          }

	        return null;
		
	}

}
