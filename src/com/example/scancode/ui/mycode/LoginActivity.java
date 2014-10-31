package com.example.scancode.ui.mycode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.scancode.BaseActivity;
import com.example.scancode.R;
import com.example.scancode.common.Urls;
import com.example.scancode.common.UserSharedData;
import com.example.scancode.utils.AnimUtil;
import com.example.scancode.utils.KeyBoard;
import com.jky.struct2.http.core.AjaxParams;
import com.jky.struct2.http.entityhandle.HttpResult;

public class LoginActivity extends BaseActivity {

	private EditText etPhone, etPwd;
	private Button btLogin;
	private TextView tvFind, tvRegister;
	private CheckBox cbRemember;
	private String strPhone, strPwd;
	private UserSharedData userShare;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentViewRes(R.layout.activity_login_layout);
		setViews();
	}

	@Override
	protected void initVariable() {
		// TODO Auto-generated method stub
		userShare = UserSharedData.getInstance(getApplicationContext());
	}

	@Override
	protected void setTitleViews() {
		// TODO Auto-generated method stub
		titleText.setText("登录");
	}

	@Override
	protected void setViews() {
		// TODO Auto-generated method stub
		etPhone = (EditText) findViewById(R.id.activity_login_et_phone);
		etPwd = (EditText) findViewById(R.id.activity_login_et_pwd);
		btLogin = (Button) findViewById(R.id.activity_login_bt_login);
		tvFind = (TextView) findViewById(R.id.activity_login_tv_find);
		tvRegister = (TextView) findViewById(R.id.activity_login_tv_register);
		cbRemember = (CheckBox) findViewById(R.id.activity_login_cb_remember);

		btLogin.setOnClickListener(this);
		tvFind.setOnClickListener(this);
		tvRegister.setOnClickListener(this);
	}

	@Override
	protected void doClickAction(int viewId) {
		// TODO Auto-generated method stub
		super.doClickAction(viewId);
		Intent intent;
		switch (viewId) {
		case R.id.title_iv_left:
			this.finish();
			AnimUtil.pushRightInAndOut(this);
			break;
		case R.id.activity_login_tv_find:// 找回密码

			break;
		case R.id.activity_login_tv_register:// 注册
			intent = new Intent(this, RegisterActivity.class);
			startActivity(intent);
			AnimUtil.pushLeftInAndOut(this);
			break;
		case R.id.activity_login_bt_login:// 登录
			KeyBoard.demissKeyBoard(getApplicationContext(), etPhone);
			KeyBoard.demissKeyBoard(getApplicationContext(), etPwd);
			strPhone = etPhone.getText().toString().trim();
			strPwd = etPwd.getText().toString().trim();
			if (TextUtils.isEmpty(strPhone)) {
				showToast("请输入手机号");
			} else if (strPhone.length() != 11) {
				showToast("请输入正确的手机号");
			}  else if (TextUtils.isEmpty(strPwd)) {
				showToast("请输入密码");
			} else if (strPwd.length() < 6) {
				showToast("请输入正确的密码");
			} else {
				requestLogin();
			}
			break;
		}
	}

	/** 登录*/
	private void requestLogin() {
		showLoading();
		AjaxParams params = new AjaxParams();
		params.put("usrno", strPhone);
		params.put("pass", strPwd);
		httpRequest.get(Urls.LOGIN, params, callBack, 0);
	}

	@Override
	protected void handleResult(int requestCode, HttpResult result) {
		// TODO Auto-generated method stub
		super.handleResult(requestCode, result);
		Log.e("", result.baseJson + "");
		JSONArray baseArray;
		try {
			baseArray = new JSONArray(result.baseJson);
			JSONObject baseObj = baseArray.getJSONObject(0);
			switch (requestCode) {
			case 0:
				String loginFlag = baseObj.optString("flag");
				String loginMsg = baseObj.optString("msg");
				if ("0".equals(loginFlag)) {
					showToast("登录成功");
					String loginLevel = baseObj.optString("level");
					String loginName = baseObj.optString("usr");
					String loginPorint = baseObj.optString("point2");
					userShare.SaveName(loginName);
					userShare.SavePhone(strPhone);
					userShare.SavePwd(strPwd);
					userShare.SaveLevel(loginLevel);
					userShare.SaveFlag(true);
					userShare.SaveAllPoint(loginPorint);
					this.finish();
				} else {
					showToast(loginMsg);
				}
				break;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
