package com.taishonet.enter.checker.helper;

import android.content.Context;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;

public class PlaceDataAdapter extends SimpleCursorAdapter {

	public PlaceDataAdapter(Context context, int layout, Cursor c,
			String[] from, int[] to, int flags) {
		super(context, layout, c, from, to, flags);
	}
}
