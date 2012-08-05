/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.genidea.checknames.service;

import ch.genidea.checknames.model.SourceList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marcomolteni
 */

@Transactional
public interface SourceListService {
    public List<SourceList> getLists();
     public SourceList findSourceList(int id);
    public void update(SourceList sourceList);
    public String getFileName(SourceList sourceList);

}
