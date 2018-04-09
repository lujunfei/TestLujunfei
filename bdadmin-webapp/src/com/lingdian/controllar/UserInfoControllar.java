package com.lingdian.controllar;

import com.lingdian.Dto.UserDto;
import com.lingdian.model.*;
import com.lingdian.service.AdministratorService;
import com.lingdian.service.DeptService;
import com.lingdian.service.RunRecordHisService;
import com.lingdian.service.UserService;
import com.lingdian.serviceImp.AdministratorServiceImpl;
import com.lingdian.serviceImp.DeptServiceImpl;
import com.lingdian.serviceImp.RunRecordHisServiceImp;
import com.lingdian.serviceImp.UserServiceImpl;
import net.sf.json.JSONObject;
//import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 2018/3/7.
 */
public class UserInfoControllar {
    UserService userService = new UserServiceImpl();
    DeptService deptService = new DeptServiceImpl();
    AdministratorService administratorService = new AdministratorServiceImpl();
    RunRecordHisService runRecordHisService = new RunRecordHisServiceImp();

    /**
     * 查询该企业下用户所有数据(包括在职和离职的)
     */
    public void queryUserAll(HttpServletRequest request, HttpServletResponse response, Long instId, int userStatus)
            throws ServletException, IOException {
        List<User> lis = userService.queryAllData(instId, userStatus);
        List<UserDto> userDtoList = new ArrayList<UserDto>();
        if (lis != null && lis.size() > 0) {
            for (User item : lis) {
                UserDto userDto = new UserDto();
                userDto.setJobNo(item.getJobNo());
                userDto.setUserName(item.getUserName());
                //根据部门id查询部门
                if (item.getDeptId() != null) {
                    Dept dept = deptService.getDeptById(item.getDeptId());
                    if (dept != null) {
                        userDto.setDeptName(dept.getDeptName());//获取部门名称
                        userDto.setDeptId(item.getDeptId());
                    }
                }
                userDto.setPhone(item.getPhone());
                userDto.setUserStatus(item.getUserStatus()); //就职状态
                if (item.getUserStatus() == 0) {
                    userDto.setUserStatusValue("离职");
                } else if (item.getUserStatus() == 1) {
                    userDto.setUserStatusValue("在职");
                }
                userDtoList.add(userDto);
            }
        }

        PrintWriter wirte = response.getWriter();
        JSONObject json = new JSONObject();
        json.put("list", userDtoList);
        wirte.print(json);

        request.setAttribute("list", userDtoList);
//      request.getRequestDispatcher("/jsp/user/serviceEmployees.jsp").forward(request, response);
    }

    /**
     * 判断登录账号和密码是否正确
     */
    public List<Administrator> getAdministratorInfoByConditions(HttpServletRequest request, HttpServletResponse response,HttpSession session, String userName, String password) throws ServletException, IOException {
        List<Administrator> administratorList = administratorService.getAdministratorInfoByConfditions(userName, password);
        if (administratorList != null && administratorList.size() > 0) {
            String sessionKey = null;
            Integer instId = null;
            for (Administrator item : administratorList) {
                sessionKey = item.getSessionKey();
                instId = item.getInstId();
                break;
            }
            request.setAttribute("sessionKey", sessionKey);
            request.setAttribute("instId", instId);
            request.setAttribute("msg", "登录成功!!!");
            //添加到Cookie中
            Administrator administrator = administratorList.get(0);
            session.setAttribute("instId",instId);
//            request.getRequestDispatcher("/jsp/user/serviceEmployees.jsp").forward(request, response);
            request.getRequestDispatcher("/jsp/user/serviceEmployees.jsp").forward(request, response);
        } else {
            request.setAttribute("msg", "登陆失败!!!");
            request.getRequestDispatcher("/jsp/error/error.jsp").forward(request, response);
        }
        return administratorList;
    }

