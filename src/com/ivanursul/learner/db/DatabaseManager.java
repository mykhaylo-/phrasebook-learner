package com.ivanursul.learner.db;

import com.j256.ormlite.dao.Dao;

import android.content.Context;

public class DatabaseManager {

    static private DatabaseManager instance;

    static public DatabaseManager getInstance(Context ctx) {
        if (null==instance) {
            instance = new DatabaseManager(ctx);
        }
        
        return instance;
    }

    static public DatabaseManager getInstance() {
        return instance;
    }

    private DatabaseHelper helper;
    
    private DatabaseManager(Context ctx) {
        helper = new DatabaseHelper(ctx);
    }

	public <M> Dao<M, ?> getDao(Class<M> clz) {
    	return helper.getRequiredDao(clz);
    }

}