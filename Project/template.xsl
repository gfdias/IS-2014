<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" doctype-system="about:legacy-compat" />

	<xsl:template match="/">
		<html>
			<head>
				<title> ISNEWS </title>
			</head>
			<body>
				<center>
					<h1 style="font-size:400%">
						<xsl:value-of select="//topicname" />
						News
					</h1>
				</center>
				<hr>
				<div class="container" align="center" style="margin-top:50px">
					<xsl:for-each select="//news">
						<div  class="row" align="center" style="width:50%;">

						<center><h2><xsl:value-of select="title"></xsl:value-of></h2></center>
						<center><xsl:value-of select="content"></xsl:value-of></center>

						<br></br>
							
						</div>
					</xsl:for-each>
				</div>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>