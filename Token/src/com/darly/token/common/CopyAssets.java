/**
 * 上午11:25:50
 * @author zhangyh2
 * $
 * CopyAssets.java
 * TODO
 */
package com.darly.token.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.darly.token.app.Constract;
import com.lidroid.xutils.util.LogUtils;

/**
 * @author zhangyh2 CopyAssets $ 上午11:25:50 TODO
 */
public class CopyAssets {

	private static int download_precent = 0;

	public static void downAssets(Context context, List<String> urls, Handler ha) {
		if (urls == null) {
			return;
		}
		File filed = new File(Constract.TRAINED);
		if (!filed.exists()) {
			filed.mkdir();
		}
		String[] files = filed.list();
		A: for (String url : urls) {
			String filename = url.substring(url.length() - 9, url.length() - 1);
			for (int i = 0; i < files.length; i++) {
				if (filename.endsWith(files[i])) {
					continue A;
				}
			}

			Message msg = ha.obtainMessage(2, filename);
			ha.sendMessage(msg);
			InputStream in = null;
			OutputStream out = null;
			try {
				URL realUrl = new URL(new String(url.getBytes(), "ISO-8859-1"));
				// 打开和URL之间的连接
				HttpURLConnection conn = (HttpURLConnection) realUrl
						.openConnection();
				// 设置通用的请求属性
				conn.setRequestMethod("GET");
				conn.setRequestProperty("accept", "*/*");
				conn.setRequestProperty("connection", "Keep-Alive");

				// 定义BufferedReader输入流来读取URL的响应
				long length = conn.getContentLength();
				in = conn.getInputStream();

				File outFile = new File(Constract.TRAINED, filename);
				out = new FileOutputStream(outFile);
				copyDown(length, in, out, ha);
				in.close();
				in = null;
				out.flush();
				out.close();
				out = null;
			} catch (IOException e) {
				Log.e("tag", "Failed to copy asset file: " + filename, e);
			}
			Message message = ha.obtainMessage(1);
			ha.sendMessage(message);
		}
	}

	private static void copyDown(long length, InputStream in, OutputStream out,
			Handler ha) throws IOException {
		byte[] buffer = new byte[1024];
		int read;
		long count = 0;
		int precent = 0;
		while ((read = in.read(buffer)) != -1) {

			out.write(buffer, 0, read);
			count += read;
			precent = (int) (((double) count / length) * 100);
			LogUtils.i("网络下载;precent=" + precent + ";count=" + count
					+ ";length=" + length);
			if (precent - download_precent >= 1) {
				download_precent = precent;
				Message message = ha.obtainMessage(3, precent);
				ha.sendMessage(message);
			}
		}
	}

	public static void copyAssets(Context context, Handler ha) {
		AssetManager assetManager = context.getAssets();
		String[] files = null;
		try {
			files = assetManager.list("");
		} catch (IOException e) {
			Log.e("tag", "Failed to get asset file list.", e);
		}
		for (String filename : files) {
			if (!filename.contains("traineddata")) {
				continue;
			}
			Message msg = ha.obtainMessage(2, filename);
			ha.sendMessage(msg);
			InputStream in = null;
			OutputStream out = null;
			try {
				in = assetManager.open(filename);
				File filed = new File(Constract.TRAINED);
				if (!filed.exists()) {
					filed.mkdir();
				}
				File outFile = new File(Constract.TRAINED, filename);
				out = new FileOutputStream(outFile);
				copyFile(in, out, ha);
				in.close();
				in = null;
				out.flush();
				out.close();
				out = null;
			} catch (IOException e) {
				Log.e("tag", "Failed to copy asset file: " + filename, e);
			}

			Message message = ha.obtainMessage(1);
			ha.sendMessage(message);
		}
	}

	private static void copyFile(InputStream in, OutputStream out, Handler ha)
			throws IOException {
		long length = in.available();
		byte[] buffer = new byte[1024];
		int read;
		long count = 0;
		int precent = 0;
		while ((read = in.read(buffer)) != -1) {

			out.write(buffer, 0, read);
			count += read;
			precent = (int) (((double) count / length) * 100);
			LogUtils.i("网络下载;precent=" + precent + ";count=" + count
					+ ";length=" + length);
			if (precent - download_precent >= 1) {
				download_precent = precent;
				Message message = ha.obtainMessage(3, precent);
				ha.sendMessage(message);
			}
		}
	}
}
