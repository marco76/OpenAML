package ch.genidea.checknames.utility;


import ch.genidea.checknames.model.ListEntry;

import ch.genidea.checknames.model.SourceList;
import ch.genidea.checknames.service.SourceListService;
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
public class ParseOFACImpl {
    private List<ListEntry> entries  = new ArrayList<ListEntry>();
    private String          fileName = null;
    private SourceList sl;
    private SourceListService service;

    public List<ListEntry> getOfacList() {

        // TODO code application logic here
    	 Document               doc;
        try {
             sl = service.findSourceList(1);

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder        docBuilder        = docBuilderFactory.newDocumentBuilder();
            doc = docBuilder.parse(fileName);

            doc.getDocumentElement().normalize();
         
            NodeList listOfEntries = doc.getElementsByTagName("sdnEntry");
            int      totalEntries  = listOfEntries.getLength();

            // System.out.println("Number of entries = " + totalEntries);
            for (int s = 0; s < listOfEntries.getLength(); s++) {
                Node firstEntityNode = listOfEntries.item(s);

                if (firstEntityNode.getNodeType() == Node.ELEMENT_NODE) {
                    ListEntry entry              = new ListEntry();
                    Element   firstEntityElement = (Element) firstEntityNode;
                    NodeList  uidList            = firstEntityElement.getElementsByTagName("uid");
                    Element   uidElement         = (Element) uidList.item(0);
                    NodeList  textUIDList        = uidElement.getChildNodes();

                    entry.setUid(
                        BigInteger.valueOf(Long.parseLong(((Node) textUIDList.item(0)).getNodeValue().trim())));

                    // System.out.println("UID : " + ((Node)textUIDList.item(0)).getNodeValue().trim());
                    NodeList gList    = firstEntityElement.getElementsByTagName("firstName");
                    Element  gElement = (Element) gList.item(0);

                    if (gElement != null) {
                        NodeList gTextList = gElement.getChildNodes();

                        entry.setFirstName(((Node) gTextList.item(0)).getNodeValue().trim());
                    }

                    // lose of information with the conversion, no functional risks the long is used only for compatibility with the db
                    entries.add(entry);

                    String result = this.parseNode(firstEntityElement, "lastName");

                    if (result != null) {
                        entry.setFamilyName(result);
                    }

                    entry.setSourceList(sl);
                    entry.setListName("OFAC");
                    result = this.parseNode(firstEntityElement, "sdnType");

                    if (result != null) {
                        entry.setType(result);
                    }

                  result = this.parseNode(firstEntityElement, "uid");
    }
            }
            
           parseAlias(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }

    
        return entries;
    }

    protected String parseNode(Element firstEntityElement, String tag) {
        String   result   = null;
        NodeList gList    = firstEntityElement.getElementsByTagName(tag);
        Element  gElement = (Element) gList.item(0);

        if (gElement != null) {
            NodeList gTextList = gElement.getChildNodes();

            result = ((Node) gTextList.item(0)).getNodeValue().trim();
        }

        return result;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int numberOfEntries() {
        return entries.size();
    }

    /**
     * @param service the service to set
     */
    public void setService(SourceListService service) {
        this.service = service;
    }
    
    private void parseAlias(Document doc){
    	NodeList listOfEntries = doc.getElementsByTagName("aka");
        int      totalEntries  = listOfEntries.getLength();

        // System.out.println("Number of entries = " + totalEntries);
        for (int s = 0; s < listOfEntries.getLength(); s++) {
            Node firstEntityNode = listOfEntries.item(s);

            if (firstEntityNode.getNodeType() == Node.ELEMENT_NODE) {
                ListEntry entry              = new ListEntry();
                Element   firstEntityElement = (Element) firstEntityNode;
                NodeList  uidList            = firstEntityElement.getElementsByTagName("uid");
                Element   uidElement         = (Element) uidList.item(0);
                NodeList  textUIDList        = uidElement.getChildNodes();

                entry.setUid(
                    BigInteger.valueOf(Long.parseLong(((Node) textUIDList.item(0)).getNodeValue().trim())));

                // System.out.println("UID : " + ((Node)textUIDList.item(0)).getNodeValue().trim());
                NodeList gList    = firstEntityElement.getElementsByTagName("firstName");
                Element  gElement = (Element) gList.item(0);

                if (gElement != null) {
                    NodeList gTextList = gElement.getChildNodes();

                    entry.setFirstName(((Node) gTextList.item(0)).getNodeValue().trim());
                }

                // lose of information with the conversion, no functional risks the long is used only for compatibility with the db
                entries.add(entry);

                String result = this.parseNode(firstEntityElement, "lastName");

                if (result != null) {
                    entry.setFamilyName(result);
                }

                entry.setSourceList(sl);
                entry.setListName("OFAC");
                result = this.parseNode(firstEntityElement, "sdnType");

                if (result != null) {
                    entry.setType(result);
                }

/*
                result = this.parseNode(firstEntityElement, "program");
                if (result != null)
                  entry.setProgramList(result);
*/					
                result = this.parseNode(firstEntityElement, "uid");
        }}
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
