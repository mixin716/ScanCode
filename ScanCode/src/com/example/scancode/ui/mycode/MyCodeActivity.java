package com.example.scancode.ui.mycode;

import android.os.Bundle;

import com.example.scancode.BaseActivity;
import com.example.scancode.R;

public class MyCodeActivity extends BaseActivity {

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

	}

	@Override
	protected void setTitleViews() {
		// TODO Auto-generated method stub
		titleText.setText("Œ“µƒ¬Î¿÷");
	}

	@Override
	protected void setViews() {
		// TODO Auto-generated method stub

	}

}
