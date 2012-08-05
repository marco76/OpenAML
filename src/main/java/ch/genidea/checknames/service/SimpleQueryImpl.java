/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.genidea.checknames.service;

import ch.genidea.checknames.model.ListEntry;
import ch.genidea.checknames.model.PEPEntry;
import ch.genidea.checknames.search.SearchBean;
import ch.genidea.checknames.search.SearchBeanPEP;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.jpa.impl.FullTextQueryImpl;
import org.hibernate.search.reader.ReaderProvider;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author marcomolteni
 */
@Transactional
public class SimpleQueryImpl implements SimpleQuery {
 @PersistenceContext
 EntityManager em;
 private final int MAX_RESULTS = 1000;

    public void setEntityManager (EntityManager em){
        this.em = em;
    }

    
    public org.apache.lucene.search.Query multiFieldQuery(String criteria){
        String searchQuery = criteria;
        String fields[] = {"familyName", "allNames"};

        Map <String, Float> boostPerField = new HashMap<String, Float>(2);
        boostPerField.put("familaName", (float)4);
        boostPerField.put("allNames", (float)1);

        QueryParser parser = new MultiFieldQueryParser(fields, new StandardAnalyzer(), boostPerField);
        org.apache.lucene.search.Query luceneQuery = null;
        try {
            luceneQuery = parser.parse(searchQuery);
            
        }
        catch (ParseException e) {
        throw new RuntimeException("Unable to parse query: " + searchQuery, e);
}
        return luceneQuery;
    }

    public SearchBean HibernateSearch(String criteria){
      return HibernateSearch(criteria, 0, MAX_RESULTS);  

    }

    public SearchBean HibernateFuzzySearch (String criteria, float level){
        FullTextEntityManager ftem = Search.getFullTextEntityManager(em);
        FuzzyQuery query = new FuzzyQuery( new Term("lastName", criteria), level);
        org.hibernate.search.jpa.FullTextQuery hibQuery = ftem.createFullTextQuery(query, ListEntry.class);

        // scoring
        hibQuery.setProjection(FullTextQuery.DOCUMENT, FullTextQuery.SCORE, FullTextQuery.DOCUMENT_ID);
        List<Object[]> list = hibQuery.getResultList();
        for (Object[] element : list){
            System.out.println("score => " + element[1]);
            System.out.println(hibQuery.explain((Integer)element[2]));
         }





//        List<OFACEntry> result =  hibQuery.getResultList();
//        return result;
  return null;
    }


     public String HibernateFuzzyHighlightedSearch (String criteria, float level) throws Exception{

         Analyzer analyzer = new StandardAnalyzer();
         IndexReader reader = null;
         ReaderProvider readerProvider;
         QueryParser parser = new QueryParser("lastName", analyzer);
         FuzzyQuery fquery = new FuzzyQuery( new Term("lastName", criteria), level);
         StringBuilder sb = new StringBuilder();
         Query query = parser.parse("laden");

    // Query query = parser.parse("lastName: laden~0.6");
           System.out.println( query.toString());
         query = query.rewrite(reader);
         


       /* 
        FullTextEntityManager ftem = Search.getFullTextEntityManager(em);
        org.hibernate.search.jpa.FullTextQuery hibQuery = ftem.createFullTextQuery(fquery, ListEntry.class);

        List<ListEntry> results = hibQuery.getResultList();

        Highlighter highlighter = new Highlighter(new QueryScorer(fquery));
        highlighter.setTextFragmenter(new SimpleFragmenter(20));

        int maxNumFragmentsRequired = 2;

        for (ListEntry entry : results){
            String text = entry.getFamilyName();
            TokenStream tokenStream = analyzer.tokenStream(text, new StringReader(text));
            String result= "<tr><td>"+entry.getUid()+"</td><td>"+entry.getFirstName()+"</td><td>"+ highlighter.getBestFragments( tokenStream, text, maxNumFragmentsRequired, "...") +"</td></tr>";
            sb.append(result);
           // System.out.println(result);
        }
        */
          return null;
        }


    /*
    public List<OFACEntry> HibernateSearch (List<String> criteria){
      PerformanceTest pt = new PerformanceTest();
     pt.startTime();
        org.apache.lucene.search.Query luceneQuery = multiFieldQuery(criteria);
        FullTextEntityManager ftem = Search.getFullTextEntityManager(em);
        javax.persistence.Query query = ftem.createFullTextQuery(luceneQuery, OFACEntry.class);
       // query.setFirstResult(20).setMaxResults(20);
        List<OFACEntry> results = query.getResultList();
     pt.stopTime();
        for (OFACEntry entry : (List<OFACEntry>) results){
    //        System.out.println(entry.getFirstName() + " - " + entry.getLastName());

    }
     return results;

    }
*/

    public List<ListEntry> HibernatePhraseQuery (String criteria, float level){
        FullTextEntityManager ftem = Search.getFullTextEntityManager(em);
        FuzzyQuery query = new FuzzyQuery( new Term("lastName", criteria), level);

        PhraseQuery pq = new PhraseQuery();

        StringTokenizer st = new StringTokenizer(criteria, " ");
        while (st.hasMoreElements()){
            pq.add(new Term ("lastName", st.nextToken().toLowerCase()));
        }
        pq.setSlop(10);
        System.out.println(pq.toString());

        org.hibernate.search.jpa.FullTextQuery hibQuery = ftem.createFullTextQuery(query, ListEntry.class);


        // scoring
        hibQuery.setProjection(FullTextQuery.DOCUMENT, FullTextQuery.SCORE, FullTextQuery.DOCUMENT_ID);
        List<Object[]> list = hibQuery.getResultList();
        for (Object[] element : list){
            System.out.println("score => " + element[1]);
            System.out.println(hibQuery.explain((Integer)element[2]));



    }
         return null;
}


	public SearchBean HibernateSearch(String criteria, int firstResult,
			int maxResult) {
			org.apache.lucene.search.Query luceneQuery = multiFieldQuery(criteria);
	        FullTextEntityManager ftem = Search.getFullTextEntityManager(em);
	        javax.persistence.Query query = ftem.createFullTextQuery(luceneQuery, ListEntry.class);

	        query.setFirstResult(firstResult);
	        //TODO: eliminate HARD CODE
		     
	        query.setMaxResults(100);
	       
	       
	        SearchBean sb = new SearchBean();
	       sb.setListResults(query.getResultList());
	       sb.setListResults(sb.getListResults().subList(0,  Math.min(maxResult, sb.getListResults().size())));
	        sb.setRecordsTotal(((FullTextQueryImpl)query).getResultSize());
	        
	        return sb;
	}
	
	public SearchBeanPEP HibernateSearchPEP(String criteria, int firstResult,
			int maxResult) {
			org.apache.lucene.search.Query luceneQuery = multiFieldQuery(criteria);
	        FullTextEntityManager ftem = Search.getFullTextEntityManager(em);
	        javax.persistence.Query query = ftem.createFullTextQuery(luceneQuery, PEPEntry.class);

	        query.setFirstResult(firstResult);
	        //TODO: eliminate HARD CODE
	        query.setMaxResults(100);
	       
	        SearchBeanPEP sb = new SearchBeanPEP();
	        sb.setListResults(query.getResultList());
		    
	        sb.setListResults(sb.getListResults().subList(0,  Math.min(maxResult, sb.getListResults().size())));
		         sb.setRecordsTotal(((FullTextQueryImpl)query).getResultSize());
	        return sb;
	}
   }

    


