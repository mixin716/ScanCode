package com.example.scancode;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.example.scancode.utils.AnimUtil;

public class LoadingActivity extends Activity {

	private int width,height;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		handler.sendEmptyMessageDelayed(0, 2000);
		getWindowHW();
	}
	
	/**
	 * 获得手机分辨率
	 */
	public void getWindowHW() {
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		width = dm.widthPixels;
		height = dm.heightPixels;
		SharedPreferences sp = this.getSharedPreferences("common", 0);
		sp.edit().putInt("height", height).commit();
		sp.edit().putInt("width", width).commit();
		Log.e("", width + "--" + height);
	}


	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			Intent intent = new Intent(LoadingActivity.this,HomeTabActivity.class);
			startActivity(intent);
			AnimUtil.pushLeftInAndOut(LoadingActivity.this);
			LoadingActivity.this.finish();
		};
	};
}
