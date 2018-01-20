<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>登陆</title>
    <link href="dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/signin.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript">
		$(function(){
			$("#login").click(function(){
				var name = $("input").eq(0).val();
				var password = $("input").eq(1).val();
				if(name == '' || password == ''){
					alert("账号/密码不能为空");
					return false;
				}
				$.ajax({
	                url:"/djfsoft/login",
	                type:"post",
	                dataType:"json",
	                data:{"userName":name,"password":password},
	                success:function(data){
	                	var code = data.code;
	                	if(code == 0){
	                		window.location="main";
	                	}else{
	     					alert("用户名或密码错误。");           		
	                	}
	                },
	                error:function(){
	                }
	            });
			})
			//回车登录
			$(document).keyup(function(event){
				if(event.keyCode == 13){
					$("#login").click();
				}
			})
		})
	</script>
  </head>

  <body>
    <div class="container">
		<div class="login-panel panel panel-default">
               <div class="panel-heading">
                   <h3 class="panel-title">请登录</h3>
               </div>
               <div class="panel-body">
                     <div class="form-group">
                         <input class="form-control" placeholder="账户名" name="username" autofocus>
                     </div>
                     <div class="form-group">
                         <input class="form-control" placeholder="密码" name="password" type="password">
                     </div>
                     <div class="checkbox">
                         <label>
                             <input name="remember" type="checkbox" value="RememberMe">记住我
                         </label>
                     </div>
                     <input id="login" type="button" value="登录" class="btn btn-primary form-control">
                </div>
		</div>
	</div>
  </body>
</html>

