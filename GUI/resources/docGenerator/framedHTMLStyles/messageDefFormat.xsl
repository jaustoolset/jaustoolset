<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<html>
			<head>
				<title>
					<xsl:value-of select="message_def/@name"/>
				</title>
				<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
				<link rel="stylesheet" href="framedStyleSheet.css"/>
			</head>
			
			<body>
				<!-- TITLE -->
				<!-- ===== -->
				<h1> 
					<xsl:value-of select="message_def/@name"/>
				</h1>
				<h3>
					ID: <xsl:value-of select="message_def/@message_id"/>
				</h3>
				
				<!-- DESCRIPTIONS -->
				<!-- ============ -->
				<h2>
					<b>
						<u>
							Description:
						</u>
					</b>
				</h2>
				<table border="0" width="100%" cellpadding="0" cellspacing="0" summary="">
					<xsl:value-of select="message_def/description"/>
				</table>
				<br/>
				
				<!-- MESSAGE CONTENTS -->
				<!-- ================ -->
				<h2>
					<b>
						<u>
							Message Header:
						</u>
					</b>
				</h2>
				<xsl:choose>
					<xsl:when test="message_def/header">
						<xsl:apply-templates select="message_def/header"/>
					</xsl:when>
					<xsl:when test="message_def/declared_header">
						<xsl:apply-templates select="message_def/declared_header"/>
					</xsl:when>
				</xsl:choose>
				
				<h2>
					<b>
						<u>
							Message Body:
						</u>
					</b>
				</h2>
				<xsl:choose>
					<xsl:when test="message_def/body">
						<xsl:apply-templates select="message_def/body"/>
					</xsl:when>
					<xsl:when test="message_def/declared_body">
						<xsl:apply-templates select="message_def/declared_body"/>
					</xsl:when>
				</xsl:choose>
				
				
				<h2>
					<b>
						<u>
							Message Footer:
						</u>
					</b>
				</h2>
				<xsl:choose>
					<xsl:when test="message_def/footer">
						<xsl:apply-templates select="message_def/footer"/>
					</xsl:when>
					<xsl:when test="message_def/declared_footer">
						<xsl:apply-templates select="message_def/declared_footer"/>
					</xsl:when>
				</xsl:choose>
				
				
			</body>
		</html>	
	</xsl:template>
	
	<xsl:template match="header">
		<!-- display tiered header structure -->
		<ul>
			<xsl:choose>
				<xsl:when test="record">
					<xsl:call-template name="recordName">
						<xsl:with-param name="record" select="record"/>
					</xsl:call-template>
				</xsl:when>
				<xsl:when test="list">
					<xsl:call-template name="listName">
						<xsl:with-param name="list" select="list"/>
					</xsl:call-template>
				</xsl:when>
				<xsl:when test="variant">
					<xsl:call-template name="variantName">
						<xsl:with-param name="variant" select="variant"/>
					</xsl:call-template>
				</xsl:when>
				<xsl:when test="sequence">
					<xsl:call-template name="sequenceName">
						<xsl:with-param name="sequence" select="sequence"/>
					</xsl:call-template>
				</xsl:when>
				<xsl:when test="declared_record">
					<xsl:call-template name="declaredRecordName">
						<xsl:with-param name="declaredRecord" select="declared_record"/>
					</xsl:call-template>
				</xsl:when>
				<xsl:when test="declared_list">
					<xsl:call-template name="declaredListName">
						<xsl:with-param name="declaredList" select="declared_list"/>
					</xsl:call-template>
				</xsl:when>
				<xsl:when test="declared_variant">
					<xsl:call-template name="declaredVariantName">
						<xsl:with-param name="declaredVariant" select="declared_variant"/>
					</xsl:call-template>
				</xsl:when>
				<xsl:when test="declared_sequence">
					<xsl:call-template name="declaredSequenceName">
						<xsl:with-param name="declaredSequence" select="declared_sequence"/>
					</xsl:call-template>
				</xsl:when>
				<xsl:otherwise>
					<i>
						Empty
					</i>
				</xsl:otherwise>
			</xsl:choose>
		</ul>
		
		<!-- display record tables -->
		<xsl:choose>
			<xsl:when test="record">
				<xsl:apply-templates select="record"/>
			</xsl:when>
			<xsl:when test="list">
				<xsl:apply-templates select="list"/>
			</xsl:when>
			<xsl:when test="variant">
				<xsl:apply-templates select="variant"/>
			</xsl:when>
			<xsl:when test="sequence">
				<xsl:apply-templates select="sequence"/>
			</xsl:when>
		</xsl:choose>
	</xsl:template>
	
	<xsl:template match="declared_header">
		<ul>
			<xsl:choose>
				<xsl:when test="@name">
					<image src="images/branch.gif" width="15" height="14"/> declared header name = <xsl:value-of select="@name"/>
					<xsl:if test="@interpretation">
						(<xsl:value-of select="@interpretation"/>)
					</xsl:if>
				</xsl:when>
				<xsl:otherwise>
					<i>
						Empty
					</i>
				</xsl:otherwise>
			</xsl:choose>
		</ul>
	</xsl:template>
	
	<xsl:template match="body">
		<!-- display tiered body structure -->
		<ul>
			<xsl:choose>
				<xsl:when test="record">
					<xsl:call-template name="recordName">
						<xsl:with-param name="record" select="record"/>
					</xsl:call-template>
				</xsl:when>
				<xsl:when test="list">
					<xsl:call-template name="listName">
						<xsl:with-param name="list" select="list"/>
					</xsl:call-template>
				</xsl:when>
				<xsl:when test="variant">
					<xsl:call-template name="variantName">
						<xsl:with-param name="variant" select="variant"/>
					</xsl:call-template>
				</xsl:when>
				<xsl:when test="sequence">
					<xsl:call-template name="sequenceName">
						<xsl:with-param name="sequence" select="sequence"/>
					</xsl:call-template>
				</xsl:when>
				<xsl:when test="declared_record">
					<xsl:call-template name="declaredRecordName">
						<xsl:with-param name="declaredRecord" select="declared_record"/>
					</xsl:call-template>
				</xsl:when>
				<xsl:when test="declared_list">
					<xsl:call-template name="declaredListName">
						<xsl:with-param name="declaredList" select="declared_list"/>
					</xsl:call-template>
				</xsl:when>
				<xsl:when test="declared_variant">
					<xsl:call-template name="declaredVariantName">
						<xsl:with-param name="declaredVariant" select="declared_variant"/>
					</xsl:call-template>
				</xsl:when>
				<xsl:when test="declared_sequence">
					<xsl:call-template name="declaredSequenceName">
						<xsl:with-param name="declaredSequence" select="declared_sequence"/>
					</xsl:call-template>
				</xsl:when>
				<xsl:otherwise>
					<i>
						Empty
					</i>
				</xsl:otherwise>
			</xsl:choose>
		</ul>
		
		<!-- display record tables -->
		<xsl:choose>
			<xsl:when test="record">
				<xsl:apply-templates select="record"/>
			</xsl:when>
			<xsl:when test="list">
				<xsl:apply-templates select="list"/>
			</xsl:when>
			<xsl:when test="variant">
				<xsl:apply-templates select="variant"/>
			</xsl:when>
			<xsl:when test="sequence">
				<xsl:apply-templates select="sequence"/>
			</xsl:when>
		</xsl:choose>
	</xsl:template>
	
	<xsl:template match="declared_body">
		<ul>
			<xsl:choose>
				<xsl:when test="@name">
					<image src="images/branch.gif" width="15" height="14"/> declared body name = <xsl:value-of select="@name"/>
					<xsl:if test="@interpretation">
						(<xsl:value-of select="@interpretation"/>)
					</xsl:if>
				</xsl:when>
				<xsl:otherwise>
					<i>
						Empty
					</i>
				</xsl:otherwise>
			</xsl:choose>
		</ul>
	</xsl:template>
	
	<xsl:template match="footer">
		<!-- display tiered footer structure -->
		<ul>
			<xsl:choose>
				<xsl:when test="record">
					<xsl:call-template name="recordName">
						<xsl:with-param name="record" select="record"/>
					</xsl:call-template>
				</xsl:when>
				<xsl:when test="list">
					<xsl:call-template name="listName">
						<xsl:with-param name="list" select="list"/>
					</xsl:call-template>
				</xsl:when>
				<xsl:when test="variant">
					<xsl:call-template name="variantName">
						<xsl:with-param name="variant" select="variant"/>
					</xsl:call-template>
				</xsl:when>
				<xsl:when test="sequence">
					<xsl:call-template name="sequenceName">
						<xsl:with-param name="sequence" select="sequence"/>
					</xsl:call-template>
				</xsl:when>
				<xsl:when test="declared_record">
					<xsl:call-template name="declaredRecordName">
						<xsl:with-param name="declaredRecord" select="declared_record"/>
					</xsl:call-template>
				</xsl:when>
				<xsl:when test="declared_list">
					<xsl:call-template name="declaredListName">
						<xsl:with-param name="declaredList" select="declared_list"/>
					</xsl:call-template>
				</xsl:when>
				<xsl:when test="declared_variant">
					<xsl:call-template name="declaredVariantName">
						<xsl:with-param name="declaredVariant" select="declared_variant"/>
					</xsl:call-template>
				</xsl:when>
				<xsl:when test="declared_sequence">
					<xsl:call-template name="declaredSequenceName">
						<xsl:with-param name="declaredSequence" select="declared_sequence"/>
					</xsl:call-template>
				</xsl:when>
				<xsl:otherwise>
					<i>
						Empty
					</i>
				</xsl:otherwise>
			</xsl:choose>
		</ul>
		
		<!-- display record tables -->
		<xsl:choose>
			<xsl:when test="record">
				<xsl:apply-templates select="record"/>
			</xsl:when>
			<xsl:when test="list">
				<xsl:apply-templates select="list"/>
			</xsl:when>
			<xsl:when test="variant">
				<xsl:apply-templates select="variant"/>
			</xsl:when>
			<xsl:when test="sequence">
				<xsl:apply-templates select="sequence"/>
			</xsl:when>
		</xsl:choose>
	</xsl:template>
	
	<xsl:template match="declared_footer">
		<ul>
			<xsl:choose>
				<xsl:when test="@name">
					<image src="images/branch.gif" width="15" height="14"/> declared footer name = <xsl:value-of select="@name"/>
					<xsl:if test="@interpretation">
						(<xsl:value-of select="@interpretation"/>)
					</xsl:if>
				</xsl:when>
				<xsl:otherwise>
					<i>
						Empty
					</i>
				</xsl:otherwise>
			</xsl:choose>
		</ul>
	</xsl:template>
	
	<xsl:template match="record">
		<b>		
			<xsl:value-of select="@name"/>
		</b>
		<table border="1" width="90%" cellpadding="1" cellspacing="1" summary="">
			<tr>
				<th align="center" bgcolor="#cccccc">
					<div>
						Name
					</div>
				</th>
				<th align="center" bgcolor="#cccccc">
					<div>
						Type
					</div>
				</th>
				<th align="center" bgcolor="#cccccc">
					<div>
						Units
					</div>
				</th>
				<th align="center" bgcolor="#cccccc">
					<div>
						Optional
					</div>
				</th>
				<th align="center" bgcolor="#cccccc">
					<div>
						Interpretation
					</div>
				</th>
			</tr>
			
			<xsl:apply-templates/>
		</table>
		<br/>
		
	</xsl:template>
	
	<xsl:template match="list">
		<xsl:apply-templates/>
	</xsl:template>
	
	<xsl:template match="variant">
		<xsl:apply-templates/>
	</xsl:template>
	
	<xsl:template match="sequence">
		<xsl:apply-templates/>
	</xsl:template>
	
	<xsl:template match="declared_record">
		<b>
			<xsl:value-of select="@name"/>
		</b>
		<table border="1" width="90%" cellpadding="1" cellspacing="1" summary="">
			<tr>
				<th align="center" bgcolor="#cccccc">
					<div>
						Name
					</div>
				</th>
				<th align="center" bgcolor="#cccccc">
					<div>
						Interpretation
					</div>
				</th>
			</tr>
			<tr>
				<td align="left">
					<xsl:value-of select="@name"/>
				</td>
				<td align="left">
					<xsl:value-of select="@interpretation"/>
				</td>
			</tr>
		</table>
		<br/>
	</xsl:template>
	
	<xsl:template match="declared_list">
		<ul>
			<image src="images/branch.gif" width="15" height="14"/> declared list name = <xsl:value-of select="@name"/>
			<br/>
		</ul>
	</xsl:template>
	
	<xsl:template match="declared_variant">
		<ul>
			<image src="images/branch.gif" width="15" height="14"/> declared variant name = <xsl:value-of select="@name"/>
			<br/>
		</ul>
	</xsl:template>
	
	<xsl:template match="declared_sequence">
		<ul>
			<image src="images/branch.gif" width="15" height="14"/> declared sequence name = <xsl:value-of select="@name"/>
			<br/>
		</ul>
	</xsl:template>
	
	<xsl:template name="recordName">
		<xsl:param name="record"/>
		
		<ul>
			<image src="images/branch.gif" width="15" height="14"/> record name = <xsl:value-of select="$record/@name"/>
			<br/>
		
			<xsl:if test="$record/record">
				<xsl:for-each select="$record/record">
					<xsl:call-template name="recordName">
						<xsl:with-param name="record" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$record/list">
				<xsl:for-each select="$record/list">
					<xsl:call-template name="listName">
						<xsl:with-param name="list" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$record/variant">
				<xsl:for-each select="$record/variant">
					<xsl:call-template name="variantName">
						<xsl:with-param name="variant" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$record/sequence">
				<xsl:for-each select="$record/sequence">
					<xsl:call-template name="sequenceName">
						<xsl:with-param name="sequence" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$record/declared_list">
				<xsl:for-each select="$record/declared_list">
					<xsl:call-template name="declaredListName">
						<xsl:with-param name="declaredList" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$record/declared_variant">
				<xsl:for-each select="$record/declared_variant">
					<xsl:call-template name="declaredVariantName">
						<xsl:with-param name="declaredVariant" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$record/declared_sequence">
				<xsl:for-each select="$record/declared_sequence">
					<xsl:call-template name="declaredSequenceName">
						<xsl:with-param name="declaredSequence" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
		</ul>
	</xsl:template>
	
	<xsl:template name="listName">
		<xsl:param name="list"/>
		
		<ul>
			<image src="images/branch.gif" width="15" height="14"/> list name = <xsl:value-of select="$list/@name"/><br/>(count_field = <xsl:value-of select="$list/count_field/@field_type_unsigned"/>)
			<br/>
		
			<xsl:if test="$list/record">
				<xsl:for-each select="$list/record">
					<xsl:call-template name="recordName">
						<xsl:with-param name="record" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$list/list">
				<xsl:for-each select="$list/list">
					<xsl:call-template name="listName">
						<xsl:with-param name="list" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$list/variant">
				<xsl:for-each select="$list/variant">
					<xsl:call-template name="variantName">
						<xsl:with-param name="variant" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$list/sequence">
				<xsl:for-each select="$list/sequence">
					<xsl:call-template name="sequenceName">
						<xsl:with-param name="sequence" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$list/declared_list">
				<xsl:for-each select="$list/declared_list">
					<xsl:call-template name="declaredListName">
						<xsl:with-param name="declaredList" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$list/declared_variant">
				<xsl:for-each select="$list/declared_variant">
					<xsl:call-template name="declaredVariantName">
						<xsl:with-param name="declaredVariant" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$list/declared_sequence">
				<xsl:for-each select="$list/declared_sequence">
					<xsl:call-template name="declaredSequenceName">
						<xsl:with-param name="declaredSequence" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
		</ul>
	</xsl:template>
	
	<xsl:template name="variantName">
		<xsl:param name="variant"/>
		
		<ul>
			<image src="images/branch.gif" width="15" height="14"/> variant name = <xsl:value-of select="$variant/@name"/>
			<br/>
		
			<xsl:if test="$variant/record">
				<xsl:for-each select="$variant/record">
					<xsl:call-template name="recordName">
						<xsl:with-param name="record" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$variant/list">
				<xsl:for-each select="$variant/list">
					<xsl:call-template name="listName">
						<xsl:with-param name="list" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$variant/variant">
				<xsl:for-each select="$variant/variant">
					<xsl:call-template name="variantName">
						<xsl:with-param name="variant" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$variant/sequence">
				<xsl:for-each select="$variant/sequence">
					<xsl:call-template name="sequenceName">
						<xsl:with-param name="sequence" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$variant/declared_list">
				<xsl:for-each select="$variant/declared_list">
					<xsl:call-template name="declaredListName">
						<xsl:with-param name="declaredList" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$variant/declared_variant">
				<xsl:for-each select="$variant/declared_variant">
					<xsl:call-template name="declaredVariantName">
						<xsl:with-param name="declaredVariant" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$variant/declared_sequence">
				<xsl:for-each select="$variant/declared_sequence">
					<xsl:call-template name="declaredSequenceName">
						<xsl:with-param name="declaredSequence" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
		</ul>
	</xsl:template>
	
	<xsl:template name="sequenceName">
		<xsl:param name="sequence"/>
		
		<ul>
			<image src="images/branch.gif" width="15" height="14"/> sequence name = <xsl:value-of select="$sequence/@name"/>
			<br/>
		
			<xsl:if test="$sequence/record">
				<xsl:for-each select="$sequence/record">
					<xsl:call-template name="recordName">
						<xsl:with-param name="record" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$sequence/list">
				<xsl:for-each select="$sequence/list">
					<xsl:call-template name="listName">
						<xsl:with-param name="list" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$sequence/variant">
				<xsl:for-each select="$sequence/variant">
					<xsl:call-template name="variantName">
						<xsl:with-param name="variant" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$sequence/sequence">
				<xsl:for-each select="$sequence/sequence">
					<xsl:call-template name="sequenceName">
						<xsl:with-param name="sequence" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$sequence/declared_list">
				<xsl:for-each select="$sequence/declared_list">
					<xsl:call-template name="declaredListName">
						<xsl:with-param name="declaredList" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$sequence/declared_variant">
				<xsl:for-each select="$sequence/declared_variant">
					<xsl:call-template name="declaredVariantName">
						<xsl:with-param name="declaredVariant" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$sequence/declared_sequence">
				<xsl:for-each select="$sequence/declared_sequence">
					<xsl:call-template name="declaredSequenceName">
						<xsl:with-param name="declaredSequence" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
		</ul>
	</xsl:template>
	
	<xsl:template name="declaredRecordName">
		<xsl:param name="declaredRecord"/>
		
		<ul>
			<image src="images/branch.gif" width="15" height="14"/> declared record name = <xsl:value-of select="$declaredRecord/@name"/>
			<br/>
		
			<xsl:if test="$declaredRecord/record">
				<xsl:for-each select="$declaredRecord/record">
					<xsl:call-template name="recordName">
						<xsl:with-param name="record" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$declaredRecord/list">
				<xsl:for-each select="$declaredRecord/list">
					<xsl:call-template name="listName">
						<xsl:with-param name="list" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$declaredRecord/variant">
				<xsl:for-each select="$declaredRecord/variant">
					<xsl:call-template name="variantName">
						<xsl:with-param name="variant" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$declaredRecord/sequence">
				<xsl:for-each select="$declaredRecord/sequence">
					<xsl:call-template name="sequenceName">
						<xsl:with-param name="sequence" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$declaredRecord/declared_list">
				<xsl:for-each select="$declaredRecord/declared_list">
					<xsl:call-template name="declaredListName">
						<xsl:with-param name="declaredList" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$declaredRecord/declared_variant">
				<xsl:for-each select="$declaredRecord/declared_variant">
					<xsl:call-template name="declaredVariantName">
						<xsl:with-param name="declaredVariant" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$declaredRecord/declared_sequence">
				<xsl:for-each select="$declaredRecord/declared_sequence">
					<xsl:call-template name="declaredSequenceName">
						<xsl:with-param name="declaredSequence" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
		</ul>
	</xsl:template>
	
	<xsl:template name="declaredListName">
		<xsl:param name="declaredList"/>
		
		<ul>
			<image src="images/branch.gif" width="15" height="14"/> declared list name = <xsl:value-of select="$declaredList/@name"/>
			<br/>
		
			<xsl:if test="$declaredList/record">
				<xsl:for-each select="$declaredList/record">
					<xsl:call-template name="recordName">
						<xsl:with-param name="record" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$declaredList/list">
				<xsl:for-each select="$declaredList/list">
					<xsl:call-template name="listName">
						<xsl:with-param name="list" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$declaredList/variant">
				<xsl:for-each select="$declaredList/variant">
					<xsl:call-template name="variantName">
						<xsl:with-param name="variant" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$declaredList/sequence">
				<xsl:for-each select="$declaredList/sequence">
					<xsl:call-template name="sequenceName">
						<xsl:with-param name="sequence" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$declaredList/declared_list">
				<xsl:for-each select="$declaredList/declared_list">
					<xsl:call-template name="declaredListName">
						<xsl:with-param name="declaredList" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$declaredList/declared_variant">
				<xsl:for-each select="$declaredList/declared_variant">
					<xsl:call-template name="declaredVariantName">
						<xsl:with-param name="declaredVariant" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$declaredList/declared_sequence">
				<xsl:for-each select="$declaredList/declared_sequence">
					<xsl:call-template name="declaredSequenceName">
						<xsl:with-param name="declaredSequence" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
		</ul>
	</xsl:template>
	
	<xsl:template name="declaredVariantName">
		<xsl:param name="declaredVariant"/>
		
		<ul>
			<image src="images/branch.gif" width="15" height="14"/> declared variant name = <xsl:value-of select="$declaredVariant/@name"/>
			<br/>
		
			<xsl:if test="$declaredVariant/record">
				<xsl:for-each select="$declaredVariant/record">
					<xsl:call-template name="recordName">
						<xsl:with-param name="record" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$declaredVariant/list">
				<xsl:for-each select="$declaredVariant/list">
					<xsl:call-template name="listName">
						<xsl:with-param name="list" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$declaredVariant/variant">
				<xsl:for-each select="$declaredVariant/variant">
					<xsl:call-template name="variantName">
						<xsl:with-param name="variant" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$declaredVariant/sequence">
				<xsl:for-each select="$declaredVariant/sequence">
					<xsl:call-template name="sequenceName">
						<xsl:with-param name="sequence" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$declaredVariant/declared_list">
				<xsl:for-each select="$declaredVariant/declared_list">
					<xsl:call-template name="declaredListName">
						<xsl:with-param name="declaredList" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$declaredVariant/declared_variant">
				<xsl:for-each select="$declaredVariant/declared_variant">
					<xsl:call-template name="declaredVariantName">
						<xsl:with-param name="declaredVariant" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$declaredVariant/declared_sequence">
				<xsl:for-each select="$declaredVariant/declared_sequence">
					<xsl:call-template name="declaredSequenceName">
						<xsl:with-param name="declaredSequence" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
		</ul>
	</xsl:template>
	
	<xsl:template name="declaredSequenceName">
		<xsl:param name="declaredSequence"/>
		
		<ul>
			<image src="images/branch.gif" width="15" height="14"/> declared sequence name = <xsl:value-of select="$declaredSequence/@name"/>
			<br/>
		
			<xsl:if test="$declaredSequence/record">
				<xsl:for-each select="$declaredSequence/record">
					<xsl:call-template name="recordName">
						<xsl:with-param name="record" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$declaredSequence/list">
				<xsl:for-each select="$declaredSequence/list">
					<xsl:call-template name="listName">
						<xsl:with-param name="list" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$declaredSequence/variant">
				<xsl:for-each select="$declaredSequence/variant">
					<xsl:call-template name="variantName">
						<xsl:with-param name="variant" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$declaredSequence/sequence">
				<xsl:for-each select="$declaredSequence/sequence">
					<xsl:call-template name="sequenceName">
						<xsl:with-param name="sequence" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$declaredSequence/declared_list">
				<xsl:for-each select="$declaredSequence/declared_list">
					<xsl:call-template name="declaredListName">
						<xsl:with-param name="declaredList" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$declaredSequence/declared_variant">
				<xsl:for-each select="$declaredSequence/declared_variant">
					<xsl:call-template name="declaredVariantName">
						<xsl:with-param name="declaredVariant" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
			<xsl:if test="$declaredSequence/declared_sequence">
				<xsl:for-each select="$declaredSequence/declared_sequence">
					<xsl:call-template name="declaredSequenceName">
						<xsl:with-param name="declaredSequence" select="."/>
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
		</ul>
	</xsl:template>
	
	<xsl:template match="presence_vector">
		<tr>
			<td align="center">
				<div>
					<xsl:text> (presence_vector) </xsl:text>
				</div>
			</td>
			<td align="center">
				<div>	
					<xsl:value-of select="@field_type_unsigned"/>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:text> &#160; </xsl:text>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:text> &#160; </xsl:text>
				</div>
			</td>
			<td align="left">
				<div>
					<xsl:text> &#160; </xsl:text>
				</div>
			</td>
		</tr>
	</xsl:template>
	
	<xsl:template match="array">
		<image src="images/branch.gif" width="15" height="14"/> array name = <xsl:value-of select="@name"/>
		<br/>
	</xsl:template>
	
	<xsl:template match="declared_array">
		<image src="images/branch.gif" width="15" height="14"/> declared array name = <xsl:value-of select="@name"/>
		<br/>
	</xsl:template>
	
	<xsl:template match="fixed_field">
		<tr>
			<td align="center">
				<div>
					<xsl:value-of select="@name"/>
					<br/>
					(fixed_field)
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:value-of select="@field_type"/>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:value-of select="@field_units"/>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:value-of select="@optional"/>
				</div>
			</td>
			<td align="left">
				<div>
					<xsl:choose>
						<xsl:when test="@interpretation">
							<xsl:value-of select="@interpretation"/>
							<br/>
						</xsl:when>
						<xsl:otherwise>
							<xsl:text> &#160; </xsl:text>
						</xsl:otherwise>
					</xsl:choose>
					<xsl:apply-templates/>
				</div>
			</td>
		</tr>
	</xsl:template>
	
	<xsl:template match="declared_fixed_field">
		<tr>
			<td align="center">
				<div>
					<xsl:value-of select="@name"/>
					<br/>
					(declared_fixed_field)
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:text> &#160; </xsl:text>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:text> &#160; </xsl:text>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:value-of select="@optional"/>
				</div>
			</td>
			<td align="left">
				<div>
					<xsl:choose>
						<xsl:when test="@interpretation">
							<xsl:value-of select="@interpretation"/>
							<br/>
						</xsl:when>
						<xsl:otherwise>
							<xsl:text> &#160; </xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</div>
			</td>
		</tr>
	</xsl:template>
	
	<xsl:template match="variable_field">
		<tr>
			<td align="center">
				<div>
					<xsl:value-of select="@name"/>
					<br/>
					(variable_field)
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:text> &#160; </xsl:text>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:text> &#160; </xsl:text>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:value-of select="@optional"/>
				</div>
			</td>
			<td align="left">
				<div>
					<xsl:choose>
						<xsl:when test="@interpretation">
							<xsl:value-of select="@interpretation"/>
							<br/>
						</xsl:when>
						<xsl:otherwise>
							<xsl:text> &#160; </xsl:text>
						</xsl:otherwise>
					</xsl:choose>
					<xsl:apply-templates select="@type_and_units_enum"/>
				</div>
			</td>
		</tr>
	</xsl:template>
	
	<xsl:template match="declared_variable_field">
		<tr>
			<td align="center">
				<div>
					<xsl:value-of select="@name"/>
					<br/>
					(declared_variable_field)
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:text> &#160; </xsl:text>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:text> &#160; </xsl:text>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:value-of select="@optional"/>
				</div>
			</td>
			<td align="left">
				<div>
					<xsl:choose>
						<xsl:when test="@interpretation">
							<xsl:value-of select="@interpretation"/>
							<br/>
						</xsl:when>
						<xsl:otherwise>
							<xsl:text> &#160; </xsl:text>
						</xsl:otherwise>
					</xsl:choose>
					<xsl:apply-templates select="@type_and_units_enum"/>
				</div>
			</td>
		</tr>
	</xsl:template>
	
	<xsl:template match="bit_field">
		<tr>
			<td align="center">
				<div>
					<xsl:value-of select="@name"/>
					<br/>
					(bit_field)
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:value-of select="@field_type_unsigned"/>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:text> &#160; </xsl:text>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:value-of select="@optional"/>
				</div>
			</td>
			<td align="left">
				<div>
					<xsl:choose>
						<xsl:when test="@interpretation">
							<xsl:value-of select="@interpretation"/>
							<br/>
						</xsl:when>
						<xsl:otherwise>
							<xsl:text> &#160; </xsl:text>
						</xsl:otherwise>
					</xsl:choose>
					<xsl:apply-templates/>
				</div>
			</td>
		</tr>
	</xsl:template>
	
	<xsl:template match="declared_bit_field">
		<tr>
			<td align="center">
				<div>
					<xsl:value-of select="@name"/>
					<br/>
					(declared_bit_field)
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:text> &#160; </xsl:text>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:text> &#160; </xsl:text>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:value-of select="@optional"/>
				</div>
			</td>
			<td align="left">
				<div>
					<xsl:choose>
						<xsl:when test="@interpretation">
							<xsl:value-of select="@interpretation"/>
							<br/>
						</xsl:when>
						<xsl:otherwise>
							<xsl:text> &#160; </xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</div>
			</td>
		</tr>
	</xsl:template>
	
	<xsl:template match="fixed_length_string">
		<tr>
			<td align="center">
				<div>
					<xsl:value-of select="@name"/>
					<br/>
					(fixed_length_string)
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:text> &#160; </xsl:text>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:text> &#160; </xsl:text>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:value-of select="@optional"/>
				</div>
			</td>
			<td align="left">
				<div>
					String Length: <xsl:value-of select="@string_length"/>
					<br/>
					<xsl:choose>
						<xsl:when test="@interpretation">
							<xsl:value-of select="@interpretation"/>
							<br/>
						</xsl:when>
						<xsl:otherwise>
							<xsl:text> &#160; </xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</div>
			</td>
		</tr>
	</xsl:template>
	
	<xsl:template match="declared_fixed_length_string">
		<tr>
			<td align="center">
				<div>
					<xsl:value-of select="@name"/>
					<br/>
					(declared_fixed_length_string)
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:text> &#160; </xsl:text>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:text> &#160; </xsl:text>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:value-of select="@optional"/>
				</div>
			</td>
			<td align="left">
				<div>
					<xsl:choose>
						<xsl:when test="@interpretation">
							<xsl:value-of select="@interpretation"/>
							<br/>
						</xsl:when>
						<xsl:otherwise>
							<xsl:text> &#160; </xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</div>
			</td>
		</tr>
	</xsl:template>
	
	<xsl:template match="variable_length_string">
		<tr>
			<td align="center">
				<div>
					<xsl:value-of select="@name"/>
					<br/>
					(variable_length_string)
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:choose>
						<xsl:when test="count_field/@field_type_unsigned">
							<xsl:value-of select="count_field/@field_type_unsigned"/>
						</xsl:when>
						<xsl:otherwise>
							<xsl:text> &#160; </xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:text> &#160; </xsl:text>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:value-of select="@optional"/>
				</div>
			</td>
			<td align="left">
				<div>
					<xsl:if test="count_field/@min_count">
						Min: <xsl:value-of select="count_field/@min_count"/>
						<br/>
					</xsl:if>
					<xsl:if test="count_field/@max_count">
						Max: <xsl:value-of select="count_field/@max_count"/>
						<br/>
					</xsl:if>
					<xsl:if test="@interpretation">
						<xsl:value-of select="@interpretation"/>
						<br/>
					</xsl:if>
				</div>
			</td>
		</tr>
	</xsl:template>
		
	<xsl:template match="declared_variable_length_string">
		<tr>
			<td align="center">
				<div>
					<xsl:value-of select="@name"/>
					<br/>
					(declared_variable_length_string)
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:text> &#160; </xsl:text>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:text> &#160; </xsl:text>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:value-of select="@optional"/>
				</div>
			</td>
			<td align="left">
				<div>
					<xsl:choose>
						<xsl:when test="@interpretation">
							<xsl:value-of select="@interpretation"/>
							<br/>
						</xsl:when>
						<xsl:otherwise>
							<xsl:text> &#160; </xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</div>
			</td>
		</tr>
	</xsl:template>
	
	<xsl:template match="variable_length_field">
		<tr>
			<td align="center">
				<div>
					<xsl:value-of select="@name"/>
					<br/>
					(variable_length_field)
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:choose>
						<xsl:when test="count_field/@field_type_unsigned">
							<xsl:value-of select="count_field/@field_type_unsigned"/>
						</xsl:when>
						<xsl:otherwise>
							<xsl:text> &#160; </xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:text> &#160; </xsl:text>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:value-of select="@optional"/>
				</div>
			</td>
			<td align="left">
				<div>
					<xsl:if test="count_field/@min_count">
						Min: <xsl:value-of select="count_field/@min_count"/>
						<br/>
					</xsl:if>
					<xsl:if test="count_field/@max_count">
						Max: <xsl:value-of select="count_field/@max_count"/>
						<br/>
					</xsl:if>
					<xsl:if test="@interpretation">
						<xsl:value-of select="@interpretation"/>
						<br/>
					</xsl:if>
				</div>
			</td>
		</tr>
	</xsl:template>
	
	<xsl:template match="declared_variable_length_field">
		<tr>	
			<td align="center">
				<div>
					<xsl:value-of select="@name"/>
					<br/>
					(declared_variable_length_field)
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:text> &#160; </xsl:text>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:text> &#160; </xsl:text>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:value-of select="@optional"/>
				</div>
			</td>
			<td align="left">
				<div>
					<xsl:choose>
						<xsl:when test="@interpretation">
							<xsl:value-of select="@interpretation"/>
							<br/>
						</xsl:when>
						<xsl:otherwise>
							<xsl:text> &#160; </xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</div>
			</td>
		</tr>
	</xsl:template>
	
	<xsl:template match="variable_format_field">
		<tr>
			<td align="center">
				<div>
					<xsl:value-of select="@name"/>
					<br/>
					(variable_format_field)
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:choose>
						<xsl:when test="count_field/@field_type_unsigned">
							<xsl:value-of select="count_field/@field_type_unsigned"/>
						</xsl:when>
						<xsl:otherwise>
							<xsl:text> &#160; </xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:text> &#160; </xsl:text>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:value-of select="@optional"/>
				</div>
			</td>
			<td align="left">
				<div>
					<xsl:if test="count_field/@min_count">
						Min: <xsl:value-of select="count_field/@min_count"/>
						<br/>
					</xsl:if>
					<xsl:if test="count_field/@max_count">
						Max: <xsl:value-of select="count_field/@max_count"/>
						<br/>
					</xsl:if>
					<xsl:if test="@interpretation">
						<xsl:value-of select="@interpretation"/>
						<br/>
					</xsl:if>
				</div>
			</td>
		</tr>
	</xsl:template>
	
	<xsl:template match="declared_variable_format_field">
		<tr>
			<td align="center">
				<div>
					<xsl:value-of select="@name"/>
					<br/>
					(declared_variable_format_field)
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:text> &#160; </xsl:text>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:text> &#160; </xsl:text>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:value-of select="@optional"/>
				</div>
			</td>
			<td align="left">
				<div>
					<xsl:choose>
						<xsl:when test="@interpretation">
							<xsl:value-of select="@interpretation"/>
							<br/>
						</xsl:when>
						<xsl:otherwise>
							<xsl:text> &#160; </xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</div>
			</td>
		</tr>
	</xsl:template>
	
	<xsl:template match="sub_field">
		<xsl:apply-templates select="bit_range"/> 
		<xsl:value-of select="@name"/>
		<xsl:if test="value_set">
			(<xsl:apply-templates select="value_set"/>)
		</xsl:if>
		<br/>
	</xsl:template>
	
	<xsl:template match="scale_range">
		<xsl:value-of select="@real_lower_limit"/>...<xsl:value-of select="@real_upper_limit"/> (<xsl:value-of select="@integer_function"/>)
		<br/>
	</xsl:template>
	
	<xsl:template match="value_set">
		<xsl:apply-templates/>
	</xsl:template>	
	
	<xsl:template match="bit_range">
		Bits <xsl:value-of select="@from_index"/>-<xsl:value-of select="@to_index"/>:
	</xsl:template>
	
	<xsl:template match="value_range">
		<xsl:value-of select="@lower_limit"/>...<xsl:value-of select="@upper_limit"/> 
		<xsl:if test="@interpretation">
			: <xsl:value-of select="@interpretation"/>
		</xsl:if>
		
	</xsl:template>
	
	<xsl:template match="value_enum">
		<xsl:value-of select="@enum_index"/>: <xsl:value-of select="@enum_const"/>
		<br/>
	</xsl:template>
	
	<xsl:template match="type_and_units_enum">
		<xsl:apply-templates/>
	</xsl:template>
	
</xsl:stylesheet>

