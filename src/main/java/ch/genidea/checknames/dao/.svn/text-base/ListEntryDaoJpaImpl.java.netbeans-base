/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.genidea.checknames.dao;

import ch.genidea.checknames.model.ListEntry;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transaction;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marcomolteni
 */
@Repository
public class ListEntryDaoJpaImpl implements ListEntryDao{


    EntityManager em;


     @PersistenceContext(name="XMLReaderPU")
     public void setEntityManager(EntityManager em) {
        this.em = em;
    }



    public ListEntry findById(long id) {
        return em.find(ListEntry.class, id);
    }

    public void saveListEntry(ListEntry entry) {
       if (entry.getTitle() != null && entry.getTitle().length() > 25)
    	   entry.setTitle(entry.getTitle().substring(0,24));
    	em.persist(entry);

    }

    public ListEntry update(ListEntry entry) {

       return em.merge(entry);
    }

    public void delete(ListEntry entry) {
       em.remove(entry);
    }


    @Transactional(readOnly=true)
    public int getNumberOfEntries(){
       Query qry = em.createNativeQuery("Select count(*) from ListEntry");
       BigInteger result = (BigInteger) qry.getSingleResult();
       return result.intValue();
    }
    
    @SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
    public List<ListEntry> findAll(){
        return em.createQuery("Select o from ListEntry o").getResultList();
    }
  
    @Transactional
    public void persistList(List<ListEntry> list){
    	for (Iterator<ListEntry> it = list.iterator(); it.hasNext();) {
            ListEntry listEntry = it.next();
            if (listEntry != null)
           saveListEntry(listEntry);
         }
    }
   
    @Transactional
    public void deleteAllValuesInList(int listNumber){
    	Query qry = em.createNativeQuery("Delete from ListEntry where listCode  = ?").setParameter(1, listNumber);
    	qry.executeUpdate();
    }



}
