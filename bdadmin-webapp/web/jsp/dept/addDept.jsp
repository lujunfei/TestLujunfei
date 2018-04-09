<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>凡城运动 - 贝达药业后台管理系统达</title>
<link rel="stylesheet" href="../../jsp/layui/css/layui.css">
</head>
<body class="layui-layout-body">
	<form class="layui-form" style="padding:20px 15px;"  >
		<div class="layui-form-item">
			  <input name="deptName" lay-verify="required" autocomplete="off" placeholder="请输入部门名称" class="layui-input" type="text">

		</div>

		<div class="layui-form-item">


				<button class="layui-btn" lay-submit="" lay-filter="demo1">保存</button>

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

		//监听提交
		form.on('submit(demo1)', function(data){
			$.ajax({
				url:'${url}/liangdian/UserServlet?who=insertDept',
				type:'POST',
				data:{
					data:JSON.stringify(data.field)
				},
				dataType:"json",
				success:function(data){
					layer.msg('新增成功', {
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
			return false;
		});


	});

</script>
</body>
</html>