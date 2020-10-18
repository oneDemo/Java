package com.wang.sqlsession;

import com.wang.config.Configuration;
import com.wang.config.XMlConfigBuilder;

import java.io.InputStream;

public class SqlSessionFactoryBuilder {

    private Configuration configuration;

    /**
     * 构造者模式
     *
     * @param inputStream
     * @return
     */
    public SqlSessionFactory build(InputStream inputStream) {

        //执行解析流程
        XMlConfigBuilder xMlConfigBuilder = new XMlConfigBuilder();

        configuration = xMlConfigBuilder.parse(inputStream);

        return build();
    }

    private SqlSessionFactory build() {
        return new DefaultSqlSessionFactory(configuration);
    }

}
