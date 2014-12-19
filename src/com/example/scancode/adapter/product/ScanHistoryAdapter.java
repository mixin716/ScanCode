package com.example.scancode.adapter.product;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.scancode.R;
import com.example.scancode.bean.product.ScanHistoryBean;
import com.example.scancode.utils.ListViewPassValuetoActivityListener;

public class ScanHistoryAdapter extends BaseAdapter {

	private Context context;
	private List<ScanHistoryBean> list;
	private ListViewPassValuetoActivityListener listener;

	public ScanHistoryAdapter(Context context,List<ScanHistoryBean> list) {
		this.context = context;
		this.list = list;
	}
	
	public void setListener(ListViewPassValuetoActivityListener listener){
		this.listener = listener;
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.adapter_scan_history_layout, null);
			holder.tvName = (TextView) convertView.findViewById(R.id.adapter_scan_history_tv_name);
			holder.tvValue = (TextView) convertView.findViewById(R.id.adapter_scan_history_tv_value);
			holder.tvTime = (TextView) convertView.findViewById(R.id.adapter_scan_history_tv_time);
			holder.tvJudge = (TextView) convertView.findViewById(R.id.adapter_scan_history_tv_judge);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.tvName.setText(list.get(position).getName());
		holder.tvValue.setText(list.get(position).getValue());
		holder.tvTime.setText(list.get(position).getTime());
		holder.tvJudge.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				listener.doPassActionListener(null, position, 0, null);
			}
		});
		return convertView;
	}

	class ViewHolder {
		TextView tvName, tvValue, tvTime,tvJudge;
	}

}
