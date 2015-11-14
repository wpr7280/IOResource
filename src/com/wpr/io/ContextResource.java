package com.wpr.io;
/**
 * {@link Resource} 的实现类，用于向外暴露路径的接口
 * <p>对于{@link javax.servlet.ServletContext}或者{@link javax.portlet.PortletContext}
 * 和普通的classpath型或相对路径型的文件系统(没有file:这种前缀的)返回path
 * <p>e.g. 和ClasspathResource(需要制定path和classloader)，此接口可以用来对外暴露path
 * @author wpr
 *
 */
public interface ContextResource extends Resource {
	/**
	 * 返回context的path
	 * @return
	 */
	String getPathWithinContext();
	
}
