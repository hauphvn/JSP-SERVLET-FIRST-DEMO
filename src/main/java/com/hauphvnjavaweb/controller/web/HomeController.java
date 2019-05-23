package com.hauphvnjavaweb.controller.web;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hauphvnjavaweb.model.UserModel;
import com.hauphvnjavaweb.service.ICategoryService;
import com.hauphvnjavaweb.service.INewService;
import com.hauphvnjavaweb.service.IUserService;
import com.hauphvnjavaweb.utils.FormUtil;
import com.hauphvnjavaweb.utils.SessionUtil;

@WebServlet(urlPatterns = {"/trang-chu","/dang-nhap","/thoat"})
public class HomeController extends HttpServlet {

	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
	private static final long serialVersionUID = -1569707547782560482L;
	@Inject
	private ICategoryService categoryService;
	@Inject
	private INewService newsService;
	@Inject
	private IUserService userService;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		String message = req.getParameter("message");
		String alert = req.getParameter("alert");
		if(message!= null && alert!= null){
			req.setAttribute("message", resourceBundle.getString(message));
			req.setAttribute("alert",alert);
		}
		if(action != null && action.equals("login")) {
			RequestDispatcher rd = req.getRequestDispatcher("/views/login.jsp");
			rd.forward(req, resp);
		}else if(action != null && action.equals("logout")) {
			SessionUtil.getInstance().removeValue(req,"USERMODEL");
			resp.sendRedirect(req.getContextPath()+"/trang-chu");
		}else {
			RequestDispatcher rd = req.getRequestDispatcher("/views/web/home.jsp");
			rd.forward(req, resp);
		}
	}

	@Override 	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if(action != null && action.equals("login")) {
			UserModel userModel = FormUtil.toModel(UserModel.class, req);
			userModel = userService.findByUsernameAndPasswordAndStatus(userModel.getUsername(), userModel.getPassword(), 1);
			if(userModel != null) {
				SessionUtil.getInstance().putValue(req,"USERMODEL", userModel);
				if(userModel.getRole().getCode().equals("USER")){
					resp.sendRedirect(req.getContextPath()+"/trang-chu");
				}else if(userModel.getRole().getCode().equals("ADMIN")) {
					resp.sendRedirect(req.getContextPath()+"/admin-home");
				}
			}else {
				resp.sendRedirect(req.getContextPath()+"/dang-nhap?action=login&message=username_password_invalid&alert=danger");
			}
		}
	}
}
