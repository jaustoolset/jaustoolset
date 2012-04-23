<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<html>
			<head>
				<title>
					<xsl:value-of select="service_def/@name"/>
				</title>
				<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
				<link rel="stylesheet" href="framedStyleSheet.css"/>
			</head>
			
			<body>
				<xsl:value-of select="service_def/serviceSetName/@name"/>
			
				<!-- TITLE -->
				<!-- ===== -->
				<h1> 
					<xsl:value-of select="service_def/@name"/> Service
				</h1>
				<br/>
				
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
					<xsl:value-of select="service_def/description"/>
				</table>
				<br/>
				
				<!-- ASSUMPTIONS -->
				<!-- =========== -->
				<h2>
					<b>
						<u>
							Assumptions:
						</u>
					</b>
				</h2>
				<table border="0" width="100%" cellpadding="0" cellspacing="0" summary="">
					<xsl:value-of select="service_def/assumptions"/>
				</table>
				<br/>
				
				<!-- REFERENCES -->
				<!-- ========== -->
				<h2>
					<b>
						<u>
							References:
						</u>
					</b>
				</h2>
				
				<ul>
					<b> Inherits from: </b> 
					<xsl:choose>
						<xsl:when test="service_def/references/inherits_from">
							<xsl:apply-templates select="service_def/references/inherits_from"/>
						</xsl:when>
						<xsl:otherwise>
							None
						</xsl:otherwise>
					</xsl:choose> 
					<br/>
					
					<b> Client of: </b> 
					<xsl:choose>
						<xsl:when test="service_def/references/client_of">
							<xsl:apply-templates select="service_def/references/client_of"/>
						</xsl:when>
						<xsl:otherwise>
							None
						</xsl:otherwise>
					</xsl:choose>
					<br/>
				</ul>
								
				<!-- DECLARED CONSTANT SET -->
				<!-- ===================== -->
				<h2>
					<b>
						<u>
							Declared Constant Set:
						</u>
					</b>
				</h2>
				
				<ul>
					<b> Referenced constants: </b>
					<xsl:choose>
						<xsl:when test="service_def/declared_const_set/declared_const_set_ref">
							<xsl:apply-templates select="service_def/declared_const_set/declared_const_set_ref"/>
						</xsl:when>
						<xsl:otherwise>
							None
						</xsl:otherwise>
					</xsl:choose>
					<br/>
					
					<b> Defined constants: </b>
					<xsl:choose>
						<xsl:when test="service_def/declared_const_set/const_def">
							<table  border="1" width="100%" cellspacing="1" cellpadding="0" summary="">
								<tr>
									<th align="center">
										Name
									</th>
									<th align="center">
										Type
									</th>
									<th align="center">
										Units
									</th>
									<th align="center">
										Value
									</th>
									<th align="center">
										Interpretation
									</th>
								</tr>
							</table>
							<xsl:apply-templates select="service_def/declared_const_set/const_def"/>
						</xsl:when>
						<xsl:otherwise>
							None
						</xsl:otherwise>
					</xsl:choose>
					<br/>
				</ul>
				
				<!-- DECLARED TYPE SET -->
				<!-- ================= -->
				<h2>
					<b>
						<u>
							Declared Type Set:
						</u>
					</b>
				</h2>
				
				<ul>
					<b>	Referenced Constants: </b>
					<xsl:choose>
						<xsl:when test="service_def/declared_type_set/declared_const_set_ref">
							<xsl:apply-templates select="service_def/declared_type_set/declared_const_set_ref"/>
						</xsl:when>
						<xsl:otherwise>
							None
						</xsl:otherwise>
					</xsl:choose>
					<br/>
					
					<b> Referenced Types: </b>
					<xsl:choose>
						<xsl:when test="service_def/declared_type_set/declared_const_set_ref">
							<xsl:apply-templates select="service_def/declared_type_set/declared_const_set_ref"/>
						</xsl:when>
						<xsl:otherwise>
							None
						</xsl:otherwise>
					</xsl:choose>
					<br/>
				</ul>
				
				<!-- MESSAGE SET -->
				<!-- =========== -->
				<h2>
					<b>
						<u>
							Message Set:
						</u>
					</b>
				</h2>
				<table border="0" width="100%" cellpadding="0" cellspacing="0" summary="">
					<tr>
						<th align="center">
							<div>
								<h3>
									<u>
										Input Set
									</u>
								</h3>
							</div>
						</th>
					</tr>
				</table>
				
				<table border="1" width="100%" cellpadding="1" cellspacing="1" summary="">
					<tr bgcolor="#cccccc">
						<th>
							Message Definition
						</th>
						<th>
							Description
						</th>
					</tr>
					<xsl:apply-templates select="service_def/message_set/input_set"/>
				</table>
				<br/>
				<br/>
								
				<table border="0" width="100%" cellpadding="0" cellspacing="0" summary="">
					<tr>
						<th align="center">
							<div>
								<h3>
									<u>
										Output Set
									</u>
								</h3>
							</div>
						</th>
					</tr>
				</table>
				<table border="1" width="100%" cellpadding="1" cellspacing="1" summary="">
					<tr bgcolor="#cccccc">
						<th>
							Message Definition
						</th>
						<th>
							Description
						</th>
					</tr>
					<xsl:apply-templates select="service_def/message_set/output_set"/>
				</table>
				
				<!-- INTERNAL EVENTS SET -->
				<!-- =================== -->
				<h2>
					<b>
						<u>
							Internal Events Set:
						</u>
					</b>
				</h2>
				<table border="1" width="100%" cellpadding="1" cellspacing="0" summary="">
					<tr bgcolor="#cccccc">
						<th>
							Event
						</th>
						<th>
							Description
						</th>
					</tr>
					<xsl:apply-templates select="service_def/internal_events_set"/>
				</table>
				<br/>
				
				<xsl:if test="service_def/internal_events_set/event_def">
					<xsl:apply-templates select="service_def/internal_events_set/event_def"/>
				</xsl:if>
				
				<!-- PROTOCOL BEHAVIOR -->
				<!-- ================= -->
				<h2>
					<b>
						<u>
							Protocol Behavior:
						</u>
					</b>
				</h2>
				
				<xsl:apply-templates select="service_def/protocol_behavior"/>
				<br/>
				
			</body>
		</html>
	</xsl:template>

	<xsl:template match="inherits_from">
		<xsl:value-of select="@name"/>
		
		<xsl:if test="not (position()=last())">
	         <xsl:text>, </xsl:text>
		</xsl:if>
	</xsl:template>

	<xsl:template match="client_of">
		<xsl:value-of select="@name"/>
	
		<xsl:if test="not (position()=last())">
	         <xsl:text>, </xsl:text>
		</xsl:if>
	</xsl:template>

	<xsl:template match="input_set">
		<xsl:apply-templates select="message_def"/>
	</xsl:template>

	<xsl:template match="output_set">
		<xsl:apply-templates select="message_def"/>
	</xsl:template>
	
	<xsl:template match="message_def">
		<xsl:variable name="messageName">
			<xsl:value-of select="@name"/>
		</xsl:variable>
	
		<tr>
			<td align="left">
				<div>
					<a href="messages/{$messageName}.html">		<!-- create hyperlink to message file -->
						<xsl:value-of select="@name"/>
					</a>
				</div>
			</td>
			<td align="left">
				<div>
					<xsl:value-of select="description"/>
				</div>
			</td>
		</tr>
	</xsl:template>
	
	<xsl:template match="internal_events_set">
		<xsl:choose>
			<xsl:when test="event_def">
				<xsl:for-each select="event_def">
					<tr>
						<td align="left">
							<div>
								<xsl:value-of select="@name"/>
							</div>
						</td>
						<td align="left">
							<div>
								<xsl:value-of select="description"/>
							</div>
						</td>
					</tr>
				</xsl:for-each>
			</xsl:when>
			<xsl:when test="declared_event_def">
				<xsl:for-each select="declared_event_def">
					<tr>
						<td align="left">
							<div>
								<xsl:value-of select="@name"/>
							</div>
						</td>
						<td align="left">
							<div>
								<xsl:value-of select="@declared_type"/>
							</div>
						</td>
					</tr>
				</xsl:for-each>
			</xsl:when>
		</xsl:choose>
	</xsl:template>

	<xsl:template match="event_def">
		<ul>
			<h3>
				<u>
					<xsl:value-of select="@name"/>
				</u>
			</h3>
			
			<ul>
				<xsl:choose>
					<xsl:when test="header">
						<xsl:apply-templates select="header"/>
					</xsl:when>
					<xsl:when test="declared_header">
						<xsl:apply-templates select="declared_header"/>
					</xsl:when>
				</xsl:choose>
				<xsl:choose>
					<xsl:when test="body">
						<xsl:apply-templates select="body"/>
					</xsl:when>
					<xsl:when test="declared_body">
						<xsl:apply-templates select="declared_body"/>
					</xsl:when>
				</xsl:choose>
				<xsl:choose>
					<xsl:when test="footer">
						<xsl:apply-templates select="footer"/>
					</xsl:when>
					<xsl:when test="declared_footer">
						<xsl:apply-templates select="declared_footer"/>
					</xsl:when>
				</xsl:choose>
			</ul>
		</ul>
		<br/>
	</xsl:template>
	
	<xsl:template match="declared_event_def">
		<tr>
			<td align="left">
				<div>
					<xsl:value-of select="@name"/>
				</div>
			</td>
			<td align="left">
				<div>
					<xsl:value-of select="@declared_type"/>
				</div>
			</td>
		</tr>
	</xsl:template>

	<xsl:template match="declared_const_set_ref">
		<xsl:value-of select="@name"/>
		
		<xsl:if test="not (position()=last())">
	         <xsl:text>, </xsl:text>
		</xsl:if> 
	</xsl:template>
	
	<xsl:template match="const_def">
		<tr>
			<td align="center">
				<div>
					<xsl:value-of select="@name"/>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:value-of select="@const_type"/>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:value-of select="@field_units"/>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:value-of select="@const_value"/>
				</div>
			</td>
			<td align="left">
				<div>
					<xsl:value-of select="@interpretation"/>
				</div>
			</td>
		</tr>
	</xsl:template>

	<xsl:template match="declared_type_set_ref">
		<xsl:value-of select="@name"/>
		
		<xsl:if test="not (position()=last())">
	         <xsl:text>, </xsl:text>
		</xsl:if>
	</xsl:template>
	
	<xsl:template match="protocol_behavior">
	  <img src="protocolBehavior.png" alt="--NOTE: STATE MACHINE IMAGE WAS NOT GENERATED. TO GENERATE IMAGE, FIRST FORMAT THE STATE MACHINE IN THE JTS GUI AND SAVE IT TO THE DATABASE, THEN GENERATE DOCUMENTATION. --" />
	  <br/>
		<xsl:apply-templates select="state_machine"/>
	</xsl:template>
	
	<xsl:template match="state_machine">
		<b> State Machine: </b> <xsl:value-of select="@name"/>
		<xsl:if test="@interpretation">
			(<xsl:value-of select="@interpretation"/>)
		</xsl:if>
		<br/>
		
		(Initial State: 
		<xsl:call-template name="findStartState">
			<xsl:with-param name="state_machine_name" select="@name"/>
			<xsl:with-param name="protocol_behavior" select="../../protocol_behavior"/>
		</xsl:call-template>
		)
		<br/>
		
		<xsl:apply-templates select="state"/>
		<br/>
		
		<b> Service Conditions </b>
		<table border="2" width="100%" cellspacing="1" cellpadding="0" summary="">
			<tr bgcolor="#cccccc">
				<th align="center">
					Condition
				</th>
				<th align="center">
					Interpretation
				</th>
			</tr>
			<xsl:call-template name="findTransitions">
				<xsl:with-param name="state" select="state"/>
				<xsl:with-param name="find" select="'conditions'"/>
			</xsl:call-template>
		</table>
		<br/>
		
		<b> Service Actions </b>
		<table border="2" width="100%" cellspacing="1" cellpadding="0" summary="">
			<tr bgcolor="#cccccc">
				<th align="center">
					Action
				</th>
				<th align="center">
					Interpretation
				</th>
			</tr>
			
			<xsl:call-template name="findEntries">
				<xsl:with-param name="state" select="state"/>
				<xsl:with-param name="find" select="'actions'"/>
			</xsl:call-template>
			
			<xsl:call-template name="findTransitions">
				<xsl:with-param name="state" select="state"/>
				<xsl:with-param name="find" select="'actions'"/>
			</xsl:call-template>
			
			<xsl:call-template name="findExits">
				<xsl:with-param name="state" select="state"/>
				<xsl:with-param name="find" select="'actions'"/>
			</xsl:call-template>
			
		</table>
		<br/>
	</xsl:template>
	
	<xsl:template match="state">
		<table border="2" width="100%" cellspacing="1" cellpadding="0" summary="">
			<tr>
				<td>
					<b> <xsl:value-of select="@name"/> </b>
					
					<!-- if the state contains additional states within itself, recurse -->
					<xsl:if test="state">
						<xsl:apply-templates select="state"/>
					</xsl:if>
					<xsl:if test="default_state">
						<xsl:apply-templates select="default_state"/>
					</xsl:if>
					<xsl:if test="transition">
						<!-- Service Transitions -->
						<table border="2" width="100%" cellspacing="1" cellpadding="0" summary="">
							<tr bgcolor="#cccccc">
								<th align="center">
									Trigger 
								</th>
								<th align="center" width="200">
									Guard
								</th>
								<th align="center">
									Actions
								</th>
								<th align="center">
									Next State
								</th>
							</tr>	 
							<xsl:apply-templates select="transition"/>
						</table>
					</xsl:if>
				</td>
			</tr>
		</table>
		<br/>
	</xsl:template>
	
	<xsl:template match="default_state">
		<table border="2" width="100%" cellspacing="1" cellpadding="0" summary="">
			<tr>
				<td>
					<b> Default State </b>
				
					<xsl:if test="transition">
						<!-- Service Transitions -->
						<table border="2" width="100%" cellspacing="1" cellpadding="0" summary="">
							<tr bgcolor="#cccccc">
								<th align="center">
									Trigger 
								</th>
								<th align="center" width="200">
									Guard
								</th>
								<th align="center">
									Actions
								</th>
								<th align="center">
									Next State
								</th>
							</tr>	 
							<xsl:apply-templates select="transition"/>
						</table>
					</xsl:if>
				</td>
			</tr>
		</table>
		<br/>
	</xsl:template>
	
	<xsl:template match="entry">
		<xsl:apply-templates select="action"/>
	</xsl:template>
	
	<xsl:template match="transition">
		<tr>
			<td align="center">
				<xsl:value-of select="@name"/> 
				(<xsl:apply-templates select="parameter"/>)
			</td>
			<td align="center" width="200">
				<xsl:choose>
					<xsl:when test="guard">
						<xsl:for-each select="guard">
							<xsl:value-of select="@condition"/>
							
							<xsl:if test="not (position()=last())">
						         <xsl:text>, </xsl:text>
							</xsl:if> 
						</xsl:for-each>
					</xsl:when>
					<xsl:otherwise>
						<xsl:text> &#160; </xsl:text>
					</xsl:otherwise>	
				</xsl:choose>
			</td>
			<td align="center">
				<xsl:choose>
					<xsl:when test="action">
						<xsl:for-each select="action">
							<xsl:value-of select="@name"/>
							(<xsl:for-each select="argument">
								<xsl:value-of select="@value"/>
							
								<xsl:if test="not (position()=last())">
							         <xsl:text>, </xsl:text>
							         <xsl:text> &#160; </xsl:text>
								</xsl:if>
							</xsl:for-each>) 
							<xsl:if test="not (position()=last())">
						         <xsl:text>, </xsl:text>
						         <xsl:text> &#160; </xsl:text>
							</xsl:if>
							<br/>
						</xsl:for-each>
					</xsl:when>
					<xsl:otherwise>
						<xsl:text> &#160; </xsl:text>
					</xsl:otherwise>	
				</xsl:choose>
			</td>
			<td>
				<xsl:choose>
					<xsl:when test="simple">
						<xsl:choose>
							<xsl:when test="simple/end_state">
								<xsl:value-of select="simple/end_state/@state"/>
							</xsl:when>
							<xsl:when test="../@name">
								<xsl:value-of select="../@name"/>
							</xsl:when>
							<xsl:when test="../../default_state">
								Default State
							</xsl:when>
							<xsl:otherwise>
								<xsl:text> &#160; </xsl:text>
							</xsl:otherwise>
						</xsl:choose>
					</xsl:when>
					<xsl:when test="push">
						<xsl:choose>
							<xsl:when test="end_state">
								<xsl:value-of select="end_state"/>
							</xsl:when>
							<xsl:otherwise>
								<xsl:text> &#160; </xsl:text>
							</xsl:otherwise>
						</xsl:choose>
					</xsl:when>
					<xsl:when test="pop">
						<xsl:choose>
							<xsl:when test="end_state">
								<xsl:value-of select="end_state"/>
							</xsl:when>
							<xsl:otherwise>
								<xsl:text> &#160; </xsl:text>
							</xsl:otherwise>
						</xsl:choose>
					</xsl:when>	
				</xsl:choose>
			</td>
		</tr>
	</xsl:template>

	<xsl:template match="parameter">
		<xsl:value-of select="@type"/> 
		<xsl:text> &#160; </xsl:text>
		<xsl:value-of select="@value"/>
				
		<xsl:if test="not (position()=last())">
	         <xsl:text>, </xsl:text>
	         <xsl:text> &#160; </xsl:text>
		</xsl:if> 
	</xsl:template>

	<xsl:template match="guard">
		<tr>
			<td align="left">
				<xsl:value-of select="@condition"/>
			</td>
			<td align="left">
				<xsl:choose>
					<xsl:when test="@interpretation">
						<xsl:value-of select="@interpretation"/>
					</xsl:when>
					<xsl:otherwise>
						<xsl:text> &#160; </xsl:text>
					</xsl:otherwise>
				</xsl:choose>
			</td>
		</tr>
	</xsl:template>

	<xsl:template match="action">
		<tr>
			<td align="left">
				<xsl:value-of select="@name"/>
				(<xsl:for-each select="argument">
					<xsl:value-of select="@value"/>
				
					<xsl:if test="not (position()=last())">
				         <xsl:text>, </xsl:text>
				         <xsl:text> &#160; </xsl:text>
					</xsl:if>
				</xsl:for-each>) 
			
				<!-- <xsl:value-of select="@name"/> -->
			</td>
			<td align="left">
				<xsl:choose>
					<xsl:when test="@interpretation">
						<xsl:value-of select="@interpretation"/>
					</xsl:when>
					<xsl:otherwise>
						<xsl:text> &#160; </xsl:text>
					</xsl:otherwise>
				</xsl:choose>
			</td>
		</tr>
	</xsl:template>
		
	<xsl:template match="send_action">
		<tr>
			<td align="center">
				<div>
					<xsl:value-of select="@name"/>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:value-of select="../@name"/>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:choose>
						<xsl:when test="@interpretation">
							<xsl:value-of select="@interpretation"/>
						</xsl:when>
						<xsl:otherwise>
							<xsl:text> &#160; </xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</div>
			</td>
			<td align="center">
				<div>
					<xsl:choose>
						<xsl:when test="argument">
							<xsl:for-each select="argument">
								<xsl:value-of select="@value"/>
								
								<xsl:if test="not (position()=last())">
							         <xsl:text>, </xsl:text>
								</xsl:if> 
							</xsl:for-each>
						</xsl:when>	
						<xsl:otherwise>
							<xsl:text> &#160; </xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</div>
			</td>
		</tr>
	</xsl:template>
	
	<xsl:template match="header">
		<b> Header </b>
		<br/>
		
		<ul>
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
				<xsl:otherwise>
					<i>
						Empty
					</i>
				</xsl:otherwise>
			</xsl:choose>
		</ul>
		<br/>
	</xsl:template>
	
	<xsl:template match="declared_header">
		declared header name = <xsl:value-of select="@name"/>
		<xsl:if test="@interpretation">
			(<xsl:value-of select="@interpretation"/>)
		</xsl:if>
		<br/>
	</xsl:template>
	
	<xsl:template match="body">
		<b> Body </b>
		<br/>
		
		<ul>
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
				<xsl:otherwise>
					<i>
						Empty
					</i>
				</xsl:otherwise>
			</xsl:choose>
		</ul>
		<br/>
	</xsl:template>
	
	<xsl:template match="declared_body">
		declared body name = <xsl:value-of select="@name"/>
		<xsl:if test="@interpretation">
			(<xsl:value-of select="@interpretation"/>)
		</xsl:if>
		<br/>
		<br/>
	</xsl:template>
	
	<xsl:template match="footer">
		<b> Footer </b>
		<br/>
		
		<ul>
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
				<xsl:otherwise>
					<i>
						Empty
					</i>
				</xsl:otherwise>
			</xsl:choose>
		</ul>
		<br/>
	</xsl:template>
	
	<xsl:template match="declared_footer">
		declared footer name = <xsl:value-of select="@name"/>
		<xsl:if test="@interpretation">
			(<xsl:value-of select="@interpretation"/>)
		</xsl:if>
		<br/>
	</xsl:template>
	
	<xsl:template match="record">
		<image src="images/branch.gif" width="15" height="14"/> record name = <xsl:value-of select="@name"/>
		<br/>
		
		<table  border="1" width="90%" cellpadding="1" cellspacing="1" summary="">
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
				<th align="left" bgcolor="#cccccc">
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
		<image src="images/branch.gif" width="15" height="14"/> list name = <xsl:value-of select="@name"/>
		<br/>
	
		<xsl:apply-templates/>
	</xsl:template>
	
	<xsl:template match="variant">
		<image src="images/branch.gif" width="15" height="14"/> variant name = <xsl:value-of select="@name"/>
		<br/>
		
		<xsl:apply-templates/>
	</xsl:template>
	
	<xsl:template match="sequence">
		<image src="images/branch.gif" width="15" height="14"/> sequence name = <xsl:value-of select="@name"/>
		<br/>
	
		<xsl:apply-templates/>
	</xsl:template>
	
	<xsl:template match="declared_record">
		<image src="images/branch.gif" width="15" height="14"/> declared record name = <xsl:value-of select="@name"/>
		<br/>
		
		<table  border="1" width="90%" cellpadding="1" cellspacing="1" summary="">
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
				<td align="center">
					<xsl:value-of select="@name"/>
				</td>
				<td align="center">
					<xsl:value-of select="@interpretation"/>
				</td>
			</tr>
		</table>
		<br/>
	</xsl:template>
	
	<xsl:template match="declared_list">
		<image src="images/branch.gif" width="15" height="14"/> declared list name = <xsl:value-of select="@name"/>
		<br/>
	</xsl:template>
	
	<xsl:template match="declared_variant">
		<image src="images/branch.gif" width="15" height="14"/> declared variant name = <xsl:value-of select="@name"/>
		<br/>
	</xsl:template>
	
	<xsl:template match="declared_sequence">
		<image src="images/branch.gif" width="15" height="14"/> declared sequence name = <xsl:value-of select="@name"/>
		<br/>
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
					<xsl:if test="@interpretation">
						<xsl:value-of select="@interpretation"/>
						<br/>
					</xsl:if>
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
					<xsl:if test="@interpretation">
						<xsl:value-of select="@interpretation"/>
						<br/>
					</xsl:if>
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
					<xsl:if test="@interpretation">
						<xsl:value-of select="@interpretation"/>
						<br/>
					</xsl:if>
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
					<xsl:if test="@interpretation">
						<xsl:value-of select="@interpretation"/>
						<br/>
					</xsl:if>
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
					<xsl:if test="@interpretation">
						<xsl:value-of select="@interpretation"/>
						<br/>
					</xsl:if>
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
					<xsl:if test="@interpretation">
						<xsl:value-of select="@interpretation"/>
						<br/>
					</xsl:if>
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
					<xsl:if test="@interpretation">
						<xsl:value-of select="@interpretation"/>
						<br/>
					</xsl:if>
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
					<xsl:if test="@interpretation">
						<xsl:value-of select="@interpretation"/>
						<br/>
					</xsl:if>
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
					<xsl:if test="@interpretation">
						<xsl:value-of select="@interpretation"/>
						<br/>
					</xsl:if>
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
					<xsl:if test="@interpretation">
						<xsl:value-of select="@interpretation"/>
						<br/>
					</xsl:if>
				</div>
			</td>
		</tr>
	</xsl:template>
	
	<xsl:template match="variable_format_field">
		<tr>
			<td align="center">
				<div>
					<xsl:value-of select="@name"/>
					(variable_format_field)
					<br/>
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
					<xsl:if test="@interpretation">
						<xsl:value-of select="@interpretation"/>
						<br/>
					</xsl:if>
				</div>
			</td>
		</tr>
	</xsl:template>
	
	<xsl:template match="sub_field">
		<xsl:apply-templates select="bit_range"/> 
		<xsl:value-of select="@name"/>,
		<xsl:apply-templates select="value_set"/>
		<br/>
	</xsl:template>
	
	<xsl:template match="scale_range">
		<xsl:value-of select="@real_lower_limit"/>...<xsl:value-of select="@real_upper_limit"/> (<xsl:value-of select="@integer_function"/>)
	</xsl:template>
	
	<xsl:template match="value_set">
		<xsl:apply-templates/>
	</xsl:template>	
	
	<xsl:template match="bit_range">
		Bits <xsl:value-of select="@from_index"/>-<xsl:value-of select="@to_index"/>:
	</xsl:template>
	
	<xsl:template match="value_range">
		<xsl:value-of select="@lower_limit"/>...<xsl:value-of select="@upper_limit"/>: 
		<xsl:if test="@interpretation">
			<xsl:value-of select="@interpretation"/>
		</xsl:if>
		<br/>
	</xsl:template>
	
	<xsl:template match="value_enum">
		<xsl:value-of select="@enum_index"/>: <xsl:value-of select="@enum_const"/>
		<br/>
	</xsl:template>
	
	<xsl:template match="type_and_units_enum">
		<xsl:apply-templates/>
	</xsl:template>

	<!-- template to recursively search through states to find entries and their actions -->
	<xsl:template name="findEntries">
		<xsl:param name="state"/>
		<xsl:param name="find"/>
		
		<xsl:if test="$state/entry">
			<xsl:apply-templates select="$state/entry"/>
		</xsl:if>
		<xsl:if test="$state/state">
			<xsl:call-template name="findEntries">
				<xsl:with-param name="state" select="$state/state"/>
				<xsl:with-param name="find" select="$find"/>
			</xsl:call-template>
		</xsl:if>
		
	</xsl:template>

	<!-- template to recursively search through states to find states with transitions -->
	<xsl:template name="findTransitions">
		<xsl:param name="state"/>
		<xsl:param name="find"/>
		
		<xsl:if test="$state/transition">
			<!-- <xsl:for-each select="$state/transition"> -->
				<xsl:call-template name="findConditions">
					<xsl:with-param name="find" select="$find"/>
					<xsl:with-param name="transition" select="$state/transition"/>
				</xsl:call-template>
			<!-- </xsl:for-each> -->
		</xsl:if>
		<xsl:if test="$state/state">
			<xsl:call-template name="findTransitions">
				<xsl:with-param name="state" select="$state/state"/>
				<xsl:with-param name="find" select="$find"/>
			</xsl:call-template>
		</xsl:if>
		<xsl:if test="$state/default_state">
			<xsl:call-template name="findTransitions">
				<xsl:with-param name="state" select="$state/default_state"/>
				<xsl:with-param name="find" select="$find"/>
			</xsl:call-template>
		</xsl:if>
	</xsl:template>
	
	<!-- template to recursively search through states to find entries and their actions -->
	<xsl:template name="findExits">
		<xsl:param name="state"/>
		<xsl:param name="find"/>
		
		<xsl:if test="$state/exit">
			<xsl:apply-templates select="$state/exit"/>
		</xsl:if>
		<xsl:if test="$state/state">
			<xsl:call-template name="findExits">
				<xsl:with-param name="state" select="$state/state"/>
				<xsl:with-param name="find" select="$find"/>
			</xsl:call-template>
		</xsl:if>
		
	</xsl:template>
	
	<xsl:template name="findConditions">
		<xsl:param name="find"/>
		<xsl:param name="transition"/>
		
		<xsl:choose>
			<xsl:when test="$find = 'conditions'">
				<xsl:apply-templates select="$transition/guard"/>
			</xsl:when>
			<xsl:when test="$find = 'actions'">
				<xsl:call-template name="findActions">
					<xsl:with-param name="transition" select="$transition"/>
				</xsl:call-template>
			</xsl:when>
		</xsl:choose>
	</xsl:template>
	
	<!-- template to recursively search through transitions to find transitions with actions -->
	<xsl:template name="findActions">
		<xsl:param name="transition"/>
		
		<xsl:choose>
			<xsl:when test="$transition/action">
				<xsl:apply-templates select="$transition/action"/>
			</xsl:when>
			<xsl:when test="$transition/send_action">
				<xsl:apply-templates select="$transition/send_action"/>
			</xsl:when>
		</xsl:choose>
	</xsl:template>
	
	<xsl:template name="findStartState">
		<xsl:param name="state_machine_name"/>
		<xsl:param name="protocol_behavior"/>
		
		<xsl:for-each select="$protocol_behavior/start">
			<xsl:if test="@state_machine_name = $state_machine_name">
				<xsl:value-of select="@state_name"/>
			</xsl:if>
		</xsl:for-each>
	</xsl:template>
	
</xsl:stylesheet>