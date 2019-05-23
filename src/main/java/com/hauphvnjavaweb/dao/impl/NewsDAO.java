package com.hauphvnjavaweb.dao.impl;

import java.util.List;

import com.hauphvnjavaweb.dao.INewDAO;
import com.hauphvnjavaweb.mapper.NewsMapper;
import com.hauphvnjavaweb.model.NewsModel;
import com.hauphvnjavaweb.paging.Pageble;

public class NewsDAO extends AbstractDAO<NewsModel> implements INewDAO {

	@Override
	public List<NewsModel> findByCategoryId(Long categoryid) {
		String sql = "SELECT * FROM news WHERE categoryid = ?";
		return query(sql, new NewsMapper(), categoryid);
	}

	@Override
	public Long save(NewsModel news) {
		String sql = "INSERT INTO news(title,categoryid,content,thumbnail,shortdescription,createdate,modifieddate,createdby,modifiedby) VALUES(?,?,?,?,?,?,?,?,?)";
		return insert(sql, news.getTitle(), news.getCategoryId(), news.getContent(), news.getThumbnail(),
				news.getShortDescription(), news.getCreateDate(), news.getModifiedDate(), news.getCreatedBy(),
				news.getModifiedBy());
	}

	@Override
	public NewsModel findOne(Long id) {
		String sql = "SELECT * FROM news WHERE id = ?";
		List<NewsModel> news = query(sql, new NewsMapper(), id);
		return news.isEmpty() ? null : news.get(0);
	}

	@Override
	public void update(NewsModel news) {
		String sql = "UPDATE news SET categoryid = ?, content = ?,title = ?,thumbnail = ?,shortdescription = ?, createdate = ?, createdby = ?, modifieddate = ?, modifiedby = ? WHERE id = ?";
		update(sql, news.getCategoryId(), news.getContent(), news.getTitle(), news.getThumbnail(),
				news.getShortDescription(), news.getCreateDate(), news.getCreatedBy(), news.getModifiedDate(),
				news.getModifiedBy(), news.getId());
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM news WHERE id = ?";
		update(sql, id);
	}

	@Override
	public List<NewsModel> findAll(Pageble pageble) {

		StringBuilder sql = new StringBuilder("SELECT * FROM news ");
		if (pageble.getSorter() != null) {
			sql.append("ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy() + "");
		}
		if (pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT " + pageble.getOffset() + "," + pageble.getLimit() + "");
		}
		return query(sql.toString(), new NewsMapper());
	}

	@Override
	public int countItem() {
		String sql = "SELECT COUNT(*) FROM news";
		return count(sql);
	}
}
