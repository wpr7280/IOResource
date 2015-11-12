package com.wpr.io;

import java.io.IOException;
import java.io.InputStream;
/**
 * {@link Resource}的实现类，InputStream类型的Resource
 * @author wpr
 *
 */
public class InputStreamResource extends AbstractResource {
	private final InputStream inputStream;
	private final String description;
	boolean isRead = false;
	
	
	public InputStreamResource(InputStream inputStream) {
		this(inputStream,"load inputstream");
	}

	public InputStreamResource(InputStream inputStream, String description) {
		if(inputStream==null){
			throw new IllegalArgumentException("inputStream cannot be null");
		}
		this.inputStream = inputStream;
		this.description = (description!=null)?description:"";
	}
	@Override
	public boolean exists() {
		return true;
	}
	@Override
	public boolean isOpen() {
		return true;
	}
	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		if(this.isRead){
			throw new IllegalStateException("this resource already be reading");
		}
		this.isRead = true;
		return this.inputStream;
	}

}
