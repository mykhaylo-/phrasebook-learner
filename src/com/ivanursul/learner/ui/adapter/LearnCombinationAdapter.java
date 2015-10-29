package com.ivanursul.learner.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ivanursul.learner.R;
import com.ivanursul.learner.model.LearnCombination;

public class LearnCombinationAdapter extends BaseAdapter {

	private Context context;
	private List<LearnCombination> learnCombinations;
	private LayoutInflater lInflater;
	
	public LearnCombinationAdapter(Context context,List<LearnCombination> combinations){
		this.context = context;
		this.learnCombinations = combinations;
	    this.lInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		return learnCombinations.size();
	}

	@Override
	public Object getItem(int position) {
		return learnCombinations.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

	    View view = convertView;
	    if (view == null) {
	      view = lInflater.inflate(R.layout.learc_combination_item, parent, false);
	    }

	    LearnCombination learnCombination = (LearnCombination) getItem(position);

	    TextView originalView = ((TextView) view.findViewById(R.id.learnCombinationOriginWord));
	    TextView translatedView = ((TextView) view.findViewById(R.id.learnCombinationTranslatedWord));
	    
	    originalView.setText(learnCombination.getOriginalWord());
	    translatedView.setText(learnCombination.getTranslatedWord());
	    
	    return view;
	}

}
