package com.darly.tokentest.ui;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.darly.tokentest.R;
import com.darly.tokentest.ui.login.LoginAcitvity;
import com.darly.tokentest.widget.numberprogressbar.NumberProgressBar;
import com.darly.tokentest.widget.numberprogressbar.OnProgressBarListener;

public class MainActivity extends Activity implements OnProgressBarListener {

	NumberProgressBar bar;
	Timer timer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bar = (NumberProgressBar) findViewById(R.id.main_progbar);
		bar.setOnProgressBarListener(this);

		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						bar.incrementProgressBy(1);
					}
				});
			}
		}, 1000, 100);

		Button btn = (Button) findViewById(R.id.main_login);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this, LoginAcitvity.class));
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onProgressChange(int current, int max) {
		// TODO Auto-generated method stub
		if (current == max) {
			bar.setProgress(0);
		}

	}

}
