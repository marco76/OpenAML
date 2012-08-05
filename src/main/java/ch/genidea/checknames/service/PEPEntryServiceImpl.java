/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.genidea.checknames.service;

import ch.genidea.checknames.dao.PEPEntryDao;
import ch.genidea.checknames.model.PEPEntry;
import java.util.HashMap;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marcomolteni
 */
@Transactional
public class PEPEntryServiceImpl implements PEPEntryService{

    PEPEntryDao pepEntryDao;

    public boolean insert(PEPEntry entry) {
    	pepEntryDao.saveListEntry(entry);
       return false;
    }

    public int persist(HashMap<Long, PEPEntry> list) {
      int total = 0;
        
      for (PEPEntry entry : list.values()){
    	  pepEntryDao.update(entry);
           total++;
           
           }
      return total;
    }
      public void persistList(List <PEPEntry> list){
    	  pepEntryDao.persistList(list);
       
    }

    public int getNumberOfEntries() {
        return pepEntryDao.getNumberOfEntries();
    }

    public List<PEPEntry> findAll() {
        return pepEntryDao.findAll();
    }

    /**
     * @param listEntryDao the listEntryDao to set
     */
    public void setPepEntryDao(PEPEntryDao pepEntryDao) {
        this.pepEntryDao = pepEntryDao;
    }

    public int mergeOnUID(PEPEntry entry) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void updateList(int listNumber, List <PEPEntry> list ){
    	pepEntryDao.deleteAllValuesInList(listNumber);
    	persistList(list);
    }

}
