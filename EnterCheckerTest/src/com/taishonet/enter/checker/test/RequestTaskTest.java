package com.taishonet.enter.checker.test;

import android.content.Context;
import android.test.AndroidTestCase;

import com.taishonet.enter.checker.interfaces.CallBack;
import com.taishonet.enter.checker.interfaces.RunTask;
import com.taishonet.enter.checker.model.RequestTask;


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
			public Object run() {
				return null;
			}
		};
		CallBack callback = new CallBack() {

			@Override
			public void doCallBack(Object data) {
			}
		};
		RequestTask request = new RequestTask(mContext, task, callback);
		request.execute();
	}
}
