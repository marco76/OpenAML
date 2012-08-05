package ch.genidea.ofac.web.jsf.managedBean;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LoginMB {
	 public String doLogin() throws IOException, ServletException
	    {
	        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
	 
	        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest())
	                 .getRequestDispatcher("/j_spring_security_check");
	 
	        dispatcher.forward((ServletRequest) context.getRequest(),
	                (ServletResponse) context.getResponse());
	 
	        FacesContext.getCurrentInstance().responseComplete();
	       
	        return null;
	    }

}
