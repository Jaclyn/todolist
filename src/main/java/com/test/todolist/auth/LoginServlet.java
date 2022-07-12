package com.test.todolist.auth;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeServlet;
import com.google.api.client.http.GenericUrl;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns ="/login", loadOnStartup = 1)
public class LoginServlet extends AbstractAuthorizationCodeServlet {

	@Override
	protected String getUserId(HttpServletRequest request) {
		return request.getSession().getId();
	}

	 @Override 
	 protected AuthorizationCodeFlow initializeFlow() throws IOException
	 { 
		 return OAuthUtils.newFlow(); 
	 }
	

	@Override
	protected String getRedirectUri(HttpServletRequest request) {
		GenericUrl url = new GenericUrl(request.getRequestURL().toString());
		url.setRawPath("/login-callback");
		return url.build();
	}
	

//	  @Override
//	  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//	      throws ServletException, IOException {
//	    System.out.println("LoginServlet doGet() method is invoked.");
//	    doAction(req, resp);
//	  }
//	  @Override
//	  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
//	      throws ServletException, IOException {
//	    System.out.println("LoginServlet doPost() method is invoked.");
//	    doAction(req, resp);
//	  }
//	  
//	  private void doAction(HttpServletRequest req, HttpServletResponse resp)
//		      throws ServletException, IOException {
//		    String name = req.getParameter("name");
//		    resp.setContentType("text/plain");
//		    resp.getWriter().write("Hello " + name + "!");
//		  }

}
