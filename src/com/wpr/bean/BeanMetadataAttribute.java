package com.wpr.bean;
/**
 * BeanDefinition定义中的属性键值对容器
 * @author wpr
 *
 */
public class BeanMetadataAttribute implements BeanMetadataElement {
	private final String name;
	private final Object value;
	private Object object;
	
	public BeanMetadataAttribute(String name, Object value) {
		if(name==null){
			throw new IllegalArgumentException("name must be not null");
		}
		this.name = name;
		this.value = value;
	}

	@Override
	public Object getSource() {
		return this.object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	public Object getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
}
