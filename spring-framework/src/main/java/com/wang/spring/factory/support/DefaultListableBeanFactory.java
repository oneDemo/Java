package com.wang.spring.factory.support;

import com.wang.spring.definition.BeanDefinition;
import com.wang.spring.factory.ListableBeanFactory;
import com.wang.spring.factory.registry.BeanDefinitionRegistry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * spring底层工厂类，由它使用bean的管理
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory
    implements BeanDefinitionRegistry, ListableBeanFactory {

    public <T> List<T> getBeansByType(Class<?> type) {
        List<T> beans = new ArrayList<T>();
        try {
            for (BeanDefinition bd : beanDefinitions.values()) {
                String clazzName = bd.getClassName();
                // 获取BeanDefinition对应的class的类对象
                Class<?> clazz = Class.forName(clazzName);
                // 如果type是clazz的父类或者是当前类，则返回true
                if (type.isAssignableFrom(clazz)) {
                    Object bean = getBean(bd.getBeanName());
                    beans.add((T)bean);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beans;
    }

    public <T> List<T> getBeanNamesByType(Class<?> type) {
        return null;
    }

    /**
     * 以beanname为key，以BeanDefinition对象为value的存储集合
     */
    private Map<String, BeanDefinition> beanDefinitions = new HashMap<String, BeanDefinition>();

    @Override
    public BeanDefinition getBeanDefinition(String name) {
        return this.beanDefinitions.get(name);
    }

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        this.beanDefinitions.put(name, beanDefinition);
    }

    public List<BeanDefinition> getBeanDefinitions() {
        Collection<BeanDefinition> values = beanDefinitions.values();
        List<BeanDefinition> definitions = new ArrayList<BeanDefinition>();
        for (BeanDefinition beanDefinition : values) {
            definitions.add(beanDefinition);
        }
        return definitions;
    }

}
