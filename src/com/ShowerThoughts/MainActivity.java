package com.ShowerThoughts;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
//import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	
	TextView firstSong;
	TextView secondSong;
	TextView thirdSong;
	TextView fourthSong;
	TextView fifthSong;
	HttpClient client;
	JSONObject json;
	String url;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		firstSong = (TextView) findViewById(R.id.textView1);
		secondSong = (TextView) findViewById(R.id.textView2);
		thirdSong = (TextView) findViewById(R.id.textView3);
		fourthSong = (TextView) findViewById(R.id.textView4);
		fifthSong = (TextView) findViewById(R.id.textView5);
		
		client = new DefaultHttpClient();
		new Read().execute("name");
		
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		String username = bundle.getString("containsUsername");
		//WebView listOfSongs = (WebView) findViewById (R.id.webview);
			
		
		url = "http://ws.audioscrobbler.com/2.0/?method=user.gettoptracks&user="+username+"&api_key=68f82cd7b37e7b23800c2025066531c9&format=json";
		//listOfSongs.loadUrl(url); http://ws.audioscrobbler.com/2.0/?method=user.gettoptracks&user="+username+"&api_key=68f82cd7b37e7b23800c2025066531c9&format=js
		
	}
	
	
	
	public JSONObject songCalled(int number) throws ClientProtocolException, IOException, JSONException {
		StringBuilder URL = new StringBuilder(url);
		HttpGet get = new HttpGet(URL.toString());
		HttpResponse r = client.execute(get);
		int status = r.getStatusLine().getStatusCode();
		if (status == 200) {
			HttpEntity e = r.getEntity();
			String data = EntityUtils.toString(e);
			JSONArray songList = new JSONArray(data);
			JSONObject latest = songList.getJSONObject(0);  
			return latest; 
		} else {
			return null;
		}
		
	}
	
	public class Read extends AsyncTask<String, Integer, String>{
		
		@Override
		protected String doInBackground(String... params) {
		try {
				json = songCalled(5);
				return json.getString(params[0]);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			
			return null;
		}
	}
	
	protected void onPostExecute(String result) {
		//firstSong.setText
	}
	
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
