package com.example.scancode.ui.mycode;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.scancode.BaseActivity;
import com.example.scancode.R;
import com.example.scancode.common.UserSharedData;
import com.example.scancode.utils.AnimUtil;

public class MyCodeActivity extends BaseActivity {

	private LinearLayout llScore, llExchange, llLottery, llLook, llChage;
	private UserSharedData userShare;
	private Intent intent;

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
		llScore = (LinearLayout) findViewById(R.id.activity_my_code_ll_score);
		llExchange = (LinearLayout) findViewById(R.id.activity_my_code_ll_exchange);
		llLottery = (LinearLayout) findViewById(R.id.activity_my_code_ll_lottery);
		llLook = (LinearLayout) findViewById(R.id.activity_my_code_ll_look);
		llChage = (LinearLayout) findViewById(R.id.activity_my_code_ll_change);

		llScore.setOnClickListener(this);
		llExchange.setOnClickListener(this);
		llLottery.setOnClickListener(this);
		llLook.setOnClickListener(this);
		llChage.setOnClickListener(this);
	}

	@Override
	protected void doClickAction(int viewId) {
		// TODO Auto-generated method stub
		super.doClickAction(viewId);
		switch (viewId) {
		case R.id.activity_my_code_ll_score:// 积分历史记录
			intent = new Intent(this, ScoreHistoryActivity.class);
			startActivity(intent);
			AnimUtil.pushLeftInAndOut(this);
			break;
		case R.id.activity_my_code_ll_exchange:// 兑换历史记录
			intent = new Intent(this, ExchangeHistoryActivity.class);
			startActivity(intent);
			AnimUtil.pushLeftInAndOut(this);
			break;
		case R.id.activity_my_code_ll_lottery:// 抽奖历史记录
			intent = new Intent(this, LotteryHistoryActivity.class);
			startActivity(intent);
			AnimUtil.pushLeftInAndOut(this);

			break;
		case R.id.activity_my_code_ll_look:// 查看抽奖机会

			break;
		case R.id.activity_my_code_ll_change:// 修改密码
			break;
		}
	}

}
