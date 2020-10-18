package com.wang.spring.factory.support;

import com.wang.spring.aware.Aware;
import com.wang.spring.aware.BeanFactoryAware;
import com.wang.spring.definition.BeanDefinition;
import com.wang.spring.definition.PropertyValue;
import com.wang.spring.definition.RuntimeBeanReference;
import com.wang.spring.definition.TypedStringValue;
import com.wang.spring.factory.AutowireCapableBeanFactory;
import com.wang.spring.util.ReflectUtils;

public abstract class AbstractAutowireCapableBeanFactory
    extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    public Object createBean(Class<?> type) {
        return createBean(new BeanDefinition(null, null));
    }

    @Override
    public Object createBean(BeanDefinition beanDefinition) {

        String className = beanDefinition.getClassName();

        Class<?> clazz = resolveClassName(className);
        if (clazz == null) {
            return null;
        }
        // 实例化bean
        // 注意：此时只是new了一个空对象
        Object singlet = createBeanInstance(clazz);
        // bean的属性填充
        populateBean(singlet, beanDefinition);

        // 初始化bean
        initBean(singlet, beanDefinition);

        return singlet;

    }

    private void populateBean(Object singlet, BeanDefinition beanDefinition) {

        for (PropertyValue propertyValue : beanDefinition.getPropertyValues()) {

            //属性名称
            String name = propertyValue.getName();

            //属性值
            Object value = propertyValue.getValue();

            // 解决之后的value值
            Object valueToUse = null;

            if (value instanceof TypedStringValue) {

                TypedStringValue typedStringValue = (TypedStringValue)value;

                String stringValue = typedStringValue.getValue();
                // 获取参数的类型
                Class<?> targetType = typedStringValue.getTargetType();

                // TODO 建议使用策略模式进行优化
                if (targetType == Integer.class) {
                    valueToUse = Integer.parseInt(stringValue);
                } else if (targetType == String.class) {
                    valueToUse = stringValue;
                } else {
                    // ....
                }

            } else if (value instanceof RuntimeBeanReference) {

                RuntimeBeanReference reference = (RuntimeBeanReference)value;

                // 递归获取指定名称的bean实例
                // TODO 此处可能会发送循环依赖问题
                valueToUse = getBean(reference.getRef());
            } else {
                // ....
            }

            // 利用反射去设置bean的属性
            ReflectUtils.setProperty(singlet, name, valueToUse);

        }
    }

    private Object createBeanInstance(Class<?> clazz) {

        return ReflectUtils.createObject(clazz);

    }

    private Class<?> resolveClassName(String clazzName) {

        try {
            return Class.forName(clazzName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void initBean(Object sinleton, BeanDefinition bd) {
        // TODO 完成Aware接口（标记接口）相关的处理,spring mvc代码会用到
        // 1. aware接口的处理
        if (sinleton instanceof Aware) {
            if (sinleton instanceof BeanFactoryAware) {
                ((BeanFactoryAware)sinleton).setBeanFactory(this);
            } // ....
        }
        // TODO BeanPostProcessor的前置方法执行
        initMethod(sinleton, bd);
        // TODO BeanPostProcessor的后置方法执行（AOP代理对象产生的入口）
    }

    private void initMethod(Object sinleton, BeanDefinition bd) {
        // TODO 完成InitializingBean接口（标记接口）的处理，调用的是afterPropertySet方法

        // 完成init-metho标签属性对应的方法调用
        ReflectUtils.invokeMethod(sinleton, bd.getInitMethod());
    }

}
