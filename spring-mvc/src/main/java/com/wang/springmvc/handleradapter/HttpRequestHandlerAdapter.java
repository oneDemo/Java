package com.wang.springmvc.handleradapter;

import com.wang.springmvc.handleradapter.iface.HandlerAdapter;
import com.wang.springmvc.handler.iface.HttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 将HttpRequestHandler适配成HandlerAdapter接口的实现
 */
public class HttpRequestHandlerAdapter implements HandlerAdapter {

    public boolean supports(Object handler) {
        return (handler instanceof HttpRequestHandler);

    }

    public void handleRequest(Object handler, HttpServletRequest request, HttpServletResponse response)
        throws Exception {

        ((HttpRequestHandler)handler).handleRequest(request, response);

    }
}
