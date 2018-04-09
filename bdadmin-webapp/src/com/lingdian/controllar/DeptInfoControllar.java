package com.lingdian.controllar;

import com.lingdain.command.JsonResult;
import com.lingdian.Dto.DeptDto;
import com.lingdian.model.Dept;
import com.lingdian.model.DeptQuery;
import com.lingdian.model.User;
import com.lingdian.model.UserQuery;
import com.lingdian.service.DeptService;
import com.lingdian.service.UserService;
import com.lingdian.serviceImp.DeptServiceImpl;
import com.lingdian.serviceImp.UserServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by User on 2018/3/8.
 */
public class DeptInfoControllar {
    DeptService deptService = new DeptServiceImpl();
    UserService  userService = new UserServiceImpl();


    /**
     * 查询该企业下部门所有数据(即用户里查询的部门)
     */
    public void getDeptInfoAll(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws ServletException, IOException {
        //Long instId, int userStatus
        Long instId = Long.parseLong(session.getAttribute("instId").toString());
        DeptQuery deptQuery = new DeptQuery();
        deptQuery.setInstId(instId);
        deptQuery.setDeptType(0);
        List<Dept> deptList = deptService.getDeptInfoAll(deptQuery);
        request.setAttribute("deptList", deptList);
        request.getRequestDispatcher("/jsp/user/addPerson.jsp").forward(request, response);
    }
    /**
     * 根据部门id查询部门信息
     */
    public void getDeptById(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws ServletException, IOException {
        Long  instId = Long.parseLong(session.getAttribute("instId").toString());
        String deptId =  request.getParameter("id");//部门id
//      Long instId = Long.parseLong(session.getAttribute("instId").toString());
        Dept dept = deptService.getDeptById(Long.parseLong(deptId));
        DeptQuery deptQuery = new DeptQuery();
        deptQuery.setInstId(instId);
        deptQuery.setDeptType(1);
        List<Dept> deptList = deptService.getDeptInfoAll(deptQuery);//获取部门列表
        request.setAttribute("deptList",deptList);
        request.setAttribute("dept", dept);
        if(dept != null  &&  deptList != null){
            request.setAttribute("msg","获取部门信息成功!!!");
        }else{
            request.setAttribute("msg","获取部门信息失败!!!");
        }
        request.getRequestDispatcher("/jsp/dept/editDept.jsp").forward(request, response);
    }

    public void getDeptNumberInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws ServletException, IOException {
        Long instId = Long.parseLong(session.getAttribute("instId").toString());
        DeptQuery query = new DeptQuery();
        query.setInstId(instId);
        query.setDeptType(1);
        List<Dept> deptList = deptService.getDeptInfoAll(query);
        List<DeptDto> deptDtoList = new ArrayList<DeptDto>();
        if(deptList!=null && deptList.size()>0){
            for(Dept item:deptList){
                DeptDto deptDto = new DeptDto();
                int  number = 0;
                UserQuery userQuery = new UserQuery();
                userQuery.setUserStatus(1);
                userQuery.setInstId(instId);
                userQuery.setDeptId(item.getId());
                List<User> userList = userService.getUserInfoByConditions(userQuery);
                deptDto.setId(item.getId());
                deptDto.setDeptName(item.getDeptName());
                deptDto.setNumber(userList.size());
                deptDtoList.add(deptDto);
            }
        }
        request.setAttribute("deptDtoList", deptDtoList);
        request.getRequestDispatcher("/jsp/dept/departManage.jsp").forward(request, response);
    }

    /**
     *新增部门
     */
    public void insertDept(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws ServletException, IOException {
        JSONObject json  = new JSONObject();
        PrintWriter writer = response.getWriter();
        Long instId = Long.parseLong(session.getAttribute("instId").toString());
        //获取前端传过来的json数据并遍历
        String data  = request.getParameter("data");
        JSONObject jsonOne = new JSONObject();
        jsonOne=jsonOne.fromObject(data);
//        String  deptName =  request.getParameter("deptName");
        Dept dept = new  Dept();
        dept.setDeptType(0);
        dept.setHeadUrl("http://121.40.179.35/ftp/beida/beida_head_1.1.jpg");
        dept.setInstId(instId);
        dept.setDeptName(jsonOne.get("deptName").toString());
        dept.setCreateTime(new Date());
        dept.setUpdateTime(new Date());
        Dept dept1=  deptService.insertDept(dept);
        if(dept1!=null){
            request.setAttribute("msg","新增部门成功!!!");
            json.put("msg","新增部门成功!!!");
            json.put("status",1);
        }else{
            request.setAttribute("msg","新增部门失败!!!");
            json.put("msg","新增部门失败!!!");
            json.put("status",1);
        }
        writer.print(json);
//        request.getRequestDispatcher("/jsp/dept/addDept.jsp").forward(request, response);
    }
    /**
     *新增部门
     */
    public void insertDeptNew(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/dept/addDept.jsp").forward(request, response);
    }
    /**
     *更新部门
     */
    public void updateDept(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws ServletException, IOException {
        JSONObject json = new JSONObject();
        Map param = new HashMap();
        JsonResult<Map<String, String>> result = new JsonResult<>();
        PrintWriter writer = response.getWriter();
        //获取前端传过来的json数据并遍历
        String data  = request.getParameter("data");
//        String data ="{\"id\":\"160\",\"deptName\":\"技术部\"}";//模拟了一个json数据
        JSONObject jsonOne = new JSONObject();
        jsonOne=jsonOne.fromObject(data);
        Dept dept = new Dept();
        dept.setId(Long.parseLong(jsonOne.get("id").toString()));
        dept.setDeptName(jsonOne.get("deptName").toString());
//        dept.setDeptType(Integer.parseInt(deptType));
        Integer  i  =   deptService.updateDept(dept);
        if(i>0){
            request.setAttribute("msg","编辑部门成功");
            json.put("msg","编辑部门成功");
            json.put("status",1);
            writer.print(json);
        }else {
//          request.setAttribute("msg","新增部门失败!!!");
            json.put("msg","编辑部门失败!!!");
            json.put("status",0);
            writer.print(json);
        }
    }

    /**
     *删除部门
     */
    public void  updateQuitDept(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws ServletException, IOException {
        JSONObject json = new JSONObject();
        PrintWriter writer = response.getWriter();
        String  id = request.getParameter("id");
        Long instId = Long.parseLong(session.getAttribute("instId").toString());
        //查看该部门下是否有人
        UserQuery userQuery = new UserQuery();
        userQuery.setUserStatus(1);
        userQuery.setInstId(instId);
        userQuery.setDeptId(Long.parseLong(id));
        List<User> userList = userService.getUserInfoByConditions(userQuery);
        if(userList!=null && userList.size()>0){
//            request.setAttribute("msg","删除部门失败，该部门下有用户");
//            request.getRequestDispatcher("/jsp/user.jsp").forward(request, response);
            json.put("msg","删除部门失败，该部门下有用户");
            json.put("status",0);
            writer.print(json);
            return ;
        }

//      String  deptName =  request.getParameter("deptName");
//      String  deptType  = request.getParameter("deptType");
        Dept dept = new Dept();
        dept.setId(Long.parseLong(id));
//      dept.setDeptName(deptName);
        dept.setDeptType(1);
        Integer  i  =   deptService.updateQuitDept(dept);
        if(i>0){
//            request.setAttribute("msg","新增部门成功");
            json.put("msg","新增部门成功");
            json.put("status",1);
            writer.print(json);
        }
//        request.getRequestDispatcher("/jsp/user.jsp").forward(request, response);
    }

}
