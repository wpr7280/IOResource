package com.wpr.bean;
/**
 * 保存构造方法的参数值，特别是作为Beandefinition的一部分
 * <p>这个是一个<bean>标签内部对应的所有的构造器参数的集合的表示，
 * 在内部维护了一个Map来存储所有和<construct-arg>有关的值
 * 
 * @author wpr
 *
 */
public class ConstructorArgumentValues {

	public boolean isEmpty() {
		return false;
	}
	

}
