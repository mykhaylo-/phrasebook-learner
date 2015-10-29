package com.ivanursul.learner.ui.view;

import android.app.Activity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

public class DefaultActionBarDrawerToggle extends ActionBarDrawerToggle {

	private Activity activity;
	private CharSequence mTitle;
	
	public DefaultActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, int drawerImageRes,
			int openDrawerContentDescRes, int closeDrawerContentDescRes) {
		super(activity, drawerLayout, drawerImageRes, openDrawerContentDescRes, closeDrawerContentDescRes);
		this.activity = activity;
		mTitle = activity.getTitle();
	}
	
	@Override
	public void onDrawerClosed(View view) {
		activity.getActionBar().setTitle(mTitle);
		activity.invalidateOptionsMenu();
	}

	@Override
	public void onDrawerOpened(View drawerView) {
		activity.getActionBar().setTitle(mTitle);
		activity.invalidateOptionsMenu();
	}

}
