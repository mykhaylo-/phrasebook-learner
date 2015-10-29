package com.ivanursul.learner.ui.listener.menu;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.widget.Toast;

import com.ivanursul.learner.ui.activity.MainActivity;
import com.ivanursul.learner.ui.listener.AbstractHandler;

public class AddFileItemHandler extends AbstractHandler<Void> {

	
	public AddFileItemHandler(Activity activity) {
		super(activity);
	}

	@Override
	public void handle(Void vd) {
		showFileDialog();
	}

	private void showFileDialog() {
		
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("*/*");
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		
		try {
			activity.startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), MainActivity.FILE_SELECT_REQUEST_CODE);
		} catch (ActivityNotFoundException ex) {
			Toast.makeText(activity, "Please install a File Manager.", Toast.LENGTH_SHORT).show();
		}
	}
	
}
