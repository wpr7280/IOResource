package com.wpr.io;

import javax.servlet.ServletContext;

import com.wpr.web.context.ServletContextResource;
/**
 * @author wpr
 * @see ServletContextResource
 */
public class ServletContextResourceLoader extends DefaultResourceLoader{
	private final ServletContext servletContext;
	
	public ServletContextResourceLoader(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Override
	protected Resource getResourceByPath(String location) {
		return new ServletContextResource(this.servletContext,location);
	}
}
