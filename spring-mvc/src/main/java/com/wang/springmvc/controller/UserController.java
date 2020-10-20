package com.wang.springmvc.controller;

import com.wang.springmvc.annotation.Controller;
import com.wang.springmvc.annotation.RequestMapping;
import com.wang.springmvc.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/***
 * 注意：一个请求对应一个Handler
 * 一个Controller包含多个请求对应的方法，所以说Controller不是Handler类
 * 真正的Handler其实指的是【Controller类和指定请求URL对应的方法】
 * 我们使用封装的类去表示注解方式下的Handler，这个类就叫HandlerMethod（Controller类、Method方法）
 * 总结：在注解开发方式下，一个请求对应一个HandlerMethod(其实就是Handler类)
 *
 */
@Controller
@RequestMapping("springmvc_custom11_war_exploded")
public class UserController {

    @RequestMapping("queryUser3")
    @ResponseBody
    public Map<String, Object> query(Integer id, String name) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("name", name);
        return map;
    }

    @RequestMapping("saveUser3")
    @ResponseBody
    public String save() {
        return "添加成功";
    }
}
