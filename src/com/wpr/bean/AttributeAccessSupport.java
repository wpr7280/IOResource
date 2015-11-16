package com.wpr.bean;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * {@link AttributeAccessor}的默认实现类
 * <p>基础的实现，其子类可以拓展这个类
 * @author wpr
 *
 */
public class AttributeAccessSupport implements AttributeAccessor{
	
	private final Map<String,Object> attributes = new LinkedHashMap<>();
	@Override
	public void setAttribute(String name, Object value) {
		if(name==null){
			throw new IllegalArgumentException("name must not be null");
		}
		if(value!=null){
			this.attributes.put(name, value);
		}else{
			this.removeAttribute(name);
		}
	}

	@Override
	public Object getAttribute(String name) {
		if(name==null){
			throw new IllegalArgumentException("name must not be null");
		}
		return this.attributes.get(name);
	}

	@Override
	public Object removeAttribute(String name) {
		if(name==null){
			throw new IllegalArgumentException("name must not be null");
		}
		return this.attributes.remove(name);
	}

	@Override
	public boolean hasAttribute(String name) {
		if(name==null){
			throw new IllegalArgumentException("name must not be null");
		}
		return this.attributes.containsKey(name);
	}

	@Override
	public String[] attributeNames() {
		return this.attributes.keySet().toArray(new String[this.attributes.size()]);
	}

}
