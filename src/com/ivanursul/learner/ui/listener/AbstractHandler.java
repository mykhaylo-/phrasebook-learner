package com.ivanursul.learner.ui.listener;

import android.app.Activity;

public abstract class AbstractHandler<T> implements EventHandler<T> {

	protected Activity activity;
	
	public AbstractHandler(Activity activity) {
		this.activity = activity;
	}
	
}
