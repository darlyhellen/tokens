package cn.com.darly.rich.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import cn.com.darly.rich.R;
import cn.com.darly.rich.app.Constract;
import cn.com.darly.rich.base.BaseActivity;
import cn.com.darly.rich.receiver.ExampleUtil;

import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

	private MessageReceiver reseiver;

	@ViewInject(R.id.main_text)
	private TextView tv;
	@ViewInject(R.id.main_marquee)
	private TextView marq;

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

		registerMessageReceiver();
	}

	private void registerMessageReceiver() {
		reseiver = new MessageReceiver();
		IntentFilter filter = new IntentFilter();
		filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
		filter.addAction(MESSAGE_RECEIVED_ACTION);
		registerReceiver(reseiver, filter);
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

	public class MessageReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
				String messge = intent.getStringExtra(KEY_MESSAGE);
				String extras = intent.getStringExtra(KEY_EXTRAS);
				StringBuilder showMsg = new StringBuilder();
				showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
				if (!ExampleUtil.isEmpty(extras)) {
					showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
				}
				tv.setText(showMsg);
				LogUtils.i(showMsg.toString());
				marq.setText(showMsg);
				TranslateAnimation animation = new TranslateAnimation(
						Constract.width, -marq.getWidth(), 0, 0);
				animation.setDuration(3000);
				marq.setAnimation(animation);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		unregisterReceiver(reseiver);
		super.onDestroy();
	}

}
