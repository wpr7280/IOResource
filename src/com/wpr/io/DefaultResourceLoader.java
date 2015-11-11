package com.wpr.io;
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
		this.classLoader = DefaultResourceLoader.getDefaultClassLoader();
	}
	public static ClassLoader getDefaultClassLoader(){
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		if(cl==null){
			cl = DefaultResourceLoader.class.getClassLoader();
		}
		return cl;
	}
	public DefaultResourceLoader(ClassLoader classloader) {
		this.classLoader = classloader;
	}
	/**
	 *<p> 获得资源，首先判断是否ClassPath类型（classpath:前缀型）
	 *否则判断为UrlResource，如果还不行，
	 */
	@Override
	public Resource getResource(String location) {
		if(location==null){
			throw new IllegalArgumentException("Location must not be null");
		}
		if(location.startsWith(CLASSPATH_URL_PREFIX)){
			//return new Class
		}
		return null;
	}

	public ClassLoader getClassLoader() {
		return (this.classLoader!=null)?this.classLoader:DefaultResourceLoader.getDefaultClassLoader();
	}

	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}



}
