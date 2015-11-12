package com.wpr.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import com.wpr.utils.ResourceUtils;

/**
 * 这个抽象类的作用是：将URL类型转化成为文件（File）型抽象资源
 * e.g. {@link UrlResource} or {@link ClassPathResource}
 * @author wpr
 *
 */
public abstract class AbstractFileResolvingResource extends AbstractResource{

	@Override
	public boolean exists() {
		try {
			URL url = getURL();
			if(ResourceUtils.isFileURL(url)){
				//文件类型的比较好处理
				return getFile().exists();
			}else{
				//测试URL连接
				URLConnection conn = url.openConnection();
				ResourceUtils.useCachesIfNecessary(conn);
				HttpURLConnection httpConn =(conn instanceof HttpURLConnection?(HttpURLConnection)conn:null);
				if(httpConn !=null){
					httpConn.setRequestMethod("HEAD");
					int code = httpConn.getResponseCode();
					if(code ==HttpURLConnection.HTTP_OK){
						return true;
					}else if(code == HttpURLConnection.HTTP_NOT_FOUND){
						return false;
					}					
				}
				if(conn.getContentLength()>=0){
					return true;
				}
				if(httpConn!=null){
					//没有收到HTTP OK 或者NOT_FOUND的状态码，并且content-length==0
					httpConn.disconnect();
					return false;
				}else{
					//最后实在没办法了，测试是否可以打开stream
					InputStream in = getInputStream();
					in.close();
					return true;
				}
			}
		} catch (IOException e) {
			//打不开stream或者有其他的IOException的时候触发
			return false;
		}
	}

	@Override
	public boolean isReadable() {
		try {
			URL url = this.getURL();
			if(ResourceUtils.isFileURL(url)){
				File file = this.getFile();
				return (file.canRead()&&!file.isDirectory());
			}else{
				return true;
			}
		} catch (IOException e) {
			return false;
		}
	}

	@Override
	public File getFile() throws IOException {
		URL url = getURL();
		return ResourceUtils.getFile(url,getDescription());
	}
	/**
	 * <p>对于文件类型的，返回文件的长度；
	 * <p>对于URL连接的，返回连接内容的长度
	 * @return
	 * @throws IOException URL格式不对，或者Http连接不可达
	 */
	@Override
	public long contentLength() throws IOException {
		URL url = getURL();
		if(ResourceUtils.isFileURL(url)){
			return getFile().length();
		}else{
			URLConnection con = url.openConnection();
			ResourceUtils.useCachesIfNecessary(con);
			if (con instanceof HttpURLConnection) {
				((HttpURLConnection) con).setRequestMethod("HEAD");
			}
			return con.getContentLength();
		}
	}

	@Override
	public long lastModified() throws IOException {
		URL url = getURL();
		if(ResourceUtils.isFileURL(url)){
			return super.lastModified();
		}else{
			URLConnection conn = url.openConnection();
			ResourceUtils.useCachesIfNecessary(conn);
			if(conn instanceof HttpURLConnection){
				((HttpURLConnection)conn).setRequestMethod("HEAD");
			}
			return conn.getLastModified();
		}
	}

	protected File getFile(URI uri) throws FileNotFoundException {
		return ResourceUtils.getFile(uri,getDescription());
	}

}
