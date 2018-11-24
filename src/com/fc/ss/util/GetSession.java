package com.fc.ss.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class GetSession {
	public SqlSession getSession() throws IOException {

        String resource = "SqlMapConfig.xml"; //mybatis配置文件

        //得到配置文件的流
        InputStream inputStream = Resources.getResourceAsStream(resource);

        //创建会话工厂SqlSessionFactory,要传入mybaits的配置文件的流
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //通过工厂得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        return sqlSession;
    }
}
