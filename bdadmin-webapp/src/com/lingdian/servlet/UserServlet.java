package com.lingdian.servlet;

import com.lingdian.controllar.DeptInfoControllar;
import com.lingdian.controllar.UserInfoControllar;
import com.lingdian.model.User;
import com.lingdian.model.UserQuery;
import com.lingdian.service.UserService;
import com.lingdian.serviceImp.UserServiceImpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by User on 2018/3/7.
 */
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    //这里建立 与service层的  联系  创建一个service层imp的某个的对
    UserService myITb_UserService = new UserServiceImpl();
    UserInfoControllar userInfo = new UserInfoControllar();
    DeptInfoControllar deptInfo = new DeptInfoControllar();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");//解决乱码
        String type = request.getParameter("who");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String sessionKey = request.getParameter("sessionKey");//用来判断是否登录成功，防止用户未登录直接跳转到用户界面
        HttpSession session =request.getSession();

        if ("login".equals(type)) {//登录接口,
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            userInfo.getAdministratorInfoByConditions(request, response,session, userName, password);//通过账号和密码判定是否登录成功
        } else if ("queryUserAll".equals(type)) {//查询员工信息
            isLogin(request, response, session);
            String instId = session.getAttribute("instId").toString();
            Long inId = Long.parseLong(instId);
            String  userStatus = request.getParameter("userStatus");
            userInfo.queryUserAll(request, response, inId,Integer.parseInt(userStatus));
        } else if("getUserInfoByConditions".equals(type)){ //根据搜索条件获取用户列表
            isLogin(request, response, session);
            String  userName = request.getParameter("userName");//姓名
            String  inId  =session.getAttribute("instId").toString();
            String  userStatus = request.getParameter("userStatus");      //判断查询是否是离职还是在职的
            UserQuery userQuery = new UserQuery();
            userQuery.setUserName(userName);
            userQuery.setInstId(Long.parseLong(inId));
            userQuery.setUserStatus(Integer.parseInt(userStatus));
            userInfo.getUserInfoByConditions(request,response,session,userQuery);
        }
        else if("getQuitUserInfoByConditions".equals(type)){ //根据搜索条件获取离职用户列表
            isLogin(request, response, session);
            String  userName = request.getParameter("userName");//姓名
            String  inId  =session.getAttribute("instId").toString();
            String  userStatus = request.getParameter("userStatus");      //判断查询是否是离职还是在职的
            UserQuery userQuery = new UserQuery();
            userQuery.setUserName(userName);
            userQuery.setInstId(Long.parseLong(inId));
            userQuery.setUserStatus(Integer.parseInt(userStatus));
            userInfo.getQuitUserInfoByConditions(request,response,userQuery);
        }else if("getUserById".equals(type)){
            isLogin(request,response,session);
            userInfo.getUserById(request,response,session);
        }else if("insertUser".equals(type)){
            isLogin(request,response,session);
            userInfo.insertUser(request,response,session);
        }else if("getDeptInfoAll".equals(type)){//获取部门信息接口
            isLogin(request,response,session);
            deptInfo.getDeptInfoAll(request,response,session);
        }else if("updateUser".equals(type)){
            isLogin(request,response,session);
            userInfo.updateUser(request,response,session);
        }else if("quitUser".equals(type)){//办理离职
            isLogin(request,response,session);
            userInfo.quitUser(request,response,session);
        }else  if("getDeptNumberInfo".equals(type)){//获取部门信息接口
            isLogin(request,response,session);
            deptInfo.getDeptNumberInfo(request,response,session);
        }else if("insertDept".equals(type)){//新增部门接口
            isLogin(request,response,session);
            deptInfo.insertDept(request,response,session);
        }else if("updateDept".equals(type)){//编辑部门
            isLogin(request,response,session);
            deptInfo.updateDept(request,response,session);
        }else if("getDeptById".equals(type)){
            isLogin(request,response,session);
            deptInfo.getDeptById(request,response,session);
        } else if("updateQuitDept".equals(type)){//删除部门
            isLogin(request,response,session);
            deptInfo.updateQuitDept(request,response,session);
        } else if("insertDeptNew".equals(type)){ //点击新增部门
            isLogin(request,response,session);
            deptInfo.insertDeptNew(request,response,session);
        }
    }
   //公共类
    public void isLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        if ( session.getAttribute("instId") == null || "".equals(session.getAttribute("instId"))) {
            request.setAttribute("msg", "用户未登录");
            request.getRequestDispatcher("/jsp/error/error.jsp").forward(request, response);
//            JSONObject  json = new JSONObject();
//            PrintWriter writer = response.getWriter();
//            json.put("msg", "用户未登录");
//            json.put("status",0);
//            writer.print(json);
        }
    }
}


