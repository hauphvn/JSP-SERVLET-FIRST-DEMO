package com.hauphvnjavaweb.dao.impl;

import java.util.List;

import com.hauphvnjavaweb.dao.ICategoryDAO;
import com.hauphvnjavaweb.mapper.CategoryMapper;
import com.hauphvnjavaweb.model.CategoryModel;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO {

	@Override
	public List<CategoryModel> findAll() {
		String sql = "SELECT * FROM category";
		return query(sql, new CategoryMapper());
	}

    @Override
    public CategoryModel findOne(long id) {
		String sql = "SELECT * FROM category WHERE id = ?";
		List<CategoryModel> listCategory =  query(sql, new CategoryMapper(),id);
		return listCategory.isEmpty() ? null : listCategory.get(0);
    }

    public void insert() {
	}
}
