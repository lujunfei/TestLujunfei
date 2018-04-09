package com.lingdian.service;

import com.lingdain.command.UserServiceCommand;
import com.lingdian.model.Administrator;

import java.util.List;

/**
 * Created by User on 2018/3/8.
 */
public interface AdministratorService  extends UserServiceCommand<Administrator>{

    List<Administrator> getAdministratorInfoByConfditions(String userName, String password);

}
