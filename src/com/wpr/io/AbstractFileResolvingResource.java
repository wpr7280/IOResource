package com.wpr.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

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
		return super.exists();
	}

	@Override
	public boolean isReadable() {
		// TODO Auto-generated method stub
		return super.isReadable();
	}

	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
		return super.isOpen();
	}

	@Override
	public URL getURL() throws IOException {
		// TODO Auto-generated method stub
		return super.getURL();
	}

	@Override
	public URI getURI() throws IOException {
		return super.getURI();
	}

	@Override
	public File getFile() throws IOException {
		URL url = getURL();
		return ResourceUtils.getFile(url,getDescription());
	}

	@Override
	public long contentLength() throws IOException {
		// TODO Auto-generated method stub
		return super.contentLength();
	}

	@Override
	public long lastModified() throws IOException {
		// TODO Auto-generated method stub
		return super.lastModified();
	}

	@Override
	public Resource createRelative(String relativePath) throws IOException {
		// TODO Auto-generated method stub
		return super.createRelative(relativePath);
	}

	@Override
	public String getFilename() {
		// TODO Auto-generated method stub
		return super.getFilename();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}


}
