package com.darly.token.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.darly.token.R;
import com.darly.token.app.Constract;
import com.darly.token.base.BaseActivity;
import com.darly.token.common.CopyAssets;
import com.darly.token.common.SharePreUtils;
import com.darly.token.ui.chart.TemperatureChart;
import com.darly.token.widget.load.DialogAssetsUtil;
import com.darly.token.widget.load.ProgressDialogUtil;
import com.darly.token.widget.photoutil.PhotoPop;
import com.googlecode.tesseract.android.TessBaseAPI;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

@SuppressLint("HandlerLeak")
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

	/**
	 * 上午9:27:39 TODO添加更多图片按钮
	 */
	@ViewInject(R.id.me_details_more)
	private ImageView more;
	/**
	 * 下午3:07:33 TODO更多图片横向排列。
	 */
	@ViewInject(R.id.me_details_linear)
	private LinearLayout container;

	@ViewInject(R.id.me_details_text)
	private TextView tv;
	@ViewInject(R.id.me_details_flip)
	private Button flip;
	@ViewInject(R.id.me_details_achart)
	private Button chart;
	@ViewInject(R.id.me_details_curl)
	private Button curl;

	/**
	 * 上午9:29:04 TODO 调出选项的POP窗口，主要为相机，相册，取消
	 */
	private PhotoPop pop;
	/**
	 * 上午9:30:17 TODO 加载动画。
	 */
	private ProgressDialogUtil loading;

	private DialogAssetsUtil dialog;

	private String language = "chi_sim";

	public Handler ha = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				dialog.setFilename("加载完成");
				SharePreUtils.isasset(MainActivity.this, true);
				dialog.dismiss();
				break;
			case 2:
				String name = (String) msg.obj;
				dialog.setFilename("正在加载" + name);
				break;
			case 3:
				int a = (Integer) msg.obj;
				dialog.setProgress(a);
				break;

			default:
				break;
			}
		}

	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.me_details_more:
			pop.show(v);
			break;
		case R.id.me_details_flip:
			startActivity(new Intent(this, FlipButtonActivity.class));
			break;
		case R.id.me_details_curl:
			startActivity(new Intent(this, CurlActivity.class));
			break;
		case R.id.me_details_achart:
			startActivity(new TemperatureChart().execute(this));
			break;

		default:
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.token.base.BaseActivity#initView(android.os.Bundle)
	 */
	@Override
	public void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// Log.i("1111111", Calendar.DAY_OF_MONTH + "");
		//
		// Calendar calendar = Calendar.getInstance();
		// calendar.set(Calendar.DAY_OF_MONTH, Calendar.DAY_OF_MONTH + 20);
		// Log.i("1111111", calendar.get(Calendar.DAY_OF_MONTH) + "");
		//
		// Intent i = new Intent(this, UpdateService.class);
		// i.putExtra("url",
		// "http://gdown.baidu.com/data/wisegame/4091da454312f1c1/QQ_288.apk");
		// startService(i);

		pop = new PhotoPop(this);
		more.setOnClickListener(this);
		loading = new ProgressDialogUtil(this);
		loading.setMessage(R.string.loading);

		dialog = new DialogAssetsUtil(this);
		// SharePreUtils.isasset(MainActivity.this, false);
		if (!SharePreUtils.getasset(this)) {
			dialog.show();
			// 设置任务栏中下载进程显示的views
			new Asy(this).execute();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.darly.token.base.BaseActivity#initData()
	 */
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		flip.setOnClickListener(this);
		curl.setOnClickListener(this);
		chart.setOnClickListener(this);
		if (new Random().nextBoolean()) {
			language = "chi_sim";
		} else {
			language = "eng";
		}
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
	 * @see android.support.v4.app.FragmentActivity#onActivityResult(int, int,
	 * android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != RESULT_OK) {
			return;
		}
		if (requestCode == Constract.REQUESTCODE_CUT) {
			// 裁剪
			if (data != null) {
				Bundle extras = data.getExtras();
				Bitmap head = extras.getParcelable("data");
				getWord(head);
				Log.i("getWord", head.getWidth() + "---" + head.getHeight());
				ImageView imageView = new ImageView(MainActivity.this);
				LayoutParams lp = new LayoutParams(Constract.width / 5,
						Constract.width / 5);
				lp.setMargins(2, 2, 2, 2);
				imageView.setLayoutParams(lp);
				imageView.setImageBitmap(head);
				container.addView(imageView, container.getChildCount() - 1);
			}
		} else {

			// 拍照或相册
			String head_path = null;
			if (data == null) {
				if (pop == null) {
					head_path = Constract.capUri;
				} else {
					head_path = pop.PopStringActivityResult(null,
							Constract.REQUESTCODE_CAP);
				}
			} else {
				head_path = pop.PopStringActivityResult(data,
						Constract.REQUESTCODE_CAM);

			}
			File temp = new File(head_path);
			pop.cropPhoto(Uri.fromFile(temp));// 裁剪图片
		}
	}

	/**
	 * @param head
	 *            下午5:28:05
	 * @author zhangyh2 MainActivity.java TODO 从图片中获取文字信息
	 */
	private void getWord(final Bitmap head) {
		// TODO Auto-generated method stub
		handler.post(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				doTrasFromImage(head);
			}
		});
	}

	/**
	 * 
	 * 下午5:31:21
	 * 
	 * @author zhangyh2 MainActivity.java TODO
	 * @param head
	 */
	protected void doTrasFromImage(Bitmap head) {
		// TODO Auto-generated method stub
		TessBaseAPI baseApi = new TessBaseAPI();
		// 初始化tess
		// android下面，tessdata肯定得放到sd卡里了
		// 如果tessdata这个目录放在sd卡的根目录
		// 那么path直接传入sd卡的目录
		// eng就是英文，关于语言，按ISO 639-3标准的代码就行，具体请移步wiki
		baseApi.init(Constract.ROOT, language);
		// 设置要ocr的图片bitmap
		baseApi.setImage(head);
		// 根据Init的语言，获得ocr后的字符串
		String text = baseApi.getUTF8Text();
		// 释放bitmap
		baseApi.clear();
		// 如果连续ocr多张图片，这个end可以不调用，但每次ocr之后，必须调用clear来对bitmap进行释放
		// 释放native内存
		baseApi.end();
		StringBuilder builder = new StringBuilder();
		builder.append(tv.getText().toString());
		builder.append("\r\n");
		builder.append(text);
		tv.setText(builder.toString());
	}

	class Asy extends AsyncTask<Object, Object, Object> {
		private Context context;

		public Asy(Context context) {
			this.context = context;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#doInBackground(java.lang.Object[])
		 */
		@Override
		protected Object doInBackground(Object... params) {
			// TODO Auto-generated method stub
			// CopyAssets.copyAssets(context, ha);
			ArrayList<String> data = new ArrayList<String>();
			data.add("http://dlsw.baidu.com/sw-search-sp/soft/3a/12350/QQ_7.8.16379.0_setup.1446522220.exe");
			data.add("http://dlsw.baidu.com/sw-search-sp/soft/b1/38200/WeChat_1.5.0.33_setup.1446175356.exe");
			CopyAssets.downAssets(context, data, ha);
			return null;
		}

	}
}
