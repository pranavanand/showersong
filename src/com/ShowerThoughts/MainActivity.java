package com.ShowerThoughts;

import java.util.Locale;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class MainActivity extends ActionBarActivity implements OnInitListener, View.OnClickListener {
	
    public static final String TAG = "ST_Main";

    private ListView listview;
    private AsyncHttpClient client;
    String url;
    private TextToSpeech tts;
    Button readSongs;
    String readThis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        tts = new TextToSpeech(this, this);
        Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		String username = bundle.getString("containsUsername");
		
		readSongs = (Button)findViewById(R.id.editText1);
		listview = (ListView) findViewById(R.id.listView1);
		readSongs.setOnClickListener(this);
		
		url = "http://ws.audioscrobbler.com/2.0/?method=user.gettoptracks&user="+username+"&api_key=68f82cd7b37e7b23800c2025066531c9&format=json";

        client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {
            //ContactList adapter;

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
                    JSONArray tracks = topTracks.getJSONArray("track");
                    String[] songNames = new String[tracks.length()];
                    String[] artistNames = new String[tracks.length()];
                    String[] songAndArtists = new String[tracks.length()];

                    for(int i = 0; i < tracks.length(); i++) {
                        songNames[i]  = tracks.getJSONObject(i).getString("name");
                        artistNames[i] = tracks.getJSONObject(i).getJSONObject("artist").getString("name");
                    }
                    
                    for(int i = 0; i < tracks.length(); i++) {
                    	songAndArtists[i] = songNames[i] + " - " + artistNames[i];
                    }
                    
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, songAndArtists);
                    listview.setAdapter(adapter);
                    for (int i = 0; i < tracks.length(); i++) {
                    	readThis += songAndArtists[i] + ", ";
                    }
                    
                } catch (JSONException e) {
                    Log.e(TAG, "Failed to parse JSON. " + e.toString());
                }
            }
          //adding comments
            
            
            

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
    @SuppressWarnings("deprecation")
	private void readSongs() {
		// TODO Auto-generated method stub
    	tts.speak(readThis, TextToSpeech.QUEUE_FLUSH, null);
	}
	@Override
    public void onInit(int code) {
          if (code==TextToSpeech.SUCCESS) {
        	  tts.setLanguage(Locale.getDefault());
          } else {
        	  tts = null;
        	  Toast.makeText(this, "Failed to initialize TTS engine.",
        	  Toast.LENGTH_SHORT).show();
          }
    }
    
    
    

    
    @Override
    protected void onDestroy() {
          if (tts!=null) {
        	  tts.stop();
        	  tts.shutdown();
          }
          super.onDestroy();
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.login:
			readSongs();
			break;
	}
	}
}
	
	
	