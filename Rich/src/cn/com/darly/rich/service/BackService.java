package cn.com.darly.rich.service;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.VideoView;

/**
 * @author zhangyh2 BackService $ APP开启直接启动此服务。 上午9:41:52 TODO 生成一个服务。在后台进行一些操作。
 */
public class BackService extends Service {

	private Timer timer;
	private int cout;

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		
		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				cout++;
				Log.i("BackService","onStartCommand run" + cout);
			}
		}, 100, 2000);
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.i("BackService", "onDestroy");
		timer.cancel();
		super.onDestroy();
	}
}
