package com.ShowerThoughts;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class GetMethod extends Activity {
	Intent intent = getIntent();
	Bundle bundle = intent.getExtras();
	String username = bundle.getString("containsUsername");
	//WebView listOfSongs = (WebView) findViewById (R.id.webview);
		
	
	//String url = "http://ws.audioscrobbler.com/2.0/?method=user.gettoptracks&user="+username+"&api_key=68f82cd7b37e7b23800c2025066531c9&format=json";
	//listOfSongs.loadUrl(url); 
	
	public String getInternetData() throws Exception{
		BufferedReader in = null;
		String data = null;
		try {
			HttpClient client = new DefaultHttpClient();
			URI website = new URI("http://ws.audioscrobbler.com/2.0/?method=user.gettoptracks&user=" + username + "&api_key=68f82cd7b37e7b23800c2025066531c9&format=json");
			HttpGet request = new HttpGet();
			request.setURI(website);
			HttpResponse response = client.execute(request);
			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer sb = new StringBuffer("");
			String l = "";
			String nl = System.getProperty("line.separator");
			while ((l = in.readLine()) != null){
				sb.append(l+nl);
			}
			in.close();
			data = sb.toString();
			return data;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	
}
