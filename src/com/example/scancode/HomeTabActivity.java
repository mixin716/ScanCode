package com.example.scancode;

import java.lang.reflect.Field;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.scancode.common.UserSharedData;
import com.example.scancode.ui.mycode.MyCodeActivity;
import com.example.scancode.ui.product.ProductJudgeActivity;
import com.example.scancode.ui.scancode.MipcaActivityCapture;

public class HomeTabActivity extends TabActivity implements
		OnCheckedChangeListener, OnClickListener {

	private String TAG = "HomeTabActivity";
	private RadioGroup rg;
	private RadioButton rbPromotion, rbScanCode, rbEvaluation, rbMall,
			rbMyCode;
	private Intent mPromotionIntent, mMallIntent, mScanCodeIntent,
			mEvaluationIntent, mMyCodeIntent, intent;
	private final String TAB_PROMOTION = "TestSugarActivity";
	private final String TAB_MALL = "ManageSugarActivity";
	private final String TAB_SCANCODE = "DoctorMainActivity";
	private final String TAB_EVALUATION = "ManageMedicineActivity";
	private final String TAB_MYCODE = "MyCodeActivity";
	private TabHost tabHost;
	private int intoSelect = 5;//
	public UserSharedData userShare;
	/** 用于判断退出 */
	private boolean isExit = false;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_tab_layout);
		this.tabHost = getTabHost();
		userShare = UserSharedData.getInstance(getApplicationContext());
		// if (!userShare.GetFlag()) {
		// Toast.makeText(getApplicationContext(), "请先登录", 0).show();
		// Intent intent = new Intent(this, LoginActivity.class);
		// startActivity(intent);
		// }
		intoSelect = this.getIntent().getIntExtra("into_tab", 1);
		InitViews();
		InitIntent();
		setupIntent();
		setCurrentActivity(intoSelect);
	}

	private void InitViews() {
		rg = (RadioGroup) findViewById(R.id.buttom_rgroup);
		rbPromotion = (RadioButton) findViewById(R.id.activity_home_rb_promotion);
		rbMall = (RadioButton) findViewById(R.id.activity_home_rb_mall);
		rbScanCode = (RadioButton) findViewById(R.id.activity_home_rb_scanCode);
		rbEvaluation = (RadioButton) findViewById(R.id.activity_home_rb_evaluation);
		rbMyCode = (RadioButton) findViewById(R.id.activity_home_rb_myCode);

		rbPromotion.setChecked(true);
		rbPromotion.setOnClickListener(this);
		rbMall.setOnClickListener(this);
		rbScanCode.setOnClickListener(this);
		rbEvaluation.setOnClickListener(this);
		rbMyCode.setOnClickListener(this);
	}

	private void InitIntent() {
		mPromotionIntent = new Intent(HomeTabActivity.this,
				MyCodeActivity.class);
		mMallIntent = new Intent(HomeTabActivity.this, MyCodeActivity.class);
		mScanCodeIntent = new Intent(HomeTabActivity.this,
				MipcaActivityCapture.class);
		mEvaluationIntent = new Intent(HomeTabActivity.this,
				ProductJudgeActivity.class);
		mMyCodeIntent = new Intent(HomeTabActivity.this, MyCodeActivity.class);
	}

	private void setupIntent() {
		TabHost localTabHost = this.tabHost;
		try {
			Field current = tabHost.getClass().getDeclaredField("mCurrentTab");
			current.setAccessible(true);
			current.setInt(tabHost, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		localTabHost.addTab(buildTabSpec(TAB_PROMOTION, TAB_PROMOTION,
				mPromotionIntent));
		localTabHost.addTab(buildTabSpec(TAB_MALL, TAB_MALL, mMallIntent));
		localTabHost.addTab(buildTabSpec(TAB_SCANCODE, TAB_SCANCODE,
				mScanCodeIntent));
		localTabHost.addTab(buildTabSpec(TAB_EVALUATION, TAB_EVALUATION,
				mEvaluationIntent));
		localTabHost
				.addTab(buildTabSpec(TAB_MYCODE, TAB_MYCODE, mMyCodeIntent));

		try {
			Field current = tabHost.getClass().getDeclaredField("mCurrentTab");
			current.setAccessible(true);
			current.set(tabHost, -1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private TabHost.TabSpec buildTabSpec(String tag, String resLabel,
			final Intent content) {
		return this.tabHost.newTabSpec(tag).setIndicator(resLabel)
				.setContent(content);
	}

	private void setCurrentActivity(int index) {
		switch (index) {
		case 1:
			onCheckedChanged(rg, R.id.activity_home_rb_promotion);
			break;
		case 2:
			onCheckedChanged(rg, R.id.activity_home_rb_mall);
			break;
		case 3:
			onCheckedChanged(rg, R.id.activity_home_rb_scanCode);
			break;
		case 4:
			onCheckedChanged(rg, R.id.activity_home_rb_evaluation);
			break;
		case 5:
			onCheckedChanged(rg, R.id.activity_home_rb_myCode);
			break;
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		// rbPromotion
		// .setBackgroundResource(R.drawable.ic_home_tab_promotion_normal);
		// rbMall.setBackgroundResource(R.drawable.ic_home_tab_mall_normal);
		rbScanCode
				.setBackgroundResource(R.drawable.ic_home_tab_scan_code_normal);
		rbEvaluation
				.setBackgroundResource(R.drawable.ic_home_tab_evaluation_normal);
		rbMyCode.setBackgroundResource(R.drawable.ic_home_tab_my_code_normal);
		switch (arg1) {
		case R.id.activity_home_rb_promotion:
			// this.tabHost.setCurrentTabByTag(TAB_PROMOTION);
			// rbPromotion
			// .setBackgroundResource(R.drawable.ic_home_tab_promotion_press);
			break;
		case R.id.activity_home_rb_mall:
			// this.tabHost.setCurrentTabByTag(TAB_MALL);
			// rbMall.setBackgroundResource(R.drawable.ic_home_tab_mall_press);
			break;
		case R.id.activity_home_rb_scanCode:
			this.tabHost.setCurrentTabByTag(TAB_SCANCODE);
			rbScanCode
					.setBackgroundResource(R.drawable.ic_home_tab_scan_code_press);
			break;
		case R.id.activity_home_rb_evaluation:

			 this.tabHost.setCurrentTabByTag(TAB_EVALUATION);
			 rbEvaluation
			 .setBackgroundResource(R.drawable.ic_home_tab_evaluation_press);
			break;

		case R.id.activity_home_rb_myCode:
			this.tabHost.setCurrentTabByTag(TAB_MYCODE);
			rbMyCode.setBackgroundResource(R.drawable.ic_home_tab_my_code_press);
			break;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		onCheckedChanged(rg, v.getId());
	}

	// @Override
	// public boolean dispatchKeyEvent(KeyEvent event) {
	// // TODO Auto-generated method stub
	// Log.e(TAG, "dispatchKeyEvent");
	// if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
	// // ExitProgram();
	// return true;
	// }
	// return true;
	// }

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		Log.e(TAG, "onKeyDown");
		return true;
	}

	public void ExitProgram() {
		if (!isExit) {
			isExit = true;
			Toast.makeText(HomeTabActivity.this, "再按一次\"退出\"程序", 0).show();
			exitHandler.sendEmptyMessageDelayed(0, 2000);
		} else {
			this.finish();
		}
	}

	public Handler exitHandler = new Handler() {
		public void handleMessage(Message msg) {
			isExit = false;
		};
	};

}
