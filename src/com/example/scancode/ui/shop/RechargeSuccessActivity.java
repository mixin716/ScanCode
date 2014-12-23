package com.example.scancode.ui.shop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.scancode.BaseActivity;
import com.example.scancode.R;
import com.example.scancode.utils.AnimUtil;

/** 充值成功界面 */
public class RechargeSuccessActivity extends BaseActivity {

	private TextView tv;
	private String strPhone, strMoney;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentViewRes(R.layout.activity_recharge_success_layout);
		setViews();
	}

	@Override
	protected void initVariable() {
		// TODO Auto-generated method stub
		strPhone = this.getIntent().getStringExtra("phone");
		strMoney = this.getIntent().getStringExtra("money");
	}

	@Override
	protected void setTitleViews() {
		// TODO Auto-generated method stub
		titleText.setText("充值提示");
	}

	@Override
	protected void setViews() {
		// TODO Auto-generated method stub
		tv = (TextView) findViewById(R.id.activity_recharge_success_tv);
		tv.setText("正在向手机"+strPhone+"内充值"+strMoney+"元，7个工作日内会充到，请及时查询。");
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
