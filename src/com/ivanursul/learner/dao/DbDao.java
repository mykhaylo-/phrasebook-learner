package com.ivanursul.learner.dao;

import java.sql.SQLException;
import java.util.List;

public interface DbDao<M, I> {

	public abstract List<M> getList() throws SQLException;

	public abstract void save(M entity) throws SQLException;

	public abstract void update(M entity) throws SQLException;

	public abstract M getById(I id) throws SQLException;

	public abstract void saveAll(List<M> entities) throws SQLException;

}