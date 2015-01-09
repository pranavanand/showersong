package com.ShowerThoughts;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class MainActivity extends ActionBarActivity {
	boolean canLogIn = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent intent = getIntent();
		String username = intent.getStringExtra("containsUsername");
		if (intent.hasExtra("containsUsername")){ //go to the url with the username in it and the api key
			canLogIn = true;
		}
		WebView listOfSongs = (WebView) findViewById (R.id.webview);
		if (canLogIn) { //load url if it is a valid username entered
			String url = "http://ws.audioscrobbler.com/2.0/?method=.gettoptracks&user=" + username + "&api_key=68f82cd7b37e7b23800c2025066531c9&format=json";
			listOfSongs.loadUrl(url); 
		}
 
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
