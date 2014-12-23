package com.example.scancode.ui.shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.scancode.BaseActivity;
import com.example.scancode.R;
import com.example.scancode.utils.AnimUtil;

/** 码乐商城 */
public class CodeShopActivity extends BaseActivity {

	private Button btnChou, btnChong, btnDian;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentViewRes(R.layout.activity_shop_main_layout);
		setViews();
	}

	@Override
	protected void initVariable() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setTitleViews() {
		// TODO Auto-generated method stub
		titleIvLeft.setVisibility(View.INVISIBLE);
	}

	@Override
	protected void setViews() {
		// TODO Auto-generated method stub
		btnChou = (Button) findViewById(R.id.activity_shop_main_btn_choujiang);
		btnChong = (Button) findViewById(R.id.activity_shop_main_btn_chongzhi);
		btnDian = (Button) findViewById(R.id.activity_shop_main_btn_dianzi);

		btnChou.setOnClickListener(this);
		btnChong.setOnClickListener(this);
		btnDian.setOnClickListener(this);
	}

	@Override
	protected void doClickAction(int viewId) {
		// TODO Auto-generated method stub
		super.doClickAction(viewId);
		switch (viewId) {
		case R.id.activity_shop_main_btn_choujiang:

			break;
		case R.id.activity_shop_main_btn_chongzhi:
			intent = new Intent(this, RechargeFirstActivity.class);
			startActivity(intent);
			AnimUtil.pushLeftInAndOut(this);
			break;
		case R.id.activity_shop_main_btn_dianzi:
			intent = new Intent(this, ExchangeBillFirstActivity.class);
			startActivity(intent);
			AnimUtil.pushLeftInAndOut(this);
			break;
		}
	}

}
