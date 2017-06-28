package com.gog.spider.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {

    private static SqlSessionFactory sqlSessionFactory = null;

    public static SqlSessionFactory getSessionFactory() throws IOException {
        if(sqlSessionFactory == null) {
            String resource = "mybatis/mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            return new SqlSessionFactoryBuilder().build(inputStream);
        }

        return sqlSessionFactory;
    }

    public static SqlSession getSession() {
        SqlSession session = null;
        try {
            session = getSessionFactory().openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return session;
    }
}
