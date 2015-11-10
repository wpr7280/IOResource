package com.wpr.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * InputStreamSource封装任何能够返回InputStream的类
 * <ul>包括：
 * 		<li>File类</li>
 * 		<li>classpath下的资源</li>
 * 		<li>Byte Array等</li>
 * </ul>
 * 这个类也是资源抽象类Resource {@link Resource}的父类
 * @author wpr
 * @since 11.09.2015
 * @see Resource
 */
public interface InputStreamSource {
	/**
	 * 用来返回一个输入流
	 * @return 底层资源的InputStream （must not be {@code null}）
	 * @throws IOException	if stream could not be opened
	 */
	InputStream getInputStream() throws IOException;
}
