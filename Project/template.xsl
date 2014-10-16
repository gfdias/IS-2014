<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" doctype-system="about:legacy-compat" />

	<xsl:template match="/">
		<xsl:text disable-output-escaping="yes">&lt;!DOCTYPE html&gt;</xsl:text>
		<html>
			<head>
				<h1>ISNN NEWS</h1>
			</head>
			<body>
				<xsl:apply-templates />
			</body>
		</html>
	</xsl:template>

	<xsl:template match="news">
		<p>
			<xsl:apply-templates select="title" />
			<xsl:apply-templates select="author" />
			<xsl:apply-templates select="date" />
			<xsl:apply-templates select="highlights" />
			<xsl:apply-templates select="content" />
			<xsl:apply-templates select="photos" />
		</p>
	</xsl:template>

	<xsl:template match="title">
		Title:
		<span style="color:#ff0000">
			<xsl:value-of select="." />
		</span>
		<br />
	</xsl:template>

	<xsl:template match="content">
		Content:
		<span style="color:#00ff00">
			<xsl:value-of select="." />
		</span>
		<br />
	</xsl:template>

</xsl:stylesheet>