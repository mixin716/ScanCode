package com.example.scancode.adapter.mycode;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.scancode.R;
import com.example.scancode.bean.mycode.LotteryHistoryBean;
import com.example.scancode.utils.ListViewPassValuetoActivityListener;

/** 抽奖历史记录adapter */
public class LotteryHistoryAdapter extends BaseAdapter {

	private Context context;
	private List<LotteryHistoryBean> list;
	private ListViewPassValuetoActivityListener listener;

	public LotteryHistoryAdapter(Context context, List<LotteryHistoryBean> list) {
		this.context = context;
		this.list = list;
	}

	public void setListener(ListViewPassValuetoActivityListener listener) {
		this.listener = listener;
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(context,
					R.layout.adapter_lottery_history_layout, null);
			holder.tvName = (TextView) convertView
					.findViewById(R.id.adapter_lottery_history_tv_name);
			holder.tvValue = (TextView) convertView
					.findViewById(R.id.adapter_lottery_history_tv_value);
			holder.tvTime = (TextView) convertView
					.findViewById(R.id.adapter_lottery_history_tv_time);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tvName.setText(list.get(position).getName());
		if (list.get(position).getFlag() == 0) {
			holder.tvValue.setText("领取");
			holder.tvValue.setBackgroundResource(R.drawable.ic_my_code_receive);
			holder.tvValue.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					listener.doPassActionListener(null, position, 0, null);
				}
			});
		} else {
			holder.tvValue.setText("以领取");
			holder.tvValue
					.setBackgroundResource(R.drawable.ic_my_code_no_receive);
		}
		holder.tvTime.setText(list.get(position).getTime());
		return convertView;
	}

	class ViewHolder {
		TextView tvName, tvValue, tvTime;
	}

}
