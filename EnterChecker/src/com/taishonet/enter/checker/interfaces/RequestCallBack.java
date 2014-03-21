package com.taishonet.enter.checker.interfaces;

import org.json.JSONObject;

public interface RequestCallBack extends CallBack {
	public void doCallBack(JSONObject data);
}
