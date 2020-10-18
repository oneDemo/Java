package com.wang.execute;

import com.wang.config.Configuration;
import com.wang.config.MappedStatement;
import com.wang.execute.iface.Executor;

import java.util.List;

public class CachingExecutor implements Executor {

    private Executor delegate;

    public CachingExecutor(Executor delegate) {
        this.delegate = delegate;
    }

    @Override
    public <T> List<T> query(MappedStatement mappedStatement, Configuration configuration, Object param) {
        return delegate.query(mappedStatement,configuration,param);
    }
}
