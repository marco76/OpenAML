/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.genidea.checknames.service;

import ch.genidea.checknames.dao.SourceListDao;
import ch.genidea.checknames.model.SourceList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marcomolteni
 */
@Transactional
public class SourceListServiceImpl implements SourceListService{

    private SourceListDao sourceListDao;

    public List<SourceList> getLists() {
        return sourceListDao.findAll();
    }

    /**
     * @param sourceListDao the sourceListDao to set
     */
    public void setSourceListDao(SourceListDao sourceListDao) {
        this.sourceListDao = sourceListDao;
    }

  

    public SourceList findSourceList(int id) {
        return sourceListDao.findById(id);
    }

    public void update(SourceList sourceList) {
        sourceListDao.update(sourceList);
    }

	public String getFileName(SourceList sourceList) {
		String fileName = sourceListDao.getFileName(sourceList).replace(" ", "_");
		return fileName;
	}

}
