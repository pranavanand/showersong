package com.ShowerThoughts;

import java.io.BufferedReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class GetMethod {

	
	public String getInternetData() throws Exception{
		BufferedReader in = null;
		String data = null;
		try {
			HttpClient client = new DefaultHttpClient();
			URI website = new URI("http://ws.audioscrobbler.com/2.0/?method=user.gettoptracks&user=thisisdyan&api_key=68f82cd7b37e7b23800c2025066531c9&format=json");
			HttpGet request = new HttpGet();
			request.setURI(website);
			HttpResponse response = client.execute(request);
			
		}
		
	}
}
