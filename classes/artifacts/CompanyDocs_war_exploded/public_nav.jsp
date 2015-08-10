<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
	<div class="row" style="height:20px"></div>
    <div class="row">
			<div class="navbar navbar-default" style="background-color:#1c588f;color:white" role="navigation">
			<div class="navbar-header">
				<!-- .navbar-toggle样式用于toggle收缩的内容，即nav-collapse collapse样式所在元素 -->
				<button class="navbar-toggle" type="button" data-toggle="collapse"
					data-target=".navbar-responsive-collapse">
					<span class="sr-only">全国中小企业股份转让系统</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<!-- 确保无论是宽屏还是窄屏，navbar-brand都显示 -->
				<a href="" class="navbar-brand">全国中小企业股份转让系统</a>
			</div>
			<!-- 屏幕宽度小于768px时，div.navbar-responsive-collapse容器里的内容都会隐藏，显示icon-bar图标，当点击icon-bar图标时，再展开。屏幕大于768px时，默认显示。 -->
			<div class="collapse navbar-collapse navbar-responsive-collapse">
				<ul class="nav navbar-nav list-inline">

					<li><a href="index.jsp">系统首页</a></li>
					<li><a href="list.jsp">文档信息</a></li>
					<li><a href="statisbar.jsp">数据统计</a></li>
					<li><a href="user.jsp">个人设置</a></li>
				</ul>
				<p style="float:right;height:10ox;margin-top:15px;">欢迎你:Admin</p>
			</div>
			</div>
	</div>