package com.example.scancode.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class UserSharedData {

	static SharedPreferences share;
	static Editor edit;

	private static UserSharedData userShare;

	public static UserSharedData getInstance(Context context) {
		if (userShare == null) {
			synchronized (UserSharedData.class) {
				if (userShare == null) {
					userShare = new UserSharedData();
					share = context.getSharedPreferences("user_share_data",
							Context.MODE_WORLD_READABLE
									| Context.MODE_WORLD_WRITEABLE);
					edit = share.edit();
				}
			}
		}
		return userShare;
	}

	/** 保存登录状状态 */
	public void SaveFlag(boolean flag) {
		edit.putBoolean("user_flag", flag);
		edit.commit();
	}

	/** 获取登录状状态 */
	public boolean GetFlag() {
		return share.getBoolean("user_flag", false);
	}

	/** 保存手机号 */
	public void SavePhone(String phone) {
		edit.putString("user_phone", phone);
		edit.commit();
	}

	/** 获取手机号 */
	public String Getphone() {
		return share.getString("user_phone", null);
	}

	/** 保存密码 */
	public void SavePwd(String pwd) {
		edit.putString("user_pwd", pwd);
		edit.commit();
	}

	/** 获取密码 */
	public String GetPwd() {
		return share.getString("user_pwd", null);
	}

	/** 保存等级 */
	public void SaveLevel(String level) {
		edit.putString("user_level", level);
		edit.commit();
	}

	/** 获取等级 */
	public String GetLevel() {
		return share.getString("user_level", null);
	}

	/** 保存姓名 */
	public void SaveName(String name) {
		edit.putString("user_name", name);
		edit.commit();
	}

	/** 获取姓名 */
	public String GetName() {
		return share.getString("user_name", null);
	}

	/** 保存总码乐 */
	public void SaveAllPoint(String point) {
		edit.putString("user_all_point", point);
		edit.commit();
	}

	/** 获取总码乐 */
	public String GetAllPoint() {
		return share.getString("user_all_point", null);
	}

}
