package ch.genidea.checknames.ws;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.xpath.DefaultXPath;
import org.springframework.ws.server.endpoint.AbstractDom4jPayloadEndpoint;

import ch.genidea.checknames.model.ListEntry;
import ch.genidea.checknames.service.SearchResultService;

public class OfacScanEndpoint extends AbstractDom4jPayloadEndpoint{

	
	private SearchResultService searchResultService;
	private static final String namespaceUri = 
		"http://ofacscan.com/services/schemas"; 
		private XPath namePath; 
		
		public OfacScanEndpoint() { 
		
		// Create the XPath objects, including the namespace 
		
			
		Map<String, String> namespaceUris = new HashMap<String, String>(); 
		namespaceUris.put("ofacscan", namespaceUri); 
		namePath = new DefaultXPath("/ofacscan:OfacScanRequest/ofacscan:name");
		namePath.setNamespaceURIs(namespaceUris);
		} 
		
		protected Element invokeInternal(Element requestElement, 
		Document responseDocument) throws Exception { 
		// Extract the service parameters from the request message 
			String name = namePath.valueOf(requestElement); 
		// Invoke the back-end service to handle the request 
		List<ListEntry>	list = searchResultService.getResults(name);
		// Build the response message from the result of back-end service 
		Element responseElement = responseDocument.addElement( 
		"OfacScanResponse", namespaceUri); 
		for (ListEntry entry : list) { 
		  Element alertElement = responseElement.addElement( 
		    "AlertInfo"); 
		  responseElement.addElement("uid").setText(getValidValue(entry.getUid().toString()));
		  responseElement.addElement("firstName").setText(getValidValue(entry.getFirstName())); 
		  responseElement.addElement("familyName").setText(getValidValue(entry.getFamilyName()));
		  	
		} 
		return responseElement; 
		}

		public void setSearchResultService(SearchResultService searchResultService) {
			this.searchResultService = searchResultService;
		}
		
		private String getValidValue(String value)
		{
			String result = "";
			
			if (value != null)
				result = value;
			return result;
		}

		
	

}
