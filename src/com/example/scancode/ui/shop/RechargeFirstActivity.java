package com.example.scancode.ui.shop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.scancode.BaseActivity;
import com.example.scancode.R;
import com.example.scancode.utils.AnimUtil;

/** 充值第一步，选择价格 */
public class RechargeFirstActivity extends BaseActivity {

	private Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentViewRes(R.layout.activity_recharge_first_layout);
		setViews();
	}

	@Override
	protected void initVariable() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setTitleViews() {
		// TODO Auto-generated method stub
		titleText.setText("充值确认");
	}

	@Override
	protected void setViews() {
		// TODO Auto-generated method stub
		btn = (Button) findViewById(R.id.activity_recharge_first_btn);
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
		case R.id.activity_recharge_first_btn:
			Intent intent = new Intent(this, RechargeSuccessActivity.class);
			intent.putExtra("phone", "15012341234");
			intent.putExtra("money", "20");
			startActivity(intent);
			AnimUtil.pushLeftInAndOut(this);
			break;
		}
	}

}
