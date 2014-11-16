<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" doctype-system="about:legacy-compat" />
	<xsl:template match="/">
		<html>
			<head>
				<title>ISNEWS</title>
			</head>
			<body>
				<center>
					<h1 style="font-size:400%">
						<xsl:value-of select="//topicname" />
						News
					</h1>
				</center>
				<hr></hr>
				<div class="container" align="center" style="margin-top:50px">
					<xsl:for-each select="//news">
						<div class="row" align="center" style="margin-top:30px;width:50%;">
							<center>
								<h2>
									<xsl:value-of select="title"></xsl:value-of>
								</h2>
							</center>
							<br></br>
							<strong>
								<span style="font-size:20; color:#388E8E">Highlights</span>
							</strong>
							<xsl:for-each select="highlights">
								<ul>
									<li>
										<xsl:value-of select="current()"></xsl:value-of>
									</li>
								</ul>
							</xsl:for-each>
							<hr></hr>
							<p>
								<xsl:value-of select="author"></xsl:value-of>
							</p>
							<p>
								<xsl:value-of select="date"></xsl:value-of>
							</p>
							<hr></hr>
							<center>
								<p>
									<xsl:value-of select="content"></xsl:value-of>
								</p>
							</center>
							<xsl:for-each select="photos">
								<xsl:if test="not(position() > 10)">

									<img
										style="height:auto; width:auto; max-width:500px; max-height:500px;">
										<xsl:attribute name="src">
        								<xsl:value-of select="current()" />
    								</xsl:attribute>
									</img>
								</xsl:if>
							</xsl:for-each>

							<br></br>
						</div>
						<hr></hr>
					</xsl:for-each>
				</div>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>