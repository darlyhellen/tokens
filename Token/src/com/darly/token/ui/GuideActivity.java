/**
 * 上午11:46:04
 * @author zhangyh2
 * $
 * GuideActivity.java
 * TODO
 */
package com.darly.token.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.darly.token.R;
import com.darly.token.adapter.ImageAdapter;
import com.darly.token.base.BaseActivity;
import com.darly.token.common.SharePreUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author zhangyh2 GuideActivity $ 上午11:46:04 TODO
 */
@ContentView(R.layout.activity_guide)
public class GuideActivity extends BaseActivity implements OnPageChangeListener {
	@ViewInject(R.id.guide_view_pager)
	private ViewPager vp;
	@ViewInject(R.id.guide_view_circle)
	private LinearLayout tb;

	private List<View> viewList;

	private ImageView points[];// 存放小圆圈数组

	private int currentIndex = 0;// 当前页面,默认首页

	private Button startButton;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.token.base.BaseActivity#initView(android.os.Bundle)
	 */
	@Override
	public void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		if (!SharePreUtils.getfirstcome(this)) {
			// 第一次进入程序，
			SharePreUtils.isfirstcome(this, true);
			initViewPager();// 初始化ViewPager对象
			initPoint();// 初始化导航小圆点

		} else {
			// 非第一次使用
			intoMainActivity();
		}

	}

	/**
	 * 
	 * 下午4:20:18
	 * 
	 * @author zhangyh2 GuideActivity.java TODO
	 */
	private void initViewPager() {
		// TODO Auto-generated method stub
		viewList = new ArrayList<View>();// 实例化list集合

		// 用xml静态添加view
		viewList.add(View.inflate(GuideActivity.this, R.layout.guide_view1,
				null));
		viewList.add(View.inflate(GuideActivity.this, R.layout.guide_view2,
				null));
		viewList.add(View.inflate(GuideActivity.this, R.layout.guide_view3,
				null));

		// 设置适配器
		ImageAdapter adapter = new ImageAdapter(viewList);

		// 绑定适配器
		vp.setAdapter(adapter);

		// 设置页卡切换监听
		vp.setOnPageChangeListener(this);

	}

	/**
	 * 
	 * 下午4:20:13
	 * 
	 * @author zhangyh2 GuideActivity.java TODO
	 */
	private void initPoint() {
		// TODO Auto-generated method stub
		points = new ImageView[viewList.size()];
		for (int i = 0; i < points.length; i++) {
			ImageView iv = new ImageView(this);
			iv.setClickable(true);
			iv.setImageResource(R.drawable.point_selector);
			iv.setPadding(15, 15, 15, 15);
			tb.addView(iv);
			points[i] = iv;// 遍历LinearLayout下的所有ImageView子节点
			points[i].setEnabled(true);// 设置当前状态为允许（可点，灰色）
			// 设置点击监听
			points[i].setOnClickListener(this);
			// 额外设置一个标识符，以便点击小圆点时跳转对应页面
			points[i].setTag(i);// 标识符与圆点顺序一致
		}

		currentIndex = 0;
		points[currentIndex].setEnabled(false);// 设置首页为当前页(小点着色,蓝色)

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.token.base.BaseActivity#initData()
	 */
	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.token.base.BaseActivity#refreshGet(java.lang.Object)
	 */
	@Override
	public void refreshGet(Object object) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.token.base.BaseActivity#refreshPost(java.lang.Object)
	 */
	@Override
	public void refreshPost(Object object) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.view.ViewPager.OnPageChangeListener#
	 * onPageScrollStateChanged(int)
	 */
	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.support.v4.view.ViewPager.OnPageChangeListener#onPageScrolled
	 * (int, float, int)
	 */
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.support.v4.view.ViewPager.OnPageChangeListener#onPageSelected
	 * (int)
	 */
	@Override
	public void onPageSelected(int position) {
		// TODO Auto-generated method stub
		if (position == 2) {// 由于进入微信这个按钮在第4个页面（view）才会出现，如果一开始就加载这个按钮监听，就导致空指针异常
			startButton = (Button) findViewById(R.id.startbutton);
			startButton.setOnClickListener(new OnClickListener() {// 匿名内部类，区分小圆圈的点击事件

						@Override
						public void onClick(View v) {
							// 跳到首页
							intoMainActivity();
						}
					});
		}
		points[position].setEnabled(false);// 不可点
		points[currentIndex].setEnabled(true);// 恢复之前页面状态
		currentIndex = position;
	}

	public void intoMainActivity() {
		Intent intent = new Intent(GuideActivity.this, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);

		finish();

	}
}
