package com.lingdian.dao;

import com.lingdain.command.UserServiceCommand;
import com.lingdian.model.Administrator;
import com.lingdian.model.User;

import java.util.List;

/**
 * Created by User on 2018/3/8.
 */
public interface AdministratorDao extends UserServiceCommand<Administrator>{

     List<Administrator> getAdministratorInfoByConfditions(String userName, String password);
}
