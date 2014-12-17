package com.example.scancode.adapter.mycode;

import java.util.List;

import com.example.scancode.R;
import com.example.scancode.bean.mycode.ScoreHistoryBean;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


/** 积分历史记录adapter*/
public class ScoreHistoryAdapter extends BaseAdapter{

	private Context context;
	private List<ScoreHistoryBean> list;
	
	public ScoreHistoryAdapter(Context context,List<ScoreHistoryBean> list){
		this.context = context;
		this.list = list;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
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
		ViewHolder holder;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.adapter_score_history_layout, null);
			holder.tvName = (TextView) convertView.findViewById(R.id.adapter_score_history_tv_name);
			holder.tvValue = (TextView) convertView.findViewById(R.id.adapter_score_history_tv_value);
			holder.tvTime = (TextView) convertView.findViewById(R.id.adapter_score_history_tv_time);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.tvName.setText(list.get(position).getName());
		holder.tvValue.setText(list.get(position).getValue());
		holder.tvTime.setText(list.get(position).getTime());
		return convertView;
	}
	
	class ViewHolder{
		TextView tvName,tvValue,tvTime;
	}

}
