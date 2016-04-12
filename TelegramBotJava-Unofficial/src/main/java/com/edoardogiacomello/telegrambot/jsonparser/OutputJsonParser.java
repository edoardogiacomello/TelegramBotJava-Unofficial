package com.edoardogiacomello.telegrambot.jsonparser;

import com.edoardogiacomello.telegrambot.methods.TelegramMethods;
import com.edoardogiacomello.telegrambot.types.TelegramData;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OutputJsonParser {
	private static final String requestBaseUrl = "https://api.telegram.org/bot";
	private static String requestUrl;
	private HttpClient httpClient;
	
	public OutputJsonParser(String token) {
		// Building url for making requests
		requestUrl = requestBaseUrl + token + "/";
		//Initializing http client
		httpClient= HttpClients.createDefault();
		
	}
	
	private URL buildRequestURL(TelegramMethods method) throws MalformedURLException{
		return new URL(requestUrl+method.getValue());
	}
	
	/**
	 * Returns a list containing all received responses starting from a telegram request.
	 * @param method The {@link TelegramMethods} identifying the request
	 * @param paramsData a list of {@link NameValuePair} containing the parameters
	 * @return List containing response objects received from server, or null if the request fails
	 */
	public List<TelegramData> request(TelegramMethods method,  List<NameValuePair> paramsData){
		List<NameValuePair> params = paramsData;
		if (params == null) params = new ArrayList<NameValuePair>(); 
		List<TelegramData> responseData= null;
		try {
			//Building request
			HttpPost httpPost = new HttpPost(buildRequestURL(method).toURI());
			httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			//executing request and parsing response
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			
			if (entity != null) {
			    InputStream instream = entity.getContent();
			    try {
			    	  responseData = InputJsonParser.parseResponse(instream);
			    	  return responseData;
			    } finally {
			        instream.close();
			    }
			}
		} catch (IOException | URISyntaxException e) {
			Logger.getGlobal().log(Level.SEVERE, e.toString());
		}
		return responseData;
	}



	
	
	
}
