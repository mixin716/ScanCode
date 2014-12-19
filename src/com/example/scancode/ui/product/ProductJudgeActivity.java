package com.example.scancode.ui.product;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.scancode.BaseActivity;
import com.example.scancode.R;
import com.example.scancode.utils.AnimUtil;
import com.example.scancode.utils.KeyBoard;

/** 产品评价*/
public class ProductJudgeActivity extends BaseActivity{

	private EditText et;
	private Button btn;
	private String strSearch;
	private Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentViewRes(R.layout.activity_product_judge_layout);
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
		titleText.setText("产品评价");
	}

	@Override
	protected void setViews() {
		// TODO Auto-generated method stub
		et = (EditText) findViewById(R.id.activity_product_judge_et);
		btn = (Button) findViewById(R.id.activity_product_judge_btn_right);
		btn.setOnClickListener(this);
	}
	
	@Override
	protected void doClickAction(int viewId) {
		// TODO Auto-generated method stub
		super.doClickAction(viewId);
		switch (viewId) {
		case R.id.activity_product_judge_btn_right:
			KeyBoard.demissKeyBoard(getApplicationContext(), et);
			strSearch = et.getText().toString().trim();
			if(TextUtils.isEmpty(strSearch)){
				showToast("请输入搜索内容");
			}else{
				intent = new Intent(this,ProductListActivity.class);
				startActivity(intent);
				AnimUtil.pushLeftInAndOut(this);
			}
			break;

		default:
			break;
		}
	}

}
