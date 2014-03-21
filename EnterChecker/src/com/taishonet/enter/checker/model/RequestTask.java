package com.taishonet.enter.checker.model;

import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;

import com.taishonet.enter.checker.R;
import com.taishonet.enter.checker.interfaces.CallBack;
import com.taishonet.enter.checker.interfaces.RequestCallBack;
import com.taishonet.enter.checker.interfaces.UICallBack;

public class RequestTask extends AsyncTask<Void, Void, JSONObject> implements
		OnCancelListener {

	private ProgressDialog mDialog;
	private Context mContext;
	private RunTask mTask;
	private CallBack mCallBack;
	private int mTaskMode;

	public static final int TASK_MODE_UI = 0;
	public static final int TASK_MODE_NETWORK = 1;
	
	public RequestTask(Context context, RunTask task, CallBack callback , int mode) {
		mContext = context;
		mTask = task;
		mCallBack = callback;
		mTaskMode = mode;
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
		mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		mDialog.setCancelable(true);
		mDialog.setOnCancelListener(this);
		mDialog.show();
	}

	@Override
	public JSONObject doInBackground(Void... params) {
		JSONObject data = mTask.run();
		return data;
	}

	@Override
	public void onPostExecute(JSONObject result) {
		if (result == null || mCallBack == null)
			return;
		switch(mTaskMode){
		case TASK_MODE_UI:
			((UICallBack)mCallBack).doCallBack();
			break;
		case TASK_MODE_NETWORK:
			((RequestCallBack)mCallBack).doCallBack(result);
			break;
		}
	
	}

}
