package com.darly.tokentest.common;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * @ClassName: ToastApp
 * @Description: TODO(Toast统一管理类)
 * @author 张宇辉 zhangyuhui@octmami.com
 * @date 2014年10月24日 下午3:08:55
 *
 */
public class ToastApp {

	public static boolean isShow = true;

	private static Toast toast = null;
	/**
	 * VERSION參數，当参数为0的时候，展示最老的首页版本，当参数为1的时候，展示现在的每周精选版本。
	 */
	private static int TOASTTIME = 10;

	/**
	 * @auther Darly Fronch 2015 下午2:25:56 TODO显示Toast
	 */
	public static void showToast(Context context, String msg) {
		if (!isShow)
			return;

		if (toast == null) {
			toast = Toast.makeText(context, msg, TOASTTIME);
		} else {
			toast.setText(msg);
		}
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	public static void showToast(Context context, int msg) {
		if (!isShow)
			return;

		if (toast == null) {
			toast = Toast.makeText(context, msg, TOASTTIME);
		} else {
			toast.setText(msg);
		}
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

}