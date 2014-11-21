<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<html lang="en">
<head>
<meta charset="utf-8">
<title>World</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!--link rel="stylesheet/less" href="less/bootstrap.less" type="text/css" /-->
<!--link rel="stylesheet/less" href="less/responsive.less" type="text/css" /-->
<!--script src="js/less-1.3.3.min.js"></script-->
<!--append ‘#!watch’ to the browser URL, then refresh the page. -->

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
  <![endif]-->

<!-- Fav and touch icons -->
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="img/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="img/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="img/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="img/apple-touch-icon-57-precomposed.png">
<link rel="shortcut icon" href="img/favicon.png">

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/scripts.js"></script>

</head>


<body style="background-color: #f8f8f8">
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<nav class="navbar navbar-default navbar-fixed-top"
					role="navigation" style="background-color: #006FA4">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1">
							<span class="sr-only">Toggle navigation</span><span
								class="icon-bar"></span><span class="icon-bar"></span><span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="Main" style="color: #FFFFFF">IS
							NEWS</a>
					</div>
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li><a href="http://localhost:8080/Proj2-Web/topicNews?U.S." id="usNews" style="color: #FFFFFF">US</a></li>
							<li><a href="http://localhost:8080/Proj2-Web/topicNews?MiddleEast" id="middleNews" style="color: #FFFFFF">Middle
									East</a></li>
							<li><a href="http://localhost:8080/Proj2-Web/topicNews?Asia" id="asiaNews" style="color: #FFFFFF">Asia</a></li>
							<li><a href="http://localhost:8080/Proj2-Web/topicNews?Africa" id="africaNews" style="color: #FFFFFF">Africa</a></li>
							<li><a href="http://localhost:8080/Proj2-Web/topicNews?Europe" id="euNews" style="color: #FFFFFF">Europe</a></li>
							<li><a href="http://localhost:8080/Proj2-Web/topicNews?LatinAmerica" id="latinNews" style="color: #FFFFFF">Latin
									America</a></li>
							<li><a href="http://localhost:8080/Proj2-Web/topicNews?World" id="worldNews" style="color: #FFFFFF">World</a></li>
							<li><a href="http://localhost:8080/Proj2-Web/topicNews?WorldSport" id="sportNews" style="color: #FFFFFF">World
									Sport</a></li>
							<li><a href="#" id="searchDate"
								style="color: #FFFFFF">Search by Date</a></li>
							<li><a href="#" id="searchAuthor" style="color: #FFFFFF">Search
									by Author</a></li>
							<li><a style="color: #FFFFFF">Search highlights</a></li>
						</ul>
						<form class="navbar-form navbar-left">
							<div class="form-group">
								<input type="text" id="toSearch">
							</div>
							<a href="#" id="search" type="button"
								class="btn btn-success btn-sm">Go</a>
						</form>
						<ul class="nav navbar-nav navbar-right">
							<li><a href="#" id="admin" style="color: #FFFFFF">Admin
									Panel</a></li>
							<li><a href="#" id="userStats" style="color: #FFFFFF"><%=session.getAttribute("username")%></a></li>
							<li><a href="Logout" id="Logout" style="color: #FFFFFF">Logout</a></li>
						</ul>
					</div>
				</nav>
			</div>
		</div>
		<div class="row clearfix">
			<div class="col-md-12 column" style="margin-top: 100px;">
				<div class="col-md-12 column" id="dinamicDiv"
					style="margin-top: 22px">
					<h3 style="color: #006FA4; margin-top: -2px">World News</h3>
					<hr style="height: 2px; color: #f00; background-color: #f00">

					<div class="container">
						<div id="blog" class="row">
						<c:forEach begin="0" end="${fn:length(news) - 1}" var="index">
								<div class="col-md-10 blogShort">
									<h1><c:out value="${news[index].title}"/> </h1>
									<img
										src = <c:out value="${photos[index]}"/>
										alt="http://www.floridaacs.com/images/image_not_found.png"
										class="pull-left img-responsive thumb margin10 img-thumbnail">
									<article>
									 	<p>On <c:out value="${news[index].date}"/></p>
										<p>By <c:out value="${news[index].author.name}"/></p>    
										<c:forEach var="highlight" items="${news[index].highlights}">
											<li>${highlight.content}</li>
										</c:forEach>
									</article>
									<a class="btn btn-blog pull-right marginBottom10"
										href="http://bootsnipp.com/user/snippets/2RoQ">READ MORE</a>
								</div>
							</c:forEach>
							
							<div class="col-md-12 gap10"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
