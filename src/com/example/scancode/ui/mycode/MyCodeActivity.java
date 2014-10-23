package com.example.scancode.ui.mycode;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.example.scancode.BaseActivity;
import com.example.scancode.R;
import com.example.scancode.common.UserSharedData;

public class MyCodeActivity extends BaseActivity {

	private TextView tvMyCode;//我的码乐
	private UserSharedData userShare;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentViewRes(R.layout.activity_my_code_layout);
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
		titleText.setText("我的码乐");
	}

	@Override
	protected void setViews() {
		// TODO Auto-generated method stub
		tvMyCode = (TextView) findViewById(R.id.activity_my_code_tv_codes);
		Log.e("", userShare.GetAllPoint()+"");
		if(TextUtils.isEmpty(userShare.GetAllPoint()) || "0.0".equals(userShare.GetAllPoint())){
			tvMyCode.setText(String.format(this.getResources().getString(R.string.my_code_number), "0.0"));
		}else{
			tvMyCode.setText(String.format(this.getResources().getString(R.string.my_code_number), userShare.GetAllPoint()));
		}
	}

}
