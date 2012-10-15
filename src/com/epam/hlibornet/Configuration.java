package com.epam.hlibornet;

public class Configuration {
	private String scanPackage;
	private String url;
	private String bdName;
	private String username;
	private String password;
	private Dialect dialect;
	public String getScanPackage() {
		return scanPackage;
	}
	public void setScanPackage(String scanPackage) {
		this.scanPackage = scanPackage;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBdName() {
		return bdName;
	}
	public void setBdName(String bdName) {
		this.bdName = bdName;
	}
	public Dialect getDialect() {
		return dialect;
	}
	public void setDialect(Dialect dialect) {
		this.dialect = dialect;
	}
	
	
}
