package com.hauphvnjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.hauphvnjavaweb.model.NewsModel;

public class NewsMapper implements RowMapper<NewsModel>{

	@Override
	public NewsModel mapRow(ResultSet resultSet) {
			NewsModel newsModel = new NewsModel();
			try {
				newsModel.setId(resultSet.getLong("id"));
				newsModel.setTitle(resultSet.getString("title"));
				newsModel.setCategoryId(resultSet.getLong("categoryid"));
				newsModel.setContent(resultSet.getString("content"));
				newsModel.setThumbnail(resultSet.getString("thumbnail"));
				newsModel.setShortDescription(resultSet.getString("shortdescription"));
				newsModel.setCreateDate(resultSet.getTimestamp("createdate"));
				newsModel.setModifiedDate(resultSet.getTimestamp("modifieddate"));
				newsModel.setCreatedBy(resultSet.getString("createdby"));
				newsModel.setModifiedBy(resultSet.getString("modifiedby"));
				return newsModel;
			} catch (SQLException e) {
				return null;
			}
	}

}
