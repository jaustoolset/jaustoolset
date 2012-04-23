<?xml version='1.0'?>
    <!--
      Copyright 2010 - Jim Albers
      Creative Commons Attribution-ShareAlike 3.0
      http://creativecommons.org/licenses/by-sa/3.0/us/
    -->
<!-- 
  
  Further edits 2010 by Ian Durkan, Progeny Systems Corp
  
  This transform turns Docbook representing a service definition into an HTML document.
  It also consumes the non-Docbook elements produced by servdef_jsidl_db_cals.xsl to help
  with styling the HTML as desired.
  
-->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:import href="html/docbook.xsl"/>  <!-- use this to get single html file output -->
  <xsl:param name="stylesheet.result.type" select="html"/> 
  <xsl:param name="paper.type" select="1"/>

  <!-- TOC overrides, start with the stock fo/param.xsl definition -->
  <xsl:param name="toc.section.depth" select="'3'"/>
  <xsl:param name="toc.max.depth" select="'3'"/>
  <xsl:param name="generate.toc">
    part/article  toc,title,figure,table,example,equation
    section title,figure,table,equation,example
    part      nop
  </xsl:param>
  <!-- The following are necessary to get the additional TOC info generated -->
  <xsl:param name="generate.division.figure.lot" select="1" />  
  <xsl:param name="generate.division.table.lot" select="1" />  
  <xsl:param name="generate.division.example.lot" select="1" /> 
  <xsl:param name="generate.division.equation.lot" select="1" />
  <!-- set stylesheet the output HTML will use. -->
  <xsl:param name="html.stylesheet" select="'linearStyleSheet.css'"/>
  <!-- Fix up the section title styles -->
  <xsl:attribute-set name="component.title.properties">
    <xsl:attribute name="font-size">10pt</xsl:attribute>
  </xsl:attribute-set>
  <xsl:attribute-set name="section.title.properties">
    <xsl:attribute name="font-size">10pt</xsl:attribute>
  </xsl:attribute-set>
  <xsl:attribute-set name="simplesect.title.properties">
    <xsl:attribute name="font-size">10pt</xsl:attribute>
    <xsl:attribute name="text-align">center</xsl:attribute>
  </xsl:attribute-set>
  <xsl:attribute-set name="section.title.level1.properties">
    <xsl:attribute name="font-size">10pt</xsl:attribute>
  </xsl:attribute-set>
  <xsl:attribute-set name="section.title.level2.properties">
    <xsl:attribute name="font-size">10pt</xsl:attribute>
  </xsl:attribute-set>
  <xsl:attribute-set name="section.title.level3.properties">
    <xsl:attribute name="font-size">10pt</xsl:attribute>
  </xsl:attribute-set>
  <xsl:attribute-set name="section.title.level4.properties">
    <xsl:attribute name="font-size">10pt</xsl:attribute>
  </xsl:attribute-set>
  <xsl:attribute-set name="section.title.level5.properties">
    <xsl:attribute name="font-size">10pt</xsl:attribute>
  </xsl:attribute-set>

  <!-- labelling and title generation settings -->
  <xsl:param name="section.autolabel" select="'1'"/>
  <xsl:param name="section.label.includes.component.label" select="'1'"/>
  <xsl:param name="section.autolabel.maxdepth" select="'8'"/>
  <xsl:param name="formal.title.placement">
    figure after
    example before
    equation after
    table before
    procedure before
  </xsl:param>

  <!-- Some special character handling per SAE template, uses <emphasis role="<special>"/> -->
  <xsl:template match="emphasis[@role='bold']">
    <b>
      <xsl:apply-templates/>
    </b>
  </xsl:template>
  
  <xsl:template match="emphasis[@role='optionalfield']">
    <b><i>
      <xsl:apply-templates/>
    </i></b>
  </xsl:template>
  
  <xsl:template match="emphasis[@role='saedoctype']">
    <b style="font-size:18pt"><xsl:apply-templates/></b>
  </xsl:template>
  
  <!-- IMD: gives way to explicitly pick a style in Docbook template or JSIDL->HTML stylesheet. -->
  <!-- Specify style-string to give the span a style attribute -->
  <!-- Specify class-string to give the span a class attribute -->
  <!-- Specify id-string to give the span an ID attribute -->
  <!-- beware: Docbook template containing span-wrap is not valid Docbook! -->
  <xsl:template match="span-wrap">
    <span>
      <xsl:if test="@style-string">
        <xsl:attribute name="style"><xsl:value-of select="@style-string"/></xsl:attribute>
      </xsl:if>
      <xsl:if test="@class-string">
        <xsl:attribute name="class"><xsl:value-of select="@class-string"/></xsl:attribute>
      </xsl:if>
      <xsl:if test="@id-string">
        <xsl:attribute name="id"><xsl:value-of select="@id-string"/></xsl:attribute>
      </xsl:if>
      <xsl:apply-templates/>
    </span>
  </xsl:template>
  
  <!-- IMD: to generate "linebreaks" on demand -->
  <xsl:template match="processing-instruction('linebreak')">
    <br/>
  </xsl:template>
  
  <!-- Suppress the title and info on the part and article -->
  <xsl:template match="/part/title"/>
  <xsl:template match="/part/info"/>
  

  <!-- Suppress the Part title -->
  <xsl:template name="part.titlepage"/>

</xsl:stylesheet>
