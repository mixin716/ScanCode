package com.example.scancode.ui.scancode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.scancode.BaseActivity;
import com.example.scancode.R;
import com.example.scancode.common.Urls;
import com.example.scancode.utils.AnimUtil;
import com.example.scancode.utils.KeyBoard;
import com.jky.struct2.http.core.AjaxParams;
import com.jky.struct2.http.entityhandle.HttpResult;

/** 验证 */
public class CheckCodeActivity extends BaseActivity {

	private LinearLayout llID;
	private TextView tvID;
	private EditText etID, etSecurity;
	private Button btChcek;
	private int intoFlag;// 1 扫描进入 2直接输入
	private String strIDUrl;
	private String strID, strXtype, strBrand;// 扫描ID
	private String strSecurity;// 防伪码
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentViewRes(R.layout.activity_check_code_layout);
		setViews();
	}

	@Override
	protected void initVariable() {
		// TODO Auto-generated method stub
		intoFlag = this.getIntent().getIntExtra("intoFlag", 1);
		strIDUrl = this.getIntent().getStringExtra("scanIDUrl");
		Log.e("", strIDUrl + "");
		if (intoFlag == 1) {
			if (strIDUrl.indexOf("code") != -1) {
				strID = strIDUrl.substring(strIDUrl.indexOf("code") + 5,
						strIDUrl.indexOf("code") + 15);
			}
			if (strIDUrl.indexOf("xtype") != -1) {
				strXtype = strIDUrl.substring(strIDUrl.indexOf("xtype") + 6,
						strIDUrl.indexOf("brand") - 1);
			}
			if (strIDUrl.indexOf("brand") != -1) {
				strBrand = strIDUrl.substring(strIDUrl.indexOf("brand") + 6,
						strIDUrl.length());
			}
		}
	}

	@Override
	protected void setTitleViews() {
		// TODO Auto-generated method stub
		titleText.setText("验证");
	}

	@Override
	protected void setViews() {
		// TODO Auto-generated method stub
		llID = (LinearLayout) findViewById(R.id.activity_check_code_ll_id);
		tvID = (TextView) findViewById(R.id.activity_check_code_tv_id);
		etID = (EditText) findViewById(R.id.activity_check_code_et_id);
		etSecurity = (EditText) findViewById(R.id.activity_check_code_et_security);
		btChcek = (Button) findViewById(R.id.activity_check_code_bt_check);
		btChcek.setOnClickListener(this);

		if (intoFlag == 1) {
			llID.setVisibility(View.VISIBLE);
			etID.setVisibility(View.GONE);
			tvID.setText(strID);
		} else {
			llID.setVisibility(View.GONE);
			etID.setVisibility(View.VISIBLE);
		}
	}

	@Override
	protected void doClickAction(int viewId) {
		// TODO Auto-generated method stub
		super.doClickAction(viewId);
		switch (viewId) {
		case R.id.title_iv_left:
			this.finish();
			AnimUtil.pushRightInAndOut(CheckCodeActivity.this);
			break;
		case R.id.activity_check_code_bt_check:
			KeyBoard.demissKeyBoard(getApplicationContext(), etSecurity);
			strSecurity = etSecurity.getText().toString().trim();
			if (intoFlag == 2) {
				strID = etID.getText().toString().trim();
				if (TextUtils.isEmpty(strID)) {
					showToast("请输入ID ");
				} else if (strID.length() != 10) {
					showToast("请输入10位ID");
				} else if (TextUtils.isEmpty(strSecurity)) {
					showToast("请输入防伪码");
				} else if (strSecurity.length() != 6) {
					showToast("请输入6位防伪码");
				} else {
					updateCheck();
				}
			} else {
				if (TextUtils.isEmpty(strSecurity)) {
					showToast("请输入防伪码");
				} else if (strSecurity.length() != 6) {
					showToast("请输入6位防伪码");
				} else {
					updateCheck();
				}
			}
			break;
		}
	}

	/** 信息验证 */
	private void updateCheck() {
		showLoading();
		AjaxParams params = new AjaxParams();
		if (intoFlag == 2) {
			params.put("xtype", "oym");
			params.put("brand", "oym");
			params.put("c1", strSecurity);
			params.put("c2", strID);
		} else {
			params.put("xtype", strXtype);
			params.put("brand", strBrand);
			params.put("c1", strSecurity);
			params.put("c2", strID);
		}
		httpRequest.get(Urls.VERIFY, params, callBack, 0);
	}

	@Override
	protected void handleResult(int requestCode, HttpResult result) {
		// TODO Auto-generated method stub
		super.handleResult(requestCode, result);
		JSONArray baseArray;
		try {
			Log.e("", result.baseJson + "");
			baseArray = new JSONArray(result.baseJson);
			JSONObject baseObj = baseArray.getJSONObject(0);
			switch (requestCode) {
			case 0:
				String codeFlag = baseObj.optString("flag");
				String codeMsg = baseObj.optString("msg");
				String codeCount = baseObj.optString("count");
				intent = new Intent(this, ScanCodeResultActivity.class);
				if ("0".equals(codeFlag)) {
					if ("success".equals(codeMsg)) {
						showToast("验证成功");
					}
					intent.putExtra("scanTimes", Integer.valueOf(codeCount) + 1);
				} else {
					intent.putExtra("scanTimes", 0);
					showToast(codeMsg);
				}
				startActivity(intent);
				AnimUtil.pushLeftInAndOut(this);
				if ("0".equals(codeFlag)) {
					this.finish();
				}
				break;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
