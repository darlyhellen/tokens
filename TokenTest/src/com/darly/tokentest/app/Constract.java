package com.darly.tokentest.app;

import android.os.Environment;

/**
 * @ClassName: Literal
 * @Description: TODO(Handler的常量类)
 * @author 张宇辉 zhangyuhui@octmami.com
 * @date 2014年11月26日 上午9:03:57
 *
 */
public class Constract {

	public static int width = -1;

	public static int height = -1;

	public static int desty = -1;

	public static final int REQUESTCODE_CAM = 0x1001;

	public static final int REQUESTCODE_CAP = 0x1002;

	public static final int REQUESTCODE_CUT = 0x1003;

	public static final String ROOT = Environment.getExternalStorageDirectory()
			.getAbsolutePath() + "/token/";

	public static final String SROOT = ROOT + "image/";

	public static final String TRAINED = ROOT + "tessdata/";

	public static final String LOG = ROOT + "log/";

	/** 头像的路径(所有照片的临时存储位置和名字) */
	public static final String HEAD = SROOT + "head.png";

	public static String capUri;

}
