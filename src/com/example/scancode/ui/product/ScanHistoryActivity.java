package com.example.scancode.ui.product;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.scancode.BaseActivity;
import com.example.scancode.R;
import com.example.scancode.adapter.product.ScanHistoryAdapter;
import com.example.scancode.bean.product.ScanHistoryBean;
import com.example.scancode.utils.AnimUtil;
import com.example.scancode.utils.ListViewPassValuetoActivityListener;

/** 扫码历史记录 */
public class ScanHistoryActivity extends BaseActivity implements ListViewPassValuetoActivityListener{

	private ListView lv;
	private ScanHistoryAdapter adapter;
	private List<ScanHistoryBean> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentViewRes(R.layout.activity_scan_history_layout);
		setViews();
	}

	@Override
	protected void initVariable() {
		// TODO Auto-generated method stub
		list = new ArrayList<ScanHistoryBean>();
		for (int i = 0; i < 10; i++) {
			ScanHistoryBean bean = new ScanHistoryBean();
			bean.setName("海飞丝真好");
			bean.setValue("获得5个码乐盾");
			bean.setTime("2014.13.32 24:01");
			list.add(bean);
		}
		adapter = new ScanHistoryAdapter(getApplicationContext(),list);
		adapter.setListener(this);
	}

	@Override
	protected void setTitleViews() {
		// TODO Auto-generated method stub
		titleText.setText("扫描历史记录");
	}

	@Override
	protected void setViews() {
		// TODO Auto-generated method stub
		lv = (ListView) findViewById(R.id.activity_scan_history_lv);
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

	@Override
	public void doPassActionListener(Object obj, int org1, int org2, String str) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this,JudgeProductActivity.class);
		startActivity(intent);
		AnimUtil.pushLeftInAndOut(this);
	}

}
