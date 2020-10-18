package com.wang.execute;

import com.wang.config.Configuration;
import com.wang.config.MappedStatement;
import com.wang.execute.iface.Executor;
import com.wang.sqlsource.BoundSql;
import com.wang.sqlsource.ParameterMapping;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimpleExecutor extends BaseExecutor {

    @Override
    public List<Object> queryFromDataBase(MappedStatement mappedStatement, Configuration configuration, Object param,
                                          BoundSql boundSql) {
        List<Object> results = new ArrayList<Object>();
        try {
            // 获取连接
            Connection connection = getConnection(configuration);
            // 获取SQL语句
            String sql = boundSql.getSql();
            // 判断创建哪种Statement
            ResultSet resultSet = null;
            if ("prepared".equals(mappedStatement.getStatementType())) {
                // 创建Statement
                PreparedStatement prepareStatement = connection.prepareStatement(sql);
                // 设置参数
                parameterize(prepareStatement, mappedStatement, boundSql, param);
                // 执行Statement
                resultSet = prepareStatement.executeQuery();
            } else if ("callable".equals(mappedStatement.getStatementType())) {
                // 创建Statement
                // 设置参数
                // 执行Statement
            }

            // 处理结果集
            if (resultSet != null) {
                handleResultSet(mappedStatement, resultSet, results);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }

    private void handleResultSet(MappedStatement mappedStatement, ResultSet resultSet, List<Object> results)
        throws Exception {
        Class<?> resultTypeClass = mappedStatement.getResultTypeClass();
        // 没遍历一次是一行数据，对应一个映射对象
        while (resultSet.next()) {
            Object result = resultTypeClass.newInstance();
            // 每一列，对应映射对象的一个属性
            // 列的名称要和对象的属性名称一致
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                String columnName = metaData.getColumnName(i + 1);
                Field field = resultTypeClass.getDeclaredField(columnName);
                field.setAccessible(true);
                field.set(result, resultSet.getObject(i + 1));

            }

            results.add(result);
        }

    }

    private void parameterize(PreparedStatement prepareStatement, MappedStatement mappedStatement, BoundSql boundSql,
                              Object param) throws Exception {
        // 先判断入参类型
        Class<?> parameterTypeClass = mappedStatement.getParameterTypeClass();
        if (parameterTypeClass == Integer.class) {
            prepareStatement.setObject(1, Integer.parseInt(param.toString()));
        } else if (parameterTypeClass == String.class) {
            prepareStatement.setObject(1, param.toString());
        } else if (parameterTypeClass == Map.class) {

        } else {// 自定义对象类型
            List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();

            for (int i = 0; i < parameterMappings.size(); i++) {
                // 获取#{}中的属性名称
                ParameterMapping parameterMapping = parameterMappings.get(i);

                String name = parameterMapping.getName();

                // 根据属性名称，获取入参对象中对应的属性的值
                // 要求#{}中的属性名称和入参对象中的属性名称一致
                Field field = parameterTypeClass.getDeclaredField(name);
                field.setAccessible(true);
                Object value = field.get(param);

                prepareStatement.setObject(i + 1, value);
            }
        }

    }

    private Connection getConnection(Configuration configuration) {
        Connection connection = null;
        try {
            DataSource dataSource = configuration.getDataSource();
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
