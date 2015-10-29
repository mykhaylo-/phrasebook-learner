package com.ivanursul.learner.ui.listener.activityresult;

import java.io.InputStream;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.ivanursul.learner.ApplicationManager;
import com.ivanursul.learner.dao.DbDao;
import com.ivanursul.learner.dao.LearnCombinationDao;
import com.ivanursul.learner.model.LearnCombination;
import com.ivanursul.learner.ui.listener.AbstractHandler;
import com.ivanursul.learner.utils.WordUtils;

public class FileSelectHandler extends AbstractHandler<Intent> {

	private ApplicationManager appManager = ApplicationManager.getInstance();
	private DbDao<LearnCombination, Integer> learnCombinationDao = appManager.get(LearnCombinationDao.class);

	public FileSelectHandler(Activity activity) {
		super(activity);
	}

	@Override
	public void handle(Intent data) {
		try {
			Uri uri = data.getData();

			InputStream inputStream = activity.getApplicationContext().getContentResolver().openInputStream(uri);
			List<LearnCombination> words = WordUtils.getLearnCombinations(inputStream);
			learnCombinationDao.saveAll(words);

		} catch (Exception e) {
			Toast.makeText(activity, e.getClass().getName() + ": " + e.getMessage(), Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
	}

}
