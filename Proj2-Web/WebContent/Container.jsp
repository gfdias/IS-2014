<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
<head>
<meta charset="utf-8">
<title>${news[0].title}</title>
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
							<li><a href="http://localhost:8080/Proj2-Web/topicNews?U.S."
								id="usNews" style="color: #FFFFFF">US</a></li>
							<li><a
								href="http://localhost:8080/Proj2-Web/topicNews?MiddleEast"
								id="middleNews" style="color: #FFFFFF">Middle East</a></li>
							<li><a href="http://localhost:8080/Proj2-Web/topicNews?Asia"
								id="asiaNews" style="color: #FFFFFF">Asia</a></li>
							<li><a
								href="http://localhost:8080/Proj2-Web/topicNews?Africa"
								id="africaNews" style="color: #FFFFFF">Africa</a></li>
							<li><a
								href="http://localhost:8080/Proj2-Web/topicNews?Europe"
								id="euNews" style="color: #FFFFFF">Europe</a></li>
							<li><a
								href="http://localhost:8080/Proj2-Web/topicNews?LatinAmerica"
								id="latinNews" style="color: #FFFFFF">Latin America</a></li>
							<li><a
								href="http://localhost:8080/Proj2-Web/topicNews?World"
								id="worldNews" style="color: #FFFFFF">World</a></li>
							<li><a
								href="http://localhost:8080/Proj2-Web/topicNews?WorldSport"
								id="sportNews" style="color: #FFFFFF">World Sport</a></li>
							

							<li><a href="#" id="searchAuthor" style="color: #FFFFFF">Search
									by Author</a></li>
									<li>

								<div style="margin-top:8px; position:relative" class="dropdown">
									<button class="btn btn-default dropdown-toggle" type="button"
										id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
										Search by Date <span class="caret"></span>
									</button>
									<ul class="dropdown-menu" role="menu"
										aria-labelledby="dropdownMenu1">
										<li role="presentation"><a role="menuitem" tabindex="-1"
											href="http://localhost:8080/Proj2-Web/DateSearch?12">Less than 12 hours</a></li>
										<li role="presentation"><a role="menuitem" tabindex="-1"
											href="http://localhost:8080/Proj2-Web/DateSearch?24">Less than 24 hours</a></li>
										<li role="presentation"><a role="menuitem" tabindex="-1"
											href="http://localhost:8080/Proj2-Web/DateSearch?48">Less than 48 hours</a></li>
										<li role="presentation"><a role="menuitem" tabindex="-1"
											href="http://localhost:8080/Proj2-Web/DateSearch?All">All time</a></li>
									</ul>
								</div></li>
							<li><a style="color: #FFFFFF; margin-left:10px">Search highlights</a></li>
						</ul>
						<form class="navbar-form navbar-left" >
						<div class="form-group">
							<input type="text" id="toSearch"  >
						</div> 
						<a href="#" id="search" type="button" class="btn btn-success btn-sm">Go</a>
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
					<h3 style="color: #006FA4; margin-top: -2px">${news[0].title}</h3>
					<hr style="height: 2px; color: #f00; background-color: #f00">

					<div class="container">
						<div id="blog" class="row">
							<c:forEach begin="0" end="${fn:length(news) - 1}" var="index">
								<div class="col-md-10 blogShort">

						
									<img src=<c:out value="${photos[index]}"/> alt="post img"

										class="pull-left img-responsive thumb margin10 img-thumbnail">
									<article>
										<div style="margin-bottom: 10px">

											<span class="btn btn-info btn pull-center marginBottom10"
												style="opacity: 1.0; box-shadow: none; cursor: default"><c:out
													value="${news[index].date}" /></span>
										</div>
										<strong> <span style="margin-top: 10px"><c:out
													value="${news[index].author.name}" /></span>
										</strong>
										<hr style="height: 2px; color: black; background-color: black">
										
										<c:forEach var="highlight" items="${news[index].highlights}">
											<li>${highlight.content}</li>
										</c:forEach>
										<hr style="height: 2px; color: black; background-color: black">
										<p><c:out
													value="${news[index].content}" /></p>
									</article>
									<p></p>
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
