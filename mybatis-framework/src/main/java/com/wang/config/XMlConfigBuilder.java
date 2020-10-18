package com.wang.config;

import com.wang.io.Resources;
import com.wang.util.DocumentUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * mybatis的config配置解析
 *
 * @author wangtenglong
 */
public class XMlConfigBuilder {

    private Configuration configuration;

    public XMlConfigBuilder() {
        this.configuration = new Configuration();
    }

    /**
     * 从根标签开始解析全局配置文件
     *
     * @param inputStream
     * @return
     */
    public Configuration parse(InputStream inputStream) {

        Document document = DocumentUtils.readDocument(inputStream);
        Element rootElement = document.getRootElement();

        parserConfiguration(rootElement);

        return configuration;
    }

    /**
     * @param rootElement
     */
    private void parserConfiguration(Element rootElement) {
        Element environmentsElement = rootElement.element("environments");
        //解析数据源等相关信息
        parseEnvironmentsElement(environmentsElement);

        //解析mapper文件
        Element mappersElement = rootElement.element("mappers");
        parseMappersElement(mappersElement);
    }

    /**
     * @param environmentsElement <environments>
     */
    @SuppressWarnings("unchecked")
    private void parseEnvironmentsElement(Element environmentsElement) {
        String defaultEnvId = environmentsElement.attributeValue("default");
        if (defaultEnvId == null || defaultEnvId.equals("")) {
            return;
        }
        List<Element> elements = environmentsElement.elements("environment");
        for (Element envElement : elements) {
            String id = envElement.attributeValue("id");
            if (defaultEnvId.equals(id)) {
                parseDataSource(envElement.element("dataSource"));
            }
        }
    }

    /**
     * @param dbElement <dataSource>
     */
    @SuppressWarnings("unchecked")
    private void parseDataSource(Element dbElement) {
        String dbType = dbElement.attributeValue("type");
        if ("DBCP".equals(dbType)) {
            BasicDataSource dataSource = new BasicDataSource();

            Properties properties = new Properties();

            List<Element> propertyElements = dbElement.elements();
            for (Element prop : propertyElements) {
                String name = prop.attributeValue("name");
                String value = prop.attributeValue("value");
                properties.put(name, value);
            }

            dataSource.setDriverClassName(properties.getProperty("driver"));
            dataSource.setUrl(properties.getProperty("url"));
            dataSource.setUsername(properties.getProperty("username"));
            dataSource.setPassword(properties.getProperty("password"));

            configuration.setDataSource(dataSource);
        }
    }

    @SuppressWarnings("unchecked")
    private void parseMappersElement(Element mappersElement) {
        List<Element> mapperElements = mappersElement.elements("mapper");
        for (Element mapperElement : mapperElements) {
            parseMapper(mapperElement);
        }
    }

    /**
     * 解析<mapper>标签
     *
     * @param mapperElement
     */
    private void parseMapper(Element mapperElement) {

        String resource = mapperElement.attributeValue("resource");

        InputStream inputStream = Resources.getResourceAsStream(resource);
        Document document = DocumentUtils.readDocument(inputStream);
        //创建专门来解析映射文件的解析类
        XMLMapperBuilder mapperBuilder = new XMLMapperBuilder(configuration);
        mapperBuilder.parse(document.getRootElement());
    }

}
