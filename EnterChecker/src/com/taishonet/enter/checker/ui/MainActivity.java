package com.taishonet.enter.checker.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.taishonet.enter.checker.R;

public class MainActivity extends Activity implements OnClickListener {

	private Button mRegisterButton, mViewButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mRegisterButton = (Button) findViewById(R.id.main_menu_register_button);
		mViewButton = (Button) findViewById(R.id.main_menu_view_button);
		mRegisterButton.setOnClickListener(this);
		mViewButton.setOnClickListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.main_menu_register_button:
			intent.setClass(MainActivity.this, SearchPlaceActivity.class);
			startActivity(intent);
			break;

		case R.id.main_menu_view_button:
			intent.setClass(MainActivity.this, PlaceListActivity.class);
			break;
		}
		startActivity(intent);
	}
}
