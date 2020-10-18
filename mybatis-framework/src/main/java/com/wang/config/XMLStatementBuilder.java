package com.wang.config;

import com.wang.sqlsource.iface.SqlSource;
import org.dom4j.Element;

/**
 * 解析CRUD语句
 *
 * @author wangtenglong
 */
public class XMLStatementBuilder {

    private Configuration configuration;

    public XMLStatementBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parseStatement(Element selectElement, String namespace) {

        String statementId = selectElement.attributeValue("id");

        if (statementId == null || selectElement.equals("")) {
            return;
        }
        // 一个CURD标签对应一个MappedStatement对象
        // 一个MappedStatement对象由一个statementId来标识，所以保证唯一性
        // statementId = namespace + "." + CRUD标签的id属性
        statementId = namespace + "." + statementId;

        String parameterType = selectElement.attributeValue("parameterType");

        Class<?> parameterClass = resolveType(parameterType);

        String resultType = selectElement.attributeValue("resultType");

        Class<?> resultClass = resolveType(resultType);

        String statementType = selectElement.attributeValue("statementType");

        statementType = statementType == null || statementType == "" ? "prepared" : statementType;

        // 解析SQL信息
        SqlSource sqlSource = createSqlSource(selectElement);

        // TODO 建议使用构建者模式去优化
        MappedStatement mappedStatement = new MappedStatement(statementId, parameterClass, resultClass, statementType,
            sqlSource);
        configuration.addMappedStatement(statementId, mappedStatement);
    }

    private SqlSource createSqlSource(Element selectElement) {

        XMLScriptParser scriptParser = new XMLScriptParser();
        SqlSource sqlSource = scriptParser.parseScriptNode(selectElement);

        return sqlSource;
    }

    private Class<?> resolveType(String parameterType) {
        try {
            Class<?> clazz = Class.forName(parameterType);
            return clazz;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
