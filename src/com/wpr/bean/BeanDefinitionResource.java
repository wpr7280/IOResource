package com.wpr.bean;

import java.io.IOException;
import java.io.InputStream;

import com.wpr.io.AbstractResource;

/**
 * @{link Resource}的实现类，表示一个BeanDefinition对象
 * @author wpr
 *
 */
public class BeanDefinitionResource extends AbstractResource{
	
	private final BeanDefinition beanDefinition;
	
	public BeanDefinitionResource(BeanDefinition beanDefinition) {
		this.beanDefinition = beanDefinition;
	}

	@Override
	public String getDescription() {
		//TODO 
		return null;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		//TODO
		return null;
	}

}
