package com.example.scancode.adapter.product;

import com.example.scancode.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/** 产品评价列表adapter*/
public class ProductListAdapter extends BaseAdapter{

	private Context context;
	
	public ProductListAdapter(Context context){
		this.context= context;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView == null){
			convertView = View.inflate(context, R.layout.adapter_product_list_layout, null);
		}
		return convertView;
	}

}
