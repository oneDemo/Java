package com.wang.spring.factory.registry;

/**
 * 单例bean注册的工厂
 */
public interface SingletonBeanRegistry {

    /**
     * 根据名称获取注册中心的单例bean实例
     *
     * @param name
     * @return
     */
    Object getSingleton(String name);

    /**
     * 往单例bean的管理中心，去注册一些bean
     *
     * @param name
     * @param bean
     */
    void addSingleton(String name, Object bean);

}
