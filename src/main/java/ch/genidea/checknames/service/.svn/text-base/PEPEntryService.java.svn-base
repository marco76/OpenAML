/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.genidea.checknames.service;


import ch.genidea.checknames.model.PEPEntry;

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

public interface PEPEntryService {

	  public boolean insert(PEPEntry entry);
	    public int persist(HashMap<Long, PEPEntry> list);
	    public int getNumberOfEntries();
	    public List<PEPEntry> findAll();
	    public int mergeOnUID(PEPEntry entry);
		public void persistList(List <PEPEntry> list);
		public void updateList(int listNumber, List <PEPEntry> list);


}
