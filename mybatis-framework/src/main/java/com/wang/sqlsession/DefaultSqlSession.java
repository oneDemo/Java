package com.wang.sqlsession;

import com.wang.config.Configuration;
import com.wang.config.MappedStatement;
import com.wang.execute.CachingExecutor;
import com.wang.execute.SimpleExecutor;
import com.wang.execute.iface.Executor;

import java.util.List;

public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statementId, Object param) {
        List<Object> list = this.selectList(statementId, param);
        if (list != null && list.size() == 1) {
            return (T)list.get(0);
        }
        return null;
    }

    @Override
    public <T> List<T> selectList(String statementId, Object param) {

        MappedStatement mappedStatement = configuration.getMappedStatementById(statementId);

        Executor executor = new CachingExecutor(new SimpleExecutor());
        return executor.query(mappedStatement, configuration, param);
    }
}
