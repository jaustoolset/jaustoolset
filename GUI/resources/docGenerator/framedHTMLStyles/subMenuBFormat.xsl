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
	      	</head>
	    	
	    	<body>
	    		<table border="0" width="100%" summary="">
		      		<tr>
		            	<th align="left">
		              		<font size="+1" class="FrameTitleFont">
		                		<b>
		                  			All Message Definitions
		                		</b>
		              		</font>
		            	</th>
		      		</tr>
				</table>
				<table border="0" width="100%" summary="">
					<tr>
	          			<td>
	            			<div>
	              				<font class="FrameItemFont">
	              					<xsl:if test="Index/Description != ''">
										<br/>
										<b>
											<xsl:value-of select="Index/Description"/> Message Definitions:
										</b>
										<br/>
									</xsl:if>
									<xsl:choose>
										<xsl:when test="Index/Item">
		              						<xsl:apply-templates select="Index/Item"/>
		              					</xsl:when>
		              					<xsl:otherwise>
		              						<i>
		              							None
		              						</i>
		              					</xsl:otherwise>
		              				</xsl:choose>
	              				</font>
	            			</div>
	          			</td>
	       			 </tr>
	      		</table>
	    	</body> 	
		</html>		    	
	</xsl:template>	
	
	<xsl:template match="Item">
		<a href="{Url}" title="{Description}" target="{Target}">
			<xsl:value-of select="Name"/>
		</a>
		<br/>    
	</xsl:template>
	
</xsl:stylesheet>	