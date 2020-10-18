package com.wang.spring.factory;

/**
 * 顶级接口，负责bean实例的获取
 */
public interface BeanFactory {

    /**
     * 根据beanName获取bean
     *
     * @param beanName
     * @return
     */
    Object getBean(String beanName);

}
