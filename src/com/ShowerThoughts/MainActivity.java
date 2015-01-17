package com.ShowerThoughts;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
//import android.webkit.WebView;

public class MainActivity extends ActionBarActivity {
	
	TextView firstSong;
	TextView secondSong;
	TextView thirdSong;
	TextView fourthSong;
	TextView fifthSong;
	HttpClient client;
	
	
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
		
		
		
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		String username = bundle.getString("containsUsername");
		//WebView listOfSongs = (WebView) findViewById (R.id.webview);
			
		
		String url = "http://ws.audioscrobbler.com/2.0/?method=user.gettoptracks&user="+username+"&api_key=68f82cd7b37e7b23800c2025066531c9&format=json";
		//listOfSongs.loadUrl(url); 
		HttpGet get = new HttpGet(url.toString());
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
