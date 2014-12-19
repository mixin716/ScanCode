package com.example.scancode.ui.product;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.scancode.BaseActivity;
import com.example.scancode.R;
import com.example.scancode.utils.AnimUtil;
import com.example.scancode.utils.KeyBoard;

/** 评价产品activity */
public class JudgeProductActivity extends BaseActivity {

	private RelativeLayout rlSelect;
	private TextView tvStartNum;
	private EditText etSuggest;
	private Button btnSubmit;
	private String comment;// 评论内容
	private PopupWindow popWindow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentViewRes(R.layout.activity_judge_product_layout);
		setViews();
	}

	@Override
	protected void initVariable() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setTitleViews() {
		// TODO Auto-generated method stub
		titleText.setText("评论产品");
	}

	@Override
	protected void setViews() {
		// TODO Auto-generated method stub
		rlSelect = (RelativeLayout) findViewById(R.id.activity_judge_product_rl_select);
		etSuggest = (EditText) findViewById(R.id.activity_judge_product_et_suggestion);
		btnSubmit = (Button) findViewById(R.id.activity_judge_product_btn_submit);
		tvStartNum = (TextView) findViewById(R.id.activity_judge_product_tv_starts);

		rlSelect.setOnClickListener(this);
		btnSubmit.setOnClickListener(this);
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
		case R.id.activity_judge_product_rl_select:// 选择星级
			initPopWindowView();
			break;
		case R.id.activity_judge_product_btn_submit:
			KeyBoard.demissKeyBoard(getApplicationContext(), etSuggest);
			comment = etSuggest.getText().toString().trim();
			if(TextUtils.isEmpty(comment)){
				showToast("请输入评论");
			}else{
				Intent intent = new Intent(this,JudgeSuccessActivity.class);
				startActivity(intent);
				AnimUtil.pushLeftInAndOut(this);
			}
			break;
		case R.id.pop_select_start_ll_five:
			popWindow.dismiss();
			tvStartNum.setText("评价等级:5星");
			break;
		case R.id.pop_select_start_ll_four:
			popWindow.dismiss();
			tvStartNum.setText("评价等级:4星");
			break;
		case R.id.pop_select_start_ll_three:
			popWindow.dismiss();
			tvStartNum.setText("评价等级:3星");
			break;
		case R.id.pop_select_start_ll_two:
			popWindow.dismiss();
			tvStartNum.setText("评价等级:2星");
			break;
		case R.id.pop_select_start_ll_one:
			popWindow.dismiss();
			tvStartNum.setText("评价等级:1星");
			break;
		}
	}

	private void initPopWindowView() {
		View contentView = getLayoutInflater().inflate(
				R.layout.pop_judge_product_layout, null, false);
		popWindow = new PopupWindow(contentView, rlSelect.getWidth(),
				LinearLayout.LayoutParams.WRAP_CONTENT);
		popWindow.showAsDropDown(rlSelect);
		contentView.findViewById(R.id.pop_select_start_ll_five)
				.setOnClickListener(this);
		contentView.findViewById(R.id.pop_select_start_ll_four)
				.setOnClickListener(this);
		contentView.findViewById(R.id.pop_select_start_ll_three)
				.setOnClickListener(this);
		contentView.findViewById(R.id.pop_select_start_ll_two)
				.setOnClickListener(this);
		contentView.findViewById(R.id.pop_select_start_ll_one)
				.setOnClickListener(this);
	}

}
