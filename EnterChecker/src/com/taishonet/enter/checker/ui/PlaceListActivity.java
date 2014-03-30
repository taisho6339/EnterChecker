package com.taishonet.enter.checker.ui;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.taishonet.enter.checker.R;
import com.taishonet.enter.checker.config.Config;
import com.taishonet.enter.checker.helper.PlaceDatabaseHelper;
import com.taishonet.enter.checker.interfaces.CallBack;
import com.taishonet.enter.checker.interfaces.RunTask;
import com.taishonet.enter.checker.model.RequestTask;

public class PlaceListActivity extends Activity implements CallBack {

	private ListView mPlaceList;
	private SimpleCursorAdapter mAdapter;
	private final String[] DATA_KEYWORDS = { Config.DB_KEY_ID,
			Config.DB_KEY_PLACE_NAME, Config.DB_KEY_PLACE_ADDRESS,
			Config.DB_KEY_PLACE_RADIUS };

	private final int[] TARGET_VIEW_IDS = { R.id.place_list_place_name,
			R.id.place_list_place_address, R.id.place_list_place_radius };

	@Override
	public void doCallBack(Object resultData) {
		Cursor c = (Cursor) resultData;
		final String[] keywords = { DATA_KEYWORDS[1], DATA_KEYWORDS[2],
				DATA_KEYWORDS[3], };
		mAdapter = new SimpleCursorAdapter(this,
				R.layout.place_view_list_layout, c, keywords, TARGET_VIEW_IDS,
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		mPlaceList.setAdapter(mAdapter);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.place_view_layout);
		mPlaceList = (ListView) findViewById(R.id.place_view_list);
		postTask();
	}

	private void postTask() {
		final RunTask runTask = new RunTask() {
			@Override
			public Object run() {
				PlaceDatabaseHelper helper = new PlaceDatabaseHelper(
						PlaceListActivity.this);
				SQLiteDatabase db = helper.getReadableDatabase();
				return db.query(Config.DB_TABLE_NAME_PLACES, DATA_KEYWORDS,
						null, null, null, null, null);
			}
		};
		RequestTask task = new RequestTask(this, runTask, this);
		task.execute();
	}
}
