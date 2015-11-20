package com.darly.token.ui;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;

import com.aphidmobile.flip.FlipViewController;
import com.darly.token.R;
import com.darly.token.adapter.FlipButtonAdapter;
import com.darly.token.base.BaseActivity;

public class FlipButtonActivity extends BaseActivity {

	private FlipViewController flipView;

	private List<String> data;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setTitle(R.string.app_name);

		flipView = new FlipViewController(this);
		flipView.setHovered(true);
		data = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			data.add(i + "");
		}
		flipView.setAdapter(new FlipButtonAdapter(data, 0, this));
		setContentView(flipView);

	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void refreshGet(Object object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refreshPost(Object object) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onResume() {
		super.onResume();
		flipView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		flipView.onPause();
	}

}
