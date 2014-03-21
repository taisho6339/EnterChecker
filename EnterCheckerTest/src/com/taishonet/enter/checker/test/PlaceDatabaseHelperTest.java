package com.taishonet.enter.checker.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.taishonet.enter.checker.config.Config;
import com.taishonet.enter.checker.helper.PlaceDatabaseHelper;

public class PlaceDatabaseHelperTest extends AndroidTestCase {

	private Context mContext;
	private PlaceDatabaseHelper mHelper;
	private SQLiteDatabase mDB;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mContext = new RenamingDelegatingContext(getContext(), "test_");
		mHelper = new PlaceDatabaseHelper(mContext);
		mDB = mHelper.getWritableDatabase();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		mHelper.close();
		mDB.close();
	}

	public void test正しくDBが作成される() {
		try {
			PlaceDatabaseHelper helper = new PlaceDatabaseHelper(mContext);
			helper.getWritableDatabase();
		} catch (SQLException e) {
			fail("DB作成に失敗");
		}
	}

	public void testDBにデータを追加する() {
		ContentValues values = new ContentValues();
		values.put(Config.DB_KEY_PLACE_LATITUDE, "123.4434");
		values.put(Config.DB_KEY_PLACE_LONGITUDE, "132.3231");
		values.put(Config.DB_KEY_PLACE_NAME, "会社");
		values.put(Config.DB_KEY_PLACE_ADDRESS, "東京都");
		mDB.insert(Config.DB_TABLE_NAME_PLACES, null, values);
		
		Cursor cur = mDB.query(Config.DB_TABLE_NAME_PLACES, null, null, null, null, null, null);
		assertEquals(1, cur.getCount());
	}

}
