package com.test.todolist.auth;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.MemoryDataStoreFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfo;

public class Authenticator {
	
	private final static String oAuthClientId = "918274769158-pfqt89bvdtkhm6dbo7prgm0kjsdpj9v3.apps.googleusercontent.com";
	private final static String oAuthClientSecret = "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";
	private final static List<String> scopes = Arrays.asList(
			  "https://www.googleapis.com/auth/userinfo.profile",
			  "https://www.googleapis.com/auth/userinfo.email");

	public static HttpRequestFactory execute(
			HttpTransport transport, JsonFactory jsonFactory, String accessToken, GenericUrl url)
					throws IOException {
		Credential credential =
				new Credential(BearerToken.authorizationHeaderAccessMethod()).setAccessToken(accessToken);
		HttpRequestFactory requestFactory = transport.createRequestFactory(credential);
		return requestFactory;
	}
	
	public static HttpResponse executeGet(
			HttpTransport transport, JsonFactory jsonFactory, String accessToken, GenericUrl url)
					throws IOException {
		Credential credential =
				new Credential(BearerToken.authorizationHeaderAccessMethod()).setAccessToken(accessToken);
		HttpRequestFactory requestFactory = transport.createRequestFactory(credential);
		return requestFactory.buildGetRequest(url).execute();
	}

	public static HttpResponse executePost(
			HttpTransport transport, JsonFactory jsonFactory, String accessToken, GenericUrl url, HttpContent content)
					throws IOException {
		Credential credential =
				new Credential(BearerToken.authorizationHeaderAccessMethod()).setAccessToken(accessToken);
		HttpRequestFactory requestFactory = transport.createRequestFactory(credential);
		return requestFactory.buildPostRequest(url, content).execute();
	}
	
	
	//Call to https://www.googleapis.com/oauth2/v4/token
	//input code=4/0AdQt8qhHoGaSvvk17-mmEm8k96bRAOhgK1Xa0VUJchBMKwUcDFOIIZNrcWg9vRyWvSsdgA&client_id=918274769158-pfqt89bvdtkhm6dbo7prgm0kjsdpj9v3.apps.googleusercontent.com&client_secret=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&grant_type=authorization_code&redirect_uri=http://localhost
	//ref: https://plswiderski.medium.com/google-api-authentication-with-oauth-2-on-the-example-of-gmail-a103c897fd98
	public static GoogleAuthorizationCodeFlow newFlow() {
			GoogleAuthorizationCodeFlow flow = null;
			try {
				flow = new GoogleAuthorizationCodeFlow.Builder(
					    // Sends requests to the OAuth server
					    new NetHttpTransport(),
					    // Converts between JSON and Java
					    JacksonFactory.getDefaultInstance(),
					    // Your OAuth client ID
					    oAuthClientId,
					    // Your OAuth client secret
					    oAuthClientSecret,
					    // Tells the user what permissions they're giving you
					    scopes)
					  // Stores the user's credential in memory 
					  .setDataStoreFactory(MemoryDataStoreFactory.getDefaultInstance())
					  .build();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return flow;
		
	}
	
	public static boolean isUserLoggedIn(String sessionId) {
		  try{
		    Credential credential = newFlow().loadCredential(sessionId);
		   return credential != null;
		  } catch(IOException e){
		    // Error getting login status
		    return false;
		  }
		}
	
	public static Userinfo getUserInfo(String sessionId) throws IOException {
		  String appName = System.getenv("APP_NAME");
		  Credential credential = newFlow().loadCredential(sessionId);
		  Oauth2 oauth2Client =
		      new Oauth2.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential)
		          .setApplicationName(appName)
		          .build();

		  Userinfo userInfo = oauth2Client.userinfo().get().execute();
		  return userInfo;
		}

}