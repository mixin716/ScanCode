package com.example.scancode.ui.mycode;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.scancode.BaseActivity;
import com.example.scancode.R;
import com.example.scancode.utils.AnimUtil;
import com.example.scancode.utils.KeyBoard;

public class ChangetPwdActivity extends BaseActivity{

	private EditText etOld,etNew,etSecond;
	private Button btn;
	private String strOld,strNew,strSecond;
	private Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentViewRes(R.layout.activity_change_pwd_layout);
		setViews();
	}
	
	@Override
	protected void initVariable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setTitleViews() {
		// TODO Auto-generated method stub
		titleIvRight.setVisibility(View.INVISIBLE);
		titleText.setText("修改密码");
	}

	@Override
	protected void setViews() {
		// TODO Auto-generated method stub
		etOld = (EditText) findViewById(R.id.activity_change_pwd_et_oldpwd);
		etNew = (EditText) findViewById(R.id.activity_change_pwd_et_new);
		etSecond = (EditText) findViewById(R.id.activity_change_pwd_et_second);
		btn = (Button) findViewById(R.id.activity_change_pwd_btn_sure);
		btn.setOnClickListener(this);
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
		case R.id.activity_change_pwd_btn_sure:
			KeyBoard.demissKeyBoard(getApplicationContext(), etOld);
			strOld = etOld.getText().toString().trim();
			strNew = etNew.getText().toString().trim();
			strSecond = etSecond.getText().toString().trim();
			if(TextUtils.isEmpty(strOld)){
				showToast("请输入原密码");
			}else if(TextUtils.isEmpty(strNew)){
				showToast("请输入新密码");
			}else if(TextUtils.isEmpty(strSecond)){
				showToast("请输入确认密码");
			}else if(!strNew.equals(strSecond)){
				showToast("两次密码输入不一致");
			}else{
				intent = new Intent(this,ChangePwdSuccessActivity.class);
				startActivity(intent);
				AnimUtil.pushLeftInAndOut(this);
				this.finish();
			}
			break;
		}
	}
	
	
	
}
