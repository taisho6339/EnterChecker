package com.taishonet.enter.chercheker.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.taishonet.enter.chercheker.R;

public class SearchPlaceActivity extends Activity {

	private Button mSearchButton;
	private Button mAddButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_place_layout);
		OnClickListener listener = new ButtonClickListener();
		mSearchButton = (Button) findViewById(R.id.search_button);
		mAddButton = (Button) findViewById(R.id.search_register_button);
		mSearchButton.setOnClickListener(listener);
		mAddButton.setOnClickListener(listener);
	}

	class ButtonClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			int id = v.getId();
			switch (id) {
			case R.id.search_button:
				break;
			case R.id.search_register_button:
				break;
			default:
				break;
			}
		}
	}

}
