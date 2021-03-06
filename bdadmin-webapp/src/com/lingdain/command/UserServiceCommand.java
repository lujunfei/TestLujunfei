package com.lingdain.command;

import java.util.List;

/**
 * Created by User on 2018/3/7.
 * 公共类
 */
public interface UserServiceCommand<T> {

    //查询所有的数据
    public List<T> queryAllData(Long instId);//企业的id

    //新增数据
    public boolean insertData(T t);

    //修改数据
    public boolean update(T t);

    //删除数据
    public boolean delete(int id);

    //查询一条数据通过ID
    public T queryDataById(int id);
}
