package com.ivanursul.learner.dao;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;

public abstract class AbstractDao<M,I> implements DbDao<M, I> {

	protected Dao<M, I> dao;
	
	public AbstractDao(Dao<M, I> dao) {
		this.dao = dao;
	}
	
	@Override
	public List<M> getList() throws SQLException {
		return dao.queryForAll();
	}
	
	@Override
	public void save(M entity) throws SQLException {
		dao.create(entity);
	}
	
	@Override
	public void update(M entity) throws SQLException {
		dao.update(entity);
	}
	
	@Override
	public M getById(I id) throws SQLException {
		return dao.queryForId(id);
	}
	
	@Override
	public void saveAll(List<M> entities) throws SQLException {
		for (M m : entities) {
			save(m);
		}
	}
}
