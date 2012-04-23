<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<html>
			<head>
				<title>
					<xsl:value-of select="service_set/@name"/>
				</title>
				<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
				<link rel="stylesheet" href="framedStyleSheet.css"/>
				
				<script type="text/javascript">
					function loadSubMenuFrame(name)
					{
						parent.frames[2].location=name;
					}
				</script>
			</head>
			
			<body>
				<h3>
					<xsl:value-of select="service_set/@name"/>
				</h3>
				
				<table border="1" width="90%" cellpadding="1" cellspacing="1" summary="">
					<tr bgcolor="#cccccc">
						<th>
							Service Definition
						</th>
						<th>
							Description
						</th>
					</tr>
					<xsl:apply-templates select="service_set/service_def"/>
				</table>
			</body>
		</html>
	</xsl:template>	
	
	<xsl:template match="service_def">
		<xsl:variable name="serviceDefName">
			<xsl:value-of select="@name"/>
		</xsl:variable>
	
		<tr>
			<td align="left">
				<div>
					<b>
						<a href="{$serviceDefName}/{$serviceDefName}.html" onClick="loadSubMenuFrame('{$serviceDefName}/subMenuB.html')">		
							<xsl:value-of select="@name"/>
						</a>
					<!--
						<a href="{$serviceDefName}/{$serviceDefName}.html">
							<xsl:value-of select="$serviceDefName"/>
						</a>
					-->
					</b>
				</div>
			</td>
			<td align="left">
				<div>
					<xsl:value-of select="description"/>
				</div>
			</td>	
		</tr>
	</xsl:template>
	
</xsl:stylesheet>