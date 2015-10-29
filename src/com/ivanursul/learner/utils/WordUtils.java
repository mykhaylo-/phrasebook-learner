package com.ivanursul.learner.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.util.Log;

import com.ivanursul.learner.model.LearnCombination;

import java.util.ArrayList;
import java.util.List;

public class WordUtils {

	private static final String TAG = "WordUtils";

	@Deprecated
	public static List<LearnCombination> getLearnCombinations(File file) throws FileNotFoundException {
		List<LearnCombination> list = new ArrayList<LearnCombination>();
		FileInputStream is = new FileInputStream(file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] row = line.split(",");

				LearnCombination learnCombination = new LearnCombination();
				learnCombination.setOriginalLang(row[0]);
				learnCombination.setTranslatedLang(row[1]);
				learnCombination.setOriginalWord(row[2]);
				learnCombination.setTranslatedWord(row[3]);
				
				list.add(learnCombination);
				Log.d(TAG, learnCombination.toString());
			}
		} catch (IOException ex) {
			// handle exception
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				
			}
		}
		
		return list;
	}
	
	public static List<LearnCombination> getLearnCombinations(InputStream inputStream) throws FileNotFoundException {
		List<LearnCombination> list = new ArrayList<LearnCombination>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] row = line.split("\t");
				
				LearnCombination learnCombination = new LearnCombination();
				learnCombination.setOriginalLang(row[0]);
				learnCombination.setTranslatedLang(row[1]);
				learnCombination.setOriginalWord(row[2]);
				learnCombination.setTranslatedWord(row[3]);
				
				list.add(learnCombination);
				Log.d(TAG, learnCombination.toString());
			}
		} catch (IOException ex) {
			// handle exception
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				
			}
		}
		
		return list;
	}

}
