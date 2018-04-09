<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--<select class="form-control " name="isOpenTopic" id="isOpenTopic">--%>
<%--<option value="">请选择</option>--%>
<%--<c:forEach var="item" items="${list}" varStatus="status">--%>
<%--<input  type ="text" value = ${item.userName}/>--%>
<%--</c:forEach>--%>
<%--</select>--%>
<form action="${url}/liangdian/UserServlet?who=queryUserAll" method="post">
    工号：<input type="text"/>
    手机号：<input type="text"/>
    sessionKey: <input name="sessionKey" value="${sessionKey}">
    公司id:<input  name = "instId" value = "${instId}">
    <input  name = "userStatus" value = "1">
    <input type="submit" value="查询"/>
    <form>
        登录信息：${msg}

        <div align="center" style="width: 400px; position: relative;left:450px;">
            <form action="${url}/lingdian/UserServlet?who=queryAll" method="post">
                <input type="submit" value="查询所有的数据"/> <br/>
                <table border="1" cellspacing="0">
                    <thead>
                    <tr>
                        <td>工号</td>
                        <td>姓名</td>
                        <td>部门</td>
                        <td>手机号</td>
                        <td>就职状态</td>
                        <td>操作</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="list">
                        <tr>
                            <td>${list.jobNo}</td>
                            <td>${list.userName}</td>
                            <td>${list.deptName}</td>
                            <td>${list.phone}</td>
                            <td>${list.userStatusValue}</td>
                            <td><a href="${url}/lingdian/UserServlet?who=queryById&id=${list.id}"
                                   style='text-decoration:none' onclick='update(this)'>修改信息&nbsp;</a>
                                <a href="${url}/lingdian/UserServlet?who=delete&id=${list.id}"
                                   style='text-decoration:none'>办理离职</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <hr/>
            </form>
        </div><br/>
        <form action="${url}/liangdian/UserServlet?who=getUserInfoByConditions" method="post">
            用户名：<input type="userName" value="丁列明"/>
            手机号：<input type="text"/>
            sessionKey: <input name="sessionKey" value="${sessionKey}">
            公司id:<input  name = "instId" value = "${instId}">  userStatus
            <input  name = "userStatus" value = "1">
            <input type="submit" value="查询条件"/>
            <form><br/>

                <div align="center" style="width: 400px; position: relative;left:450px;">
                    <form action="${url}/lingdian/UserServlet?who=queryAll" method="post">
                        <input type="submit" value="查询所有的数据"/> <br/>
                        <table border="1" cellspacing="0">
                            <thead>
                            <tr>
                                <td>工号</td>
                                <td>姓名</td>
                                <td>部门</td>
                                <td>手机号</td>
                                <td>就职状态</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${userList}" var="list">
                                <tr>
                                    <td>${list.jobNo}</td>
                                    <td>${list.userName}</td>
                                    <td>${list.deptName}</td>
                                    <td>${list.phone}</td>
                                    <td>${list.userStatusValue}</td>
                                    <td><a href="${url}/lingdian/UserServlet?who=queryById&id=${list.id}"
                                           style='text-decoration:none' onclick='update(this)'>修改信息&nbsp;</a>
                                        <a href="${url}/lingdian/UserServlet?who=delete&id=${list.id}"
                                           style='text-decoration:none'>办理离职</a></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <hr/>
                    </form>
                </div><br/>

</body>
</html>
