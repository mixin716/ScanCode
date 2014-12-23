package com.example.scancode.ui.mycode;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.scancode.BaseActivity;
import com.example.scancode.R;
import com.example.scancode.common.UserSharedData;
import com.example.scancode.ui.product.ScanHistoryActivity;
import com.example.scancode.utils.AnimUtil;
import com.example.scancode.view.PullToRefreshView;
import com.example.scancode.view.PullToRefreshView.OnFooterRefreshListener;

public class MyCodeActivity extends BaseActivity implements
		OnFooterRefreshListener {

	private PullToRefreshView mPullToRefreshView;
	private LinearLayout llScore, llExchange, llLottery, llLook, llChage;
	private LinearLayout llLogin, llNoLogin;
	private Button btnLogin;
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
		mPullToRefreshView = (PullToRefreshView) findViewById(R.id.activity_my_code_refresh_view);
		mPullToRefreshView.dismissFoot();
		mPullToRefreshView.setOnHeaderRefreshListener(this);
		mPullToRefreshView.setOnFooterRefreshListener(this);
		llLogin = (LinearLayout) findViewById(R.id.activity_my_code_ll_login);
		llNoLogin = (LinearLayout) findViewById(R.id.activity_my_code_ll_nologin);
		llScore = (LinearLayout) findViewById(R.id.activity_my_code_ll_score);
		llExchange = (LinearLayout) findViewById(R.id.activity_my_code_ll_exchange);
		llLottery = (LinearLayout) findViewById(R.id.activity_my_code_ll_lottery);
		llLook = (LinearLayout) findViewById(R.id.activity_my_code_ll_look);
		llChage = (LinearLayout) findViewById(R.id.activity_my_code_ll_change);
		btnLogin = (Button) findViewById(R.id.activity_my_code_btn_login);

		llScore.setOnClickListener(this);
		llExchange.setOnClickListener(this);
		llLottery.setOnClickListener(this);
		llLook.setOnClickListener(this);
		llChage.setOnClickListener(this);
		btnLogin.setOnClickListener(this);
		if (userShare.GetFlag()) {
			llLogin.setVisibility(View.VISIBLE);
			llNoLogin.setVisibility(View.GONE);
		} else {
			llNoLogin.setVisibility(View.VISIBLE);
			llLogin.setVisibility(View.GONE);
		}
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
			intent = new Intent(this, ScanHistoryActivity.class);
			startActivity(intent);
			AnimUtil.pushLeftInAndOut(this);

			break;
		case R.id.activity_my_code_ll_change:// 修改密码
			intent = new Intent(this, ChangetPwdActivity.class);
			startActivity(intent);
			AnimUtil.pushLeftInAndOut(this);
			break;
		case R.id.activity_my_code_btn_login:// 登录
			intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
			AnimUtil.pushLeftInAndOut(this);
			break;
		}
	}

	@Override
	public void onFooterRefresh(PullToRefreshView view) {
		// TODO Auto-generated method stub
		super.onFooterRefresh(view);
		mPullToRefreshView.onFooterRefreshComplete();
	}

	@Override
	public void onHeaderRefresh(PullToRefreshView view) {
		// TODO Auto-generated method stub
		super.onHeaderRefresh(view);
		handler.sendEmptyMessageDelayed(0, 3000);
	}

	public Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			mPullToRefreshView.onHeaderRefreshComplete();
		};
	};
}
