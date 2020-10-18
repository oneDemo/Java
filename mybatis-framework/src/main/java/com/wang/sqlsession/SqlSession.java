package com.wang.sqlsession;

import java.util.List;

public interface SqlSession {

    /**
     * 查询单个对象
     *
     * @param statementId
     * @param param
     * @return
     */
    <T> T selectOne(String statementId, Object param);

    /**
     * 多个查询
     *
     * @param statementId
     * @param param
     * @param <T>
     * @return
     */
    <T> List<T> selectList(String statementId, Object param);

}
