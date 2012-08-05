/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.genidea.checknames.utility;

//~--- non-JDK imports --------------------------------------------------------

import ch.genidea.checknames.model.ListEntry;
import ch.genidea.checknames.model.PEPEntry;

import ch.genidea.checknames.model.SourceList;
import ch.genidea.checknames.service.SourceListService;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//~--- JDK imports ------------------------------------------------------------

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * 
 * @author marcomolteni
 */
public class ParseUSASenateImpl {
	private List<PEPEntry> list = new ArrayList<PEPEntry>();
	private String fileName = null;
	private int listNumber;
	private SourceList sl;

	public void setSL (SourceList sl) {
		this.sl = sl;
	}

	public List<PEPEntry> parseUSASenateist() {

		// TODO code application logic here

		try {
			
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(fileName);

			doc.getDocumentElement().normalize();

			PEPEntry pep = null;

			NodeList nodes = doc.getElementsByTagName("member");
			for (int i = 0; i < nodes.getLength(); i++) {
				Element element = (Element) nodes.item(i);
				
				pep = new PEPEntry();
				NodeList title = element.getElementsByTagName("last_name");
				Element line = (Element) title.item(0);
				pep.setFamilyName(getCharacterDataFromElement(line));

				title = element.getElementsByTagName("first_name");
				line = (Element) title.item(0);
				pep.setFirstName(getCharacterDataFromElement(line));
				pep.setAllNames(getCharacterDataFromElement(line));

				title = element.getElementsByTagName("party");
				line = (Element) title.item(0);
				pep.setParty(getCharacterDataFromElement(line));

				title = element.getElementsByTagName("state");
				line = (Element) title.item(0);
				pep.setCounty(getCharacterDataFromElement(line));
				pep.setSourceList(sl);
				list.add(pep);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public static String getCharacterDataFromElement(Element e) {
		   Node child = e.getFirstChild();
		   if (child instanceof CharacterData) {
		     CharacterData cd = (CharacterData) child;
		       return cd.getData();
		     }
		   return "?";
		 }

	public void setListNumber(int listNumber) {
		this.listNumber = listNumber;
	}


}