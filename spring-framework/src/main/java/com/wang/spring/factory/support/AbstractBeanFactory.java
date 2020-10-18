package com.wang.spring.factory.support;

import com.wang.spring.definition.BeanDefinition;
import com.wang.spring.factory.BeanFactory;
import com.wang.spring.factory.registry.support.DefaultSingletonBeanRegistry;

/**
 * 定义bean的获取流程
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    public Object getBean(String beanName) {

        //从缓存中获取bean
        Object singleton = getSingleton(beanName);

        if (null != singleton) {
            return singleton;
        }

        // 找不到，则获取指定名称的BeanDefinition对象
        // 此处使用到的就是抽象模板方法，我此处只定流程，不去实现，我也不懂如何实现，这不是我干的事情
        BeanDefinition bd = getBeanDefinition(beanName);

        // 根据BeanDefinition中的信息，判断是单例还是多例（原型）
        if (bd.isSingleton()) {
            // 单例
            // 根据BeanDefinition对象，完成bean的创建
            singleton = createBean(bd);
            // 缓存已经创建的单例bean实例
            addSingleton(beanName, singleton);
        } else if (bd.isPrototype()) {
            // 原型
            // 根据BeanDefinition对象，完成bean的创建
            singleton = createBean(bd);

        }
        return singleton;

    }

    public abstract Object createBean(BeanDefinition beanDefinition);

    public abstract BeanDefinition getBeanDefinition(String name);

}
