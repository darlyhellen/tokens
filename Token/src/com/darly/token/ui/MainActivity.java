package com.darly.token.ui;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.darly.token.R;
import com.darly.token.service.UpdateService;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Log.i("1111111", Calendar.DAY_OF_MONTH + "");

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, Calendar.DAY_OF_MONTH+20);
		Log.i("1111111", calendar.get(Calendar.DAY_OF_MONTH) + "");

		Intent i = new Intent(this, UpdateService.class);
		i.putExtra("url",
				"http://gdown.baidu.com/data/wisegame/4091da454312f1c1/QQ_288.apk");
//		 startService(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
