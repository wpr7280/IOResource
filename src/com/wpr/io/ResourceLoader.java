package com.wpr.io;
/**
 * 策略设计去装载资源（通过class  path 或者file system）
 * 
 * <p>默认的实现类为{@link DefaultResourceLoader}
 * @author wpr
 *
 */
public interface ResourceLoader {
	String CLASSPATH_URL_PREFIX ="claspath:";
	/**
	 * 通过提供的资源location参数获取Resource实例，该实例可以是FileSystemResource、
	 * ClassPathResource、UrlResource等，但是该方法返回的Resource实例并不保证该
	 * Resource实例一定是存在的，需要调用exists()方法进行验证
	 * <p><ul>
	 * <li>支持URL的方式，e.g. “file:C:/test.dat”
	 * <li>支持classpath的方式 e.g. "classpath:test.dat"
	 * <li>支持相对路径的方式 e.g. "/WEB-INF/test.dat"
	 * </ul>
	 * @param location
	 * @return 返回自愿的具体句柄所在
	 */
	Resource getResource(String location);
	/**
	 * <p>在加载classpath下的资源作为参数传入{@link ClasspathResource}.
	 * 将ClassLoader暴露出来，对于想要获取ResourceLoader使用的ClassLoader的场景来说，
	 * 可以直接调用该方法得到，而不是依赖于Thread Context ClassLoader
	 * @return the ClassLoader (never {@code null})
	 */
	ClassLoader getClassLoader();
}
