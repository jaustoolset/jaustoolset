<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<html>
			<head>
				<title>
					<xsl:value-of select="Index/Name"/>
				</title>
				<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
				<link rel="stylesheet" href="framedStyleSheet.css"/>
				
				<script type="text/javascript">
					function loadSubMenuFrames(name)
					{
						parent.frames[1].location=name+'-subMenuA.html';
						parent.frames[2].location=name+'-subMenuB.html';
					}
				</script>
			</head>
			
			<body>
				<h2>
					All Service Sets
				</h2>
				<table border="1" width="90%" cellpadding="1" cellspacing="1" summary="">
					<tr bgcolor="#cccccc">
						<th>
							Service Set
						</th>
						<th>
							Description
						</th>
					</tr>
					<xsl:apply-templates select="Index/Item"/>
				</table>
			</body>
		</html>
	</xsl:template>
	
	<xsl:template match="Item">
		<tr>
			<td align="center">
				<div>
					<b>
						<a href="{Url}" onClick="loadSubMenuFrames('{Name}')">		<!-- create hyperlink to service set file -->
							<xsl:value-of select="Name"/>
						</a>
					</b>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:value-of select="Description"/>
				</div>
			</td>	
		</tr>
	</xsl:template>
	
</xsl:stylesheet>