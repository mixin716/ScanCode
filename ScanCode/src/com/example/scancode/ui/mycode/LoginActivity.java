package com.example.scancode.ui.mycode;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.scancode.BaseActivity;
import com.example.scancode.R;
import com.example.scancode.utils.AnimUtil;
import com.example.scancode.utils.KeyBoard;

public class LoginActivity extends BaseActivity {

	private EditText etPhone, etPwd;
	private Button btLogin;
	private TextView tvFind, tvRegister;
	private CheckBox cbRemember;
	private String strPhone, strPwd;

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

	}

	@Override
	protected void setTitleViews() {
		// TODO Auto-generated method stub
		titleText.setText("µÇÂ¼");
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
		case R.id.activity_login_tv_find:// ÕÒ»ØÃÜÂë

			break;
		case R.id.activity_login_tv_register:// ×¢²á
			intent = new Intent(this, RegisterActivity.class);
			startActivity(intent);
			AnimUtil.pushLeftInAndOut(this);
			break;
		case R.id.activity_login_bt_login:// µÇÂ¼
			KeyBoard.demissKeyBoard(getApplicationContext(), etPhone);
			KeyBoard.demissKeyBoard(getApplicationContext(), etPwd);
			strPhone = etPhone.getText().toString().trim();
			strPwd = etPwd.getText().toString().trim();

			break;
		}
	}

}
