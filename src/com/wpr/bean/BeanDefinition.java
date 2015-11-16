package com.wpr.bean;

/**
 * Bean 实例的描述接口，一个Bean的实例应当至少存在属性值、构造参数值，
 * 更多的信息应当有具体的实现类来提供
 * 里面的内容太多了，简单的实现几个基本的属性
 * @author wpr
 *
 */
public interface  BeanDefinition extends AttributeAccessor,BeanMetadataElement{
	/**
	 * <p>获取到bean的classname，对应于xml中<bean>标签中class
	 * @return
	 */
	String getBeanClassName();
	void setBeanClassName(String beanClassName);
	/**
	 * 构造注入的参数
	 * @return
	 */
	ConstructorArgumentValues getConstructorArgumentValues();
	/**
	 * 属性注入的参数
	 * @return
	 */
	MutablePropertyValues getPropertyValues();
}
