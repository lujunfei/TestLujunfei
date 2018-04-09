package com.lingdian.dao;

import com.lingdain.command.UserDaoCommand;
import com.lingdian.model.Dept;
import com.lingdian.model.DeptQuery;

import java.util.List;

/**
 * Created by User on 2018/3/8.
 */
public interface DeptDao  extends UserDaoCommand<Dept>{

    Dept getDeptById(Long  id); //通过部门id查询该部门下的信息

    List<Dept> getDeptInfoAll(DeptQuery query);  //根据部门条件获取部门信息

    Dept  insertDept(Dept  dept); //新增部门

    Integer updateDept(Dept dept); //更新部门

    Integer updateQuitDept(Dept dept);//删除部门
}
