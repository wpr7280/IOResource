package com.wpr.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * 用来解析文件的定位地址
 * @author wpr
 *
 */
public class ResourceUtils {
	/**file 类型的URL前缀*/
	public static final String FILE_URL_PREFIX ="file:";
	/**classpath类型的URL前缀	 */
	public static final String CLASSPATH_URL_PREFIX = "classpath:";
	/**File类型的URL协议*/
	public static final String URL_PROTOCOL_FILE ="file";
	
	/**
	 * 对于给定的URL创建一个URI实例
	 * @param url
	 * @return
	 * @throws URISyntaxException
	 */
	public static URI toURI(URL url) throws URISyntaxException{
		return toURI(url.toString());
	}
	/**
	 * 根据给定的字符串创建一个URI实例
	 * <p>首先要先将location中的空格替换成%20
	 * @param location
	 * @return
	 * @throws URISyntaxException
	 */
	public static URI toURI(String location) throws URISyntaxException{
		return new URI(StringUtils.replace(location, " ","%20"));
	}
	/**
	 * 将给定的URL解析成为 {@code java.io.File}
	 * @param url   	待解析的URL
	 * @param description 资源的描述 
	 * @return 
	 * @throws FileNotFoundException 如果url不能被解析为一个File
	 */
	public static File getFile(URL url, String description) throws FileNotFoundException {
		if(url==null){
			throw new IllegalArgumentException("Resource URL must not be null");
		}
		if(!URL_PROTOCOL_FILE.equals(url.getProtocol())){
			throw new FileNotFoundException(description+"cannot be resolved to file path");
		}
		//TODO
		return null;
	}
	public static void main(String[] args) throws MalformedURLException {
		URL url =new URL("file:\\D:\\Tomcat\\RUNNINT.txt");
		System.out.println(url.toString());
	}
}
