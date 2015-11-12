package com.wpr.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.wpr.utils.ClassUtils;
import com.wpr.utils.StringUtils;
/**
 * {@link Resource}的具体实现类
 * <p>需要给定ClassLoader或者一个Class来加载资源
 * <p>这个类对外暴露出的仅仅是路径和ClassPath（方便类对象使用）
 * @author wpr
 *
 */
public class ClassPathResource extends AbstractFileResolvingResource{
	private final String path;
	private ClassLoader classLoader;
	private Class<?> clazz;
	/**
	 * <p>只给出路径，使用的classloader是the thread context class loader
	 * @param path
	 */
	public ClassPathResource(String path) {
		this(path,(ClassLoader)null);
	}
	/**
	 * 创建一个ClassPathResource
	 * <p>对于path :"file:core\\..\\.\\document\\a.txt",
	 * 首先将Windows的\\替换成为/，然后将..和.这种相对路径化简,
	 * 保留file:前缀
	 * <p>对于直接的形式“\\core\\”，和之前处理一样，只不过多一步去掉了开头的/
	 * @param path   path　the absolute path within the classpath
	 * @param classLoader the class loader to load the resource ,
	 * 	@{code null} for the thread context class loader
	 */
	public ClassPathResource(String path, ClassLoader classLoader) {
		if(path==null||"".equals(path)){
			throw new IllegalArgumentException("Path must be not null");
		}
		String pathToUse = StringUtils.cleanPath(path);
		if(pathToUse.startsWith("/")){
			pathToUse = pathToUse.substring(1);
		}
		this.path =path;
		this.classLoader = (classLoader!=null)?classLoader:ClassUtils.getDefaultClassLoader();
	}
	/**
	 * 利用给定的类来得到具体的资源类型
	 * @param path
	 * @param clazz
	 */
	public ClassPathResource(String path, Class<?> clazz) {
		this.path = StringUtils.cleanPath(path);
		this.clazz = clazz;
	}
	
	public ClassPathResource(String path, ClassLoader classLoader,
			Class<?> clazz) {
		this.path = path;
		this.classLoader = classLoader;
		this.clazz = clazz;
	}
	/**
	 * @see java.lang.ClassLoader#getResource(String)
	 * @see java.lang.Class#getResource(String)
	  */
	@Override
	public boolean exists() {
		URL url;
		if(this.clazz!=null){
			url = this.clazz.getResource(this.path);
		}else{
			url = this.classLoader.getResource(this.path);
		}
		return (url!=null);
	}
	
	@Override
	public String getDescription() {
		StringBuilder sb = new StringBuilder("class path resource [");
		String pathToUse = path;
		if(this.clazz!=null&&!pathToUse.startsWith("/")){
			sb.append(this.clazz);
			sb.append('/');
		}
		if(pathToUse.startsWith("/")){
			pathToUse = pathToUse.substring(1);
		}
		sb.append(pathToUse);
		sb.append("]");
		return sb.toString();
	}
	/**
	 * @see java.lang.ClassLoader#getResourceAsStream(String)
	 * @see java.lang.Class#getResourceAsStream(String)
	 */
	@Override
	public InputStream getInputStream() throws IOException {
		InputStream in = null;
		if(this.clazz!=null){
			in = this.clazz.getResourceAsStream(this.path);
		}else{
			in = this.classLoader.getResourceAsStream(this.path);
		}
		if(in==null){
			throw new FileNotFoundException(getDescription()+"cannot be opened ");
		}
		return in;
	}
	/**
	 * @see java.lang.ClassLoader#getResource(String)
	 * @see java.lang.Class#getResource(String)
	  */
	@Override
	public URL getURL() throws IOException {
		URL url = null;
		if(this.clazz!=null){
			url = this.clazz.getResource(this.path);
		}else{
			url = this.classLoader.getResource(this.path);
		}
		if(url ==null){
			throw new FileNotFoundException(getDescription()+"cannot be resolved to a URL");
		}
		return url;
	}
	/**
	 * 根据当前ClasspathResource和相对路径来创建一个新的ClassPathResource
	 * <p>相当于利用当前的classloader或者clazz来重新定位一个新的文件
	 */
	@Override
	public Resource createRelative(String relativePath) throws IOException {
		String pathToUse = StringUtils.applyRelativePath(this.path,relativePath);
		return new ClassPathResource(pathToUse,this.classLoader,this.clazz);
	}
	/**
	 * 返回文件名，去掉路径的那种
	 * <p>e.g. document/robot.txt -->robot.txt
	 */
	@Override
	public String getFilename() {
		return StringUtils.getFilename(this.path);
	}
	
	public final ClassLoader getClassLoader() {
		return (this.classLoader!=null)?this.classLoader:this.clazz.getClassLoader();
	}
	
	public final String getPath() {
		return this.path;
	}

}
