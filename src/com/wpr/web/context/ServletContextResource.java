package com.wpr.web.context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.servlet.ServletContext;

import com.wpr.io.AbstractFileResolvingResource;
import com.wpr.io.ContextResource;
import com.wpr.io.Resource;
import com.wpr.utils.ResourceUtils;
import com.wpr.utils.StringUtils;
import com.wpr.utils.WebUtils;
/**
 * {@link com.wpr.io.Resource} 的实现类，指向了{@link javax.servlet.ServletContext} 资源
 * <p>通常指向了web application的根路径
 * @author wpr
 *
 */
public class ServletContextResource extends AbstractFileResolvingResource implements ContextResource{
	
	private final ServletContext serlvetContext;
	private final String path;
	/**
	 * 注意的是对于path，使用相对路径的方式，就是说如果没有以/开头的将会补充/
	 * @param serlvetContext
	 * @param path
	 */
	public ServletContextResource(ServletContext serlvetContext, String path) {
		if(serlvetContext==null){
			throw new IllegalArgumentException("serlvetContext cannot be null");
		}
		this.serlvetContext = serlvetContext;
		if(path == null){
			throw new IllegalArgumentException("path is required");
		}
		String pathToUse = StringUtils.cleanPath(path);
		if(!pathToUse.startsWith("/")){
			pathToUse ="/"+pathToUse;
		}
		this.path = pathToUse;
	}

	public ServletContext getSerlvetContext() {
		return this.serlvetContext;
	}
	
	public String getPath() {
		return this.path;
	}
	
	@Override
	public boolean exists() {
		try {
			URL url = this.serlvetContext.getResource(this.path);
			return (url!=null);
		} catch (Exception e) {
			return false;
		}
	}
	@Override
	public boolean isReadable() {
		InputStream in = this.serlvetContext.getResourceAsStream(this.path);
		if(in!=null){
			try {
				in.close();
			} catch (Exception e) {
				//ignore
			}
			return true;
		}
		return false;
	}
	@Override
	public URL getURL() throws IOException {
		URL url = this.serlvetContext.getResource(this.path);
		if(url==null){
			throw new FileNotFoundException(getDescription()+"cannot be resolved to URL");
		}
		return url;
	}
	
	@Override
	public File getFile() throws IOException {
		URL url =this.serlvetContext.getResource(this.path);
		if(url!=null&&ResourceUtils.isFileURL(url)){
			return super.getFile();
		}else{
			String realPath = WebUtils.getRealPath(this.serlvetContext,this.path);
			return new File(realPath);
		}
	}
	
	@Override
	public String getFilename() {
		return StringUtils.getFilename(this.path);
	}
	
	@Override
	public String getDescription() {
		return "ServletContext resource ["+this.path+"]";
	}

	@Override
	public InputStream getInputStream() throws IOException {
		InputStream in = this.serlvetContext.getResourceAsStream(this.path);
		if(in==null){
			throw new FileNotFoundException(getDescription()+"could not open");
		}
		return in;
	}
	@Override
	public Resource createRelative(String relativePath) throws IOException {
		String pathToUse = StringUtils.applyRelativePath(this.path,relativePath);
		return new ServletContextResource(this.serlvetContext,pathToUse);
	}
	@Override
	public String getPathWithinContext() {
		return this.path;
	}

}
