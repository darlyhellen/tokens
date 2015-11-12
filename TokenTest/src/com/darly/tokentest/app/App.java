/**
 * 上午10:29:28
 * @author zhangyh2
 * $
 * App.java
 * TODO
 */
package com.darly.tokentest.app;

import java.io.File;
import java.io.InvalidClassException;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.WindowManager;

import com.darly.tokentest.common.CrashHandler;
import com.darly.tokentest.common.SDKServerInitHelper;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.yuntongxun.kitsdk.core.CCPAppManager;
import com.yuntongxun.kitsdk.setting.ECPreferenceSettings;
import com.yuntongxun.kitsdk.setting.ECPreferences;
import com.yuntongxun.kitsdk.utils.FileAccessor;
import com.yuntongxun.kitsdk.utils.LogUtil;

/**
 * @author zhangyh2 App $ 上午10:29:28 TODO
 */
public class App extends Application {

	private static App instance;
	/**
	 * 单例，返回一个实例
	 * 
	 * @return
	 */
	public static App getInstance() {
		if (instance == null) {
			LogUtil.w("[ECApplication] instance is null.");
		}
		return instance;
	}

	@Override
	public void onCreate() {
		initConfig();
		super.onCreate();
		instance = this;
		getScreem();

		CCPAppManager.setContext(instance);
		FileAccessor.initFileAccess();
		setChattingContactId();
		initImageLoader();
		CrashHandler.getInstance().init(this);
	}

	/**
	 * 保存当前的聊天界面所对应的联系人、方便来消息屏蔽通知
	 */
	private void setChattingContactId() {
		try {
			ECPreferences.savePreference(
					ECPreferenceSettings.SETTING_CHATTING_CONTACTID, "", true);
		} catch (InvalidClassException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 下午4:13:31
	 * @author zhangyh2
	 * App.java
	 * TODO 初始化 SDKServerInitHelper
	 */
	private void initConfig() {
		// 1、在SDKServerInitHelper.java里面的SERVER_XML参数配置你的服务器信息
		// 2、调用SDKServerInitHelper.initServer(context)方法写入配置文件（写入之前一定要确保删除之前的配置文件）
		// if(SDKServerInitHelper.isServerConfigExist(this)) {
		// 删除之前的配置文件
		// 这里会导致每次都会把配置文件重新写一遍
		// 所以客户端需要保存时都已经更新了配置文件，如果更新了就不执行写入操作
		// SDKServerInitHelper.delServerConfig(this);
		// }
		// 3、为了避免每次都重复写入（你需要保存你是否已经更新了服务器配置文件）
		// 4、调用SDK初始化方法和登陆方法
		try {
			/*
			 * if(SDKServerInitHelper.isServerConfigExist(this)) {
			 * SDKServerInitHelper.delServerConfig(this); }
			 */
			// 第一种方式：直接写入配置文件
			SDKServerInitHelper.initServer(this);
			// 第二种方式：把本地存在的服务器配置文件初始化到应用目录
			/*
			 * File file = new File("/sdcard/ECDemo_Msg",
			 * "sdk_server_config.xml"); if(!file.exists()) { return ; }
			 * if(SDKServerInitHelper.initServerFromLocal(this,
			 * file.getAbsolutePath())) { file.delete(); }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initImageLoader() {
		File cacheDir = StorageUtils.getOwnCacheDirectory(
				getApplicationContext(), "ECSDK_Demo/image");
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				this).threadPoolSize(1)
				// 线程池内加载的数量
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.memoryCache(new WeakMemoryCache())
				// .denyCacheImageMultipleSizesInMemory()
				.diskCacheFileNameGenerator(CCPAppManager.md5FileNameGenerator)
				// 将保存的时候的URI名称用MD5 加密
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.diskCache(
						new UnlimitedDiscCache(cacheDir, null,
								CCPAppManager.md5FileNameGenerator))// 自定义缓存路径
				.defaultDisplayImageOptions(DisplayImageOptions.createSimple())
				// .writeDebugLogs() // Remove for release app
				.build();// 开始构建
		ImageLoader.getInstance().init(config);
	}

	/**
	 * 返回配置文件的日志开关
	 * 
	 * @return
	 */
	public boolean getLoggingSwitch() {
		try {
			ApplicationInfo appInfo = getPackageManager().getApplicationInfo(
					getPackageName(), PackageManager.GET_META_DATA);
			boolean b = appInfo.metaData.getBoolean("LOGGING");
			LogUtil.w("[ECApplication - getLogging] logging is: " + b);
			return b;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean getAlphaSwitch() {
		try {
			ApplicationInfo appInfo = getPackageManager().getApplicationInfo(
					getPackageName(), PackageManager.GET_META_DATA);
			boolean b = appInfo.metaData.getBoolean("ALPHA");
			LogUtil.w("[ECApplication - getAlpha] Alpha is: " + b);
			return b;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
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