    /**
     * 根据条件查询信息
     */
    public List<User> getQuitUserInfoByConditions(HttpServletRequest request, HttpServletResponse response, UserQuery userQuery) throws ServletException, IOException {
        List<User> userList = userService.getUserInfoByConditions(userQuery);
        List<UserDto> userDtosList = new ArrayList<UserDto>();
        if (userList != null && userList.size() > 0) {
            for (User item : userList) {
                UserDto userDto = new UserDto();
                userDto.setJobNo(item.getJobNo());
                userDto.setUserName(item.getUserName());
                userDto.setId(item.getId());
                //根据部门id查询部门
                if (item.getDeptId() != null) {
                    Dept dept = deptService.getDeptById(item.getDeptId());
                    if (dept != null) {
                        userDto.setDeptName(dept.getDeptName());//获取部门名称
                        userDto.setDeptId(item.getDeptId());
                    }
                }
                userDto.setPhone(item.getPhone());
                userDto.setUserStatus(item.getUserStatus()); //就职状态
                if (item.getUserStatus() == 0) {
                    userDto.setUserStatusValue("离职");
                } else if (item.getUserStatus() == 1) {
                    userDto.setUserStatusValue("在职");
                }
                userDtosList.add(userDto);
            }
        }
        request.setAttribute("userList",userDtosList);
        request.getRequestDispatcher("/jsp/user/leaveEmployees.jsp").forward(request, response);
        return userList;
    }
    /**
     * 根据条件查询离职员工信息
     */
    public List<User> getUserInfoByConditions(HttpServletRequest request, HttpServletResponse response,HttpSession session, UserQuery userQuery) throws ServletException, IOException {
        List<User> userList = userService.getUserInfoByConditions(userQuery);
        List<UserDto> userDtosList = new ArrayList<UserDto>();
//        if (userList != null && userList.size() > 0) {
//            for (User item : userList) {
//                UserDto userDto = new UserDto();
//                userDto.setJobNo(item.getJobNo());
//                userDto.setUserName(item.getUserName());
//                userDto.setId(item.getId());
//                //根据部门id查询部门
////                if (item.getDeptId() != null) {
////                    Dept dept = deptService.getDeptById(item.getDeptId());
////                    if (dept != null) {
////                        userDto.setDeptName(dept.getDeptName());//获取部门名称你这样真的好吗
////                        userDto.setDeptId(item.getDeptId());
////                    }
////                }
//                userDto.setPhone(item.getPhone());
//                userDto.setUserStatus(item.getUserStatus()); //就职状态
//                if (item.getUserStatus() == 0) {
//                    userDto.setUserStatusValue("离职");
//                } else if (item.getUserStatus() == 1) {
//                    userDto.setUserStatusValue("在职");
//                }
//                userDtosList.add(userDto);
//            }
//        }
        Long instId = Long.parseLong(session.getAttribute("instId").toString());
        DeptQuery deptQuery = new DeptQuery();
        deptQuery.setInstId(instId);
        deptQuery.setDeptType(1);
        List<Dept> deptList = deptService.getDeptInfoAll(deptQuery);

        if(userList != null && userList.size()>0){
            for(User item:userList){
                UserDto userDto = new UserDto();
                userDto.setJobNo(item.getJobNo());
                userDto.setUserName(item.getUserName());
                userDto.setId(item.getId());
                for(Dept it:deptList){
                    if(it.getId().longValue() == item.getDeptId().longValue()){
                        userDto.setDeptName(it.getDeptName());//获取部门名称
                        userDto.setDeptId(item.getDeptId());
                    }
                }
                userDto.setPhone(item.getPhone());
                userDto.setUserStatus(item.getUserStatus()); //就职状态
                if (item.getUserStatus() == 0) {
                    userDto.setUserStatusValue("离职");
                } else if (item.getUserStatus() == 1) {
                    userDto.setUserStatusValue("在职");
                }
                userDtosList.add(userDto);
            }
        }
        request.setAttribute("userList",userDtosList);
        request.getRequestDispatcher("/jsp/user/serviceEmployees.jsp").forward(
                request, response);
        return userList;
    }
    /**
     * 根据用户id查询信息
     */
    public User getUserById(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        Long  instId = Long.parseLong(session.getAttribute("instId").toString());
        String id = request.getParameter("id");//用户id
        Long userId = Long.parseLong(id);
        User user =  userService.selectUserInfoById(userId);
        Dept dept = deptService.getDeptById(user.getDeptId());//为了查询部门名称
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setJobNo(user.getJobNo());
        userDto.setDeptId(user.getDeptId());
        userDto.setPhone(user.getPhone());
        userDto.setDeptName(dept.getDeptName());
        request.setAttribute("userInfo",userDto);
        DeptQuery deptQuery = new DeptQuery();
        deptQuery.setInstId(instId);
        deptQuery.setDeptType(1);
        List<Dept> deptList = deptService.getDeptInfoAll(deptQuery);//获取部门列表
        request.setAttribute("deptList",deptList);
        request.getRequestDispatcher("/jsp/user/editdPerson.jsp").forward(request, response);
        return user;
    }
    /**
     *新增用户接口
     */
    public User insertUser(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        JSONObject json  = new JSONObject();
        PrintWriter writer = response.getWriter();
        //获取前端传过来的json数据并遍历
        String data  = request.getParameter("data");
//      String data ="{\"id\":\"160\",\"deptName\":\"技术部\"}";//模拟了一个json数据
        JSONObject jsonOne = new JSONObject();
        jsonOne=jsonOne.fromObject(data);
        //判断工号是否存在
        User  user  = new  User();
        UserQuery  userQuery = new UserQuery();
        userQuery.setJobNo(jsonOne.getString("jobNo"));
        List<User> userList = userService.getUserInfoByConditions(userQuery);
        if(userList.size()>0){
            json.put("msg","该工号已经存在!!!");
            json.put("status",0);
            writer.print(json);
            return  user;
        }

        Long  instId = Long.parseLong(session.getAttribute("instId").toString());
        user.setInstId(instId);
        user.setUserName(jsonOne.get("userName").toString());
        user.setHeadUrl("http://121.40.179.35/ftp/beida/beida_head_1.1.jpg");
        user.setPhone(jsonOne.get("phone").toString());
        user.setJobNo(jsonOne.get("jobNo").toString());
        user.setDeptId(Long.parseLong(jsonOne.get("deptId").toString()));
        user.setGroupId(Long.parseLong(jsonOne.get("deptId").toString()));
        user.setUserStatus(1);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        User  users  =  userService.insertUser(user);
        if(users != null){
             json.put("msg","新增人员成功!!!");
             json.put("status",1);
        }else {
            json.put("msg","新增人员失败!!!");
            json.put("status",0);
        }
        writer.print(json);
//        request.getRequestDispatcher("/jsp/user/serviceEmployees.jsp").forward(request, response);
        return user;
    }
    //更新用户接口
    public JSONObject  updateUser(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws  ServletException, IOException {
        JSONObject json = new JSONObject();
        PrintWriter writer = response.getWriter();
        String  userName = request.getParameter("userName");
        String  jobNo    = request.getParameter("jobNo");
        String  phone    =   request.getParameter("phone");
        String  id  = request.getParameter("id");    //用户id
        String  deptId   =   request.getParameter("deptId");//部门id
        //获取前端传过来的json数据
        String data  = request.getParameter("data");
        JSONObject jsonOne = new JSONObject();
        jsonOne=jsonOne.fromObject(data);
        //通过用户id查询部门id是否更改，若更改则要
        User  user = userService.selectUserInfoById(Long.parseLong(jsonOne.get("id").toString()));
        if(user.getDeptId().longValue() != Long.parseLong(jsonOne.get("deptId").toString())){
            //将history的数据转移到log表下，然后删除history里的历史数据
            RunRecordHisQuery runRecordHisQuery = new RunRecordHisQuery();
            runRecordHisQuery.setInstId(Long.parseLong(session.getAttribute("instId").toString()));
            runRecordHisQuery.setUserId(Long.parseLong(jsonOne.get("id").toString()));
            runRecordHisQuery.setDeptId(user.getDeptId());
            List<RunRecordHis> runRecordHisList = runRecordHisService.selectRunRecorHisByCondition(runRecordHisQuery);
            if(runRecordHisList!=null  && runRecordHisList.size()>0){
                //将该用户的历史记录存放到历史记录表里
                for(RunRecordHis item:runRecordHisList){
                    RunRecordHisLog runRecordHisLog =  runRecordHisService.insertRecordHisLog(item);//新增日志表数据
                    if(runRecordHisLog==null){
                        json.put("status",0);
                        json.put("msg","存入日志失败!!!");
                        writer.print(json);
                        return json;
//                        request.setAttribute("msg","存入日志失败！！！");
//                        request.getRequestDispatcher("/jsp/user/editdPerson.jsp").forward(request, response);
                    }
                    //删除his表中原有的用户的错误的数据
                      runRecordHisService.deleteHisById(item.getId());
                }
            }
        }
        //更新用户信息
        User user1 = new User();
        user1.setUserName(jsonOne.get("userName").toString());
        user1.setJobNo(jsonOne.get("jobNo").toString());
        user1.setDeptId(Long.parseLong(jsonOne.get("deptId").toString()));
        user1.setGroupId(Long.parseLong(jsonOne.get("deptId").toString()));
        user1.setPhone(jsonOne.get("phone").toString());
        user1.setId(Long.parseLong(jsonOne.get("id").toString()));
        int i = userService.updateUser(user1);
        if(i>0){
   //       request.setAttribute("msg","编辑人员成功!!!");
            json.put("msg","编辑人员成功!!!");
            json.put("status",1);
            writer.print(json);
        }
//        request.getRequestDispatcher("/jsp/user/serviceEmployees.jsp").forward(request, response);
        return json;
    }

    //办理离职
    public Integer  quitUser(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws  ServletException, IOException {
        JSONObject json = new JSONObject();
        PrintWriter writer = response.getWriter();

        String userId = request.getParameter("id");//用户id
       int i = userService.quitUser(Long.parseLong(userId));
        if(i>0){
            json.put("msg","办理离职成功");
            writer.print(json);
        }else{
            json.put("msg","办理离职失败");
            writer.print(json);
        }
//        request.getRequestDispatcher("/jsp/user/serviceEmployees.jsp").forward(request, response);
        return i;
    }

}
