package com.example.scancode.ui.scancode;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.scancode.BaseActivity;
import com.example.scancode.R;
import com.example.scancode.utils.AnimUtil;

/** 验证扫描结果 */
public class CheckCodeActivity extends BaseActivity {

	private LinearLayout llID;
	private TextView tvID;
	private EditText etID, etSecurity;
	private Button btChcek;
	private int intoFlag;// 1 扫码 2手动输入
	private String strID;// 传过来的ID

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentViewRes(R.layout.activity_check_code_layout);
		setViews();
	}

	@Override
	protected void initVariable() {
		// TODO Auto-generated method stub
		intoFlag = this.getIntent().getIntExtra("intoFlag", 1);
		strID = this.getIntent().getStringExtra("scanID");
	}

	@Override
	protected void setTitleViews() {
		// TODO Auto-generated method stub
		titleText.setText("验证");
	}

	@Override
	protected void setViews() {
		// TODO Auto-generated method stub
		llID = (LinearLayout) findViewById(R.id.activity_check_code_ll_id);
		tvID = (TextView) findViewById(R.id.activity_check_code_tv_id);
		etID = (EditText) findViewById(R.id.activity_check_code_et_id);
		etSecurity = (EditText) findViewById(R.id.activity_check_code_et_security);
		btChcek = (Button) findViewById(R.id.activity_check_code_bt_check);
		btChcek.setOnClickListener(this);

		if (intoFlag == 1) {
			llID.setVisibility(View.VISIBLE);
			etID.setVisibility(View.GONE);
			tvID.setText(strID);
		} else {
			llID.setVisibility(View.GONE);
			etID.setVisibility(View.VISIBLE);
		}
	}

	@Override
	protected void doClickAction(int viewId) {
		// TODO Auto-generated method stub
		super.doClickAction(viewId);
		switch (viewId) {
		case R.id.title_iv_left:
			this.finish();
			AnimUtil.pushRightInAndOut(CheckCodeActivity.this);
			break;
		case R.id.activity_check_code_bt_check:

			break;
		}
	}

}
