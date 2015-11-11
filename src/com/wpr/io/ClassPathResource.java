package com.wpr.io;

import java.io.IOException;
import java.io.InputStream;
/**
 * {@link Resource}的具体实现类
 * <p>需要给定ClassLoader或者一个Class来加载资源
 * @author wpr
 *
 */
public class ClassPathResource extends AbstractFileResolvingResource{
	private final String path;
	private ClassLoader classLoader;
	private Class<?> clazz;
	
	public ClassPathResource(String path) {
		this(path,(ClassLoader)null);
	}

	public ClassPathResource(String path, ClassLoader classLoader) {
		//TODO
		this.path =path;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}


}
