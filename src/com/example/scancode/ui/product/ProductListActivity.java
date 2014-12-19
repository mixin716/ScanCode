package com.example.scancode.ui.product;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.scancode.BaseActivity;
import com.example.scancode.R;
import com.example.scancode.adapter.product.ProductListAdapter;

public class ProductListActivity extends BaseActivity{

	private ListView lv;
	private View headView;
	private ProductListAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentViewRes(R.layout.activity_product_list_layout);
		setViews();
	}
	
	@Override
	protected void initVariable() {
		// TODO Auto-generated method stub
		adapter = new ProductListAdapter(getApplicationContext());
	}

	@Override
	protected void setTitleViews() {
		// TODO Auto-generated method stub
		titleText.setText("评价列表");
	}

	@Override
	protected void setViews() {
		// TODO Auto-generated method stub
		headView = View.inflate(getApplicationContext(), R.layout.view_product_list_head_layout, null);
		lv = (ListView) findViewById(R.id.activity_product_list_lv);
		lv.addHeaderView(headView);
		lv.setAdapter(adapter);
	}

}
