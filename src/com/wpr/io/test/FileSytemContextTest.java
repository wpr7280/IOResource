package com.wpr.io.test;

import java.io.IOException;

import org.junit.Test;

import com.wpr.io.FileSystemResourceLoader;
import com.wpr.io.Resource;

public class FileSytemContextTest {
	@Test
	public void test01() throws IOException{
		FileSystemResourceLoader loader = new FileSystemResourceLoader();
		Resource resource = loader.getResource("1.txt");
		System.out.println(resource.getURL());
	}
}
