package com.wpr.bean;
/**
 * <p>实际上就是元数据描述，对应于xml的数据描述，
 * 比如说<beans>下面有<bean><import><alias>等标签，
 * @author wpr
 *
 */
public interface BeanMetadataElement {
	Object getSource();
}
