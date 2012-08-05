/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.genidea.checknames.service;

import ch.genidea.checknames.model.ListEntry;
import ch.genidea.checknames.search.SearchBean;
import ch.genidea.checknames.search.SearchBeanPEP;

import java.util.List;

import org.apache.lucene.search.Query;

/**
 *
 * @author marcomolteni
 */
public interface SimpleQuery {

	// TODO: Generics!!!!!!!
    SearchBean HibernateSearch(String criteria);
    SearchBeanPEP HibernateSearchPEP(String criteria, int firstResult, int maxResult); 
    
    SearchBean HibernateSearch(String criteria, int firstResult, int maxResult);

    Query multiFieldQuery(String criteria);

}
