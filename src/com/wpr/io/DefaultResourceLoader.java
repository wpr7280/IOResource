package com.wpr.io;

import java.net.URL;

import com.wpr.utils.ClassUtils;

/**
 * <p>ResourceLoader默认实现类
 * <p>如果location是一个URL形式的，将会返回{@link UrlResource},
 * 如果是非URL形式的，将会返回一个{@link ClassPathResource}
 * @see ResourceLoader
 * @author wpr
 *
 */
public class DefaultResourceLoader implements ResourceLoader {
	private ClassLoader classLoader;
	
	public DefaultResourceLoader() {
		this.classLoader = ClassUtils.getDefaultClassLoader();
	}
	public DefaultResourceLoader(ClassLoader classloader) {
		this.classLoader = classloader;
	}
	/**
	 *<p> 获得资源，首先判断是否ClassPath类型（classpath:前缀型）
	 *否则判断为UrlResource，如果还不行，则认为是资源型文件（file）
	 */
	@Override
	public Resource getResource(String location) {
		if(location==null){
			throw new IllegalArgumentException("Location must not be null");
		}
		if(location.startsWith(CLASSPATH_URL_PREFIX)){
			return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()),getClassLoader());
		}else{
			try {
				URL  url = new URL(location);
				return new UrlResource(url);
			} catch (Exception e) {
				
			}
		}
		
		return null;
	}

	public ClassLoader getClassLoader() {
		return (this.classLoader!=null)?this.classLoader:ClassUtils.getDefaultClassLoader();
	}

	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

}
