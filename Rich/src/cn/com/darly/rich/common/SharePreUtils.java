/**
 * 下午1:26:26
 * @author zhangyh2
 * $
 * SharePreUtils.java
 * TODO
 */
package cn.com.darly.rich.common;

import android.content.Context;
import android.content.SharedPreferences.Editor;

/**
 * @author zhangyh2 SharePreUtils $ 下午1:26:26 TODO 一些常用数据保存
 */
public class SharePreUtils {

	private static String ISFIRSTCOME = "isfirstcome";

	/**
	 * @param come
	 *            下午1:31:00
	 * @author zhangyh2 SharePreUtils.java TODO 记录是否是首次进入程序。
	 */
	public static void isfirstcome(Context context, boolean come) {
		Editor sp = context.getSharedPreferences(ISFIRSTCOME,
				Context.MODE_PRIVATE).edit();
		sp.putBoolean(ISFIRSTCOME, come);
		sp.commit();
	}

	/**
	 * @return 下午1:31:40
	 * @author zhangyh2 SharePreUtils.java TODO 获取记录。
	 */
	public static boolean getfirstcome(Context context) {
		return context.getSharedPreferences(ISFIRSTCOME, Context.MODE_PRIVATE)
				.getBoolean(ISFIRSTCOME, false);
	}
}
