package com.wang.config;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 封装全局配置文件和信息
 */
public class Configuration {

    private DataSource dataSource;

    private Map<String, MappedStatement> mappedStatements = new HashMap<String, MappedStatement>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MappedStatement> getMappedStatements() {
        return mappedStatements;
    }

    public void setMappedStatements(Map<String, MappedStatement> mappedStatements) {
        this.mappedStatements = mappedStatements;
    }

    public void addMappedStatement(String statementId, MappedStatement mappedStatement) {
        this.mappedStatements.put(statementId, mappedStatement);
    }

    public MappedStatement getMappedStatementById(String statementId) {
        return this.mappedStatements.get(statementId);
    }

}

