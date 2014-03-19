package com.taishonet.enter.cheker.model;

import org.json.JSONObject;

/**
 *  ネットワーク通信処理など非同期で行いたい処理をラップするインターフェース
 * 	@author taisho6339
 */

public interface RunTask {
	public JSONObject run();
}
