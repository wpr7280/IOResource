package com.wpr.bean;

import com.wpr.utils.ClassUtils;

/**
 * BeanDefinition的模板
 * @author wpr
 *
 */
public  class GenericBeanDefinition extends BeanMetadataAttributeAccessor
	implements BeanDefinition{
	/*和构造器相关的参数*/
	private ConstructorArgumentValues constructorArgumentValues;
	/*和属性注入有关的参数*/
	private MutablePropertyValues propertyValues;
	
	private volatile Object beanClass;

	public GenericBeanDefinition() {
		this(null,null);
	}
	public GenericBeanDefinition(
			ConstructorArgumentValues constructorArgumentValues,
			MutablePropertyValues propertyValues) {
		setConstructorArgumentValues(constructorArgumentValues);
		setPropertyValues(propertyValues);
	}
	/**
	 * @return 该Bean是否存在构造方法注入
	 */
	boolean hasConstructorArgumentValuse(){
		return !constructorArgumentValues.isEmpty();
	}
	
	public Class<?> resolveBeanClass(ClassLoader classLoader) throws ClassNotFoundException {
		String className = getBeanClassName();
		if (className == null) {
			return null;
		}
		Class<?> resolvedClass = ClassUtils.forName(className, classLoader);
		this.beanClass = resolvedClass;
		return resolvedClass;
	}
	public void setConstructorArgumentValues(
			ConstructorArgumentValues constructorArgumentValues) {
		this.constructorArgumentValues =( constructorArgumentValues!=null)?constructorArgumentValues:null;
	}

	public MutablePropertyValues getPropertyValues() {
		return propertyValues;
	}

	public void setPropertyValues(MutablePropertyValues propertyValues) {
		this.propertyValues = (propertyValues!=null)?propertyValues:null;
	}

	public ConstructorArgumentValues getConstructorArgumentValues() {
		return constructorArgumentValues;
	}
	public Class<?> getBeanClass() {
		Object beanClassObject = this.beanClass;
		if (beanClassObject == null) {
			throw new IllegalStateException("No bean class specified on bean definition");
		}
		if (!(beanClassObject instanceof Class)) {
			throw new IllegalStateException(
					"Bean class name [" + beanClassObject + "] has not been resolved into an actual Class");
		}
		return (Class<?>) beanClassObject;
	}
	
	public void setBeanClass(Object beanClass) {
		this.beanClass = beanClass;
	}
	
	public void setBeanClassName(String beanClassName) {
		this.beanClass = beanClassName;
	}

	public String getBeanClassName() {
		Object beanClassObject = this.beanClass;
		if (beanClassObject instanceof Class) {
			return ((Class<?>) beanClassObject).getName();
		}
		else {
			return (String) beanClassObject;
		}
	}
	
}
