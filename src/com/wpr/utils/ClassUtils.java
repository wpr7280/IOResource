package com.wpr.utils;

public class ClassUtils {
	/**
	 * 返回默认的ClassLoader：
	 * <p>如果可以的话，优先返回thread context classloader
	 * @return
	 */
	public static ClassLoader getDefaultClassLoader(){
		ClassLoader cl =null;
		try {
			cl = Thread.currentThread().getContextClassLoader();
		} catch (Exception e) {
			//没能获得thread context classloader
		}
		if(cl == null){
			cl = ClassUtils.class.getClassLoader();
		}
		return cl;
	}
}
