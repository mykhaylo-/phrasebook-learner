package com.ivanursul.learner.ui.activity;

import java.lang.reflect.Field;
import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.ListView;

import com.ivanursul.learner.R;
import com.ivanursul.learner.ui.adapter.NavDrawerListAdapter;
import com.ivanursul.learner.ui.fragment.HomeFragment;
import com.ivanursul.learner.ui.fragment.LearnWordsFragment;
import com.ivanursul.learner.ui.fragment.WordListFragment;
import com.ivanursul.learner.ui.listener.EventHandler;
import com.ivanursul.learner.ui.listener.activityresult.FileSelectHandler;
import com.ivanursul.learner.ui.listener.menu.AddFileItemHandler;
import com.ivanursul.learner.ui.model.NavDrawerItem;
import com.ivanursul.learner.ui.view.DefaultActionBarDrawerToggle;

public class MainActivity extends Activity {

	public static final int FILE_SELECT_REQUEST_CODE = 38;

	// UI Fields
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;
	private ArrayList<NavDrawerItem> navDrawerItems;
	private SparseArray<EventHandler<?>> onOptionsItemSelectedHandlers;
	private SparseArray<EventHandler<Intent>> onActivityResultHandlers;

	private NavDrawerListAdapter adapter;
	private SlideMenuClickListener slideMenuClickListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
		navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

		navMenuIcons.recycle();

		// Nav Drawer items
		navDrawerItems = new ArrayList<NavDrawerItem>();
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));

		onOptionsItemSelectedHandlers = new SparseArray<EventHandler<?>>();
		onOptionsItemSelectedHandlers.put(R.id.action_new, new AddFileItemHandler(this));

		onActivityResultHandlers = new SparseArray<EventHandler<Intent>>();
		onActivityResultHandlers.put(FILE_SELECT_REQUEST_CODE, new FileSelectHandler(this));

		slideMenuClickListener = new SlideMenuClickListener(this);
		mDrawerList.setOnItemClickListener(slideMenuClickListener);
		adapter = new NavDrawerListAdapter(getApplicationContext(), navDrawerItems);
		mDrawerList.setAdapter(adapter);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new DefaultActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.app_name, R.string.app_name);

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			displayFragment(0);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		EventHandler<?> eventHandler = onOptionsItemSelectedHandlers.get(item.getItemId());

		if (eventHandler == null) {
			return super.onOptionsItemSelected(item);
		}

		eventHandler.handle(null);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			EventHandler<Intent> eventHandler = onActivityResultHandlers.get(requestCode);
			eventHandler.handle(data);
		}
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_new).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	void displayFragment(int position) {
		Fragment fragment = null;

		switch (position) {
		case 0:
			fragment = new HomeFragment();
			break;
		case 1:
			fragment = new LearnWordsFragment();
			break;
		case 2:
			fragment = new WordListFragment();
			break;
		default:
			break;
		}

		if (fragment != null) {
			getFragmentManager().beginTransaction().replace(R.id.frame_container, fragment)
					.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();

			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		getActionBar().setTitle(title);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
}