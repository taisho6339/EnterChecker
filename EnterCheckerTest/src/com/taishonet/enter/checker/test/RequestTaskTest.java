package com.taishonet.enter.checker.test;

import org.json.JSONObject;

import android.content.Context;
import android.test.AndroidTestCase;

import com.taishonet.enter.checker.interfaces.RequestCallBack;
import com.taishonet.enter.checker.model.RequestTask;
import com.taishonet.enter.checker.model.RunTask;


public class RequestTaskTest extends AndroidTestCase {

	Context mContext;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mContext = getContext();
	}

	
	//ダイアログのみ無効化
	public void test任意の非同期処理の実行に成功する() {
		RunTask task = new RunTask() {
			@Override
			public JSONObject run() {
				return null;
			}
		};
		RequestCallBack callback = new RequestCallBack() {

			@Override
			public void doCallBack(JSONObject data) {
			}
		};
		RequestTask request = new RequestTask(mContext, task, callback,RequestTask.TASK_MODE_NETWORK);
		request.execute();
	}
}
