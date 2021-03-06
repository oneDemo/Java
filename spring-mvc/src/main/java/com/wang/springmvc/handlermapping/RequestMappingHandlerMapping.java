package com.wang.springmvc.handlermapping;

import com.wang.spring.aware.BeanFactoryAware;
import com.wang.spring.definition.BeanDefinition;
import com.wang.spring.factory.BeanFactory;
import com.wang.spring.factory.support.DefaultListableBeanFactory;
import com.wang.springmvc.annotation.Controller;
import com.wang.springmvc.annotation.RequestMapping;
import com.wang.springmvc.handler.HandlerMethod;
import com.wang.springmvc.handlermapping.iface.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestMappingHandlerMapping implements HandlerMapping, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    private Map<String, HandlerMethod> urlHandlerMethods = new HashMap<String, HandlerMethod>();

    public void init() {
        // 从BeanFactory中获取所有的BeanDefinition
        List<BeanDefinition> beanDefinitions = beanFactory.getBeanDefinitions();

        for (BeanDefinition bd : beanDefinitions) {

            String clazzName = bd.getClassName();

            Class<?> clazz = getClass(clazzName);

            // 判断该BeanDefinition中的class是否带有@Controller或者@RequestMapping注解
            if (isHandler(clazz)) {

                // 如果是处理器类，则将该类中所有带有@RequestMapping注解的方法进行处理

                Method[] methods = clazz.getDeclaredMethods();

                for (Method method : methods) {
                    if (method.isAnnotationPresent(RequestMapping.class)) {

                        StringBuffer sb = new StringBuffer();
                        RequestMapping requestMapping2 = clazz.getAnnotation(RequestMapping.class);

                        if (requestMapping2 != null && requestMapping2.value() != null
                            && !requestMapping2.value().equals("")) {
                            sb.append("/");
                            sb.append(requestMapping2.value());
                        }
                        // 获取@RequestMapping注解中的url
                        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                        sb.append("/");
                        sb.append(requestMapping.value());

                        // 将对应的方法对象和Controller对象封装为HandlerMethod对象
                        Object controller = beanFactory.getBean(bd.getBeanName());
                        HandlerMethod handlerMethod = new HandlerMethod(controller, method);
                        // 建立url和HandlerMethod的映射关系
                        urlHandlerMethods.put(sb.toString(), handlerMethod);
                    }
                }
            }
        }
    }

    public void setBeanFactory(BeanFactory beanFactory) {

        this.beanFactory = (DefaultListableBeanFactory)beanFactory;
    }

    public Object getHandler(HttpServletRequest request) {

        String uri = request.getRequestURI();
        return urlHandlerMethods.get(uri);
    }

    private boolean isHandler(Class<?> clazz) {

        return (clazz.isAnnotationPresent(Controller.class) || clazz.isAnnotationPresent(RequestMapping.class));
    }

    private Class<?> getClass(String clazzName) {
        try {
            Class<?> clazz = Class.forName(clazzName);
            return clazz;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
