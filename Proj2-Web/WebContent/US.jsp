<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
<head>
<meta charset="utf-8">
<title>US</title>
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
<body>

	<h3 style="color: #006FA4; margin-top: -2px">Recent News</h3>
	<hr style="height: 2px; color: #f00; background-color: #f00">
	<div class="container">
		<div id="blog" class="row">
			<c:forEach var="news" items="${news}">
				<div class="col-md-10 blogShort">
					<h1>Title 1</h1>
					<img
						src="http://www.kaczmarek-photo.com/wp-content/uploads/2012/06/guinnes-150x150.jpg"
						alt="post img"
						class="pull-left img-responsive thumb margin10 img-thumbnail">

					<em>This snippet use <a
						href="http://bootsnipp.com/snippets/featured/sexy-sidebar-navigation"
						target="_blank">Sexy Sidebar Navigation</a></em>
					<article>
						<p>Lorem Ipsum is simply dummy text of the printing and
							typesetting industry. Lorem Ipsum has been the industry's
							standard dummy text ever since the 1500s, when an unknown printer
							took a galley of type and scrambled it to make a type specimen
							book. It has survived not only five centuries, but also the leap
							into electronic typesetting, remaining essentially unchanged. It
							was popularised in the 1960s with the release of Letraset sheets
							containing Lorem Ipsum passages, and more recently with desktop
							publishing software like Aldus PageMaker including versions of
							Lorem Ipsum.</p>
					</article>
					<a class="btn btn-blog pull-right marginBottom10"
						href="http://bootsnipp.com/user/snippets/2RoQ">READ MORE</a>
				</div>
			</c:forEach>
			<div class="col-md-12 gap10"></div>
		</div>
	</div>


</body>
</html>