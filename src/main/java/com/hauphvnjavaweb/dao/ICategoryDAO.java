package com.hauphvnjavaweb.dao;

import java.util.List;

import com.hauphvnjavaweb.model.CategoryModel;

public interface ICategoryDAO extends GenericDAO<CategoryModel>{
	List<CategoryModel> findAll();
	CategoryModel findOne(long id);
}
