package com.gog.spider.dao.imp;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.gog.spider.dao.BaseDao;
import com.gog.spider.util.MybatisUtil;

public class BaseDaoImp implements BaseDao {

    @SuppressWarnings("unchecked")
    public <T> T findOne(String statement) {
        SqlSession session = MybatisUtil.getSession();
        T obj = null;
        try {
            obj = (T) session.selectOne(statement);
        } finally {
            session.close();
        }
        return obj;
    }

    public <T> List<T> findAll(String statement) {
        SqlSession session = MybatisUtil.getSession();
        List<T> obj = null;
        try {
            obj = session.selectList(statement);
        } finally {
            session.close();
        }
        return obj;
    }

    public <T> int insert(String statement, T obj) {
        SqlSession session = MybatisUtil.getSession();
        int result = 0;
        try {
            result = session.insert(statement, obj);
            session.commit();
        } finally {
            session.close();
        }

        return result;
    }

    public <T> void batchInsert(String statement, List<T> list) {
        SqlSession session = MybatisUtil.getSession();
        try {
            session.insert(statement, list);
            session.commit();
        } finally {
            session.close();
        }
    }
}
