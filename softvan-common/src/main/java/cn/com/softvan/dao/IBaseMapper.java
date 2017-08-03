package cn.com.softvan.dao;

import java.util.List;

/**
 * Created by vicqiang on 2017/5/27 0027.
 */
public interface IBaseMapper<T> {
    int deleteByPrimaryKey(String id) throws Exception;

    int insert(T record) throws Exception;

    T selectByPrimaryKey(String id) ;

    int updateByPrimaryKeySelective(T record) throws Exception;

    List<T> selectList(T record) ;

}
