
package ch.genidea.checknames.importer


import java.util.List;
import ch.genidea.checknames.model.ListEntry;
import ch.genidea.checknames.model.SourceList;

/**
 * @author marcomolteni
 *
 */
public class OFACParserImpl implements ch.genidea.checknames.importer.OFACParser{
	String filename
	List <ListEntry> result
	SourceList sourceList
	
	
	public List<ListEntry> parse(){
		def pers=new XmlSlurper().parse(new File(filename))

		
		List <ListEntry> result = new ArrayList<ListEntry>()
		def allSdnEntry = pers.sdnEntry
		
		allSdnEntry.each{
		       // println it.uid.text() + ":" + it.lastName.text()
		          
		         result.add(addEntry(it, it)) 
		         def parent = it
		         it.akaList.aka.each{
		         result.add(addEntry(it, parent))        
		         }	      
		}

		return result
	}
	
	ListEntry addEntry(def it, def parent)
	{
			ListEntry entity = new ListEntry()
	         entity.uid = (it.uid.text() as Integer)
	         entity.sourceList=sourceList
	         entity.familyName = it.lastName.text()
	         entity.firstName = it.firstName.text()
	         entity.type = it.type.text()
	         entity.address = parent.addressList.address[0].address.text()
	         entity.addressCity =  parent.addressList.address[0].city.text()
	         entity.addressCountry = parent.addressList.address[0].country.text()
	         entity.parentIdForAlias= (parent.uid.text() as Integer)
	         return entity 
	}
	
	void setFilename(String filename)
	{
		this.filename = filename
	}
	void setSourceList(SourceList sourceList)
	{
		this.sourceList = sourceList
	}
	
}
