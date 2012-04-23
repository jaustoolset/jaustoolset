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
	      			function loadOverviewFrames()
	      			{
	      				parent.frames[1].location='subMenuA.html';
	      				parent.frames[2].location='subMenuB.html';
	      			}
	      		
					function loadAllServiceSetsFrames()
					{
						parent.frames[1].location='subMenuA.html';
						parent.frames[2].location='subMenuB.html';
					}
				
					function loadAllServiceDefinitionsFrames()
					{
						parent.frames[1].location='subMenuA.html';
						parent.frames[2].location='subMenuB.html';
					}
				
					function loadAllMessageDefinitionsFrames()
					{
						parent.frames[2].location='subMenuB.html';
					}
				
					function loadServiceSetFrames(name)
					{
						parent.frames[1].location=name+'-subMenuA.html';
						parent.frames[2].location=name+'-subMenuB.html';
					}
				</script>
	      
	      </head>
	    	
	    	<body>
	    		<table border="0" width="100%" summary="">
		      		<tr>
		            	<th align="left">
		              		<font size="+2" class="FrameTitleFont">
		                		<b>
		                  			JSIDLv1.0 Service Definitions
		                		</b>
		              			<br/>
		              		</font>
		            	</th>
		      		</tr>
				</table>
				<table border="0" width="100%" summary="">
	        		<tr>
	          			<td>
	            			<div>
	              				<font class="FrameItemFont">
	                				<xsl:apply-templates select="Index/Item"/>
	              				</font>
	            			</div>
	          			</td>
	       			 </tr>
				</table>
	    		<img src="images/JTSman_small.png" alt="JTS Logo"/>
	    	</body> 	
		</html>		    	
	</xsl:template>	
	
	<xsl:template match="Item">
		<xsl:choose>
			<xsl:when test="Name = 'Overview'">
				<a href="{Url}" target="{Target}" onClick="loadOverviewFrames()">
					<xsl:value-of select="Name"/>
				</a>
			</xsl:when>
			<xsl:when test="Name = 'All Service Sets'">
				<a href="{Url}" target="{Target}" onClick="loadAllServiceSetsFrames()">
					<xsl:value-of select="Name"/>
				</a>
			</xsl:when>
			<xsl:when test="Name = 'All Service Definitions'">
				<a href="{Url}" target="{Target}" onClick="loadAllServiceDefinitionsFrames()">
					<xsl:value-of select="Name"/>
				</a>
			</xsl:when>
			<xsl:when test="Name = 'All Message Definitions'">
				<a href="{Url}" target="{Target}" onClick="loadAllMessageDefinitionsFrames()">
					<xsl:value-of select="Name"/>
				</a>
			</xsl:when>
			<xsl:otherwise>
				<a href="{Url}" target="{Target}" onClick="loadServiceSetFrames('{Name}')">
					<xsl:value-of select="Name"/>
				</a>
			</xsl:otherwise>
		</xsl:choose>
	
		<br/>    
		<xsl:if test="Name='All Message Definitions'">
			<xsl:if test="/Index//Name='Main Menu'">
				<br/>
				<b>
					Service Sets
				</b>
				<br/>
			</xsl:if>
		</xsl:if>
	</xsl:template>
	
</xsl:stylesheet>	