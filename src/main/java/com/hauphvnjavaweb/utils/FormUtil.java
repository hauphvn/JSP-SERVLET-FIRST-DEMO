package com.hauphvnjavaweb.utils;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtilsBean;

public class FormUtil {
	public static <T> T toModel(Class<T> tClass, HttpServletRequest req) {
			T object = null;
				try {
					object = tClass.getConstructor().newInstance();
					BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
					beanUtilsBean.populate(object, req.getParameterMap());
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return object;
		
	}
}
