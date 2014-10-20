package com.example.scancode.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class KeyBoard {
	private static InputMethodManager imm;

	/**
	 * 隐藏键盘
	 * 
	 * @param context
	 * @param e
	 */
	public static void demissKeyBoard(Context c, EditText e) {
		if (imm == null)
			imm = (InputMethodManager) c
					.getSystemService(Context.INPUT_METHOD_SERVICE);

		imm.hideSoftInputFromWindow(e.getWindowToken(), 0);
		e.clearFocus();
	}

	/**
	 * 显示键盘
	 * 
	 * @param context
	 * @param e
	 */
	public static void showKeyBoard(Context c, EditText e) {
		if (imm == null)
			imm = (InputMethodManager) c
					.getSystemService(Context.INPUT_METHOD_SERVICE);
		// �����?
		imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
				InputMethodManager.HIDE_NOT_ALWAYS);
	}

}
