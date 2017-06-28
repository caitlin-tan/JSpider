package com.gog.spider.dao;

import java.util.List;

public interface BaseDao {

    public <T> T findOne(String statement);

    public <T> List<T> findAll(String statement);

    public <T> int insert(String statement, T t);

    public <T> void batchInsert(String statement, List<T> list);
}
