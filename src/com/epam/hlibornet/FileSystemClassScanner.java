package com.epam.hlibornet;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class FileSystemClassScanner {
	private static final Logger LOGGER = Logger.getLogger(FileSystemClassScanner.class);
	
	public static List<Class<?>> getClassesInPackage(String basePackage) throws ClassNotFoundException{
		String path = basePackage.replaceAll("\\.", "/");
		File file = new File("src/" + path);
		return getClassesInDirectory(file);
	}
	private static List<Class<?>> getClassesInDirectory(File directory) throws ClassNotFoundException{
		List<Class<?>> result = new ArrayList<>();
		for (File file : directory.listFiles()) {
			if(file.isFile()){
				String className = file.toString().replaceAll("\\\\", "\\.");
				className = className.substring(4);
				className = className.substring(0, className.length() - 5);
				Class<?> forName = Class.forName(className);
				LOGGER.info("find: " + forName.toString());
				result.add(forName);
			}else{
				result.addAll(getClassesInDirectory(file));
			}
		} 
		return result;
	}
}
