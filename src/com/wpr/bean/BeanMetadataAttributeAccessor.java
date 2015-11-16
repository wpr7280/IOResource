package com.wpr.bean;
/**
 * @{link AttributeAccessSupport}扩展，同时也实现了BeanMetadataElement接口
 * @author wpr
 *
 */
public class BeanMetadataAttributeAccessor extends AttributeAccessSupport implements BeanMetadataElement{
	private Object source;
	
	@Override
	public Object getSource() {
		return this.source;
	}
	public void setSource(Object source) {
		this.source = source;
	}

	public void addMetadataAttribute(BeanMetadataAttribute attribute){
		super.setAttribute(attribute.getName(),attribute.getValue());
	}
	
	public BeanMetadataAttribute getMetaAttribute(String name){
		return (BeanMetadataAttribute)super.getAttribute(name);
	}
	
	@Override
	public void setAttribute(String name, Object value) {
		super.setAttribute(name,new BeanMetadataAttribute(name, value));
	}
	
	@Override
	public Object getAttribute(String name) {
		BeanMetadataAttribute attribute = (BeanMetadataAttribute) super.getAttribute(name);
		return (attribute!=null)?attribute.getValue():null;
	}
	
	@Override
	public Object removeAttribute(String name) {
		BeanMetadataAttribute attribute =(BeanMetadataAttribute) super.removeAttribute(name);
		return (attribute!=null)?attribute.getValue():null;
	}
}
