package com.example.scancode.ui.shop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.scancode.BaseActivity;
import com.example.scancode.R;
import com.example.scancode.utils.AnimUtil;

/** 确认兑换信息 */
public class MakeSureExchangeActivity extends BaseActivity {

	private Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentViewRes(R.layout.activity_make_sure_exchage_activity);
		setViews();
	}

	@Override
	protected void initVariable() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setTitleViews() {
		// TODO Auto-generated method stub
		titleText.setText("确认兑换信息");
	}

	@Override
	protected void setViews() {
		// TODO Auto-generated method stub
		btn = (Button) findViewById(R.id.activity_mage_sure_btn);
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
		case R.id.activity_mage_sure_btn:
			Intent intent = new Intent(this, NoEnoughActivity.class);
			startActivity(intent);
			AnimUtil.pushLeftInAndOut(this);
			break;
		}
	}
}
