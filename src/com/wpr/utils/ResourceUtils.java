package com.wpr.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * 用来解析文件的定位地址
 * @author wpr
 *
 */
public class ResourceUtils {
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
}
