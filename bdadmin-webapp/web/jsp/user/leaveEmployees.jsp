<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>凡城运动 - 贝达药业后台管理系统达</title>
<link rel="stylesheet" href="../../jsp/layui/css/layui.css">
</head>
<style type="text/css">
    .addPersonnelBtn{
        position: absolute;
        right: 15px;
        top: 20px;
    }
    .search-container{
        width:300px;
        float:left;
    }
</style>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin" id="servingDepartment">
    <div class="layui-header">
        <div class="layui-logo">贝达药业后台管理系统达</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-right layui-hide">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    贤心
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">退了</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item "><a href="${url}/liangdian/UserServlet?who=getUserInfoByConditions&userStatus=1">在职员工</a></li>
                <li class="layui-nav-item layui-this"><a href="${url}/liangdian/UserServlet?who=getQuitUserInfoByConditions&userStatus=0">离职员工</a></li>
                <li class="layui-nav-item"><a href="${url}/liangdian/UserServlet?who=getDeptNumberInfo">部门管理</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px 10px;">
            <div class="layui-clear" style="padding:10px 0;">
                <form  action="${url}/liangdian/UserServlet?who=getQuitUserInfoByConditions&userStatus=0" method="post">
                    <input type="submit"  class="layui-btn"   value="查询" id="searchBtn"/>
                    <input type="hidden"/>
                    <div class="search-container">
                        <input type="text" name="userName"  placeholder="请输入姓名搜索" autocomplete="off" class="layui-input">
                    </div>
                </form>

            </div>
            <div>
                <table class="layui-table" id="tableB">
                    <colgroup>
                        <col width="20%">
                        <col width="20%">
                        <col width="20%">
                        <col width="20%">
                        <col width="20%">

                    </colgroup>
                    <thead>
                    <tr>
                        <th>工号</th>
                        <th>姓名</th>
                        <th>部门</th>
                        <th>手机号</th>
                        <th>就职状态</th>

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

                            </tr>
                        </c:forEach>


                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>



<script src="../../jsp/layui/layui.js"></script>
<script>
    //JavaScript代码区域

    layui.use(['layer','form','table'], function(){ //独立版的layer无需执行这一句
        var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
    });


</script>
</body>

</html>