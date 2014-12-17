package com.example.scancode.ui.mycode;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.scancode.BaseActivity;
import com.example.scancode.R;
import com.example.scancode.adapter.mycode.ExchangeHistoryAdapter;
import com.example.scancode.bean.mycode.ExchangeHistoryBean;
import com.example.scancode.common.UserSharedData;
import com.example.scancode.utils.AnimUtil;

public class ExchangeHistoryActivity extends BaseActivity {

	private ListView lv;
	private ExchangeHistoryAdapter adapter;
	private List<ExchangeHistoryBean> list;
	private UserSharedData userShare;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentViewRes(R.layout.activity_exchange_history_layout);
		setViews();
	}

	@Override
	protected void initVariable() {
		// TODO Auto-generated method stub
		userShare = UserSharedData.getInstance(getApplicationContext());
		list = new ArrayList<ExchangeHistoryBean>();
		for (int i = 0; i < 10; i++) {
			ExchangeHistoryBean bean = new ExchangeHistoryBean();
			bean.setName("10元手机充值");
			bean.setValue("消耗5个码乐盾");
			bean.setTime("2014.13.11 11:21");
			list.add(bean);
		}
		adapter = new ExchangeHistoryAdapter(getApplicationContext(), list);
	}

	@Override
	protected void setTitleViews() {
		// TODO Auto-generated method stub
		titleIvRight.setVisibility(View.INVISIBLE);
		titleText.setText("兑换历史记录");
	}

	@Override
	protected void setViews() {
		// TODO Auto-generated method stub
		lv = (ListView) findViewById(R.id.activity_exchange_history_lv);
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
