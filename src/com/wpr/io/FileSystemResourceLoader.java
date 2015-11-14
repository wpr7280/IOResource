package com.wpr.io;
/**
 * {@link ResourceLoader}的实现类
 * {@link DefaultResourceLoader}的子类，主要是改变了对于"document/file"这种策略
 * <p>在DefaultResourceLoader中默认的策略是ClassPathContextResource，
 * 而对于FileSystemResourceLoader的策略是实现成为FileSystemContextResource
 * @author wpr
 *
 */
public class FileSystemResourceLoader extends DefaultResourceLoader{
	/**
	 * 将locationl作为filesystem的path
	 * <p>注意：如果文件路径是以/开头的，它将被解释为当前VM工作目录
	 */
	@Override
	protected Resource getResourceByPath(String location) {
		if(location !=null && location.startsWith("/")){
			location = location.substring(1);
		}
		return new FileSystemContextResource(location);
	}
	
	private class FileSystemContextResource extends FileSystemResource implements ContextResource{
		public FileSystemContextResource(String path) {
			super(path);
		}
		@Override
		public String getPathWithinContext() {
			return this.getPath();
		}
	}
}
