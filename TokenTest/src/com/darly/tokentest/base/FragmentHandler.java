/**
 * 上午9:42:44
 * @author Zhangyuhui
 * BaseHandler.java
 * TODO
 */
package com.darly.tokentest.base;

import android.os.Handler;
import android.os.Message;

/**
 * @author Zhangyuhui BaseHandler 上午9:42:44 TODO 进行一个Handler的整理。方便以后使用。
 */
public class FragmentHandler extends Handler {

	private BaseFragment frag;
	public static final int GET_HANDLER = 0x01;
	public static final int POST_HANDLER = 0x02;

	public FragmentHandler(BaseFragment frag) {
		super();
		this.frag = frag;
	}

	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		super.handleMessage(msg);
		switch (msg.what) {
		case GET_HANDLER:
			frag.refreshGet(msg.obj);
			break;
		case POST_HANDLER:
			frag.refreshPost(msg.obj);
			break;
		default:
			break;
		}

	}

}
