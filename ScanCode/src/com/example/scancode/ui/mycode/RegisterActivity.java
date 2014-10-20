package com.example.scancode.ui.mycode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.example.scancode.BaseActivity;
import com.example.scancode.R;
import com.example.scancode.common.Urls;
import com.example.scancode.utils.AnimUtil;
import com.example.scancode.utils.KeyBoard;
import com.jky.struct2.http.core.AjaxParams;
import com.jky.struct2.http.entityhandle.HttpResult;

public class RegisterActivity extends BaseActivity implements
		OnCheckedChangeListener {

	private EditText etPhone, etCheck, etPwd;
	private Button btCheck, btRegister;
	private RadioGroup rg;
	private RadioButton rbMan, rbWoman;
	private String strPhone, strCheck, strPwd;
	private int sex;// 1:男，2：女

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentViewRes(R.layout.activity_rigister_layout);
		setViews();
	}

	@Override
	protected void initVariable() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setTitleViews() {
		// TODO Auto-generated method stub
		titleText.setText("注册");
	}

	@Override
	protected void setViews() {
		// TODO Auto-generated method stub
		etPhone = (EditText) findViewById(R.id.activity_register_et_phone);
		etCheck = (EditText) findViewById(R.id.activity_register_et_checkCode);
		etPwd = (EditText) findViewById(R.id.activity_register_et_pwd);
		btCheck = (Button) findViewById(R.id.activity_register_bt_checkCode);
		btRegister = (Button) findViewById(R.id.activity_login_bt_register);
		rg = (RadioGroup) findViewById(R.id.activity_register_rb_sex);
		rbMan = (RadioButton) findViewById(R.id.activity_register_rb_man);
		rbWoman = (RadioButton) findViewById(R.id.activity_register_rb_woman);

		btCheck.setOnClickListener(this);
		btRegister.setOnClickListener(this);
		rg.setOnCheckedChangeListener(this);
	}

	@Override
	protected void doClickAction(int viewId) {
		// TODO Auto-generated method stub
		super.doClickAction(viewId);
		switch (viewId) {
		case R.id.title_iv_left:
			this.finish();
			AnimUtil.pushRightInAndOut(this);
			break;
		case R.id.activity_register_bt_checkCode:
			KeyBoard.demissKeyBoard(getApplicationContext(), etPhone);
			strPhone = etPhone.getText().toString().trim();
			if (TextUtils.isEmpty(strPhone)) {
				showToast("请输入手机号");
			} else if (strPhone.length() != 11) {
				showToast("请输入正确的手机号");
			} else {
				getCheckCode();
			}
			break;
		case R.id.activity_login_bt_register:
			KeyBoard.demissKeyBoard(getApplicationContext(), etPhone);
			strPhone = etPhone.getText().toString().trim();
			strCheck = etCheck.getText().toString().trim();
			strPwd = etPwd.getText().toString().trim();
			if (TextUtils.isEmpty(strPhone)) {
				showToast("请输入手机号");
			} else if (strPhone.length() != 11) {
				showToast("请输入正确的手机号");
			} else if (TextUtils.isEmpty(strCheck)) {
				showToast("请输入验证码");
			} else if (TextUtils.isEmpty(strPwd)) {
				showToast("请输入密码");
			} else if (strPwd.length() < 6) {
				showToast("请输入正确长度的密码");
			} else {

			}

			break;
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		if (checkedId == R.id.activity_register_rb_man) {
			if (rbMan.isChecked()) {
				sex = 1;
			}
		} else if (checkedId == R.id.activity_register_rb_woman) {
			if (rbWoman.isChecked()) {
				sex = 2;
			}
		}
	}

	private void getCheckCode() {// 获取验证码
		showLoading();
		AjaxParams params = new AjaxParams();
		params.put("usrno", strPhone);
		httpRequest.get(Urls.REGISTER_GET_CODE, params, callBack, 0);
	}

	private void requestRegister() {
		showLoading();
		AjaxParams params = new AjaxParams();
		params.put("usrno", strPhone);
		params.put("pass", strPwd);
		params.put("pvn", strCheck);
		params.put("sex", sex + "");
		httpRequest.get(Urls.LOGIN, params, callBack, 1);
	}

	@Override
	protected void handleResult(int requestCode, HttpResult result) {
		// TODO Auto-generated method stub
		super.handleResult(requestCode, result);
		try {
			Log.e("", result.baseJson + "");
			JSONArray baseArray = new JSONArray(result.baseJson);
			JSONObject baseObj = baseArray.getJSONObject(0);
			switch (requestCode) {
			case 0:
				break;

			default:
				break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
