<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8;" >
	
      <link href="css/bootstrap.min.css" rel="stylesheet"/>
      <script src="js/jquery-latest.js"></script>
      <script src="js/bootstrap.min.js"></script>
	
	<title>IS NEWS - Register</title>
</head>
<body style="background-color:#006FA4;" >

<div style="margin:0 auto; width:700pt; height:150pt;  margin-top:60pt; text-align:center;">
	<p style="font-family:helvetica; margin-top:60pt; font-size:35pt;  color:#C0C0C0;">
		Welcome to
	</p>
	<p style="font-family:helvetica; margin-top:20pt; font-size:60pt;  color:#FFFFFF;">
		IS NEWS
	</p>
</div>

<div style="margin:0 auto; width:300pt; height:300pt; text-align:center;">
	<p style="font-family:helvetica; margin-top:40pt;font-size:30pt;  color:#FFFFFF;">
		Register
	</p>
	<form action="Register" method="post">
	<input type="text" name="username" value="" placeholder="Username" style="display:inline block; height:30pt; padding:4px 20px;">
	<br>
	<input type="text" name="email" value="" placeholder="Email" style="display:inline block; height:30pt; padding:4px 20px;">
	<br>
	<input type="password" name="password" value="" placeholder="Password" style="display:inline block; height:30pt; padding:4px 20px;">
	<br>
	<input type="password" name="password2" value="" placeholder="Confirm Password" style="display:inline block; height:30pt; padding:4px 20px;">
	<br>
	<br>
	<button class="btn btn-lg btn-success" type="submit" onclick="Submit" >Register</button>
	<br>
	<br>
	<a  href="Start" style="text-decoration:none; color:#FFFFFF;font-size:15px;">Back</a>
	
	
	</form>
</div>

	<div style=" margin:0 auto; margin-top:5pt; height:22px; width:343px; left:338px; top:224px; text-align:center; font-family:helvetica; font-size:16pt; color:#FFFFFF; " >
		
		<%=request.getAttribute("returnMessage") %>
		
	</div>
</body>
</html>