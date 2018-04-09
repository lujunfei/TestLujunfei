<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>凡城运动 - 贝达药业后台管理系统达</title>
<link rel="stylesheet" href="../../jsp/layui/css/layui.css">
</head>
<style type="text/css">
    .addDeptBtn{
       float:right;
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
                <li class="layui-nav-item"><a href="${url}/liangdian/UserServlet?who=getQuitUserInfoByConditions&userStatus=0">离职员工</a></li>
                <li class="layui-nav-item layui-this"><a href="${url}/liangdian/UserServlet?who=getDeptNumberInfo">部门管理</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px 10px;">
            <div class="layui-clear" style="padding:10px 0;">
                <button data-method="addDept" class="layui-btn addDeptBtn" id="">新增部门</button>
            </div>
            <div>
                <table class="layui-table" id="tableB">
                    <colgroup>
                        <col width="30%">
                        <col width="30%">
                        <col width="40%">
                    </colgroup>
                    <thead>
                    <tr>
                        <th>部门</th>
                        <th>人数</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${deptDtoList}" var="list">
                            <tr>
                                <td>${list.deptName}</td>
                                <td>${list.number}</td>
                                <td>
                                    <button data-method="editDept" class="layui-btn layui-btn-xs" data-id="${list.id}">修改部门名称</button>
                                    <c:if test="${list.number==0}">
                                    <button data-method="deleteDept" data-id="${list.id}" class="layui-btn layui-btn-xs">删除</button>
                                    </c:if>
                                    <c:if test="${list.number!=0}">
                                        <button data-method="promptMessage" data-id="${list.id}" class="layui-btn layui-btn-xs">删除</button>
                                    </c:if>
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
        //触发事件
        var active = {
            promptMessage : function(){
                var that = this;
                var userid  = $(that).attr("data-id");
                //多窗口模式，层叠置顶
                layer.open({
                    type: 1 //此处以iframe举例
                    ,title: '提示'
                    ,shade: [0.3,'#000']
                    ,content: '<p style="padding:15px;">该部门下存在员工，无法删除！</p>'
                });
            },
            deleteDept: function(){
                var that = this;
                var userid  = $(that).attr("data-id");
                //多窗口模式，层叠置顶
                layer.open({
                    type: 1 //此处以iframe举例
                    ,title: '提示'
                    ,shade: [0.3,'#000']
                    ,content: '<p style="padding:15px;">是否确认删除该部门?</p>'
                    ,btn: ['确认', '取消']
                    ,yes: function(){
                        $.ajax({
                            url:'${url}/liangdian/UserServlet?who=updateQuitDept',
                            type:'post',
                            data:{
                                id:userid
                            },
                            success:function(data){
                                layer.msg('删除成功', {
                                    icon: 1,
                                    time: 2000 //2秒关闭（如果不配置，默认是3秒）
                                }, function(){
                                    window.parent.location.reload();
                                });
                            },
                            error:function(data){
                               // alert(data.msg);
                                layer.msg(data.msg);
                            }
                        });
                    }
                    ,btn2: function(){
                        layer.closeAll();
                    }

                });
            },
            addDept: function(){
                var that = this;
                layer.open({
                    type: 2,
                    title: '新建部门',
                    //content:'${url}/liangdian/UserServlet?who=insertDept',
                    content:'${url}/liangdian/UserServlet?who=insertDeptNew',
                    success: function(layero){
                     // layer.setTop(layero);
                    }

                });
            },
           editDept: function(){
                var that = this;
                var editId  = $(that).attr("data-id");
                layer.open({
                    type: 2,
                    title: '修改部门名称',
                    content:'${url}/liangdian/UserServlet?who=getDeptById&id='+editId,
                    success: function(layero){
                       layer.setTop(layero);
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