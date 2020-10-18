package com.wang.config;

import org.dom4j.Element;

import java.util.List;

/**
 * 解析mapper标签文件
 *
 * @author wangtenglong
 */
public class XMLMapperBuilder {

    private Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parse(Element rootElement) {
        //为了方便管理statement，需要使用statement唯一标识
        String namespace = rootElement.attributeValue("namespace");

        List<Element> selectElements = rootElement.elements("select");
        for (Element selectElement : selectElements) {

            XMLStatementBuilder statementBuilder = new XMLStatementBuilder(configuration);
            statementBuilder.parseStatement(selectElement, namespace);
        }

    }

}
