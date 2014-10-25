package com.example.scancode.ui.scancode;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.scancode.BaseActivity;
import com.example.scancode.R;
import com.example.scancode.utils.AnimUtil;

/** 扫描结果 */
public class ScanCodeResultActivity extends BaseActivity {

	private String TAG = "ScanCodeResultActivity";
	private LinearLayout llTop;
	private ImageView imgLight;
	private TextView tvTimes, tvHelp;
	private int scanTimes;// 扫描次数 0为扫描失败

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentViewRes(R.layout.activity_scan_code_result_layout);
		setViews();
	}

	@Override
	protected void initVariable() {
		// TODO Auto-generated method stub
		scanTimes = this.getIntent().getIntExtra("scanTimes", 0);
	}

	@Override
	protected void setTitleViews() {
		// TODO Auto-generated method stub
		titleIvRight.setVisibility(View.INVISIBLE);
		if (scanTimes == 0) {
			titleText.setText("无效提示");
		} else if (scanTimes == 1) {
			titleText.setText("扫描成功");
		} else {
			titleText.setText("多次查询提示");
		}
	}

	@Override
	protected void setViews() {
		// TODO Auto-generated method stub
		llTop = (LinearLayout) findViewById(R.id.ll_activity_scan_code_result_top);
		imgLight = (ImageView) findViewById(R.id.img_activity_scan_code_result_light);
		tvTimes = (TextView) findViewById(R.id.tv_activity_scan_code_result_times);
		tvHelp = (TextView) findViewById(R.id.tv_activity_scan_code_result_help);

		if (scanTimes == 0) {
			llTop.setBackgroundResource(R.drawable.ic_check_failed_bg);
			imgLight.setImageResource(R.drawable.ic_check_fail_light_red);
			tvTimes.setText("此码无效，谨防假冒");
			tvHelp.setText("咨询请拨打电话15012345678");
		} else if (scanTimes == 1) {
			llTop.setBackgroundResource(R.drawable.ic_check_failed_bg);
			imgLight.setImageResource(R.drawable.ic_check_success_light_green);
			tvTimes.setText("此码有效，这是第1次查询。");
			tvHelp.setText("如果发现产品异常，您可以拨打电话15012345678");
		} else {
			llTop.setBackgroundResource(R.drawable.ic_check_failed_bg);
			imgLight.setImageResource(R.drawable.ic_check_fail_light_yellow);
			tvTimes.setText("此码是第" + scanTimes + "次查询，谨防假冒。");
			tvHelp.setText("咨询请拨打电话15012345678");
		}
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
