package com.example.scancode.ui.mycode;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.scancode.BaseActivity;
import com.example.scancode.R;
import com.example.scancode.adapter.mycode.LotteryHistoryAdapter;
import com.example.scancode.bean.mycode.LotteryHistoryBean;
import com.example.scancode.utils.AnimUtil;

public class LotteryHistoryActivity extends BaseActivity{

	private ListView lv;
	private LotteryHistoryAdapter adapter;
	private List<LotteryHistoryBean> list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentViewRes(R.layout.activity_lottery_history_layout);
		setViews();
	}
	
	@Override
	protected void initVariable() {
		// TODO Auto-generated method stub
		list = new ArrayList<LotteryHistoryBean>();
		for (int i = 0; i < 10; i++) {
			LotteryHistoryBean bean = new LotteryHistoryBean();
			bean.setName("奖品名称");
			bean.setFlag(i%2);
			bean.setTime("2014.12.12 11:33");
			list.add(bean);
		}
		adapter = new LotteryHistoryAdapter(getApplicationContext(), list);
	}

	@Override
	protected void setTitleViews() {
		// TODO Auto-generated method stub
		titleIvRight.setVisibility(View.INVISIBLE);
		titleText.setText("抽奖历史记录");
	}

	@Override
	protected void setViews() {
		// TODO Auto-generated method stub
		lv = (ListView) findViewById(R.id.activity_lottery_history_lv);
		lv.setAdapter(adapter);
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
