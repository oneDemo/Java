package com.wang.spring.factory;

/**
 * 具备对bean实例进行装配功能的工厂
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 创建bean实例
     *
     * @param type
     * @return
     */

    Object createBean(Class<?> type);
}
