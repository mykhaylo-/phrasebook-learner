package com.ivanursul.learner.utils;

import android.view.KeyEvent;

public class UiUtils {

	public static boolean isEnter(KeyEvent event, int keyCode) {
		return (event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER);
	}
	
}
