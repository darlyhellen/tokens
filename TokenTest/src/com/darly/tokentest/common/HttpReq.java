package com.darly.tokentest.common;

import java.io.UnsupportedEncodingException;

import org.apache.http.entity.StringEntity;

import android.content.Context;

import com.darly.tokentest.app.App;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yuntongxun.kitsdk.utils.LogUtil;

public class HttpReq {
	private static final String TAG = HttpReq.class.getName();
	private static Context context = App.getInstance();
	private static HttpUtils utils = new HttpUtils();

	public static void doPost(String url, String s,
			RequestCallBack<String> callBack) {
		if (!NetUtils.isConnected(context)) {
			ToastApp.showToast(context, "网络未连接...");
		} else {
			LogUtil.i(TAG, "接口地址--->>>" + url);
			LogUtil.i(TAG, "param--->>>" + s.toString());
			RequestParams params = new RequestParams();
			params.addHeader("Content-Type", "application/json;charset=UTF-8");
			params.addHeader("Accept", "application/json");
			params.addHeader("charset", "utf-8");
			try {
				StringEntity se = new StringEntity(s, "utf-8");
				params.setBodyEntity(se);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String token = "9xasdlfj";
			LogUtil.i(TAG, "token--->>>" + token);
			params.addHeader("token", token);
			utils.configTimeout(30 * 1000);
			utils.configRequestRetryCount(1);
			utils.configRequestThreadPoolSize(3);
			utils.configResponseTextCharset("utf-8");
			utils.send(HttpMethod.POST, url, params, callBack);
		}
	}
}
