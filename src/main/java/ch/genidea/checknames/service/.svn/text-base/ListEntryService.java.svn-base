/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.genidea.checknames.service;

import ch.genidea.checknames.model.ListEntry;

import java.util.HashMap;
import java.util.List;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marcomolteni
 */
@Transactional(propagation=Propagation.REQUIRES_NEW, isolation=Isolation.REPEATABLE_READ)

public interface ListEntryService {
	  public boolean insert(ListEntry entry);
	    public int persist(HashMap<Long, ListEntry> list);
	    public int getNumberOfEntries();
	    public List<ListEntry> findAll();
	    public int mergeOnUID(ListEntry entry);
		public void persistList(List <ListEntry> list);
		public void updateList(int listNumber, List <ListEntry> list);

  

}
