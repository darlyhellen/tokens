package com.darly.token.widget.load;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.darly.token.R;
import com.darly.token.app.Constract;

public class DialogAssetsUtil extends Dialog {
	protected Context context;

	private TextView filename;

	private ProgressBar filepro;

	public DialogAssetsUtil(Context context) {
		super(context, R.style.Custom_Progress);
		this.context = context;
		init();
	}

	public DialogAssetsUtil(Context context, int theme) {
		super(context, theme);
		this.context = context;
		init();
	}

	/**
	 * 当窗口焦点改变时调用
	 * 
	 */
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		filename = (TextView) findViewById(R.id.tvProcess);
		filepro = (ProgressBar) findViewById(R.id.pbDownload);
		filepro.setLayoutParams(new LayoutParams(Constract.width / 2, 12));
	}

	private void init() {
		setContentView(R.layout.update_service);
		// 按返回键是否取消
		setCanceledOnTouchOutside(false);
		// setCancelable(false);
		// 监听返回键处理
		// setOnCancelListener(cancelListener);
		// 设置居中
		getWindow().getAttributes().gravity = Gravity.CENTER;
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		// 设置背景层透明度
		lp.dimAmount = 0.2f;
		getWindow().setAttributes(lp);
	}

	/**
	 * @param filename
	 *            the filename to set
	 */
	public void setFilename(String title) {
		filename.setText(title);
	}

	/**
	 * 
	 * 下午1:40:42
	 * 
	 * @author zhangyh2 DialogAssetsUtil.java TODO
	 */
	public void setProgress(int progress) {
		// TODO Auto-generated method stub
		filepro.setProgress(progress);
	}

}
