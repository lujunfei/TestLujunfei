package com.lingdian.service;

import com.lingdain.command.UserServiceCommand;
import com.lingdian.model.Dept;
import com.lingdian.model.DeptQuery;

import java.util.List;

/**
 * Created by User on 2018/3/8.
 */
public interface DeptService  extends UserServiceCommand<Dept>{
    Dept getDeptById(Long  id); //通过部门id查询该部门下的信息

    List<Dept> getDeptInfoAll(DeptQuery deptQuery); //根据条件查询部门信息

    Dept  insertDept(Dept dept);

    Integer  updateDept(Dept dept);//更新

    Integer  updateQuitDept(Dept dept);//删除部门
 }
