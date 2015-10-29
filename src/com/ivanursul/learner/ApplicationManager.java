package com.ivanursul.learner;

import java.util.HashMap;
import java.util.Map;

public class ApplicationManager {

	private static final ApplicationManager instance = new ApplicationManager();
	
	private ApplicationManager() {}
	
	public static ApplicationManager getInstance() {
		return instance;
	}
	
	private Map<Class<?>, Object> instances = new HashMap<Class<?>, Object>();
	
	public <T> void put(Class<T> clz,final Object instance) {
		instances.put(clz, instance);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> clz) {
		return (T) instances.get(clz);
	}
}
