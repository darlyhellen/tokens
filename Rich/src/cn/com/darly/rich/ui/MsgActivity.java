package cn.com.darly.rich.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import cn.com.darly.rich.R;
import cn.com.darly.rich.base.BaseActivity;
import cn.jpush.android.api.JPushInterface;

import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_main)
public class MsgActivity extends BaseActivity {

	@ViewInject(R.id.main_text)
	private TextView tv;

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
	 * @see cn.com.darly.rich.base.BaseActivity#initView(android.os.Bundle)
	 */
	@Override
	public void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		if (null != intent) {
			Bundle bundle = getIntent().getExtras();
			String title = bundle
					.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
			String content = bundle.getString(JPushInterface.EXTRA_ALERT);
			tv.setText("Title : " + title + "  " + "Content : " + content);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.darly.rich.base.BaseActivity#initData()
	 */
	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.darly.rich.base.BaseActivity#refreshGet(java.lang.Object)
	 */
	@Override
	public void refreshGet(Object object) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.darly.rich.base.BaseActivity#refreshPost(java.lang.Object)
	 */
	@Override
	public void refreshPost(Object object) {
		// TODO Auto-generated method stub

	}

}
