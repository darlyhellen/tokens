/**
 * 上午10:29:28
 * @author zhangyh2
 * $
 * App.java
 * TODO
 */
package cn.com.darly.rich.app;

import cn.com.darly.rich.service.BackService;
import cn.jpush.android.api.JPushInterface;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
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
		getScreem();
		JPushInterface.setDebugMode(true);
		JPushInterface.init(this);
		//直接启动服务。
		startService(new Intent(instance, BackService.class));
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

}
