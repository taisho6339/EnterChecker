package com.taishonet.enter.cheker.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.taishonet.enter.cheker.R;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Intent intent = new Intent(this,SearchPlaceActivity.class);
		startActivity(intent);
	}
}
