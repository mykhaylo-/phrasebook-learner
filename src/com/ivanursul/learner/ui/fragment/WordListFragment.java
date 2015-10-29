package com.ivanursul.learner.ui.fragment;

import java.sql.SQLException;
import java.util.List;

import com.ivanursul.learner.ApplicationManager;
import com.ivanursul.learner.R;
import com.ivanursul.learner.dao.DbDao;
import com.ivanursul.learner.dao.LearnCombinationDao;
import com.ivanursul.learner.db.DatabaseManager;
import com.ivanursul.learner.model.LearnCombination;
import com.ivanursul.learner.ui.adapter.LearnCombinationAdapter;
import com.j256.ormlite.dao.Dao;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class WordListFragment extends Fragment {
	private static final String TAG = "WordListFragment";
	
	// Custom classes
	private ApplicationManager appManager = ApplicationManager.getInstance();
	private DatabaseManager databaseManager = appManager.get(DatabaseManager.class);
	private DbDao<LearnCombination, Integer> learnCombinationDao;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_word_list, container, false);
		
		learnCombinationDao = new LearnCombinationDao((Dao<LearnCombination, Integer>) databaseManager.getDao(LearnCombination.class));

		ListView listView = (ListView) rootView .findViewById(R.id.learnWordsListView);

		try {
			List<LearnCombination> combinations = learnCombinationDao.getList();
			ListAdapter adapter = new LearnCombinationAdapter(getActivity(), combinations);
			listView.setAdapter(adapter);
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}

		return rootView;
	}

}
