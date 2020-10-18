package com.wang.sqlsource.iface;

import com.wang.sqlsource.BoundSql;

public interface SqlSource {

    /**
     * 根据入参对象，获取JDBC可以执行的SQL语句
     *
     * @param paramObject
     * @return
     */
    BoundSql getBoundSql(Object paramObject);

}
