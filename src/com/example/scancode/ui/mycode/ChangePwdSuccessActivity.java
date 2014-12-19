package com.example.scancode.ui.mycode;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.scancode.BaseActivity;
import com.example.scancode.R;
import com.example.scancode.utils.AnimUtil;

public class ChangePwdSuccessActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentViewRes(R.layout.activity_change_pwd_success_layout);
		setViews();
		handler.sendEmptyMessageDelayed(0, 2000);
	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			ChangePwdSuccessActivity.this.finish();
			AnimUtil.pushRightInAndOut(ChangePwdSuccessActivity.this);
		};
	};

	@Override
	protected void initVariable() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setTitleViews() {
		// TODO Auto-generated method stub
		titleIvRight.setVisibility(View.INVISIBLE);
		titleText.setText("密码修改成功");
	}

	@Override
	protected void setViews() {
		// TODO Auto-generated method stub

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

		default:
			break;
		}
	}

}
