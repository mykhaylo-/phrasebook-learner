package com.ivanursul.learner.dao;

import java.sql.SQLException;
import java.util.List;

import com.ivanursul.learner.model.LearnCombination;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;

public class LearnCombinationDao extends AbstractDao<LearnCombination, Integer> {

	public LearnCombinationDao(Dao<LearnCombination, Integer> dao) {
		super(dao);
	}

	@Override
	public void saveAll(List<LearnCombination> entities) throws SQLException {
		for (LearnCombination learnCombination : entities) {
			if(getByRows(learnCombination) == null) { 
				save(learnCombination);
			}
		}
	}

	public LearnCombination getByRows(LearnCombination learnCombination) throws SQLException {
		 QueryBuilder<LearnCombination, Integer> qb = dao.queryBuilder();

		 PreparedQuery<LearnCombination> preparedQuery = qb.where()
				 
			 .eq("originalLang", new SelectArg(learnCombination.getOriginalLang()))
			 .and()
			 .eq("translatedLang", new SelectArg(learnCombination.getTranslatedLang()))
			 .and()
			 .eq("originalWord", new SelectArg(learnCombination.getOriginalWord()))
			 .and()
			 .eq("translatedWord", new SelectArg(learnCombination.getTranslatedWord())).prepare();
		 
//		 SelectArg selectArg = new SelectArg(stats);
		 
		 return dao.queryForFirst(preparedQuery);
	}
}
