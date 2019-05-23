package com.hauphvnjavaweb.dao;

import java.util.List;

import com.hauphvnjavaweb.model.NewsModel;
import com.hauphvnjavaweb.paging.Pageble;

public interface INewDAO extends GenericDAO<NewsModel>{
	NewsModel findOne(Long id);
	List<NewsModel> findByCategoryId(Long categoryid);
	Long save(NewsModel news);
	void update(NewsModel updateNews);
	void delete(long id);
	List<NewsModel> findAll(Pageble pageble);
	int countItem();
}
