package com.ShowerThoughts;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends ActionBarActivity {

    public static final String TAG = "ST_Main";

    private ListView mContactList;
    private String[] seeds;
    private AsyncHttpClient client;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		String username = bundle.getString("containsUsername");
			
		
		url = "http://ws.audioscrobbler.com/2.0/?method=user.gettoptracks&user="+username+"&api_key=68f82cd7b37e7b23800c2025066531c9&format=json";
        //mContactList = (ListView) findViewById(R.id.contact_list);

        client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {
            ContactList adapter;

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // called when response HTTP status is "200 OK"
                JSONObject topTracks = null;
                try {
                    topTracks = response.getJSONObject("toptracks");
                    JSONArray tracks = response.getJSONArray("track");
                    
                    

                    

                   //  = new String[results.length()]; // Basically IDs
                    String[] songNames = new String[tracks.length()];
                    String[] artistNames = new String[tracks.length()];

                    for(int i = 0; i < tracks.length(); i++) {
                        songNames[i]  = tracks.getJSONObject(i).getString("name");
                        artistNames[i] = tracks.getJSONObject(i).getJSONObject("artist").getString("name");
                    }

                    adapter = new ContactList(MainActivity.this, songNames, artistNames);
                    mContactList.setAdapter(adapter);
                    mContactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int p, long id) {
                            final String seed = seeds[p];

                            // Load Single Contact Activity
                        }
                    });

                } catch (JSONException e) {
                    Log.e(TAG, "Failed to parse JSON. " + e.toString());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.e(TAG, "Failed to get contacts.");
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
                Log.e(TAG, "");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
	
	
	