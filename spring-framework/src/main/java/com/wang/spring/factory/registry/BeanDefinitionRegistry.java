package com.wang.spring.factory.registry;

import com.wang.spring.definition.BeanDefinition;

import java.util.List;

/**
 * 管理beanDefinition的注册
 */
public interface BeanDefinitionRegistry {

    BeanDefinition getBeanDefinition(String name);

    List<BeanDefinition> getBeanDefinitions();

    void registerBeanDefinition(String name, BeanDefinition beanDefinition);

}
