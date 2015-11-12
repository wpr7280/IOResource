package com.wpr.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
/**
 * {@link Resource}的实现类，是ByteArray型的资源类表示
 * @author wpr
 *
 */
public class ByteArrayResource extends AbstractResource {
	private final byte[] byteArray;
	private final String descption;
	
	public ByteArrayResource(byte[] byteArray){
		this(byteArray, "load byte array");
	}
	/**
	 * 根据给定参数创建一个ByteArrayResource
	 * @param byteArray
	 * @param descption
	 */
	public ByteArrayResource(byte[] byteArray, String descption) {
		if(byteArray == null){
			throw new IllegalArgumentException("byte array must not be null");
		}
		this.byteArray = byteArray;
		this.descption = (descption!=null)?descption:"";
	}
	public final byte[] getByteArray() {
		return byteArray;
	}
	
	@Override
	public boolean exists() {
		return true;
	}
	
	@Override
	public long contentLength() throws IOException {
		return this.byteArray.length;
	}
	
	@Override
	public String getDescription() {
		return this.descption;
	}
	/**
	 * 对于byte[]型的变量输出为ByteArrayInputStream
	 */
	@Override
	public InputStream getInputStream() throws IOException {
		return new ByteArrayInputStream(this.byteArray);
	}

}
