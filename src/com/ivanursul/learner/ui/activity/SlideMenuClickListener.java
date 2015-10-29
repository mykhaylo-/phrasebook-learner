package com.ivanursul.learner.ui.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

class SlideMenuClickListener implements ListView.OnItemClickListener {
	
	private final MainActivity mainActivity;

	SlideMenuClickListener(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		this.mainActivity.displayFragment(position);
	}
}