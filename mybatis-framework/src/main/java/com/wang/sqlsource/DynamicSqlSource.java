package com.wang.sqlsource;

import com.wang.sqlnode.DynamicContext;
import com.wang.sqlnode.iface.SqlNode;
import com.wang.sqlsource.iface.SqlSource;

/**
 * 封装带有${}和动态标签的sql信息
 */
public class DynamicSqlSource implements SqlSource {

    /**
     * 只是封装了解析过程中产生的SqlNode解析信息
     */
    private SqlNode rootSqlNode;

    public DynamicSqlSource(SqlNode rootSqlNode) {
        this.rootSqlNode = rootSqlNode;
    }

    @Override
    public BoundSql getBoundSql(Object paramObject) {
        DynamicContext context = new DynamicContext(paramObject);
        // 将SqlNode处理成一条SQL语句
        rootSqlNode.apply(context);
        // 该SQL语句，此时还包含#{}，不包含${}
        String sql = context.getSql();
        // 通过SqlSourceParser去解析SqlSource中的#{}
        SqlSourceParser sqlSourceParser = new SqlSourceParser();
        // 将解析的结果，最终封装成StaticSqlSource
        SqlSource sqlSource = sqlSourceParser.parse(sql);
        // 调用StaticSqlSource的方法
        return sqlSource.getBoundSql(paramObject);
    }

}
