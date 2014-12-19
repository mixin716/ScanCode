package com.example.scancode;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scancode.bean.BaseBean;
import com.example.scancode.common.Constants;
import com.example.scancode.logic.LogicBase;
import com.example.scancode.view.PullToRefreshView;
import com.example.scancode.view.PullToRefreshView.OnFooterRefreshListener;
import com.example.scancode.view.PullToRefreshView.OnHeaderRefreshListener;
import com.jky.struct2.http.FinalHttp;
import com.jky.struct2.http.core.AjaxCallBack;
import com.jky.struct2.http.entityhandle.HttpExceptionResult;
import com.jky.struct2.http.entityhandle.HttpResult;

public abstract class BaseActivity extends FragmentActivity implements
		OnClickListener, OnHeaderRefreshListener, OnFooterRefreshListener {

	/*
	 * 控件
	 */
	protected ImageView titleIvLeft;
	protected ImageView titleIvRight;
	protected TextView titleText;
	protected ViewGroup titleLayout;
	protected ViewGroup topLayout;
	protected ViewGroup contentLayout;
	protected ViewGroup contentRefreshLayout;
	protected ViewGroup bottomLayout;
	protected ViewGroup curContent;
	protected ViewGroup netErrorLayout;
	protected ViewGroup loadingLayout;
	protected ViewGroup loadingRequest;
	protected ViewGroup loadingSend;

	protected PullToRefreshView mPullToRefreshView;
	protected TextView tvHint, tvLoadingTxt;
	protected LinearLayout llError;
	protected Toast toast;
	protected FinalHttp httpRequest;
	protected boolean[] isRequesting = { false, false, false, false, false,
			false };
	protected LayoutInflater mInflater;
	private static int width, height;
	private MarginLayoutParams mp;
	private LinearLayout.LayoutParams lp;
	private boolean showLoading = false;

	private BroadcastReceiver receiverFinish = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			onReceiverFinish(intent);

		}

	};

	protected AjaxCallBack<HttpResult> callBack = new AjaxCallBack<HttpResult>() {
		@Override
		public void onSuccess(HttpResult httpResult) {
			dismissLoading();
			System.out.println("---------解析成功：---------->>");
			handleResult(httpResult.which, httpResult);
		};

		@Override
		public void onFailure(int which, HttpExceptionResult result) {
			dismissLoading();
			System.out.println("---------解析失败：-------->>");
			handleNetErr(which, result.code);
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_base_layout);
		registerReceiver(receiverFinish, new IntentFilter(
				Constants.INTENT_ACTION_FINISH_ALL));
		httpRequest = new FinalHttp(this);
		getWindowHW();
		initVariable();
		initViews();
		setTitleViews();

	};

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	/**
	 * 获得手机分辨率
	 */
	public void getWindowHW() {
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		width = dm.widthPixels;
		height = dm.heightPixels;
	}

	/**
	 * 初始化基类中的基本控件，该方法在页面基类BaseActivity的{@link #onCreate()} 方法中
	 * {@link #initVariable()} 和 {@link #setTitleViews()}之间自动执行
	 */
	private void initViews() {
		titleLayout = (ViewGroup) findViewById(R.id.page_title);
		// mp = new MarginLayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,
		// (int) (height * 0.0875));
		// lp = new LinearLayout.LayoutParams(mp);
		// titleLayout.setLayoutParams(lp);
		titleIvLeft = (ImageView) titleLayout.findViewById(R.id.title_iv_left);
		titleIvRight = (ImageView) titleLayout
				.findViewById(R.id.title_iv_right);
		titleText = (TextView) titleLayout.findViewById(R.id.title_tv_text);
		topLayout = (ViewGroup) findViewById(R.id.page_top);
		bottomLayout = (ViewGroup) findViewById(R.id.page_bottom);
		contentLayout = (ViewGroup) findViewById(R.id.page_content);
		contentRefreshLayout = (ViewGroup) findViewById(R.id.page_refresh_content);
		netErrorLayout = (ViewGroup) findViewById(R.id.page_net_error);
		loadingLayout = (ViewGroup) findViewById(R.id.page_loading);
		tvLoadingTxt = (TextView) loadingLayout
				.findViewById(R.id.page_loading_center_tv_flag);
		loadingRequest = (ViewGroup) findViewById(R.id.page_loading_request);
		loadingSend = (ViewGroup) findViewById(R.id.page_loading_send);

		mPullToRefreshView = (PullToRefreshView) findViewById(R.id.activity_base_refresh);
		tvHint = (TextView) findViewById(R.id.page_tv_hint);

		mPullToRefreshView.setOnHeaderRefreshListener(this);
		mPullToRefreshView.setOnFooterRefreshListener(this);
		titleIvLeft.setOnClickListener(this);
		titleIvRight.setOnClickListener(this);
		loadingLayout.setOnClickListener(this);
		loadingRequest.setOnClickListener(this);
		loadingSend.setOnClickListener(this);
		netErrorLayout.setOnClickListener(this);
		netErrorLayout.setVisibility(View.GONE);
		loadingLayout.setVisibility(View.GONE);
		loadingRequest.setVisibility(View.GONE);
		loadingSend.setVisibility(View.GONE);
		tvHint.setVisibility(View.GONE);

		mInflater = LayoutInflater.from(this);
	}

	/**
	 * 该方法用于初始化页面变量，在页面基类BaseActivity的{@link #initViews()}方法之前自动执行
	 */
	protected abstract void initVariable();

	/**
	 * 填充页面顶部内容
	 * 
	 * @param layoutRes
	 */
	protected void setTopViewRes(int layoutRes) {
		topLayout.removeAllViews();
		ViewGroup topView = (ViewGroup) mInflater.inflate(layoutRes, null);
		topLayout.addView(topView, new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
	}

	/**
	 * 隐藏顶部
	 */
	protected void dismissTop() {
		if (titleLayout != null) {
			titleLayout.setVisibility(View.GONE);
		}
	}

	/**
	 * 填充页面中间部分内容
	 * 
	 * @param layoutRes
	 */
	protected void setContentViewRes(int layoutRes) {
		contentLayout.setVisibility(View.VISIBLE);
		mPullToRefreshView.setVisibility(View.GONE);
		contentLayout.removeAllViews();
		curContent = (ViewGroup) mInflater.inflate(layoutRes, null);
		contentLayout.addView(curContent, new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
	}

	/**
	 * 设置填充刷新内容
	 * 
	 * @param layoutRes
	 */
	protected void setContentViewRefreshRes(int layoutRes) {
		mPullToRefreshView.setVisibility(View.VISIBLE);
		contentLayout.setVisibility(View.GONE);
		contentRefreshLayout.removeAllViews();
		curContent = (ViewGroup) mInflater.inflate(layoutRes, null);
		contentRefreshLayout.addView(curContent, new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
	}

	/**
	 * 填充页面底部分内容
	 * 
	 * @param layoutRes
	 */
	protected void setBottomViewRes(int layoutRes) {
		bottomLayout.removeAllViews();
		ViewGroup bottomView = (ViewGroup) mInflater.inflate(layoutRes, null);
		bottomLayout.addView(bottomView, new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
	}

	/**
	 * 设置页面标题控件，该方法在页面基类BaseActivity的{@link #initViews()}方法之后自动执行
	 */
	protected abstract void setTitleViews();

	/**
	 * 对页面控件进行设置，该方法必须在实现类的{@link #onCreate()}方法显示调用
	 */
	protected abstract void setViews();

	/** 显示加载失败 */
	protected void showErrorLayout() {
		dismissLoading();
		if (netErrorLayout != null) {
			netErrorLayout.bringToFront();
			netErrorLayout.setVisibility(View.VISIBLE);
		}
	}

	/** 隐藏加载失败 */
	protected void dismissErrorLayout() {
		if (netErrorLayout != null) {
			netErrorLayout.bringToFront();
			netErrorLayout.setVisibility(View.GONE);
		}
	}

	/** 下拉刷新 */
	@Override
	public void onHeaderRefresh(PullToRefreshView view) {
		// TODO Auto-generated method stub
	}

	/** 上拉刷新 */
	@Override
	public void onFooterRefresh(PullToRefreshView view) {
		// TODO Auto-generated method stub
		mPullToRefreshView.onFooterRefreshComplete();// 直接取消掉上拉刷新
	}

	/**
	 * 成功连接网络，并有数据返回
	 * 
	 */
	protected void handleResult(int requestCode, HttpResult result) {
		String baseJson = result.baseJson;
		System.out.println("-----json:------" + baseJson);
	}

	/**
	 * 有数据返回,并且数据正确时
	 * 
	 * @param reqeustCode
	 *            :请求的指示
	 * @param jsonString
	 */
	protected void handleJson(int reqeustCode, String jsonString) {

	}

	/**
	 * 网络错误处理
	 * 
	 * @param reqeustCode
	 *            请求
	 * @param errorCode
	 *            1:网络未连接 2：
	 */
	public void handleNetErr(int requestCode, int errorCode) {

	}

	protected void handleResult400(int index, String msg) {
		tvHint.setVisibility(View.VISIBLE);
		tvHint.setText(msg);
		contentLayout.setVisibility(View.GONE);
	}

	@Override
	public void onClick(View v) {
		doClickAction(v.getId());
	}

	/**
	 * 控件点击事件
	 * 
	 * @param viewId
	 */
	protected void doClickAction(int viewId) {
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.e("", "onKeyDown");
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (showLoading) {
				return true;
			} else {
				doBackAction();
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * 显示加载提示 圈
	 */
	protected void showLoading() {
		showLoading = true;
		if (loadingLayout != null) {
			loadingLayout.bringToFront();
			loadingLayout.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * 显示加载提示 圈
	 */
	protected void showLoading(String msg) {
		showLoading = true;
		if (loadingLayout != null) {
			loadingLayout.bringToFront();
			loadingLayout.setVisibility(View.VISIBLE);
			tvLoadingTxt.setText(msg);
		}
	}

	/** 请求数据loading显示 */
	protected void showRequestLoading() {
		showLoading = true;
		if (loadingRequest != null) {
			loadingRequest.bringToFront();
			loadingRequest.setVisibility(View.VISIBLE);
		}
	}

	/** 上传数据loading显示 待定 */
	protected void showSendLoading() {
		showLoading = true;
		if (loadingSend != null) {
			loadingSend.bringToFront();
			loadingSend.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * 隐藏加载提示
	 */
	protected void dismissLoading() {
		showLoading = false;
		if (loadingLayout != null) {
			loadingLayout.setVisibility(View.GONE);
		}
		if (loadingRequest != null) {
			loadingRequest.setVisibility(View.GONE);
		}
		if (loadingSend != null) {
			loadingSend.setVisibility(View.GONE);
		}
	}

	/**
	 * 物理返回键执行的动作
	 */
	protected void doBackAction() {
		finish();
	}

	/**
	 * toast string消息,时间2秒
	 * 
	 * @param msg
	 */
	protected void showToast(String msg) {
		if (toast == null) {
			toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
			toast.setDuration(2200);
			toast.show();
		} else {
			toast.setText(msg);
			toast.setDuration(2200);
			toast.show();
		}
	}

	/**
	 * toast 资源中的消息,时间2秒
	 * 
	 * @param resId
	 */
	protected void showToast(int resId) {
		if (toast == null) {
			toast = Toast.makeText(getApplicationContext(), resId,
					Toast.LENGTH_SHORT);
			toast.setDuration(2200);
			toast.show();
		} else {
			toast.setText(resId);
			toast.setDuration(2200);
			toast.show();
		}
	}

	protected void onReceiverFinish(Intent intent) {
		finish();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiverFinish);
	}

}
