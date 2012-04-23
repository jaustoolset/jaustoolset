<?xml version='1.0'?>
    <!--
      Copyright 2010 - Jim Albers 
      Creative Commons Attribution-ShareAlike 3.0
      http://creativecommons.org/licenses/by-sa/3.0/us/
    -->
<!-- 
  
  Further edits 2010 by Ian Durkan, Progeny Systems Corp
  
  This transform turns Docbook representing a service definition into an XSL-FO document.
  It also consumes the non-Docbook elements produced by servdef_jsidl_db_cals.xsl to help
  with styling the XSL-FO for rendering as Word via XMLMind XFC.  
  
-->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:db="http://docbook.org/ns/docbook"
                xmlns:xfc="http://www.xmlmind.com/foconverter/xsl/extensions"
                version="1.0">
  <xsl:import href="fo/docbook.xsl"/>
  <!-- Our customized page masters for SAE goodness -->
  <xsl:import href="titlepage_fo.xsl"/>
  <xsl:import href="titlepage_SAE_fo.xsl"/>
  <!-- enable PDF bookmarks -->
  <xsl:param name="stylesheet.result.type" select="'fo'"/> 
  <xsl:param name="paper.type" select="'USLetter'"/>
  <xsl:param name="draft.watermark.image" select="'images/draft.png'"/>
  <!--<xsl:param name="body.start.indent" select="'0pt'"/>-->
  <!-- Page layout, single sized, no verso pages -->
  <xsl:param name="double.sided" >0</xsl:param>
  <xsl:param name="page.orientation" >portrait</xsl:param>
  <!-- Fonts -->
  <xsl:param name="body.font.family" >sans-serif</xsl:param>
  <xsl:param name="body.font.master" >10</xsl:param>
  <xsl:param name="title.font.family" >sans-serif</xsl:param>
  <xsl:param name="footnote.font.size" >9pt</xsl:param>
  <!-- No hyphenation -->
  <xsl:param name="hyphenate" select="'false'"/>
  <!-- IMD: supposedly this turns off page numbering for xrefs but doesn't seem to do anything... -->
  <xsl:param name="insert.xref.page.number">no</xsl:param>

  <!-- TOC overrides, start with the stock fo/param.xsl definition -->
  <xsl:param name="toc.section.depth" select="'3'"/>
  <xsl:param name="toc.max.depth" select="'3'"/>
  <xsl:param name="generate.toc">
    part/article          toc,title,figure,table
  </xsl:param>
  <!-- The following are necessary to get the additional SAE TOC info generated - IMD: don't seem to exist in 
    any stylesheet... -->
  <!-- <xsl:param name="generate.division.figure.lot" select="1" />  
  <xsl:param name="generate.division.table.lot" select="1" /> -->  

  <!-- Figure/Example/Equation properties 10pt title, centered -->
  <xsl:attribute-set name="formal.title.properties">
    <xsl:attribute name="font-weight">normal</xsl:attribute>
    <xsl:attribute name="text-align">center</xsl:attribute>
    <xsl:attribute name="font-size"><xsl:value-of select="concat($body.font.master,'pt')"/></xsl:attribute>
  </xsl:attribute-set>
  
  <!-- title placement settings -->
  <xsl:param name="formal.title.placement">
    figure after
    example before
    equation after
    table before
    procedure before
  </xsl:param>
  

  <!-- Fix up the section title styles -->
  <xsl:attribute-set name="component.title.properties">
    <xsl:attribute name="font-weight">normal</xsl:attribute>
    <xsl:attribute name="font-size">10pt</xsl:attribute>
  </xsl:attribute-set>
  
  <!-- IMD: need a bit more space above section titles -->
  <xsl:attribute-set name="section.title.properties">
    <xsl:attribute name="font-weight">normal</xsl:attribute>
    <!-- font size is calculated dynamically by section.heading template -->
    <xsl:attribute name="keep-with-next.within-column">always</xsl:attribute>
    <xsl:attribute name="text-align">left</xsl:attribute>
    <xsl:attribute name="space-before.minimum">14pt</xsl:attribute>
    <xsl:attribute name="space-before.optimum">14pt</xsl:attribute>
    <xsl:attribute name="space-before.maximum">14pt</xsl:attribute>
  </xsl:attribute-set>
  
  <!-- some other section settings -->
  <xsl:param name="section.autolabel" select="'1'"/>
  <xsl:param name="section.label.includes.component.label" select="'1'"/>
  <xsl:param name="section.autolabel.maxdepth" select="'8'"/>
  
  <xsl:attribute-set name="simplesect.title.properties">
    <xsl:attribute name="font-weight">normal</xsl:attribute>
    <xsl:attribute name="font-size">10pt</xsl:attribute>
    <xsl:attribute name="text-align">center</xsl:attribute>
  </xsl:attribute-set>
  <xsl:attribute-set name="section.title.level1.properties">
    <xsl:attribute name="font-weight">normal</xsl:attribute>
    <xsl:attribute name="font-size">10pt</xsl:attribute>
  </xsl:attribute-set>
  <xsl:attribute-set name="section.title.level2.properties">
    <xsl:attribute name="font-weight">normal</xsl:attribute>
    <xsl:attribute name="font-size">10pt</xsl:attribute>
  </xsl:attribute-set>
  <xsl:attribute-set name="section.title.level3.properties">
    <xsl:attribute name="font-weight">normal</xsl:attribute>
    <xsl:attribute name="font-size">10pt</xsl:attribute>
  </xsl:attribute-set>
  <xsl:attribute-set name="section.title.level4.properties">
    <xsl:attribute name="font-weight">normal</xsl:attribute>
    <xsl:attribute name="font-size">10pt</xsl:attribute>
  </xsl:attribute-set>
  <xsl:attribute-set name="section.title.level5.properties">
    <xsl:attribute name="font-weight">normal</xsl:attribute>
    <xsl:attribute name="font-size">10pt</xsl:attribute>
  </xsl:attribute-set>
  
  <!-- end section title space customizations -->

  <!-- Some special character handling per SAE template, uses <emphasis role="<special>"/> -->
  <xsl:template match="emphasis[@role='bold']">
    <fo:inline font-weight="bold">
      <xsl:apply-templates/>
    </fo:inline>
  </xsl:template>
  
  <!-- IMD: simple way to add underline to links, can't figure out what to customize 
    in Docbook XSL package to change XSL-FO results from <link>s in docbook -->
  <xsl:template match="emphasis[@role='hyperlink']">
    <fo:inline color="blue" text-decoration="underline">
      <xsl:apply-templates/>
    </fo:inline>
  </xsl:template>

  <xsl:template match="emphasis[@role='optionalfield']">
    <fo:inline font-style="italic" font-weight="bold">
      <xsl:apply-templates/>
    </fo:inline>
  </xsl:template>

  <xsl:template match="emphasis[@role='saedoctype']">
    <fo:inline font-size="18pt" font-weight="bold">
      <xsl:apply-templates/>
    </fo:inline>
  </xsl:template>
  
  <!-- IMD: gives an easier way to explicitly ask for a specific font size. -->
  <xsl:template match="font-resize">
    <fo:inline font-size="{@font-size}">
      <xsl:apply-templates/>
    </fo:inline>
  </xsl:template>

  <!-- suppress the Part title -->
  <xsl:template name="part.titlepage"/>
  
  <!-- IMD: suppress the Article titlepage. -->
  <xsl:template name="article.titlepage"/>

  <!-- override the default section title format to remove trailing space, add more space as a leader? -->

  <!-- to generate SAE hard pagebreaks -->
  <xsl:template match="processing-instruction('hard-pagebreak')">
    <fo:block break-before='page'/>
  </xsl:template>
  
  <!-- IMD: to generate "linebreaks" on demand -->
  <xsl:template match="processing-instruction('linebreak')">
    <fo:block/>
  </xsl:template>

  <!-- override default of 1.2em -->
  <xsl:param name="orderedlist.label.width">0.5in</xsl:param>

  <!-- SAE equation titles are of the form: (Eq. <eqnum>) -->
  <xsl:template match="equation/title">
    <!-- TODO, needs more research, need to run a leader out to left margin. -->
  </xsl:template>

  <!-- set off verbatim text like <programlisting/> -->
  <xsl:attribute-set name="monospace.verbatim.properties">
    <xsl:attribute name="font-size">
      <xsl:choose>
        <xsl:when test="processing-instruction('db-font-size')">
          <xsl:value-of
              select="processing-instruction('db-font-size')"/>
        </xsl:when>
        <xsl:otherwise>inherit</xsl:otherwise>
      </xsl:choose>
    </xsl:attribute>
  </xsl:attribute-set>
  <xsl:attribute-set name="shade.verbatim.style">
    <xsl:attribute name="border-style">solid</xsl:attribute>
    <xsl:attribute name="border-color">black</xsl:attribute>
    <xsl:attribute name="border-width">1pt</xsl:attribute>
    <xsl:attribute name="background-color">#E0E0E0</xsl:attribute>
    <xsl:attribute name="padding-left">12pt</xsl:attribute>
    <xsl:attribute name="padding-right">12pt</xsl:attribute>
    <xsl:attribute name="padding-top">6pt</xsl:attribute>
    <xsl:attribute name="padding-bottom">6pt</xsl:attribute>
    <xsl:attribute name="margin-left">0pt</xsl:attribute>
    <xsl:attribute name="margin-right">0pt</xsl:attribute>
    
   
  </xsl:attribute-set>

  <!-- override use of square brackets in biblio entry labels, change sep from '.' to ',' -->
  <xsl:param name="biblioentry.item.separator">, </xsl:param>
  <xsl:template name="biblioentry.label">
    <xsl:param name="node" select="."/>
    <fo:leader leader-length='1.0in'
               leader-pattern='use-content'
               leader-pattern-width='1.0in'>
      <xsl:choose>
        <xsl:when test="$bibliography.numbered != 0">
          <xsl:number from="bibliography" count="biblioentry|bibliomixed"
                      level="any" format="1"/>
          <xsl:text> </xsl:text>
        </xsl:when>
        <xsl:when test="local-name($node/child::*[1]) = 'abbrev'">
          <xsl:apply-templates select="$node/abbrev[1]"/>
          <xsl:text> </xsl:text>
        </xsl:when>
        <xsl:when test="$node/@xreflabel">
          <xsl:value-of select="$node/@xreflabel"/>
          <xsl:text> </xsl:text>
        </xsl:when>
        <xsl:when test="$node/@id or $node/@xml:id">
          <xsl:value-of select="($node/@id|$node/@xml:id)[1]"/>
          <xsl:text> </xsl:text>
        </xsl:when>
        <xsl:otherwise><!-- nop --></xsl:otherwise>
      </xsl:choose>
    </fo:leader>
  </xsl:template>

  <!-- Table of Contents overrides! -->
  
  <!-- IMD: generate correct amount of space surrouning TOC title. -->
  <xsl:template name="table.of.contents.titlepage" priority="1">
    <fo:block xsl:use-attribute-sets="section.title.level1.properties"
      text-align="center"
      space-before="12pt"
      space-after="12pt">
      <xsl:call-template name="gentext">
        <xsl:with-param name="key" select="'TableofContents'"/>
      </xsl:call-template>
    </fo:block>
  </xsl:template>
  
  <!-- All section numbers are left aligned and not indented -->
   <xsl:param name="toc.indent.width" select="0"/> 
  <!-- No trailing dots on SAE style -->
  <xsl:param name="autotoc.label.separator" select="''"/>
  <xsl:attribute-set name="toc.line.properties">
    <xsl:attribute name="text-align-last">justify</xsl:attribute>
    <xsl:attribute name="text-align">start</xsl:attribute>
  </xsl:attribute-set> 

  <!-- Tweak biblio entry to set off labels. SAE doesn't use square brackets -->
  <xsl:attribute-set name="biblioentry.properties" use-attribute-sets="normal.para.spacing">
    <xsl:attribute name="start-indent">1.0in</xsl:attribute>
    <xsl:attribute name="text-indent">-1.0in</xsl:attribute>
  </xsl:attribute-set>
  
  
  <!-- IMD: the title for the table of contents is in the localization XML data, common/en.xml -->
  <xsl:param name="local.l10n.xml" select="document('')"/> 
  <l:i18n xmlns:l="http://docbook.sourceforge.net/xmlns/l10n/1.0"> 
    <l:l10n language="en"> 
      <l:gentext key="TableofContents" text="TABLE OF CONTENTS"/>
    </l:l10n>
  </l:i18n>
  
  
  <!-- IMD: try changing TOC page margins according to how the DB XSL manual says to do it. -->
  <!-- <xsl:attribute-set name="toc.margin.properties">
    <xsl:attribute name="start-indent">0.5in</xsl:attribute>
  </xsl:attribute-set>
  
  
  <xsl:attribute-set name="toc.line.properties">
    <xsl:attribute name="text-align-last">justify</xsl:attribute>
    <xsl:attribute name="text-align">start</xsl:attribute>
    <xsl:attribute name="end-indent">1.25in</xsl:attribute>
    <xsl:attribute name="last-line-end-indent">-0.25in</xsl:attribute>
  </xsl:attribute-set> -->

  <!-- Override template in autotoc.xsl to get SAE toc.line format -->
   <xsl:template name="toc.line.prefix">
    <xsl:param name="label" select="''"/>
    <xsl:choose>
      <xsl:when test="name()='appendix'">
        <xsl:text>APPENDIX</xsl:text>
      </xsl:when>
      <xsl:when test="name()='figure'">
        <xsl:text>FIGURE</xsl:text>
      </xsl:when>
      <xsl:when test="name()='table'">
        <xsl:text>TABLE</xsl:text>
      </xsl:when>
    </xsl:choose>
    <xsl:text> </xsl:text>
    <xsl:if test="$label != ''">
      <xsl:copy-of select="$label"/>
    </xsl:if>
    <xsl:if test="parent::article"> 
      <!-- trailing dot only for top level sections -->
      <xsl:text>.</xsl:text>
    </xsl:if>
  </xsl:template>
 
  <!-- override the section object.title.template to get rid of
       trailing '.' on section number.
       TODO: add the variable length %n+space width
       based on section depth w/ fo:leader.
  -->
  <xsl:template match="section" mode="object.title.template">
    <xsl:text>%n</xsl:text>
    <xsl:if test="parent::article">
      <!-- trailing dot only for top level sections -->
      <xsl:text>.</xsl:text>
    </xsl:if>
    <xsl:text> %t</xsl:text>
  </xsl:template>


  <xsl:template name="toc.line">
    <xsl:param name="toc-context" select="NOTANODE"/>

    <xsl:variable name="id">
      <xsl:call-template name="object.id"/>
    </xsl:variable>

    <xsl:variable name="label">
      <xsl:apply-templates select="." mode="label.markup"/>
    </xsl:variable>

    <fo:block xsl:use-attribute-sets="toc.line.properties"
              end-indent="{$toc.indent.width}pt"
              last-line-end-indent="-{$toc.indent.width}pt">
      <fo:inline keep-with-next.within-line="always">
        <fo:basic-link internal-destination="{$id}">
          <xsl:call-template name="toc.line.prefix">
            <xsl:with-param name="label" select="$label"/>
          </xsl:call-template>
          <fo:leader xfc:tab-position="1.0in"/>
          <xsl:apply-templates select="." mode="titleabbrev.markup"/>
        </fo:basic-link>
      </fo:inline>
      <fo:inline keep-together.within-line="always">
        <xsl:text> </xsl:text>
        <fo:leader leader-pattern="dots" leader-pattern-width="3pt" xfc:tab-position="7.0in"/>
        <xsl:text> </xsl:text> 
        <fo:basic-link internal-destination="{$id}">
          <fo:page-number-citation ref-id="{$id}"/>
        </fo:basic-link>
      </fo:inline>
    </fo:block>
  </xsl:template>

  <!-- replaces list.of.titles in autotoc.xsl, adding space around the table-of-tables, 
    table-of-figures, etc.  Adds title to TOT and TOF also -->
  <xsl:template name="list.of.titles">
    <xsl:param name="titles" select="'table'"/>
    <xsl:param name="nodes" select=".//table"/>
    <xsl:param name="toc-context" select="."/>
    
    <xsl:variable name="id">
      <xsl:call-template name="object.id"/>
    </xsl:variable>
    
    <xsl:if test="$nodes">
      <fo:block space-before.minimum="0.5em" space-before.optimum="1em" space-before.maximum="2em"
        space-after.minimum="0.5em" space-after.optimum="1em" space-after.maximum="2em"
        id="lot...{$titles}...{$id}">
        <xsl:choose>
          <xsl:when test="$titles='table'">
            <fo:block space-before.minimum="2em" space-before.optimum="2em"
              space-before.maximum="2em" space-after="1.5em" margin-left="0pt" start-indent="0pt"
              font-size="10pt" text-align="center">
              TABLE OF TABLES</fo:block>
            <xsl:call-template name="list.of.tables.titlepage"/>
          </xsl:when>
          <xsl:when test="$titles='figure'">
            <fo:block space-before.minimum="2em" space-before.optimum="2em"
              space-before.maximum="2em" space-after="1.5em" margin-left="0pt" start-indent="0pt"
              font-size="10pt" text-align="center">
              TABLE OF FIGURES</fo:block>
            <xsl:call-template name="list.of.figures.titlepage"/>
          </xsl:when>
          <xsl:when test="$titles='equation'">
            <xsl:call-template name="list.of.equations.titlepage"/>
          </xsl:when>
          <xsl:when test="$titles='example'">
            <xsl:call-template name="list.of.examples.titlepage"/>
          </xsl:when>
          <xsl:when test="$titles='procedure'">
            <xsl:call-template name="list.of.procedures.titlepage"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:call-template name="list.of.unknowns.titlepage"/>
          </xsl:otherwise>
        </xsl:choose>
        <xsl:apply-templates select="$nodes" mode="toc">
          <xsl:with-param name="toc-context" select="$toc-context"/>
        </xsl:apply-templates>
      </fo:block>
    </xsl:if>
  </xsl:template>
  
  <!-- end TOC customization overrides! -->
  
  <!-- IMD: add tab to section titles.  section.heading is in sections.xsl originally -->
  <xsl:template name="section.heading">
    <xsl:param name="level" select="1"/>
    <xsl:param name="marker" select="1"/>
    <xsl:param name="title"/>
    <xsl:param name="marker.title"/>
    
    <!-- need to add a tab between the section label ('1.2.3') and section title -->
    <!-- however some sections don't have section labels automatically added to the title -->    
    <xsl:variable name="separated-title">
      <xsl:variable name="before-space">
        <xsl:value-of select="substring-before($title, ' ')"/>
      </xsl:variable>
      <xsl:choose>
        <xsl:when test="$before-space != ''">
          <xsl:choose>
            <!-- see if the part-before-space consists only of characters in set [.1234567890] -->
            <!-- i.e., is it a section label like '1.2.3' or '1.' -->
            <!-- if it is, eliminate it from the title. -->
            <xsl:when test="translate($before-space, '.1234567890', '') = ''">
              <xsl:value-of select="$before-space"/>
              <fo:leader xfc:tab-position="0.38in"/>
              <xsl:value-of select="substring-after($title, ' ')"/>
            </xsl:when>
            <xsl:otherwise>
              <xsl:value-of select="$title"/>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="$title"/>          
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
      
    <fo:block xsl:use-attribute-sets="section.title.properties">
      <xsl:if test="$marker != 0">
        <fo:marker marker-class-name="section.head.marker">
          <xsl:copy-of select="$marker.title"/>
        </fo:marker>
      </xsl:if>
      
      <xsl:choose>
        <xsl:when test="ancestor::simplesect">
          <fo:block xsl:use-attribute-sets="simplesect.title.properties">
            <xsl:copy-of select="$separated-title"/>
          </fo:block>
        </xsl:when>
        <xsl:when test="$level=1">
          <fo:block xsl:use-attribute-sets="section.title.level1.properties">
            <xsl:copy-of select="$separated-title"/>
          </fo:block>
        </xsl:when>
        <xsl:when test="$level=2">
          <fo:block xsl:use-attribute-sets="section.title.level2.properties">
            <xsl:copy-of select="$separated-title"/>
          </fo:block>
        </xsl:when>
        <xsl:when test="$level=3">
          <fo:block xsl:use-attribute-sets="section.title.level3.properties">
            <xsl:copy-of select="$separated-title"/>
          </fo:block>
        </xsl:when>
        <xsl:when test="$level=4">
          <fo:block xsl:use-attribute-sets="section.title.level4.properties">
            <xsl:copy-of select="$separated-title"/>
          </fo:block>
        </xsl:when>
        <xsl:when test="$level=5">
          <fo:block xsl:use-attribute-sets="section.title.level5.properties">
            <xsl:copy-of select="$separated-title"/>
          </fo:block>
        </xsl:when>
        <xsl:otherwise>
          <fo:block xsl:use-attribute-sets="section.title.level6.properties">
            <xsl:copy-of select="$separated-title"/>
          </fo:block>
        </xsl:otherwise>
      </xsl:choose>
    </fo:block>
  </xsl:template>
  
  <!-- IMD: customize tables to be centered on the page instead of left-justified -->
  <xsl:template name="table.layout">
    <xsl:param name="table.content"/>
    
    <fo:table-and-caption text-align="center">
      <xsl:copy-of select="$table.content"/>
    </fo:table-and-caption>
    
  </xsl:template>
  
  <!-- ==========================================================================
       SPECIAL HEADER / FOOTER HANDLING FOR SAE
       ======================================================================= -->

  <xsl:attribute-set name="footer.content.properties">
    <xsl:attribute name="font-size">7pt</xsl:attribute>
  </xsl:attribute-set>

  <xsl:attribute-set name="header.content.properties">
    <xsl:attribute name="font-size">12pt</xsl:attribute>
  </xsl:attribute-set>

  <!-- We will disable header and header.rule on titlepage and
       titlepage-draft pageclasses, will use the large footer table per
       SAE boilerplate -->
  
  <!-- CUSTOM titlepage SAE boilerplate Footer, used in
       sae-titlepage-* pagemasters -->
  <xsl:param name="titlepage.region.before.extent" >0in</xsl:param>
  <xsl:param name="titlepage.body.margin.top" >0in</xsl:param>
  <xsl:param name="titlepage.body.margin.bottom" >1.75in</xsl:param>
  <xsl:param name="titlepage.region.after.extent" >1.75in</xsl:param>

  <!-- Normal SAE document Header/Footer -->
  <xsl:param name="header.rule" select="'1'"/>
  <xsl:param name="footer.rule" select="0"/>
  <xsl:param name="header.column.widths">1 1 1</xsl:param>
  <xsl:param name="footer.column.widths">1 1 1</xsl:param>
  <xsl:param name="page.margin.top">0.5in</xsl:param>
  <xsl:param name="body.margin.top" >0.33in</xsl:param>
  <xsl:param name="region.before.extent" >0.5in</xsl:param>
  <xsl:param name="body.margin.bottom" >0.5in</xsl:param>
  <xsl:param name="region.after.extent" >0.5in</xsl:param>
  <xsl:param name="page.margin.bottom">0.5in</xsl:param>
  <xsl:param name="body.start.indent">0pt</xsl:param>
  <xsl:param name="margin.left.outer">0pt</xsl:param>
  <xsl:param name="page.margin.inner">0.5in</xsl:param>
  <xsl:param name="page.margin.outer">0.5in</xsl:param>
  
  <!-- Header and Footer Formatting -->

  <!-- Override header.table from pagesetup.xsl --> 
  <xsl:template name="header.table">
    <xsl:param name="pageclass" select="''"/>
    <xsl:param name="sequence" select="''"/>
    <xsl:param name="gentext-key" select="''"/>

    <!-- Customized to produce nothing for sae-titlepage-* -->
    <xsl:choose>
      <xsl:when test="$pageclass = 'titlepage'">
        <!-- return nothing -->
      </xsl:when>
      <xsl:otherwise>
        <!-- proceed exactly as with pagesetup.xsl header.table,
             copied verbatim, below -->
        <xsl:choose>
          <xsl:when test="$pageclass = 'index'">
            <xsl:attribute name="margin-left">0pt</xsl:attribute>
          </xsl:when>
        </xsl:choose>

        <xsl:variable name="column1">
          <xsl:choose>
            <xsl:when test="$double.sided = 0">1</xsl:when>
            <xsl:when test="$sequence = 'first' or $sequence = 'odd'">1</xsl:when>
            <xsl:otherwise>3</xsl:otherwise>
          </xsl:choose>
        </xsl:variable>

        <xsl:variable name="column3">
          <xsl:choose>
            <xsl:when test="$double.sided = 0">3</xsl:when>
            <xsl:when test="$sequence = 'first' or $sequence = 'odd'">3</xsl:when>
            <xsl:otherwise>1</xsl:otherwise>
          </xsl:choose>
        </xsl:variable>

        <xsl:variable name="candidate">
          <fo:table table-layout="fixed" width="100%">
            <xsl:call-template name="head.sep.rule">
              <xsl:with-param name="pageclass" select="$pageclass"/>
              <xsl:with-param name="sequence" select="$sequence"/>
              <xsl:with-param name="gentext-key" select="$gentext-key"/>
            </xsl:call-template>

            <fo:table-column column-number="1">
              <xsl:attribute name="column-width">
                <xsl:text>proportional-column-width(</xsl:text>
                <xsl:call-template name="header.footer.width">
                  <xsl:with-param name="location">header</xsl:with-param>
                  <xsl:with-param name="position" select="$column1"/>
                </xsl:call-template>
                <xsl:text>)</xsl:text>
              </xsl:attribute>
            </fo:table-column>
            <fo:table-column column-number="2">
              <xsl:attribute name="column-width">
                <xsl:text>proportional-column-width(</xsl:text>
                <xsl:call-template name="header.footer.width">
                  <xsl:with-param name="location">header</xsl:with-param>
                  <xsl:with-param name="position" select="2"/>
                </xsl:call-template>
                <xsl:text>)</xsl:text>
              </xsl:attribute>
            </fo:table-column>
            <fo:table-column column-number="3">
              <xsl:attribute name="column-width">
                <xsl:text>proportional-column-width(</xsl:text>
                <xsl:call-template name="header.footer.width">
                  <xsl:with-param name="location">header</xsl:with-param>
                  <xsl:with-param name="position" select="$column3"/>
                </xsl:call-template>
                <xsl:text>)</xsl:text>
              </xsl:attribute>
            </fo:table-column>

            <fo:table-body>
              <fo:table-row>
                <xsl:attribute name="block-progression-dimension.minimum">
                  <xsl:value-of select="$header.table.height"/>
                </xsl:attribute>
                <fo:table-cell text-align="left"
                               display-align="before">
                  <xsl:if test="$fop.extensions = 0">
                    <xsl:attribute name="relative-align">baseline</xsl:attribute>
                  </xsl:if>
                  <fo:block>
                    <xsl:call-template name="header.content">
                      <xsl:with-param name="pageclass" select="$pageclass"/>
                      <xsl:with-param name="sequence" select="$sequence"/>
                      <xsl:with-param name="position" select="'left'"/>
                      <xsl:with-param name="gentext-key" select="$gentext-key"/>
                    </xsl:call-template>
                  </fo:block>
                </fo:table-cell>
                <fo:table-cell text-align="center"
                               display-align="before">
                  <xsl:if test="$fop.extensions = 0">
                    <xsl:attribute name="relative-align">baseline</xsl:attribute>
                  </xsl:if>
                  <fo:block>
                    <xsl:call-template name="header.content">
                      <xsl:with-param name="pageclass" select="$pageclass"/>
                      <xsl:with-param name="sequence" select="$sequence"/>
                      <xsl:with-param name="position" select="'center'"/>
                      <xsl:with-param name="gentext-key" select="$gentext-key"/>
                    </xsl:call-template>
                  </fo:block>
                </fo:table-cell>
                <fo:table-cell text-align="right"
                               display-align="before">
                  <xsl:if test="$fop.extensions = 0">
                    <xsl:attribute name="relative-align">baseline</xsl:attribute>
                  </xsl:if>
                  <fo:block>
                    <xsl:call-template name="header.content">
                      <xsl:with-param name="pageclass" select="$pageclass"/>
                      <xsl:with-param name="sequence" select="$sequence"/>
                      <xsl:with-param name="position" select="'right'"/>
                      <xsl:with-param name="gentext-key" select="$gentext-key"/>
                    </xsl:call-template>
                  </fo:block>
                </fo:table-cell>
              </fo:table-row>
            </fo:table-body>
          </fo:table>
        </xsl:variable>

        <!-- Really output a header? -->
        <xsl:choose>
          <xsl:when test="$pageclass = 'titlepage' and $gentext-key = 'book'
                          and $sequence='first'">
            <!-- no, book titlepages have no headers at all -->
          </xsl:when>
          <xsl:when test="$sequence = 'blank' and $headers.on.blank.pages = 0">
            <!-- no output -->
          </xsl:when>
          <xsl:otherwise>
            <xsl:copy-of select="$candidate"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  <!-- End Override header.table from pagesetup.xsl -->

  <!-- Override header.content from pagesetup.xsl -->
  <xsl:template name="header.content">
    <xsl:param name="pageclass" select="''"/>
    <xsl:param name="sequence" select="''"/>
    <xsl:param name="position" select="''"/>
    <xsl:param name="gentext-key" select="''"/>

    <!--
        <fo:block>
        <xsl:value-of select="$pageclass"/>
        <xsl:text>, </xsl:text>
        <xsl:value-of select="$sequence"/>
        <xsl:text>, </xsl:text>
        <xsl:value-of select="$position"/>
        <xsl:text>, </xsl:text>
        <xsl:value-of select="$gentext-key"/>
        </fo:block>
    -->

    <fo:block>
      <!-- sequence can be odd, even, first, blank -->
      <!-- position can be left, center, right -->
      <xsl:choose>
        <xsl:when test="$sequence = 'blank'">
          <!-- nothing for blank pages -->
        </xsl:when>

        <xsl:when test="$sequence = 'first'">
          <!-- nothing for first pages -->
        </xsl:when>

        <xsl:otherwise>
          <xsl:choose>
            <xsl:when test="$position='left'">
              <!-- Same for odd, even, empty, and blank sequences -->
              <xsl:value-of select="//releaseinfo[@role = 'tla']/text()"/>
            </xsl:when>

            <xsl:when test="$position='center'">
              <xsl:value-of select="//productnumber/text()"/>
            </xsl:when>

            <xsl:when test="$position='right'">
              <!-- Same for odd, even, empty, and blank sequences -->
              <xsl:text> - </xsl:text>
              <fo:page-number/>
              <xsl:text> - </xsl:text>
            </xsl:when>
          </xsl:choose>
        </xsl:otherwise>
      </xsl:choose>
    </fo:block>
  </xsl:template>
  <!-- End override header.content from pagesetup.xsl -->

  <!-- Override footer.table from pagesetup.xsl -->
  <xsl:template name="footer.table">
    <xsl:param name="pageclass" select="''"/>
    <xsl:param name="sequence" select="''"/>
    <xsl:param name="gentext-key" select="''"/>

    <xsl:choose>
      <xsl:when test="$pageclass = 'sae-titlepage' and $sequence = 'first'">
        <xsl:call-template name="saefooter.table"/>
      </xsl:when>
      <xsl:otherwise>
        <!-- following verbatim footer.table from pagesetup.xsl -->
        <xsl:choose>
          <xsl:when test="$pageclass = 'index'">
            <xsl:attribute name="margin-left">0pt</xsl:attribute>
          </xsl:when>
        </xsl:choose>

        <xsl:variable name="column1">
          <xsl:choose>
            <xsl:when test="$double.sided = 0">1</xsl:when>
            <xsl:when test="$sequence = 'first' or $sequence = 'odd'">1</xsl:when>
            <xsl:otherwise>3</xsl:otherwise>
          </xsl:choose>
        </xsl:variable>

        <xsl:variable name="column3">
          <xsl:choose>
            <xsl:when test="$double.sided = 0">3</xsl:when>
            <xsl:when test="$sequence = 'first' or $sequence = 'odd'">3</xsl:when>
            <xsl:otherwise>1</xsl:otherwise>
          </xsl:choose>
        </xsl:variable>

        <xsl:variable name="candidate">
          <fo:table table-layout="fixed" width="100%">
            <xsl:call-template name="foot.sep.rule">
              <xsl:with-param name="pageclass" select="$pageclass"/>
              <xsl:with-param name="sequence" select="$sequence"/>
              <xsl:with-param name="gentext-key" select="$gentext-key"/>
            </xsl:call-template>
            <fo:table-column column-number="1">
              <xsl:attribute name="column-width">
                <xsl:text>proportional-column-width(</xsl:text>
                <xsl:call-template name="header.footer.width">
                  <xsl:with-param name="location">footer</xsl:with-param>
                  <xsl:with-param name="position" select="$column1"/>
                </xsl:call-template>
                <xsl:text>)</xsl:text>
              </xsl:attribute>
            </fo:table-column>
            <fo:table-column column-number="2">
              <xsl:attribute name="column-width">
                <xsl:text>proportional-column-width(</xsl:text>
                <xsl:call-template name="header.footer.width">
                  <xsl:with-param name="location">footer</xsl:with-param>
                  <xsl:with-param name="position" select="2"/>
                </xsl:call-template>
                <xsl:text>)</xsl:text>
              </xsl:attribute>
            </fo:table-column>
            <fo:table-column column-number="3">
              <xsl:attribute name="column-width">
                <xsl:text>proportional-column-width(</xsl:text>
                <xsl:call-template name="header.footer.width">
                  <xsl:with-param name="location">footer</xsl:with-param>
                  <xsl:with-param name="position" select="$column3"/>
                </xsl:call-template>
                <xsl:text>)</xsl:text>
              </xsl:attribute>
            </fo:table-column>

            <fo:table-body>
              <fo:table-row>
                <xsl:attribute name="block-progression-dimension.minimum">
                  <xsl:value-of select="$footer.table.height"/>
                </xsl:attribute>
                <fo:table-cell text-align="left"
                               display-align="after">
                  <xsl:if test="$fop.extensions = 0">
                    <xsl:attribute name="relative-align">baseline</xsl:attribute>
                  </xsl:if>
                  <fo:block>
                    <xsl:call-template name="footer.content">
                      <xsl:with-param name="pageclass" select="$pageclass"/>
                      <xsl:with-param name="sequence" select="$sequence"/>
                      <xsl:with-param name="position" select="'left'"/>
                      <xsl:with-param name="gentext-key" select="$gentext-key"/>
                    </xsl:call-template>
                  </fo:block>
                </fo:table-cell>
                <fo:table-cell text-align="center"
                               display-align="after">
                  <xsl:if test="$fop.extensions = 0">
                    <xsl:attribute name="relative-align">baseline</xsl:attribute>
                  </xsl:if>
                  <fo:block>
                    <xsl:call-template name="footer.content">
                      <xsl:with-param name="pageclass" select="$pageclass"/>
                      <xsl:with-param name="sequence" select="$sequence"/>
                      <xsl:with-param name="position" select="'center'"/>
                      <xsl:with-param name="gentext-key" select="$gentext-key"/>
                    </xsl:call-template>
                  </fo:block>
                </fo:table-cell>
                <fo:table-cell text-align="right"
                               display-align="after">
                  <xsl:if test="$fop.extensions = 0">
                    <xsl:attribute name="relative-align">baseline</xsl:attribute>
                  </xsl:if>
                  <fo:block>
                    <xsl:call-template name="footer.content">
                      <xsl:with-param name="pageclass" select="$pageclass"/>
                      <xsl:with-param name="sequence" select="$sequence"/>
                      <xsl:with-param name="position" select="'right'"/>
                      <xsl:with-param name="gentext-key" select="$gentext-key"/>
                    </xsl:call-template>
                  </fo:block>
                </fo:table-cell>
              </fo:table-row>
            </fo:table-body>
          </fo:table>
        </xsl:variable>

        <!-- Really output a footer? -->
        <xsl:choose>
          <xsl:when test="$pageclass='titlepage' and $gentext-key='book'
                          and $sequence='first'">
            <!-- no, book titlepages have no footers at all -->
          </xsl:when>
          <xsl:when test="$sequence = 'blank' and $footers.on.blank.pages = 0">
            <!-- no output -->
          </xsl:when>
          <xsl:otherwise>
            <xsl:copy-of select="$candidate"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  <!-- End override footer.table from pagesetup.xsl -->

  <!-- Override footer.content from pagesetup.xsl -->
  <xsl:template name="footer.content">
    <xsl:param name="pageclass" select="''"/>
    <xsl:param name="sequence" select="''"/>
    <xsl:param name="position" select="''"/>
    <xsl:param name="gentext-key" select="''"/>

    <!--
        <fo:block font-size="7pt">
        <xsl:value-of select="$pageclass"/>
        <xsl:text>, </xsl:text>
        <xsl:value-of select="$sequence"/>
        <xsl:text>, </xsl:text>
        <xsl:value-of select="$position"/>
        <xsl:text>, </xsl:text>
        <xsl:value-of select="$gentext-key"/>
        </fo:block>
    -->
    <!-- pageclass can be front, body, back -->
    <!-- sequence can be odd, even, first, blank -->
    <!-- position can be left, center, right -->
    <!-- NOTHING FOR NOW, LATER, SERVICE NAME -->

  </xsl:template>

  <xsl:template name="user.pagemasters">
    <!-- title pages -->
    <fo:simple-page-master master-name="sae-titlepage-first"
                           page-width="{$page.width}"
                           page-height="{$page.height}"
                           margin-top="{$page.margin.top}"
                           margin-bottom="{$page.margin.bottom}"
                           margin-left="{$margin.left.inner}"
                           margin-right="{$page.margin.outer}">
      <xsl:if test="$axf.extensions != 0">
        <xsl:call-template name="axf-page-master-properties">
          <xsl:with-param name="page.master">blank</xsl:with-param>
        </xsl:call-template>
      </xsl:if>
      <fo:region-body margin-bottom="{$titlepage.body.margin.bottom}"
                      margin-top="{$titlepage.body.margin.top}"
                      column-gap="{$column.gap.titlepage}"
                      column-count="{$column.count.titlepage}">
      </fo:region-body>
      <fo:region-before region-name="xsl-region-before-first"
                        extent="{$titlepage.region.before.extent}"
                        display-align="before"/>
      <fo:region-after region-name="xsl-region-after-first"
                       extent="{$titlepage.region.after.extent}"
                       display-align="after"/>
    </fo:simple-page-master>

    <fo:simple-page-master master-name="sae-titlepage-odd"
                           page-width="{$page.width}"
                           page-height="{$page.height}"
                           margin-top="{$page.margin.top}"
                           margin-bottom="{$page.margin.bottom}"
                           margin-left="{$margin.left.inner}"
                           margin-right="{$page.margin.outer}">
      <xsl:if test="$axf.extensions != 0">
        <xsl:call-template name="axf-page-master-properties">
          <xsl:with-param name="page.master">blank</xsl:with-param>
        </xsl:call-template>
      </xsl:if>
      <fo:region-body margin-bottom="{$body.margin.bottom}"
                      margin-top="{$body.margin.top}"
                      column-gap="{$column.gap.titlepage}"
                      column-count="{$column.count.titlepage}">
      </fo:region-body>
      <fo:region-before region-name="xsl-region-before-odd"
                        extent="{$region.before.extent}"
                        display-align="before"/>
      <fo:region-after region-name="xsl-region-after-odd"
                       extent="{$region.after.extent}"
                       display-align="after"/>
    </fo:simple-page-master>

    <fo:simple-page-master master-name="sae-titlepage-even"
                           page-width="{$page.width}"
                           page-height="{$page.height}"
                           margin-top="{$page.margin.top}"
                           margin-bottom="{$page.margin.bottom}"
                           margin-left="{$margin.left.outer}"
                           margin-right="{$page.margin.inner}">
      <xsl:if test="$axf.extensions != 0">
        <xsl:call-template name="axf-page-master-properties">
          <xsl:with-param name="page.master">blank</xsl:with-param>
        </xsl:call-template>
      </xsl:if>
      <fo:region-body margin-bottom="{$body.margin.bottom}"
                      margin-top="{$body.margin.top}"
                      column-gap="{$column.gap.titlepage}"
                      column-count="{$column.count.titlepage}">
      </fo:region-body>
      <fo:region-before region-name="xsl-region-before-even"
                        extent="{$region.before.extent}"
                        display-align="before"/>
      <fo:region-after region-name="xsl-region-after-even"
                       extent="{$region.after.extent}"
                       display-align="after"/>
    </fo:simple-page-master>

    <!-- setup for sae title page(s) -->
    <fo:page-sequence-master master-name="sae-titlepage">
      <fo:repeatable-page-master-alternatives>
        <fo:conditional-page-master-reference master-reference="blank"
                                              blank-or-not-blank="blank"/>
        <fo:conditional-page-master-reference master-reference="sae-titlepage-first"
                                              page-position="first"/>
        <fo:conditional-page-master-reference master-reference="sae-titlepage-odd"
                                              odd-or-even="odd"/>
        <fo:conditional-page-master-reference 
            odd-or-even="even">
          <xsl:attribute name="master-reference">
            <xsl:choose>
              <xsl:when test="$double.sided != 0">sae-titlepage-even</xsl:when>
              <xsl:otherwise>sae-titlepage-odd</xsl:otherwise>
            </xsl:choose>
          </xsl:attribute>
        </fo:conditional-page-master-reference>
      </fo:repeatable-page-master-alternatives>
    </fo:page-sequence-master>
  </xsl:template>

  <!-- Called by select.pagemaster to allow us to substitute a
       reference for our custom pagemaster(s) -->
  <xsl:template name="select.user.pagemaster">
    <xsl:param name="element"/>
    <xsl:param name="pageclass"/>
    <xsl:param name="default-pagemaster"/>

    <!-- Return SAE customized title page master name if for titlepage,
         otherwise return the default.  Use same pagemaster for both
         titlepage and titlepage-draft. -->

    <xsl:choose>
      <xsl:when test="$default-pagemaster = 'titlepage'
                      or $default-pagemaster = 'titlepage-draft'">
        <xsl:value-of select="'sae-titlepage'" />
      </xsl:when>
      <xsl:otherwise>
        <xsl:value-of select="$default-pagemaster"/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>

  <!-- Override default docbook indentation for toc entries, which was "{count(ancestor::*)*2}pc" -->
  <xsl:template match="tocpart|tocchap
                       |toclevel1|toclevel2|toclevel3|toclevel4|toclevel5">
    <xsl:apply-templates select="tocentry"/>
    <xsl:if test="tocchap|toclevel1|toclevel2|toclevel3|toclevel4|toclevel5">
      <fo:block start-indent="0pc">
        <xsl:apply-templates select="tocchap|toclevel1|toclevel2|toclevel3|toclevel4|toclevel5"/>
      </fo:block>
    </xsl:if>
  </xsl:template>
  
  <!-- override itemizedlist/listitem processing from lists.xsl to avoid wrapping listitem 
     contents in an extra <fo:block> (which causes rendering issue in XMLMind XFC)-->
  <xsl:template match="itemizedlist/listitem">
    <xsl:variable name="id"><xsl:call-template name="object.id"/></xsl:variable>
    
    <xsl:variable name="item.contents">
      <fo:list-item-label end-indent="label-end()" xsl:use-attribute-sets="itemizedlist.label.properties">
        <fo:block>
          <xsl:call-template name="itemizedlist.label.markup">
            <xsl:with-param name="itemsymbol">
              <xsl:call-template name="list.itemsymbol">
                <xsl:with-param name="node" select="parent::itemizedlist"/>
              </xsl:call-template>
            </xsl:with-param>
          </xsl:call-template>
        </fo:block>
      </fo:list-item-label>
      <fo:list-item-body start-indent="body-start()">
        <xsl:choose>
          <!-- * work around broken passivetex list-item-body rendering -->
          <xsl:when test="$passivetex.extensions = '1'">
            <xsl:apply-templates/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:apply-templates/>
          </xsl:otherwise>
        </xsl:choose>
      </fo:list-item-body>
    </xsl:variable>
    
    <xsl:choose>
      <xsl:when test="parent::*/@spacing = 'compact'">
        <fo:list-item id="{$id}" xsl:use-attribute-sets="compact.list.item.spacing">
          <xsl:copy-of select="$item.contents"/>
        </fo:list-item>
      </xsl:when>
      <xsl:otherwise>
        <fo:list-item id="{$id}" xsl:use-attribute-sets="list.item.spacing">
          <xsl:copy-of select="$item.contents"/>
        </fo:list-item>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  
  <!-- override orderedlist/listitem processing from lists.xsl to avoid wrapping listitem 
     contents in an extra <fo:block> (which causes rendering issue in XMLMind XFC)-->
  <xsl:template match="orderedlist/listitem">
    <xsl:variable name="id"><xsl:call-template name="object.id"/></xsl:variable>
    
    <xsl:variable name="item.contents">
      <fo:list-item-label end-indent="label-end()" xsl:use-attribute-sets="orderedlist.label.properties">
        <fo:block>
          <xsl:apply-templates select="." mode="item-number"/>
        </fo:block>
      </fo:list-item-label>
      <fo:list-item-body start-indent="body-start()">
        <xsl:apply-templates/>
      </fo:list-item-body>
    </xsl:variable>
    
    <xsl:choose>
      <xsl:when test="parent::*/@spacing = 'compact'">
        <fo:list-item id="{$id}" xsl:use-attribute-sets="compact.list.item.spacing">
          <xsl:copy-of select="$item.contents"/>
        </fo:list-item>
      </xsl:when>
      <xsl:otherwise>
        <fo:list-item id="{$id}" xsl:use-attribute-sets="list.item.spacing">
          <xsl:copy-of select="$item.contents"/>
        </fo:list-item>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  
  <!-- use hollow/white circle as bullet for itemized lists.
       Note: the standard bullet is &#x2022  -->
  <xsl:template name="itemizedlist.label.markup">
    <xsl:param name="itemsymbol" select="'disc'"/>
    
    <xsl:choose>
      <xsl:when test="$itemsymbol='none'"></xsl:when>
      <xsl:when test="$itemsymbol='disc'">&#x25CB;</xsl:when>
      <xsl:when test="$itemsymbol='bullet'">&#x25CB;</xsl:when>
      <xsl:when test="$itemsymbol='endash'">&#x2013;</xsl:when>
      <xsl:when test="$itemsymbol='emdash'">&#x2014;</xsl:when>
      <!-- Some of these may work in your XSL-FO processor and fonts -->
      <!--
        <xsl:when test="$itemsymbol='square'">&#x25A0;</xsl:when>
        <xsl:when test="$itemsymbol='box'">&#x25A0;</xsl:when>
        <xsl:when test="$itemsymbol='smallblacksquare'">&#x25AA;</xsl:when>
        <xsl:when test="$itemsymbol='circle'">&#x25CB;</xsl:when>
        <xsl:when test="$itemsymbol='opencircle'">&#x25CB;</xsl:when>
        <xsl:when test="$itemsymbol='whitesquare'">&#x25A1;</xsl:when>
        <xsl:when test="$itemsymbol='smallwhitesquare'">&#x25AB;</xsl:when>
        <xsl:when test="$itemsymbol='round'">&#x25CF;</xsl:when>
        <xsl:when test="$itemsymbol='blackcircle'">&#x25CF;</xsl:when>
        <xsl:when test="$itemsymbol='whitebullet'">&#x25E6;</xsl:when>
        <xsl:when test="$itemsymbol='triangle'">&#x2023;</xsl:when>
        <xsl:when test="$itemsymbol='point'">&#x203A;</xsl:when>
        <xsl:when test="$itemsymbol='hand'"><fo:inline 
        font-family="Wingdings 2">A</fo:inline></xsl:when>
      -->
      <xsl:otherwise>&#x2022;</xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  
  <!-- Suppress the title and info on the part and article -->
  <xsl:template match="/part/title"/>
  <xsl:template match="/part/info"/>
  
  <!-- override the page-number adding feature of xref FO output - doesn't
  seem to work properly when rendered using XMLMind XFC .docx output  -->
  <!-- lolwut -->  
  
  <!-- may want to apply the customization from 3 templates above to following templates, also
       from lists.xsl: 
       * varlistentry
       * procedure/step|substeps/step
       * list.item.spacing
       * callout
       -->
  
</xsl:stylesheet>
