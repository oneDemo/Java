package com.wang.spring.xml;

import com.wang.spring.factory.registry.BeanDefinitionRegistry;
import com.wang.spring.util.DocumentReader;
import org.dom4j.Document;

import java.io.InputStream;

public class XmlBeanDefinitionReader {

    private BeanDefinitionRegistry beanDefinitionRegistry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    public void loadBeanDefinitions(InputStream inputStream) {
        Document document = DocumentReader.createDocument(inputStream);

        XmlBeanDefinitionDocumentReader documentReader =
            new XmlBeanDefinitionDocumentReader(beanDefinitionRegistry);
        documentReader.registerBeanDefinitions(document.getRootElement());
    }
}
