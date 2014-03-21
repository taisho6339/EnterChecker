package com.taishonet.enter.checker.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;

import com.taishonet.enter.checker.R;
import com.taishonet.enter.checker.interfaces.CallBack;
import com.taishonet.enter.checker.interfaces.RunTask;

public class RequestTask extends AsyncTask<Void, Void, Object> implements
		OnCancelListener {

	private ProgressDialog mDialog;
	private Context mContext;
	private RunTask mTask;
	private CallBack mCallBack;
	
	public RequestTask(Context context, RunTask task, CallBack callback) {
		mContext = context;
		mTask = task;
		mCallBack = callback;
	}

	@Override
	public void onCancel(DialogInterface dialog) {
		this.cancel(true);
	}

	@Override
	public void onPreExecute() {
		mDialog = new ProgressDialog(mContext);
		mDialog.setTitle(R.string.network_task_connecting);
		mDialog.setMessage(mContext.getString(R.string.network_task_message));
		mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mDialog.setCancelable(true);
		mDialog.setOnCancelListener(this);
		mDialog.show();
	}

	@Override
	public Object doInBackground(Void... params) {
		Object data = mTask.run();
		return data;
	}

	@Override
	public void onPostExecute(Object result) {
		mDialog.dismiss();
		if (result == null || mCallBack == null)
			return;
		mCallBack.doCallBack(result);
	}

}
