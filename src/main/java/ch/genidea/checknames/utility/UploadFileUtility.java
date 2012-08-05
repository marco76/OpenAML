/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package ch.genidea.checknames.utility;

//~--- JDK imports ------------------------------------------------------------

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;



public class UploadFileUtility {
    private String targetFileName;
    private String urlAddress;
    private List<String> urlAdresses;
    
    public List<String> getUrlAdresses() {
		return urlAdresses;
	}

	public void setUrlAdresses(List<String> urlAdresses) {
		this.urlAdresses = urlAdresses;
	}

	public List<String> getFileNames() {
		return fileNames;
	}

	public void setFileNames(List<String> fileNames) {
		this.fileNames = fileNames;
	}

	public String getTargetFileName() {
		return targetFileName;
	}

	public String getUrlAddress() {
		return urlAddress;
	}

	private List<String> fileNames;

	public String uploadFileList(){
		int numberOfElements;
		numberOfElements = fileNames.size();
		
		for(int i=0; i<numberOfElements; i++)
			uploadFile(fileNames.get(i), urlAdresses.get(i));
		
		
		return null;
	}
	
    public String uploadFile(String targetFileName, String urlAddress){
    	 try {

             // InetAddress proxyAddress = InetAddress.getByName("Please enter your proxy address");
             // InetSocketAddress inetSocketAddress = new InetSocketAddress(proxyAddress, 80);
             // Proxy proxy = new Proxy(Proxy.Type.HTTP, inetSocketAddress);
             URL  url   = new URL(urlAddress);
             URLConnection urlConnection = url.openConnection();

             urlConnection.connect();

             InputStream  input  = url.openStream();
             OutputStream output = new FileOutputStream(targetFileName);
             byte[]       buf    = new byte[1024];
             int          len;

             while ((len = input.read(buf)) > 0) {
                 output.write(buf, 0, len);
             }

             input.close();
             output.close();
         } catch (Exception e) {
             e.printStackTrace();
         }
         return null;
    	
    	
    	
    }


    public String uploadFile() {
       uploadFile(targetFileName, urlAddress);
        return null;
    }

    /**
     * @param targetFileName the targetFileName to set
     */
    public void setTargetFileName(String targetFileName) {
        this.targetFileName = targetFileName;
    }

    /**
     * @param urlAddress the urlAddress to set
     */
    public void setUrlAddress(String urlAddress) {
        this.urlAddress = urlAddress;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
