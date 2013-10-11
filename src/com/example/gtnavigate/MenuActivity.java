package com.example.gtnavigate;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends Activity {
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(this, MainActivity.class);
		this.startActivity(intent);
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		Button about = (Button) findViewById(R.id.mbutton2);
		about.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent b = new Intent(getApplicationContext(), AboutActivity.class);
				startActivity(b);
				finish();
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

}
