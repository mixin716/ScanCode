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

	/** 保存登录状�? */
	public void SaveFlag(boolean flag) {
		edit.putBoolean("user_flag", flag);
		edit.commit();
	}

	/** 获取登录状�? */
	public boolean GetFlag() {
		return share.getBoolean("user_flag", false);
	}

	/** 保存token */
	public void SaveToken(String token) {
		edit.putString("user_token", token);
		edit.commit();
	}

	/** 获取token */
	public String GetToken() {
		return share.getString("user_token", null);
	}

	/** 保存id */
	public void SaveId(int id) {
		edit.putInt("user_id", id);
		edit.commit();
	}

	/** 获取id */
	public int GetId() {
		return share.getInt("user_id", 0);
	}

	/** 保存用户�?-手机�?*/
	public void SaveName(String name) {
		edit.putString("user_name", name);
		edit.commit();
	}

	/** 获取用户�?-手机�?*/
	public String GetName() {
		return share.getString("user_name", null);
	}
	
	/** 保存用户�?-真实*/
	public void SaveRealname(String realname){
		edit.putString("realname", realname);
		edit.commit();
	}
	
	/** 获取用户�?-真实*/
	public String Getrealname(){
		return share.getString("realname", null);
	}

	/** 保存手机�?*/
	public void SavePhone(String phone) {
		edit.putString("user_phone", phone);
		edit.commit();
	}

	/** 获取手机�?*/
	public String GetPhone() {
		return share.getString("user_phone", null);
	}
	/** 保存身份证号*/
	public void SaveIdcard(String idcard){
		edit.putString("idcard", idcard);
		edit.commit();
	}
	/** 获取身份证号*/
	public String GetIdcard(){
		return share.getString("idcard", null);
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

	/** 保存�?��状�? */
	public void SaveOpenFlag(int openFlag) {
		edit.putInt("user_open", openFlag);
		edit.commit();
	}

	/** 获取�?��状�? */
	public int GetOpenFlag() {
		return share.getInt("user_open", 0);
	}

	/** 保存支付密码 */
	public void SaveBuyPwd(String buyPwd) {
		edit.putString("user_buy", buyPwd);
		edit.commit();
	}

	/** 获取支付密码 */
	public String GetBuyPwd() {
		return share.getString("user_buy", null);
	}

	/** 保存银行卡数 */
	public void SaveBankSum(String banksum) {
		edit.putString("user_banksum", banksum);
		edit.commit();
	}

	/** 获取银行卡数 */
	public String GetBankSum() {
		return share.getString("user_banksum", null);
	}
}
