package cn.com.darly.rich.base;

import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * @ClassName: BaseActivity
 * @Description: 
 *               TODO(Activity工具基础类，实现了点击事件的接口，所有Activity全部继承此类，是一个至关重要的父类，整个APP全局的变量可以在这里书写
 *               。)
 * @author 张宇辉 zhangyuhui@octmami.com
 * @date 2014年12月15日 上午8:49:27
 *
 */
public abstract class BaseActivity extends FragmentActivity implements
		OnClickListener {
	protected List<View> pageviews;

	protected ActivityHandler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ViewUtils.inject(this);// 注入view和事件
		LogUtils.customTagPrefix = "xUtilsSample"; // 方便调试时过滤 adb logcat 输出
		LogUtils.allowI = false; // 关闭 LogUtils.i(...) 的 adb log 输出
		if (handler == null) {
			handler = new ActivityHandler(this);
		}
		initView(savedInstanceState);
		initData();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onPause()
	 */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
	}

	/**
	 * Auther:张宇辉 User:zhangyuhui 2015年1月5日 上午9:48:56 Project_Name:DFram
	 * Description:初始化界面控件，获取界面XML的方法体，可以在这里对所有XML中的控件进行实例。 Throws
	 * 
	 * @param savedInstanceState
	 */
	public abstract void initView(Bundle savedInstanceState);

	/**
	 * Auther:张宇辉 User:zhangyuhui 2015年1月5日 上午9:49:13 Project_Name:DFram
	 * Description:加载数据方法体，所有的参数数据都可以放到这里进行实现，当然和InitView功能差不多。 Throws
	 */
	public abstract void initData();

	/**
	 * Auther:张宇辉 User:zhangyuhui 2015年1月5日 上午9:49:34 Project_Name:DFram
	 * Description
	 * :通过Literal.GET_HANDLER调取Handler，由Handler进行调用的一个方法体，可以实现界面更改，界面刷新等功能。
	 * Throws
	 */
	public abstract void refreshGet(Object object);

	/**
	 * Auther:张宇辉 User:zhangyuhui 2015年1月5日 上午9:49:37 Project_Name:DFram
	 * Description
	 * :通过Literal.POST_HANDLER调取Handler，由Handler进行调用的一个方法体，可以实现界面更改，界面刷新等功能。
	 * Throws
	 */
	public abstract void refreshPost(Object object);

}
