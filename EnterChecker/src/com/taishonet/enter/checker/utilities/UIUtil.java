package com.taishonet.enter.checker.utilities;

import android.content.Context;
import android.widget.Toast;

public class UIUtil {

	public static void pushToast(Context context,int messageId) {
		Toast.makeText(context, messageId,
				Toast.LENGTH_SHORT).show();
	}
	
}
