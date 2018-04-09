<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta charset="utf-8">
    <title>凡城运动 - 贝达药业后台管理系统达</title>
    <link rel="stylesheet" href="jsp/layui/css/layui.css">
</head>
<style type="text/css">
    body,html{height:100%;}
    body{

        position:relative;

    }
    .layui-layout-admin{
        height:100%;
        width:100%;
    }
    .login-body{

        width:100%;
        height:100%;
        background:url(jsp/img/bj.jpg) no-repeat;
        background-size:cover;
    }
    .login-container{
        background:#fff;
        padding:20px;
        border-radius:10px;
        width:280px;
        position:absolute;
        top: calc(50% - 160px);
        top: -moz-calc(50% - 160px);
        top:-webkit-calc(50% - 160px);
        left: -webkit-calc(50% - 180px);
        left: -moz-calc(50% - 180px);
        left:calc(50% - 180px);

    }
    .login-container .layui-input-block{
        margin-left:0;
    }
    .login-container .login-form-label{
        padding-left: 0;
        width: 100%;
        text-align: left;
    }
    .login-container .login-btn{
        width:100%;
    }

</style>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin " >
    <div class="login-body">
        <div class="login-container">
            <form class="layui-form" action="${url}/liangdian/UserServlet?who=login" method="post">
                <div class="layui-form-item" style="text-align:center">
                    <img src="jsp/img/logo.jpg">
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label login-form-label">用户名:</label>
                    <div class="layui-input-block">
                        <input name="userName" lay-verify="userName" autocomplete="off" placeholder="请输入您的用户名" class="layui-input" type="text">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label login-form-label">密码:</label>
                    <div class="layui-input-block">
                        <input name="password" lay-verify="pass" autocomplete="off" placeholder="请输入您的密码" class="layui-input" type="password">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn layui-btn-normal login-btn" lay-submit="" lay-filter="demo1">立即提交</button>
                    </div>
                </div>
            </form>
        </div>
    </div></div>

<script src="jsp/layui/layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form ,layer = layui.layer;

        //自定义验证规则
        form.verify({
            userName: function(value){
                if(value.length < 2){
                    return '标题至少得5个字符啊';
                }
            }
            ,pass: [/(.+){1,5}$/, '密码必须6到12位']
            ,content: function(value){
                layedit.sync(editIndex);
            }
        });


        //监听提交
        form.on('submit(demo1)', function(data){
            /**
            layer.alert(JSON.stringify(data.field), {
                title: '最终的提交信息'
            })
            return false;**/
            console.log(data);
        });


    });
</script>
</body>
</html>