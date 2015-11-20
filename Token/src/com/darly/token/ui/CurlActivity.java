package com.darly.token.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.darly.token.R;
import com.darly.token.base.BaseActivity;
import com.darly.token.widget.curlview.CurlPage;
import com.darly.token.widget.curlview.CurlView;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_curl)
public class CurlActivity extends BaseActivity implements
		CurlView.SizeChangedObserver {
	@ViewInject(R.id.curl_curl_view)
	private CurlView curl;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object onRetainCustomNonConfigurationInstance() {
		// TODO Auto-generated method stub
		return curl.getCurrentIndex();
	}

	@Override
	public void initView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		curl.setPageProvider(new PageProvider());
		curl.setSizeChangedObserver(this);
		curl.setCurrentIndex(0);
		curl.setBackgroundColor(0xFF202830);

	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPause() {
		super.onPause();
		curl.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
		curl.onResume();
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
	public void onSizeChanged(int width, int height) {
		// TODO Auto-generated method stub
		if (width > height) {
			curl.setViewMode(CurlView.SHOW_TWO_PAGES);
			curl.setMargins(.1f, .05f, .1f, .05f);
		} else {
			curl.setViewMode(CurlView.SHOW_ONE_PAGE);
			curl.setMargins(.1f, .1f, .1f, .1f);
		}
	}

	/**
	 * Bitmap provider.
	 */
	private class PageProvider implements CurlView.PageProvider {

		// Bitmap resources.
		private int[] mBitmapIds = { R.drawable.obama, R.drawable.road_rage,
				R.drawable.taipei_101, R.drawable.world };

		@Override
		public int getPageCount() {
			return 5;
		}

		private Bitmap loadBitmap(int width, int height, int index) {
			Bitmap b = Bitmap.createBitmap(width, height,
					Bitmap.Config.ARGB_8888);
			b.eraseColor(0xFFFFFFFF);
			Canvas c = new Canvas(b);
			Drawable d = getResources().getDrawable(mBitmapIds[index]);

			int margin = 7;
			int border = 3;
			Rect r = new Rect(margin, margin, width - margin, height - margin);

			int imageWidth = r.width() - (border * 2);
			int imageHeight = imageWidth * d.getIntrinsicHeight()
					/ d.getIntrinsicWidth();
			if (imageHeight > r.height() - (border * 2)) {
				imageHeight = r.height() - (border * 2);
				imageWidth = imageHeight * d.getIntrinsicWidth()
						/ d.getIntrinsicHeight();
			}

			r.left += ((r.width() - imageWidth) / 2) - border;
			r.right = r.left + imageWidth + border + border;
			r.top += ((r.height() - imageHeight) / 2) - border;
			r.bottom = r.top + imageHeight + border + border;

			Paint p = new Paint();
			p.setColor(0xFFC0C0C0);
			c.drawRect(r, p);
			r.left += border;
			r.right -= border;
			r.top += border;
			r.bottom -= border;

			d.setBounds(r);
			d.draw(c);

			return b;
		}

		@Override
		public void updatePage(CurlPage page, int width, int height, int index) {

			switch (index) {
			case 0: {
				Bitmap front = loadBitmap(width, height, 0);
				page.setTexture(front, CurlPage.SIDE_FRONT);
				page.setColor(Color.rgb(180, 180, 180), CurlPage.SIDE_BACK);
				break;
			}
			case 1: {
				Bitmap back = loadBitmap(width, height, 2);
				page.setTexture(back, CurlPage.SIDE_BACK);
				page.setColor(Color.rgb(127, 140, 180), CurlPage.SIDE_FRONT);
				break;
			}
			case 2: {
				Bitmap front = loadBitmap(width, height, 1);
				Bitmap back = loadBitmap(width, height, 3);
				page.setTexture(front, CurlPage.SIDE_FRONT);
				page.setTexture(back, CurlPage.SIDE_BACK);
				break;
			}
			case 3: {
				Bitmap front = loadBitmap(width, height, 2);
				Bitmap back = loadBitmap(width, height, 1);
				page.setTexture(front, CurlPage.SIDE_FRONT);
				page.setTexture(back, CurlPage.SIDE_BACK);
				page.setColor(Color.argb(127, 170, 130, 255),
						CurlPage.SIDE_FRONT);
				page.setColor(Color.rgb(255, 190, 150), CurlPage.SIDE_BACK);
				break;
			}
			case 4:
				Bitmap front = loadBitmap(width, height, 0);
				page.setTexture(front, CurlPage.SIDE_BOTH);
				page.setColor(Color.argb(127, 255, 255, 255),
						CurlPage.SIDE_BACK);
				break;
			}
		}

	}
}
