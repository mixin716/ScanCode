package com.example.scancode.ui.shop;

import android.os.Bundle;
import android.widget.TextView;

import com.example.scancode.BaseActivity;
import com.example.scancode.R;
import com.example.scancode.common.UserSharedData;
import com.example.scancode.utils.AnimUtil;

/** 余额不足 */
public class NoEnoughActivity extends BaseActivity {

	private TextView tv;
	private UserSharedData userShare;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentViewRes(R.layout.activity_no_enough_layout);
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
		titleText.setText("余额不足");
	}

	@Override
	protected void setViews() {
		// TODO Auto-generated method stub
		tv = (TextView) findViewById(R.id.activity_no_enough_tv);
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
		}
	}

}
