package spring.test;

import com.wang.spring.factory.support.DefaultListableBeanFactory;
import com.wang.spring.resource.ClasspathResource;
import com.wang.spring.resource.Resource;
import com.wang.spring.xml.XmlBeanDefinitionReader;
import org.junit.Test;
import spring.Student;

import java.io.InputStream;

public class SpringTest {

    @Test
    public void test() {

        String location = "beans.xml";

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        // 将资源抽象为一个接口，通过该接口，可以获取不同地方（网络、文件系统、classpath）的资源
        Resource resource = new ClasspathResource(location);
        InputStream inputStream = resource.getResource();
        beanDefinitionReader.loadBeanDefinitions(inputStream);

        Student student = (Student)beanFactory.getBean("student");
        System.out.println(student);
    }
}
