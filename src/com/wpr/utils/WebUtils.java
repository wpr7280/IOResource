package com.wpr.utils;

import java.io.FileNotFoundException;

import javax.servlet.ServletContext;

public class WebUtils {
	/**
	 * 返回给定path的web应用程序中的真实路径，由servlet容器提供
	 * @param servletContext
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 * @see {@link javax.servlet.ServletContext#getRealPath(String)}
	 */
	public static String getRealPath(ServletContext servletContext,
			String path) throws FileNotFoundException {
		if(servletContext==null){
			throw new IllegalArgumentException("ServletContext cannot be null");
		}
		if(!path.startsWith("/")){
			path = "/"+path;
		}
		String realPath = servletContext.getRealPath(path);
		if(realPath==null){
			throw new FileNotFoundException(path+"cannot be resolved to absolute file path");
		}
		return realPath;
	}

}
