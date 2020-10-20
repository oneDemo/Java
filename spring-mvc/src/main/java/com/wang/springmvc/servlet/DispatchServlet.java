package com.wang.springmvc.servlet;

import com.wang.spring.factory.support.DefaultListableBeanFactory;
import com.wang.spring.resource.ClasspathResource;
import com.wang.spring.resource.Resource;
import com.wang.spring.xml.XmlBeanDefinitionReader;
import com.wang.springmvc.handleradapter.iface.HandlerAdapter;
import com.wang.springmvc.handlermapping.iface.HandlerMapping;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 请求分发、响应、返回结果
 */
public class DispatchServlet extends AbstractServlet {

    private static final String CONTEXT_CONFIG_LOCATION = "contextConfigLocation";

    private List<HandlerMapping> handlerMappings = new ArrayList<HandlerMapping>();

    private List<HandlerAdapter> handlerAdapters = new ArrayList<HandlerAdapter>();

    private DefaultListableBeanFactory beanFactory;

    @Override
    public void init(ServletConfig config) throws ServletException {

        String location = config.getInitParameter(CONTEXT_CONFIG_LOCATION);

        initSpringContainer(location);

        initHandlerMappings();

        initHandlerAdapters();
    }

    @Override
    public void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 根据请求查找处理器
        Object handler = getHandler(request);
        if (handler == null) {
            return;
        }
        // 根据处理器查找对应的处理器适配器
        HandlerAdapter ha = getHandlerAdapter(handler);
        if (ha == null) {
            return;
        }
        // 统一调用不同类型的处理器
        ha.handleRequest(handler, request, response);
    }

    private HandlerAdapter getHandlerAdapter(Object handler) {

        if (handlerAdapters != null) {
            for (HandlerAdapter ha : handlerAdapters) {
                if (ha.supports(handler)) {
                    return ha;
                }
            }
        }
        return null;

    }

    private Object getHandler(HttpServletRequest request) {

        // 此处使用到了策略模式完成了优化
        if (handlerMappings != null) {
            for (HandlerMapping handlerMapping : handlerMappings) {
                Object handler = handlerMapping.getHandler(request);
                if (handler != null) {
                    return handler;
                }
            }
        }
        return null;

    }

    private void initHandlerAdapters() {

        handlerAdapters = beanFactory.getBeansByType(HandlerAdapter.class);

    }

    private void initHandlerMappings() {

        handlerMappings = beanFactory.getBeansByType(HandlerMapping.class);
    }

    private void initSpringContainer(String location) {

        // 创建BeanFactory
        beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        Resource resource = new ClasspathResource(location);
        InputStream inputStream = resource.getResource();
        // 加载BeanDefinition
        reader.loadBeanDefinitions(inputStream);
    }

}
