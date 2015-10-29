package com.ivanursul.learner.ui.fragment;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ivanursul.learner.ApplicationManager;
import com.ivanursul.learner.R;
import com.ivanursul.learner.dao.DbDao;
import com.ivanursul.learner.dao.LearnCombinationDao;
import com.ivanursul.learner.db.DatabaseManager;
import com.ivanursul.learner.model.LearnCombination;
import com.j256.ormlite.dao.Dao;

public class LearnWordsFragment extends Fragment {

	private static final String TAG = "LearnWordsFragment";

	public LearnWordsFragment() {
	}

	private TextView originalLanguageView;
	private TextView translatedLanguageView;
	private TextView originalWordView;
	private TextView counterView;
	private TextView negativeCounterView;

	private Button firstBtn;
	private Button secondBtn;
	private Button thirdBtn;
	private Button fourthBtn;

	private List<Button> btns;

	// Custom classes
	private ApplicationManager appManager = ApplicationManager.getInstance();
	private DatabaseManager databaseManager = appManager.get(DatabaseManager.class);
	private DbDao<LearnCombination, Integer> learnCombinationDao;

	private List<LearnCombination> combinations;
	private LinkedList<Integer> availableIndexes;
	private LearnCombination currentCombination;
	private int correctWords = 0;
	private int incorrectWords = 0;

	private Random random;

	@SuppressWarnings("unchecked")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		learnCombinationDao = new LearnCombinationDao(
				(Dao<LearnCombination, Integer>) databaseManager
						.getDao(LearnCombination.class));
		random = new Random();
		try {
			combinations = learnCombinationDao.getList();
			fillIndexes();
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}

		View rootView = inflater.inflate(R.layout.fragment_learn_words,
				container, false);

		originalLanguageView = (TextView) rootView
				.findViewById(R.id.originalLanguageView);
		translatedLanguageView = (TextView) rootView
				.findViewById(R.id.translatedLanguageView);
		originalWordView = (TextView) rootView
				.findViewById(R.id.originalWordView);
		counterView = (TextView) rootView.findViewById(R.id.counterView);
		negativeCounterView = (TextView) rootView
				.findViewById(R.id.negativeCounterView);

		firstBtn = (Button) rootView.findViewById(R.id.firstBtn);
		secondBtn = (Button) rootView.findViewById(R.id.secondBtn);
		thirdBtn = (Button) rootView.findViewById(R.id.thirdBtn);
		fourthBtn = (Button) rootView.findViewById(R.id.fourthBtn);

		btns = Arrays.asList(firstBtn, secondBtn, thirdBtn, fourthBtn);

		for (Button btn : btns) {
			btn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					String word = ((Button) v).getText().toString();
					String correctWord = currentCombination.getTranslatedWord();

					if (isCorrect(word, correctWord)) {
						increaseCounter();
					} else {
						printCorrectWord();
						increaseNegativeCounter();
					}

					nextWord();
				}

			});
		}

		nextWord();
		return rootView;
	}

	private void fillIndexes() {
		availableIndexes = new LinkedList<Integer>();
		for(int i = 0; i < combinations.size(); i++) {
			availableIndexes.add(i);
		}
		Collections.shuffle(availableIndexes, new Random(System.nanoTime()));
	}

	private void increaseCounter() {
		correctWords += 1;
		counterView.setText(correctWords + "");
	}

	private void increaseNegativeCounter() {
		incorrectWords += 1;
		negativeCounterView.setText(incorrectWords + "");
	}

	private boolean isCorrect(String word, String correctWord) {
		String word1 = word.trim().toLowerCase(Locale.getDefault());
		String word2 = correctWord.trim().toLowerCase(Locale.getDefault());
		return word1.equals(word2);
	}

	private void printCorrectWord() {
		Toast.makeText(getActivity(), currentCombination.getTranslatedWord(), Toast.LENGTH_LONG).show();
	}

	private void nextWord() {
		if (combinations.size() == 0) {
			return;
		}

		int index = getIndex();

		currentCombination = combinations.get(index);
		availableIndexes.add(index);

		originalLanguageView.setText(currentCombination.getOriginalLang());
		translatedLanguageView.setText(currentCombination.getTranslatedLang());
		originalWordView.setText(currentCombination.getOriginalWord());

		for (Button btn : btns) {
			LearnCombination randomCombination = combinations.get(random.nextInt(combinations.size()));
			btn.setText(randomCombination.getTranslatedWord().toLowerCase(Locale.getDefault()));
		}

		btns.get(random.nextInt(4)).setText(
				currentCombination.getTranslatedWord());
	}

	private int getIndex() {
		int index = availableIndexes.removeFirst();
		return index;
	}
}