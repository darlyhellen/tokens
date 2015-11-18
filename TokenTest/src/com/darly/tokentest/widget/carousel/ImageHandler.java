package com.darly.tokentest.widget.carousel;

import java.lang.ref.WeakReference;

import android.os.Handler;
import android.os.Message;

/**
 * @author zhangyuhui 轮播的Handler，必须在展示的页面进行实例化。从而进行调取。
 */
public class ImageHandler<BaseFragment> extends Handler {

	/**
	 * 请求更新显示的View。
	 */
	public static final int MSG_UPDATE_IMAGE = 1;
	/**
	 * 请求暂停轮播。
	 */
	public static final int MSG_KEEP_SILENT = 2;
	/**
	 * 请求恢复轮播。
	 */
	public static final int MSG_BREAK_SILENT = 3;
	/**
	 * 记录最新的页号，当用户手动滑动时需要记录新页号，否则会使轮播的页面出错。 例如当前如果在第一页，本来准备播放的是第二页，而这时候用户滑动到了末页，
	 * 则应该播放的是第一页，如果继续按照原来的第二页播放，则逻辑上有问题。
	 */
	public static final int MSG_PAGE_CHANGED = 4;

	// 轮播间隔时间
	public static final long MSG_DELAY = 5000;

	// 使用弱引用避免Handler泄露.这里的泛型参数可以不是Activity，也可以是Fragment等
	private WeakReference<BaseFragment> weakReference;

	public ImageHandler(WeakReference<BaseFragment> wk) {
		weakReference = wk;
	}

	@Override
	public void handleMessage(Message msg) {

		BaseFragment activity = weakReference.get();
		if (activity == null) {
			// Activity已经回收，无需再处理UI了
			return;
		}
		// 检查消息队列并移除未发送的消息，这主要是避免在复杂环境下消息出现重复等问题。
		if (hasMessages(MSG_UPDATE_IMAGE)) {
			removeMessages(MSG_UPDATE_IMAGE);
		}
		switch (msg.what) {
		case MSG_UPDATE_IMAGE:
			// 准备下次播放
			Carousel.contents++;
			Carousel.pager.setCurrentItem(Carousel.contents);
			sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
			break;
		case MSG_KEEP_SILENT:
			// 只要不发送消息就暂停了
			break;
		case MSG_BREAK_SILENT:
			sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
			break;
		case MSG_PAGE_CHANGED:
			// 记录当前的页号，避免播放的时候页面显示不正确。
			Carousel.contents = msg.arg1;
			sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
		default:
			break;
		}
	}
}
