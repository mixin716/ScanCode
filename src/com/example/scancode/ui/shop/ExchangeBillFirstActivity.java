package com.example.scancode.ui.shop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.scancode.BaseActivity;
import com.example.scancode.R;
import com.example.scancode.utils.AnimUtil;

/** 兑换第一步 */
public class ExchangeBillFirstActivity extends BaseActivity {

	private Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentViewRes(R.layout.activity_exchage_bill_first_layout);
		setViews();
	}

	@Override
	protected void initVariable() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setTitleViews() {
		// TODO Auto-generated method stub
		titleText.setText("兑换充值");
	}

	@Override
	protected void setViews() {
		// TODO Auto-generated method stub
		btn = (Button) findViewById(R.id.activity_exchange_first_btn);
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
		case R.id.activity_exchange_first_btn:
			Intent intent = new Intent(this, MakeSureExchangeActivity.class);
			startActivity(intent);
			AnimUtil.pushLeftInAndOut(this);
			break;
		}
	}
}
