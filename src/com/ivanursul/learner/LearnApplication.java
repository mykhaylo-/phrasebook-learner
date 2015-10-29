package com.ivanursul.learner;

import com.ivanursul.learner.dao.DbDao;
import com.ivanursul.learner.dao.LearnCombinationDao;
import com.ivanursul.learner.db.DatabaseManager;
import com.ivanursul.learner.model.LearnCombination;
import com.j256.ormlite.dao.Dao;

import android.app.Application;

public class LearnApplication extends Application {

	private ApplicationManager applicationManager = ApplicationManager.getInstance();
	
	@SuppressWarnings("unchecked")
	@Override
	public void onCreate() {
		super.onCreate();
		DatabaseManager databaseManager = DatabaseManager.getInstance(getApplicationContext());
		DbDao<LearnCombination, Integer> learnCombinationDao = new LearnCombinationDao((Dao<LearnCombination, Integer>) databaseManager.getDao(LearnCombination.class));
		
		applicationManager.put(DatabaseManager.class, databaseManager);
		applicationManager.put(LearnCombinationDao.class, learnCombinationDao);
	}

}
