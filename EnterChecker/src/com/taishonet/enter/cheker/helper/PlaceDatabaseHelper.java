package com.taishonet.enter.cheker.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PlaceDatabaseHelper extends SQLiteOpenHelper {

	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "places.db";
	private static final String[] FIRST_SQLS = { "create table t_places(_id integer primary key autoincrement,"
			+ "place_name text not null,"
			+ "latitude text not null,"
			+ "longitude text not null," + "address text not null" };

	public PlaceDatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		for (String val : FIRST_SQLS) {
			db.execSQL(val);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
