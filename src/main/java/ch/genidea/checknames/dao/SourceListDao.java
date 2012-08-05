/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.genidea.checknames.dao;

import ch.genidea.checknames.model.SourceList;
import java.util.List;

/**
 *
 * @author marcomolteni
 */
public interface SourceListDao {
    public SourceList findById (int id);
    public void save(SourceList entry);
    public  SourceList update (SourceList entry);
    public void delete (SourceList entry);
    public int getNumberOfEntries ();
    public List<SourceList> findAll();
    public String getFileName(SourceList entry);

}
