package com.taishonet.enter.checker.utilities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

import com.taishonet.enter.checker.interfaces.UICallBack;

public class DialogUtil {

	public static void createSingleButtonDialog(Context context, int titleId,
			int messageId, int buttonTextId, final UICallBack callback) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(titleId).setMessage(messageId)
				.setPositiveButton(buttonTextId, new OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						callback.doCallBack();
					}
				}).create().show();
	}
}
