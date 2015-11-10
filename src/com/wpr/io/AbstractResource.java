package com.wpr.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import com.wpr.utils.ResourceUtils;

/**
 * 抽象类，目的是为了方便{@link Resource}的实现
 * <p>"exists"方法仅检查File或者InputStream时候可以打开;
 * 		“isOpen”总是返回@{code false}
 * 		“getURL” 和“getFile” throw an excepiton;
 * 		"getDescription"返回toString的内容
 * @author wpr
 *
 */
public abstract class AbstractResource implements Resource{
	/**
	 * <p>对于文件操作返回是否存在，对于目录型资源看是否可以得到IputStream
	 *
	 */
	@Override
	public boolean exists() {
		try {
			//文件型
			return getFile().exists();
		} catch (IOException e) {
			try {
				//其他类型的Resource
				InputStream in = getInputStream();
				in.close();
				return true;
			} catch (Exception e1) {
				return false;
			}
		}
	}
	/**
	 * always return {@code true}
	 */
	@Override
	public boolean isReadable() {
		return true;
	}
	/**
	 * always return {@code false}
	 */
	@Override
	public boolean isOpen() {
		return false;
	}
	/**
	 * 返回资源的URL
	 * <p>这里实现将会抛出FileNotFoundException,
	 * 假定资源不能被解析为URL
	 */
	@Override
	public	URL getURL() throws IOException{
		throw new FileNotFoundException(getDescription()+"cannot be resolved to URL");
	}
	/**
	 * 基于URL的返回来构造一个URI
	 * @{link #getURL()} 
	 */
	@Override
	public URI getURI() throws IOException {
		URL url = getURL();
		try{
			return ResourceUtils.toURI(url);
		}catch(URISyntaxException e){
			throw new IOException("Invalid URI "+url+ "\n Exception:",e);
		}
	}
	/**
	 * 抛出异常
	 */
	@Override
	public File getFile() throws IOException {
		throw new FileNotFoundException(getDescription()+"cannot be resolved to an absolute file path");
	}
	/**
	 * 得到InputStream，并且计算内容的长度
	 * @see #getInputSteam()
	 */
	@Override
	public long contentLength() throws IOException {
		InputStream in = this.getInputStream();
		if(in==null){
			throw new FileNotFoundException("resource inputstream must not be null");
		}
		try{
			long size =0;
			byte[] buffer = new byte[1024];
			int read;
			while((read=in.read(buffer))!=-1){
				size+=read;
			}
			return size;
		}finally{
			try {
				in.close();
			} catch (Exception e) {
			}
		}
		
	}
	@Override
	public long lastModified() throws IOException {
		long lastModified = getFile().lastModified();
		if(lastModified==0L){
			throw new FileNotFoundException(getDescription()+"cannot be resolved in file system");
		}
		return lastModified;
	}
	/**
	 * 假定相对资源不能被创建，直接抛出异常
	 */
	@Override
	public Resource createRelative(String relativePath) throws IOException {
		throw new FileNotFoundException("cannot create a relative resource");
	}
	/**
	 * 直接返回{@code null}
	 */
	@Override
	public String getFilename() {
		return null;
	}
	/**
	 * @see #getDescription()
	 */
	@Override
	public String toString(){
		return getDescription();
	}
}
