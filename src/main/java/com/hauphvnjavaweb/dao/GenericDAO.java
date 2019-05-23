package com.hauphvnjavaweb.dao;

import java.util.List;

import com.hauphvnjavaweb.mapper.RowMapper;

public interface GenericDAO<T>{
	<T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameter);
	void update(String sql, Object...parameters);
	Long insert(String sql, Object...parameters);
	int count(String sql, Object...parameters);
}
