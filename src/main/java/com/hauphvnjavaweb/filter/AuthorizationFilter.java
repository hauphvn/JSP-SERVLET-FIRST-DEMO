package com.hauphvnjavaweb.filter;

import com.hauphvnjavaweb.constant.SystemConstant;
import com.hauphvnjavaweb.model.UserModel;
import com.hauphvnjavaweb.utils.SessionUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter implements Filter {

    private ServletContext context;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();//Xay dung he thong filter len
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //Tim ra url /cai gi do
        StringBuffer url = request.getRequestURL();//TODO Why not StringBuider or String
        if(url.indexOf("/admin") != -1){
            UserModel userModel = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
            if(userModel != null){
                if(userModel.getRole().getCode().equals(SystemConstant.USER_ADMIN)){
                    chain.doFilter(servletRequest,servletResponse);
                }else if (userModel.getRole().getCode().equals(SystemConstant.USER_USER)){
                    response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=not_permission&alert=danger");
                }
            }else{
                response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=not_login&alert=danger");
            }
        }else{
            chain.doFilter(servletRequest,servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}

