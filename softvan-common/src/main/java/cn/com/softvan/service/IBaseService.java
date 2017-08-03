package cn.com.softvan.service;

import java.util.List;

/**
 * Created by vicqiang on 2017/6/1 0001.
 */
 public interface IBaseService<T> {
     T findDataById(T bean);

     int deleteById(T bean) throws Exception;

     int save(T bean) throws Exception;

     int update(T bean) throws Exception;

     List<T> findDataIsPage(T bean);

     List<T> findDataIsList(T bean);
}
