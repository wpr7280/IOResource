package com.wpr.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import com.wpr.utils.StringUtils;
/**
 * {@link Resource} 的实现，{@code java.io.File}文件资源
 * @author wpr
 *
 */
public class FileSystemResource extends AbstractResource implements
		WritableResource {
	
	private final File file ;
	private final String path;
	
	public FileSystemResource(File file) {
		if(file==null){
			throw new RuntimeException();
		}
		this.file = file;
		this.path = StringUtils.cleanPath(file.getPath());
	}
	
	public FileSystemResource(String path) {
		this(new File(path));
	}
	
	public final String getPath() {
		return this.path;
	}

	@Override
	public boolean exists() {
		return this.file.exists();
	};
	/**
	 * @return {@code true}文件是可读的 {@code false}目录不可读
	 */
	@Override
	public boolean isReadable() {
		return file.canRead()&&!file.isDirectory();
	};
	@Override
	public String getDescription() {
		return "file ["+this.file.getAbsolutePath()+"]";
	}
	/**
	 * @param relativePath
	 * @return
	 * @throws IOException
	 */
	@Override
	public Resource createRelative(String relativePath) throws IOException {
		String pathToUse = StringUtils.applyRelativePath(this.path,relativePath);
		return new FileSystemResource(pathToUse);
	}
	@Override
	public URL getURL() throws IOException {
		return this.file.toURI().toURL();
	};
	@Override
	public java.net.URI getURI() throws IOException {
		return this.file.toURI();
	};
	@Override
	public File getFile() throws IOException {
		return this.file;
	};
	@Override
	public long contentLength() throws IOException {
		return this.file.length();
	};
	@Override
	public String getFilename() {
		return this.file.getName();
	};
	
	@Override
	public InputStream getInputStream() throws IOException {
		return new FileInputStream(this.file);
	}

	@Override
	public boolean isWritable() {
		return this.file.canWrite()&&!this.file.isDirectory();
	}

	@Override
	public OutputStream getOutputStream() throws IOException {
		return new FileOutputStream(this.file);
	}

}
