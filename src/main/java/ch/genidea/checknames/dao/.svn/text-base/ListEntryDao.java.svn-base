/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.genidea.checknames.dao;

import ch.genidea.checknames.model.ListEntry;
import java.util.List;

import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marcomolteni
 */

public interface ListEntryDao {
    public ListEntry findById (long id);
    public void saveListEntry (ListEntry entry);
    public  ListEntry update (ListEntry entry);
    public void delete (ListEntry entry);
    public int getNumberOfEntries ();
    public List<ListEntry> findAll();
    public void persistList(List<ListEntry> list);
	@Transactional
	public void deleteAllValuesInList(int listNumber);
    	   
}
