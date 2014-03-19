package com.taishonet.enter.cheker.model;

import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;

import com.taishonet.enter.cheker.R;

public class RequestTask extends AsyncTask<Void, Void, JSONObject> implements
		OnCancelListener {

	private ProgressDialog mDialog;
	private Context mContext;
	private RunTask mTask;
	private RequestCallBack mCallBack;

	public RequestTask(Context context, RunTask task, RequestCallBack callback) {
		mContext = context;
		mTask = task;
		mCallBack = callback;
	}

	@Override
	public void onCancel(DialogInterface dialog) {
		this.cancel(true);
	}

	@Override
	protected void onPreExecute() {
		mDialog = new ProgressDialog(mContext);
		mDialog.setTitle(R.string.network_task_connecting);
		mDialog.setMessage(mContext.getString(R.string.network_task_message));
		mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		mDialog.setCancelable(true);
		mDialog.setOnCancelListener(this);
		mDialog.show();
	}

	@Override
	protected JSONObject doInBackground(Void... params) {
		JSONObject data = mTask.run();
		return data;
	}

	@Override
	protected void onPostExecute(JSONObject result) {
		if (result == null || mCallBack == null)
			return;
		mCallBack.doCallBack(result);
	}

}
