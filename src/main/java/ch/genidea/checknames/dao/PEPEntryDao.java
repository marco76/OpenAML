/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.genidea.checknames.dao;

import ch.genidea.checknames.model.PEPEntry;

import java.util.List;

import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marcomolteni
 */

public interface PEPEntryDao {
    public PEPEntry findById (long id);
    public void saveListEntry (PEPEntry entry);
    public PEPEntry update (PEPEntry entry);
    public void delete (PEPEntry entry);
    public int getNumberOfEntries ();
    public List<PEPEntry> findAll();
    public void persistList(List<PEPEntry> list);
	@Transactional
	public void deleteAllValuesInList(int listNumber);
    	   
}
