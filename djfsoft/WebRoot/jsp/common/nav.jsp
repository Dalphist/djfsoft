<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=projectName%>/js/nav/common.js"></script>
<link rel="stylesheet" type="text/css" href="<%=projectName%>/css/nav/style.css"  media="all" />

		<div id="holder" style="position:absolute;z-index:99;">
			<div id="header">
				<div class="inner">
					<div id="nav" style="width:1200px;">
						<ul id="main-menu" class="sm sm-blue">
							<li>
								<a title="Go to home page" href="/">
									<span id="logo-menu">SmartMenus</span>
								</a>
							</li>
							<li>
								<a>采购</a>
								<ul class="sub-menu">
									<li><a>采购计划</a></li>
									<li><a onclick="window.location='<%=projectName%>/purchase/purchaseOrder/index'">采购订单</a></li>
									<li><a>采购入库</a></li>
									<li><a>采购退货</a></li>
								</ul>
							</li>
							<li>
								<a>销售</a>
								<ul class="sub-menu">
									<li><a onclick="window.location='<%=projectName%>/sales/salesOrder/index'">销售订单</a></li>
									<li><a>销售出库</a></li>
									<li><a>销售退货</a></li>
								</ul>
							</li>
							<li>
								<a>仓库</a>
								<ul class="sub-menu">
									<li><a onclick="window.location='<%=projectName%>/main'">库存</a></li>
									<li><a>调拨</a></li>
									<li><a>调整</a></li>
								</ul>
							</li>
							<li>
								<a>设置</a>
								<ul class="sub-menu">
									<li><a href="1">用户</a></li>
									<li><a href="<%=projectName%>/manage/product/index">商品</a></li>
									<li><a href="<%=projectName%>/manage/productAttribute/index">规格</a></li>
								</ul>
							</li>
							<li>
								<a>统计</a>
								<ul class="sub-menu">
									<li><a>aa</a></li>
									<li><a>bb</a></li>
								</ul>
							</li>
							<li id="menu-item-155">
								<a >Download</a>
							</li>
							<li id="menu-item-165">
							<a href="1">Blog</a></li>
							<li id="menu-item-989">
							<a title="Tutorials and API documentation" href="1">
							<span aria-hidden="true" class="icon-twitter" data-icon="o">
							</span>Docs</a></li>
						</ul>
					</div>
					</div>
			</div>
		</div>

