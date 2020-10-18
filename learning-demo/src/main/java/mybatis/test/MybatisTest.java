package mybatis.test;

import com.wang.io.Resources;
import com.wang.sqlsession.SqlSession;
import com.wang.sqlsession.SqlSessionFactory;
import com.wang.sqlsession.SqlSessionFactoryBuilder;
import mybatis.po.User;
import org.junit.Test;

import java.io.InputStream;

public class MybatisTest {

    @Test
    public void test() throws  Exception{

        Class.forName("com.mysql.jdbc.Driver");


        // 调用解析流程
        // 1.指定全局配置文件路径
        String location = "mybatis-config.xml";
        // 2.加载配置文件成InputStream
        InputStream inputStream = Resources.getResourceAsStream(location);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        User param = new User();
        param.setId(1);

        // 映射文件中的statementId，由namespace和statementId组成
        String statementId = "test.queryUserById";

        User user = sqlSession.selectOne(statementId, param);

        System.out.println(user);
    }
}
