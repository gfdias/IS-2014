<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
<head>
<meta charset="utf-8">
<title>Author News Search</title>
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
	<h3 style="color: #006FA4; margin-top: -2px">Search Author News</h3>
	<hr style="height: 2px; color: #f00; background-color: #f00">

	<div class="container">
		<div class="row col-md-6 col-md-offset-2 custyle">
			<table id="authorTable" class="table table-striped custab">
				<thead>
					<tr>
						<th>Author Name</th>
						<th class="text-center">Action</th>
					</tr>
				</thead>
				<c:forEach var="author" items="${Authors}">					
				<tr id="${author.id}">
					<td contenteditable='false'>${author.name}</td>
					<td contenteditable='false' class="text-center">
						<a href="#" id="sendAuthor" class="btn btn-success btn-xs" >
					<span class="glyphicon glyphicon-check"></span> See News</a>
					
					</td>
				</tr>
				</c:forEach>
				
			</table>
		</div>
	</div>

</body>
</html>