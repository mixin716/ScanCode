package com.example.scancode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.scancode.utils.AnimUtil;

public class LoadingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		handler.sendEmptyMessageDelayed(0, 2000);
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
