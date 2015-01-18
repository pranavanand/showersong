package com.ShowerThoughts;

import android.app.Application;

public class TheUrlClass extends Application {
	private String url;
	
	public String getUrl () {
		return url;
	}
	
	public void setUrl (String enterUrl) {
		this.url= enterUrl;
	}
}
