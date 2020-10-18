package com.wang.spring.factory;

import java.util.List;

public interface ListableBeanFactory extends BeanFactory {

    /**
     * 根据bean的类型，获取它以及子类型对应的bean实例集合
     *
     * @param type
     * @return
     */
    <T> List<T> getBeansByType(Class<?> type);

    /**
     * 根据bean的类型，获取它以及子类型对应的bean的名称集合
     *
     * @param type
     * @return
     */
    <T> List<T> getBeanNamesByType(Class<?> type);
}
