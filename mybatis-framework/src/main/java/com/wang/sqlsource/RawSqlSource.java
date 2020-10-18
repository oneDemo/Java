package com.wang.sqlsource;

import com.wang.sqlnode.DynamicContext;
import com.wang.sqlnode.iface.SqlNode;
import com.wang.sqlsource.iface.SqlSource;

/**
 * 封装不包含${}和动态标签的SQL信息
 */
public class RawSqlSource implements SqlSource {

    private SqlSource sqlSource;

    public RawSqlSource(SqlNode rootSqlNode) {
        DynamicContext context = new DynamicContext(null);
        // 将SqlNode处理成一条SQL语句
        rootSqlNode.apply(context);
        // 该SQL语句，此时还包含#{}，不包含${}
        String sql = context.getSql();
        // 通过SqlSourceParser去解析SqlSource中的#{}
        SqlSourceParser sqlSourceParser = new SqlSourceParser();
        // 将解析的结果，最终封装成StaticSqlSource
        sqlSource = sqlSourceParser.parse(sql);
    }

    @Override
    public BoundSql getBoundSql(Object paramObject) {
        return sqlSource.getBoundSql(paramObject);
    }

}
