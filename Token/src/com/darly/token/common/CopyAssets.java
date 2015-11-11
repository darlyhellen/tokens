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

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.darly.token.app.Constract;

/**
 * @author zhangyh2 CopyAssets $ 上午11:25:50 TODO
 */
public class CopyAssets {

	private static int download_precent = 0;

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
			if (precent - download_precent >= 1) {
				download_precent = precent;
				Message message = ha.obtainMessage(3, precent);
				ha.sendMessage(message);
			}
		}
	}
}
