package com.wang.springmvc.handlermapping;

import com.wang.spring.aware.BeanFactoryAware;
import com.wang.spring.definition.BeanDefinition;
import com.wang.spring.factory.BeanFactory;
import com.wang.spring.factory.support.DefaultListableBeanFactory;
import com.wang.springmvc.handlermapping.iface.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanNameUrlHandlerMapping implements HandlerMapping, BeanFactoryAware {

    private Map<String, Object> urlHandlers = new HashMap<String, Object>();

    private DefaultListableBeanFactory beanFactory;

    public void init() {
        List<BeanDefinition> beanDefinitions = beanFactory.getBeanDefinitions();
        // 取出BeanDefinition对象中的beanName进行处理，如果是uri则获取该BeanDefinition对应的实例
        for (BeanDefinition beanDefinition : beanDefinitions) {
            String beanName = beanDefinition.getBeanName();
            if (beanName.startsWith("/")) {
                // 建立map集合中的映射关系key：uri value：处理器的实例
                urlHandlers.put(beanName, beanFactory.getBean(beanName));
            }
        }
    }

    @Override
    public Object getHandler(HttpServletRequest request) {
        String uri = request.getRequestURI();
        Object handler = urlHandlers.get(uri);
        return handler;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = (DefaultListableBeanFactory)beanFactory;
    }
}
