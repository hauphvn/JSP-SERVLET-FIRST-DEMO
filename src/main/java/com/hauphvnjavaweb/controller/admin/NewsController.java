package com.hauphvnjavaweb.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hauphvnjavaweb.constant.SystemConstant;
import com.hauphvnjavaweb.model.NewsModel;
import com.hauphvnjavaweb.paging.PageRequest;
import com.hauphvnjavaweb.paging.Pageble;
import com.hauphvnjavaweb.service.ICategoryService;
import com.hauphvnjavaweb.service.INewService;
import com.hauphvnjavaweb.sort.Sorter;
import com.hauphvnjavaweb.utils.FormUtil;

@WebServlet (urlPatterns = "/admin-news")
public class NewsController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1569707547782560482L;
	
	@Inject
	private INewService newsService;
	@Inject
	private ICategoryService iCategoryService;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NewsModel newsModel = new NewsModel();
		newsModel = FormUtil.toModel(NewsModel.class, req);
		String view = "";
		if(newsModel.getType().equals(SystemConstant.LIST)){
			Pageble pageble = new PageRequest(newsModel.getPage(), newsModel.getMaxItem(), new Sorter(newsModel.getSortName(), newsModel.getSortBy()));
			newsModel.setTotalItems(newsService.count());
			newsModel.setTotalPage((int)Math.ceil((double)newsModel.getTotalItems() / newsModel.getMaxItem()));
			newsModel.setListResult(newsService.findAll(pageble));
			view = "/views/admin/news/list.jsp";
		}else if(newsModel.getType().equals(SystemConstant.EDIT)){
			if(newsModel.getId() > 0) {
				newsModel = newsService.findOne(newsModel.getId());
			}else{
				//Khong tim thay id news tuong ung. se xu ly sau
			}
			req.setAttribute("categories",iCategoryService.findAll());
			view = "/views/admin/news/edit.jsp";
		}
		req.setAttribute(SystemConstant.MODEL, newsModel);
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
