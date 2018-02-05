<%@ page language="java" import="java.util.*,djfsoft.pojo.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>主页</title>
    <%String projectName  = request.getContextPath();%>
    <link rel="stylesheet" type="text/css" href="<%=projectName%>/easyui/themes/default/easyui.css">   
	<link rel="stylesheet" type="text/css" href="<%=projectName%>/easyui/themes/icon.css">   
	<script type="text/javascript" src="<%=projectName%>/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=projectName%>/easyui/jquery.easyui.min.js"></script>
	<style type="text/css"></style>
	<script type="text/javascript">
		$(function(){
		})
	</script>
  </head>
  <body>
	<div id="main_layout" class="easyui-layout" style="width:100%;height:100%;">
		<%@include file="../../common/nav.jsp"%>
		<div data-options="region:'north'" style="height:51px">
		</div>
		<div data-options="region:'west'" title="规格" style="width: 50%;">
			<iframe id="iframe_product_attribute_name" src="<%=projectName%>/manage/productAttribute/list" style="width: 99%;height: 99%;"></iframe>
		</div>
		<div data-options="region:'center'" title="值">
			<iframe id="iframe_product_attribute_value"  style="width: 99%;height: 99%;"></iframe>
		</div>
	</div>
</body>
</html>

