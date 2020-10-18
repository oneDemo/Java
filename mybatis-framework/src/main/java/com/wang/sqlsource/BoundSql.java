package com.wang.sqlsource;

import java.util.ArrayList;
import java.util.List;

public class BoundSql {

    /**
     * jdbc要执行的sql
     */
    private String sql;

    private List<ParameterMapping> parameterMappings = new ArrayList<ParameterMapping>();

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void setParameterMappings(List<ParameterMapping> parameterMappings) {
        this.parameterMappings = parameterMappings;
    }

    public BoundSql(String sql, List<ParameterMapping> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
    }
}
