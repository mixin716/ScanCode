package com.example.scancode.ui.mycode;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.scancode.BaseActivity;
import com.example.scancode.R;
import com.example.scancode.adapter.mycode.ScoreHistoryAdapter;
import com.example.scancode.bean.mycode.ScoreHistoryBean;
import com.example.scancode.utils.AnimUtil;
import com.jky.struct2.http.core.AjaxParams;

public class ScoreHistoryActivity extends BaseActivity {

	private ListView lv;
	private ScoreHistoryAdapter adapter;
	private List<ScoreHistoryBean> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentViewRes(R.layout.activity_score_history_layout);
		setViews();
	}

	@Override
	protected void initVariable() {
		// TODO Auto-generated method stub
		list = new ArrayList<ScoreHistoryBean>();
		for (int i = 0; i < 10; i++) {
			ScoreHistoryBean bean = new ScoreHistoryBean();
			bean.setName("最新版海飞丝500ml");
			bean.setTime("2011.1.1 11：11");
			bean.setValue("获取5个码乐盾");
			list.add(bean);
		}
		adapter = new ScoreHistoryAdapter(getApplicationContext(), list);
	}

	@Override
	protected void setTitleViews() {
		// TODO Auto-generated method stub
		titleIvRight.setVisibility(View.INVISIBLE);
		titleText.setText("积分历史记录");
	}

	@Override
	protected void setViews() {
		// TODO Auto-generated method stub
		lv = (ListView) findViewById(R.id.activity_score_history_lv);
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
		}
	}

	public void requestData() {
		AjaxParams params = new AjaxParams();
		params.put("", "");
		params.put("", "");
		params.put("", "");
		params.put("", "");
		httpRequest.get(null, params, callBack, 0);
	}

	@Override
	protected void handleJson(int reqeustCode, String jsonString) {
		// TODO Auto-generated method stub
		super.handleJson(reqeustCode, jsonString);
	}

}
