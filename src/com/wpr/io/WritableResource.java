package com.wpr.io;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 继承自资源接口用来表示可写属性
 * @author wpr
 * 
 */
public interface WritableResource extends Resource {
	/**
	 * 返回资源是否可以被修改
	 * <p>will be {@code true}对于typical descripors
	 */
	boolean isWritable();
	/**
	 * 对于给定的资源返回{@link OutputStream}
	 * @throws IOException 如果stream不能被打开的话
	 */
	OutputStream getOutputStream() throws IOException;
}
