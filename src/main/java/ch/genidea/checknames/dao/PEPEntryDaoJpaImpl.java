/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.genidea.checknames.dao;

import ch.genidea.checknames.model.PEPEntry;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transaction;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marcomolteni
 */

public class PEPEntryDaoJpaImpl implements PEPEntryDao{


    EntityManager em;


     @PersistenceContext
     public void setEntityManager(EntityManager em) {
        this.em = em;
    }



    public PEPEntry findById(long id) {
        return em.find(PEPEntry.class, id);
    }

    public void saveListEntry(PEPEntry entry) {
       if (entry.getTitle() != null && entry.getTitle().length() > 25)
    	   entry.setTitle(entry.getTitle().substring(0,24));
    	em.persist(entry);

    }

    public PEPEntry update(PEPEntry entry) {

       return em.merge(entry);
    }

    public void delete(PEPEntry entry) {
       em.remove(entry);
    }


    @Transactional(readOnly=true)
    public int getNumberOfEntries(){
       Query qry = em.createNativeQuery("Select count(*) from PEPEntry");
       BigInteger result = (BigInteger) qry.getSingleResult();
       return result.intValue();
    }
    
    @SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
    public List<PEPEntry> findAll(){
        return em.createQuery("Select o from PEPEntry o").getResultList();
    }
  
    @Transactional
    public void persistList(List<PEPEntry> list){
    	for (Iterator<PEPEntry> it = list.iterator(); it.hasNext();) {
    		PEPEntry listEntry = it.next();
            if (listEntry != null)
           saveListEntry(listEntry);
         }
    }
   
    @Transactional
    public void deleteAllValuesInList(int listNumber){
    	Query qry = em.createNativeQuery("Delete from PEPEntry where listCode  = ?").setParameter(1, listNumber);
    	qry.executeUpdate();
    }





}
