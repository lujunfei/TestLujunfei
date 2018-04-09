<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>凡城运动 - 贝达药业后台管理系统达</title>
<link rel="stylesheet" href="../../jsp/layui/css/layui.css">
</head>
<body class="layui-layout-body">
	<%--<form class="layui-form" style="padding:20px 15px;" action="${url}/liangdian/UserServlet?who=insertUser" method="post">--%>
	<form class="layui-form" style="padding:20px 15px;" >
		<div class="layui-form-item">
			<label class="layui-form-label">姓名</label>
			<div class="layui-input-block">
			  <input name="userName" lay-verify="required" autocomplete="off" placeholder="请输入姓名" class="layui-input" type="text">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">工号</label>
			<div class="layui-input-block">
			  <input name="jobNo" lay-verify="required" autocomplete="off" placeholder="工号" class="layui-input" type="text" id="gonghao">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">部门</label>
			<div class="layui-input-block">
			  <select name="deptId" lay-verify="required" lay-search="">
				  <option value="">直接选择或搜索选择</option>
				  <c:forEach items="${deptList}" var="list">
					  <option value="${list.id}">${list.deptName}</option>
				  </c:forEach>

				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">手机号</label>
			<div class="layui-input-block">
			  <input name="phone" lay-verify="Tel" autocomplete="off" placeholder="请输入手机号" class="layui-input" type="number">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label"></label>
			<div class="layui-input-block">
				<button class="layui-btn " lay-submit="" lay-filter="demo1">保存</button>
			</div>
		</div>

	</form>
<script src="../../jsp/layui/layui.js"></script>
<script>
	layui.use(['form', 'layedit'], function(){
		var form = layui.form
				,layer = layui.layer
				,layedit = layui.layedit;
		var $ = layui.jquery;

		//创建一个编辑器
		var editIndex = layedit.build('LAY_demo_editor');

		//自定义验证规则
		form.verify({
			Tel: [/(.+){6,12}$/, '手机号码输入有误']
			,content: function(value){
				layedit.sync(editIndex);
			}
		});
		//监听提交
		form.on('submit(demo1)', function(data){

			$.ajax({
				url:'${url}/liangdian/UserServlet?who=insertUser',
				type:'POST',
				data:{
					data:JSON.stringify(data.field)
				},
				dataType:"json",
				success:function(data){
					if(data.status==1){//1成功，0失败
						layer.msg('新增成功', {
							icon: 1,
							time: 2000 //2秒关闭（如果不配置，默认是3秒）
						}, function(){
							window.parent.location.reload();
						});

					}else{
						layer.msg(data.msg);
					}

				},
				error:function(data){
					layer.msg('网络异常,请联系技术人员！');
					return false;
				}
			});
			return false;

		});


	});

</script>
</body>
</html>