package com.hauphvnjavaweb.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hauphvnjavaweb.model.NewsModel;
import com.hauphvnjavaweb.model.UserModel;
import com.hauphvnjavaweb.service.INewService;
import com.hauphvnjavaweb.utils.HttpUtil;
import com.hauphvnjavaweb.utils.SessionUtil;

@WebServlet(urlPatterns = {"/api-admin-news"})
public class NewsAPI extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	@Inject
	private INewService newsService;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		NewsModel updateModel = HttpUtil.of(req.getReader()).toModel(NewsModel.class);
		updateModel = newsService.update(updateModel);
		mapper.writeValue(resp.getOutputStream(), updateModel);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		NewsModel newsModel =  HttpUtil.of(req.getReader()).toModel(NewsModel.class);
		newsModel.setCreatedBy(((UserModel) SessionUtil.getInstance().getValue(req,"USERNAME")).getUsername());
		newsModel = newsService.save(newsModel);
		mapper.writeValue(resp.getOutputStream(), newsModel);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		NewsModel deleteModel = HttpUtil.of(req.getReader()).toModel(NewsModel.class);
		newsService.delete(deleteModel.getIds());
		mapper.writeValue(resp.getOutputStream(), "{}");
	}
}
