package com.wang.spring.factory.registry.support;

import com.wang.spring.factory.registry.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * 存储单例bean实例的集合
     */
    private Map<String, Object> singletons = new HashMap<String, Object>();

    public Object getSingleton(String name) {
        return singletons.get(name);
    }

    public void addSingleton(String name, Object bean) {

        this.singletons.put(name, bean);
    }
}
