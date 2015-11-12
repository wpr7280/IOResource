package com.wpr.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import com.wpr.utils.ResourceUtils;
import com.wpr.utils.StringUtils;

/**
 * Url类型的资源{@link Resource}
 * <p>支持的类型包括标准的URL，同样也支持 “file:”协议的这种
 * @author wpr
 */
public class UrlResource extends AbstractFileResolvingResource{
	private final URL originUrl;
	private final URL cleanUrl;
	private final URI uri;
	/**
	 * 利用一个URL来构造
	 * @param originUrl
	 */
	public UrlResource(URL originUrl) {
		this.originUrl = originUrl;
		this.cleanUrl = getCleanUrl(originUrl,originUrl.toString());
		this.uri = null;
	}
	
	public UrlResource(URI uri) throws MalformedURLException {
		if(uri == null){
			throw new IllegalArgumentException("URI must not be null");
		}
		this.originUrl = uri.toURL();
		this.cleanUrl = getCleanUrl(this.originUrl,this.originUrl.toString());
		this.uri = uri;
	}
	public UrlResource(String path) throws MalformedURLException {
		if(path ==null){
			throw new IllegalArgumentException("URI must not be null");
		}
		this.originUrl = new URL(path);
		this.cleanUrl = getCleanUrl(originUrl, path);
		this.uri = null;
	}

	/**
	 * @param string
	 * @return
	 */
	private URL getCleanUrl(URL originUrl,String originalPath) {
		try {
			return new URL(StringUtils.cleanPath(originalPath));
		} catch (Exception e) {
			return originUrl;
		}
	}
	
	@Override
	public URL getURL() throws IOException {
		return this.originUrl;
	}
	@Override
	public URI getURI() throws IOException {
		if(this.uri==null){
			return this.uri;
		}else{
			return super.getURI();
		}
	}
	@Override
	public File getFile() throws IOException {
		if(this.uri!=null){
			return super.getFile(this.uri);
		}
		return super.getFile();
	}
	
	@Override
	public Resource createRelative(String relativePath) throws IOException {
		if(relativePath.startsWith("/")){
			relativePath = relativePath.substring(1);
		}
		return new UrlResource(new URL(this.originUrl,relativePath));
	}
	/**
	 * @see java.net.URL#getFile()
	 * @see java.io.File#getName()
	 */
	@Override
	public String getFilename()  {
		return new File(this.originUrl.getFile()).getName();
	}
	
	@Override
	public String getDescription() {
		return "URL :["+this.originUrl+"]";
	}
	/**
	  * <p>对于给定的URL返回其对应的InputStream
	  * @see java.net.URL#openConnection() 
	 */
	@Override
	public InputStream getInputStream() throws IOException {
		URLConnection conn = this.originUrl.openConnection();
		ResourceUtils.useCachesIfNecessary(conn);
		try {
			return conn.getInputStream();
		} catch (Exception e) {
			if(conn instanceof HttpURLConnection){
				((HttpURLConnection)conn).disconnect();
			}
			throw e;
		}
	}
}
