package com.hauphvnjavaweb.service;

import java.util.List;

import com.hauphvnjavaweb.model.NewsModel;
import com.hauphvnjavaweb.paging.Pageble;

public interface INewService {
	List<NewsModel> findByCategoryId(Long categoryid);
	NewsModel save(NewsModel news);
	NewsModel update(NewsModel newNews);
	void delete(long[] ids);
	List<NewsModel> findAll(Pageble pageble);
	NewsModel findOne(long id);
	int count();
}
