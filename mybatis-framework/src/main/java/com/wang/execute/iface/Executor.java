package com.wang.execute.iface;

import com.wang.config.Configuration;
import com.wang.config.MappedStatement;

import java.util.List;

public interface Executor {

    /**
     * @param mappedStatement 获取sql语句和出入参类型信息
     * @param configuration   获取数据源连接处信息
     * @param param           获取入参类型
     * @param <T>
     * @return
     */

    <T> List<T> query(MappedStatement mappedStatement, Configuration configuration, Object param);

}
