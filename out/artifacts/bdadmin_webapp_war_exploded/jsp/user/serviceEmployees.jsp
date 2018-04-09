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
                <li class="layui-nav-item layui-this"><a href="${url}/liangdian/UserServlet?who=getUserInfoByConditions&userStatus=1">在职员工</a></li>
                <li class="layui-nav-item"><a href="${url}/liangdian/UserServlet?who=getQuitUserInfoByConditions&userStatus=0">离职员工</a></li>
                <li class="layui-nav-item"><a href="${url}/liangdian/UserServlet?who=getDeptNumberInfo">部门管理</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px 10px;">
            <div class="layui-clear" style="padding:10px 0;">
                <form  action="${url}/liangdian/UserServlet?who=getUserInfoByConditions&userStatus=1" method="post">
                    <input type="submit"  class="layui-btn"   value="查询" id="searchBtn"/>
                    <input type="hidden"/>
                    <div class="search-container">
                        <input type="text" name="userName"  placeholder="请输入姓名搜索" autocomplete="off" class="layui-input">
                    </div>
                </form>
                <button data-method="addDimission" class="layui-btn addPersonnelBtn" id="">新增人员</button>
            </div>
            <div>
                <table class="layui-table" id="tableB">
                    <colgroup>
                        <col width="15%">
                        <col width="15%">
                        <col width="15%">
                        <col width="15%">
                        <col width="15%">
                        <col width="25%">
                    </colgroup>
                    <thead>
                    <tr>
                        <th>工号</th>
                        <th>姓名</th>
                        <th>部门</th>
                        <th>手机号</th>
                        <th>就职状态</th>
                        <th>操作</th>
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
                                <td>
                                    <button data-method="editDimission" class="layui-btn layui-btn-xs" data-id="${list.id}">修改信息</button>
                                    <button data-method="transactDimission" data-id="${list.id}" class="layui-btn layui-btn-xs">办理离职</button>
                                </td>
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

        //table
        var table = layui.table;
       /** table.render({
            elem: '#tableB'
            ,url:'/liangdian/UserServlet?who=queryUserAll&userStatus=1'
            ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,cols: [[
                {field:'username', width:'15%', title: '工号'}
                ,{field:'sex', width:'15%', title: '姓名', sort: true}
                ,{field:'city', width:'15%', title: '部门'}
                ,{field:'sign', title: '手机号', width: '15%', minWidth: 100} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
                ,{field:'experience', title: '就职状态', sort: true,width:'15%'}
                ,{field:'score', title: '操作', sort: true,width:'25%'}
            ]]
        });**/

        //触发事件
        var active = {
            transactDimission: function(){
                var that = this;
                var userid  = $(that).attr("data-id");
                //多窗口模式，层叠置顶
                layer.open({
                    type: 1 //此处以iframe举例
                    ,title: '提示'
                    ,shade: [0.3,'#000']
                    ,content: '<p style="padding:15px;">离职后员工将从排行榜移除,且无法再次登录！</p>'
                    ,btn: ['确定', '取消']
                    ,yes: function(){
                        $.ajax({
                            url:'${url}/liangdian/UserServlet?who=quitUser',
                            type:'post',
                            data:{
                                id:userid
                            },
                            success:function(data){
                                layer.msg('办理离职成功', {
                                    icon: 1,
                                    time: 2000 //2秒关闭（如果不配置，默认是3秒）
                                }, function(){
                                    window.parent.location.reload();
                                });

                            },
                            error:function(data){
                                layer.msg('网络异常,请联系技术人员！');
                                return false;
                            }
                        });
                    }
                    ,btn2: function(){
                        layer.closeAll();
                    }

                });
            },
            addDimission: function(){
                var that = this;
                layer.open({
                    type: 2,
                    title: '新增人员',
                    area: ['450px','550px'],
                    content:'${url}/liangdian/UserServlet?who=getDeptInfoAll',
                    success: function(layero){
                      //  layer.setTop(layero);
                    }

                });
            },
            editDimission: function(){
                var that = this;
                var userid  = $(that).attr("data-id");
                layer.open({
                    type: 2,
                    title: '修改信息',
                    area: ['450px','550px'],
                    content:'${url}/liangdian/UserServlet?who=getUserById&id='+userid,
                    success: function(layero){
                       // layer.setTop(layero);
                    }


            });


            }
        };

        $('#servingDepartment .layui-btn').on('click', function(){
            var othis = $(this), method = othis.data('method');
            active[method] ? active[method].call(this, othis) : '';
        });

       // $("#searchBtn").trigger("click");


    });




</script>
</body>

</html>