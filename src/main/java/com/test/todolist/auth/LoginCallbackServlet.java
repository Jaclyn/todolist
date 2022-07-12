package com.test.todolist.auth;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeCallbackServlet;
import com.google.api.client.http.GenericUrl;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login-callback")
public class LoginCallbackServlet extends AbstractAuthorizationCodeCallbackServlet {

  @Override
  protected AuthorizationCodeFlow initializeFlow() throws IOException {
    return OAuthUtils.newFlow();
  }

  @Override
  protected String getRedirectUri(HttpServletRequest request) {
    GenericUrl url = new GenericUrl(request.getRequestURL().toString());
    url.setRawPath("/login-callback");
    return url.build();
  }

  @Override
  protected String getUserId(HttpServletRequest request) {
    return request.getSession().getId();
  }

  @Override
  protected void onSuccess(HttpServletRequest request, HttpServletResponse response, Credential credential)
      throws IOException {
	  StringBuilder htmlBuilder = new StringBuilder().append("<h1>Login successfully</h1>");
	  String sessionId = request.getSession().getId();
	  htmlBuilder.append("<h2>Welcome! "+OAuthUtils.getUserInfo(sessionId).getGivenName()+"</h2>");
	  htmlBuilder.append("Your access token to be used: " + credential.getAccessToken());
	  response.getWriter().print(htmlBuilder.toString());
    //response.sendRedirect("/profile");
  }

  @Override
  protected void onError(
      HttpServletRequest request, HttpServletResponse response, AuthorizationCodeResponseUrl errorResponse)
      throws IOException {
    response.getWriter().print("Login cancelled.");
  }
}