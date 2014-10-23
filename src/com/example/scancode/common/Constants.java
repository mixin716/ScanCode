package com.example.scancode.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.graphics.drawable.Drawable;
import android.net.Uri;

public class Constants {

	public static final String INTENT_ACTION_FINISH_ALL = "com.bsk.yun.intent.ACTION_FINISH_ALL";// �ر�����ҳ��Intent

	public static Map<String, Object> passValueMap = new HashMap<String, Object>();
	public static Drawable drawable;
	public static boolean isChange = false;

	public static final String APP_KEY = "2025482806";

	public static final String REDIRECT_URL = "http://open.weibo.com/apps/2025482806/info/advanced";

	public static final String SCOPE = "email,direct_messages_read,direct_messages_write,"
			+ "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
			+ "follow_app_official_microblog," + "invitation_write";

	public static int cid;
	public static boolean refresh = false;
	public static boolean pushInput = false;// ͨ�����ʹ����

}
