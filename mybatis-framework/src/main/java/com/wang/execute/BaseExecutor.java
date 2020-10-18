package com.wang.execute;

import com.wang.config.Configuration;
import com.wang.config.MappedStatement;
import com.wang.execute.iface.Executor;
import com.wang.sqlsource.BoundSql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseExecutor implements Executor {

    private Map<String, List<Object>> oneLevelCache = new HashMap<String, List<Object>>();

    @Override
    public <T> List<T> query(MappedStatement mappedStatement, Configuration configuration, Object param) {

        BoundSql boundSql = mappedStatement.getSqlSource().getBoundSql(param);

        // 处理一级缓存
        List<Object> results = oneLevelCache.get(boundSql.getSql());
        if (results != null) {
            return (List<T>)results;
        }

        // 查询数据库
        results = queryFromDataBase(mappedStatement, configuration, param, boundSql);

        oneLevelCache.put(boundSql.getSql(), results);

        return (List<T>)results;
    }

    public abstract List<Object> queryFromDataBase(MappedStatement mappedStatement, Configuration configuration,
                                                   Object param, BoundSql boundSql);
}
