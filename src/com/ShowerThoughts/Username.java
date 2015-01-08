package com.ShowerThoughts;



import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;


public class Username extends ActionBarActivity implements View.OnClickListener {
	EditText eUsername;
	Button login;
	String sUsername;
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);
        
        eUsername = (EditText) findViewById (R.id.username);
        sUsername = eUsername.getText().toString();
        login = (Button)findViewById(R.id.login);
	    login.setOnClickListener(this); 
    }
	private void loginClick() {
		Intent intent = new Intent(this, MainActivity.class );
		intent.putExtra("containsUsername", sUsername); //first argument is the name of the string being passed 
		startActivity(intent);
	}
	
	public void onClick (View v) {
		switch (v.getId()) {
			case R.id.login:
				loginClick();
				break;
		}
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.username, menu);
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
