/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.genidea.checknames.service;

import ch.genidea.checknames.dao.ListEntryDao;
import ch.genidea.checknames.model.ListEntry;
import java.util.HashMap;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marcomolteni
 */
@Transactional
public class ListEntryServiceImpl implements ListEntryService{

    ListEntryDao listEntryDao;

    public boolean insert(ListEntry entry) {
       listEntryDao.saveListEntry(entry);
       return false;
    }

    public int persist(HashMap<Long, ListEntry> list) {
      int total = 0;
        
      for (ListEntry entry : list.values()){
           listEntryDao.update(entry);
           total++;
           
           }
      return total;
    }
      public void persistList(List <ListEntry> list){
      listEntryDao.persistList(list);
       
    }

    public int getNumberOfEntries() {
        return listEntryDao.getNumberOfEntries();
    }

    public List<ListEntry> findAll() {
        return listEntryDao.findAll();
    }

    /**
     * @param listEntryDao the listEntryDao to set
     */
    public void setListEntryDao(ListEntryDao listEntryDao) {
        this.listEntryDao = listEntryDao;
    }

    public int mergeOnUID(ListEntry entry) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void updateList(int listNumber, List <ListEntry> list ){
    	listEntryDao.deleteAllValuesInList(listNumber);
    	persistList(list);
    }

}
