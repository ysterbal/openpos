package org.openpos;

public interface AppContext {

	public <T> T getBean(Class<T> beanClass);

	public <T> T getBean(Class<T> beanClass, String beanName);
}
