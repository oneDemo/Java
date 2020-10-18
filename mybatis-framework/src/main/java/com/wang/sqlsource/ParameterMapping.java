package com.wang.sqlsource;

/**
 * 解析#和{}获取到的参数信息
 */
public class ParameterMapping {

    private String name;

    private Class<?> type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public ParameterMapping(String name) {
        this.name = name;
    }
}
