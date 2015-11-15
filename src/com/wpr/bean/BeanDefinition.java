package com.wpr.bean;
/**
 * Bean 实例的描述接口，一个Bean的实例应当至少存在属性值、构造参数值，
 * 更多的信息应当有具体的实现类来提供
 * <p>这只是一个最简单的接口，主要功能是允许{@link BeanFactoryPostProcesor},例如
 * {@link PropertyPlaceholderConfigurer}能够检索和修改属性值或者其他的元数据
 * @author wpr
 *
 */
public interface  BeanDefinition {

}
