package com.taishonet.enter.cheker.test;

import org.json.JSONObject;

import android.content.Context;
import android.test.AndroidTestCase;

import com.taishonet.enter.cheker.model.RequestCallBack;
import com.taishonet.enter.cheker.model.RequestTask;
import com.taishonet.enter.cheker.model.RunTask;

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
		RequestTask request = new RequestTask(mContext, task, callback);
		request.execute();
	}
}
