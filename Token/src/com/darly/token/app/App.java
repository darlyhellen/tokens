/**
 * 上午10:29:28
 * @author zhangyh2
 * $
 * App.java
 * TODO
 */
package com.darly.token.app;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;
import android.view.WindowManager;

/**
 * @author zhangyh2 App $ 上午10:29:28 TODO
 */
public class App extends Application {

	private static App instance;

	/**
	 * @return the instance
	 */
	public static App getInstance() {
		if (instance == null) {
			return null;
		}
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Application#onCreate()
	 */
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		instance = this;

		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			// 建立文件夹
			File file = new File(Constract.ROOT);
			if (!file.exists()) {
				file.mkdir();
			}
		}
		getScreem();
		copyAssets();
	}

	/**
	 * 
	 * 上午10:31:50
	 * 
	 * @author zhangyh2 App.java TODO 获取屏幕参数
	 */
	@SuppressWarnings("deprecation")
	private void getScreem() {
		if (Constract.width == -1 && Constract.height == -1) {
			WindowManager wm = (WindowManager) instance
					.getSystemService(Context.WINDOW_SERVICE);
			Constract.width = wm.getDefaultDisplay().getWidth();
			Constract.height = wm.getDefaultDisplay().getHeight();
			Constract.desty = wm.getDefaultDisplay().getDisplayId();
		}
	}

	private void copyAssets() {
		AssetManager assetManager = getAssets();
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
			InputStream in = null;
			OutputStream out = null;
			try {
				in = assetManager.open(filename);
				File outFile = new File(Constract.TRAINED, filename);
				out = new FileOutputStream(outFile);
				copyFile(in, out);
				in.close();
				in = null;
				out.flush();
				out.close();
				out = null;
			} catch (IOException e) {
				Log.e("tag", "Failed to copy asset file: " + filename, e);
			}
		}
	}

	private void copyFile(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int read;
		while ((read = in.read(buffer)) != -1) {
			out.write(buffer, 0, read);
		}
	}

}
