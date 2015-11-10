package com.wpr.io;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

/**
 *  资源描述接口，底层资源的抽象
 *  
 *  <p> 对于每一种具体形式的资源，可以得到InputStream，不是URL或者
 *  文件句柄Handle仅能返回资源表示。确切的行为是由具体的实现类来定的
 * @author wpr
 * @since 09.11.2015
 */
public interface Resource extends InputStreamSource{
	/**
	 * 返回这个资源是否是具体形式的，可以得到InputSteam的那种
	 * @return
	 */
	boolean exists();
	/**
	 * 资源是否可读
	 * {@code true} 资源可读，但是需要注意在读取的时候仍然可能会失败
	 * {@code false}资源不可读
	 * @return
	 */
	boolean isReadable();
	
	/**
	 * @return
	 * 	{@code true} 资源已经被打开，不能被同时读写，必须等使用者
	 * 	关闭以后才可以开始读，以防止resource leak
	 * 	{@code false}  	典型的资源返回
	 */
	boolean isOpen();
	/**
	 *	返回资源的URL句柄 
	 * @throws IOException 如果这个资源不能被解析成URL，
	 * i.e. 比如这个资源不是有效的描述符
	 */
	URL getURL() throws IOException;
	/**
	 *	返回资源的URI句柄 
	 * @throws IOException 如果这个资源不能被解析成URI，
	 * i.e. 比如这个资源不是有效的描述符
	 */
	URI getURI() throws IOException;
	/**
	 * 返回资源的文件句柄 
	 * @throws IOException 如果这个文件不能被解析成File，
	 * i.e. 如果这个资源在文件系统中不可用
	 */
	File getFile() throws IOException;
	/**
	 * 返回资源的长度
	 * @throws IOException 如果资源不能被正确处理
	 */
	long contentLength() throws IOException;
	/**
	 * 返回资源最后被修改的timestamp
	 * @throws IOException
	 */
	long lastModified() throws IOException;
	/**
	 *	创建一个资源文件 
	 * @param relativePath 相对路径
	 * @return 资源返回句柄
	 * @throws IOException 
	 */
	Resource createRelative(String relativePath) throws IOException;
	/**
	 * <p>@return {@code nulll} 如果这类资源不存在
	 */
	String getFilename();
	/**
	 * 返回资源的描述
	 * @return
	 */
	String getDescription();
	
}	
