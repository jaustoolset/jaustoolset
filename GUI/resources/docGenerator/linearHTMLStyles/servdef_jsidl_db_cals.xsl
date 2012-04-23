<?xml version="1.0" encoding="UTF-8"?>
    <!--
      Copyright 2010 - Jim Albers
      Creative Commons Attribution-ShareAlike 3.0
      http://creativecommons.org/licenses/by-sa/3.0/us/
    -->
	<!-- 
	  
	Further edits 2010 by Ian Durkan, Progeny Systems Corp
	
	This transform turns JSIDL service definitions, one service_def per file,
	into Docbook format.   
	
	However, there are also some non-Docbook elements output, such as <span-wrap>,
	that are intended to be consumed by docbook_cals_html.xsl to produce custom HTML output.
	
	-->
<xsl:stylesheet
    xmlns="http://docbook.org/ns/docbook"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
    xmlns:jaus="urn:jaus:jsidl:1.0"
    xmlns:x="http://www.w3.org/1999/xhtml">

  <!--
     
  -->

  <xsl:output method="xml" encoding="UTF-8" indent="yes"/>
  <xsl:strip-space elements="*"/>

  <xsl:template name="grey.bgcolor">
    <!-- LF to make Processing Instruction easier to read inline -->
    <xsl:text>
    </xsl:text>
    <!-- disabling for now. <xsl:processing-instruction name="dbfo">
      <xsl:text>bgcolor="#EEEEEE"</xsl:text>
    </xsl:processing-instruction> -->
    <xsl:text>
    </xsl:text>
  </xsl:template>

  <!-- Comment out the following to hide comments -->
  <xsl:template match="comment()|processing-instruction()">
    <p><xsl:value-of select="."/></p>
  </xsl:template>
  <!-- Comment out the above to hide comments -->  

  <!-- Main Processing -->
  <xsl:template match="/">
    <xsl:apply-templates select="jaus:service_def"/>
  </xsl:template>

  <xsl:template match="jaus:service_def">
    <xsl:variable name="sectname">
      <xsl:text>sect.</xsl:text>
      <xsl:value-of select="@name"/>
      <xsl:text>_JSIDL</xsl:text>
    </xsl:variable>
    <section xml:id="{$sectname}">
      <xsl:attribute name="version">1.0</xsl:attribute>
      <title>
        <xsl:value-of select="@name"/>
      </title>
      
      <para>
        name=<xsl:value-of select="@name"/>
      </para>
      
      <para>
      version=<xsl:value-of select="@version"/>
      </para>
      
      <para>
      id=<xsl:value-of select="@id"/>
      </para>

      <xsl:apply-templates select="jaus:description | jaus:assumptions" />
      
      <section>
        <title>Vocabulary</title>
        <xsl:call-template name="make-vocabulary-table"/>
        <xsl:call-template name="make-internal-event-set-table"/>      
      </section>
      
      <section>
        <title>Encoding</title>
        <xsl:apply-templates select="jaus:internal_events_set"/>
        <xsl:apply-templates select="jaus:message_set"/>
      </section>

      <!-- always a pagebreak before protocol behavior section due to diagram -->
      <xsl:processing-instruction name="hard-pagebreak"/>
      <section>
        <title>Protocol Behavior</title>
        <para>Add textual protocol behavior information/details here.</para>
        <xsl:call-template name="make-protocol-behavior-diagram"/>
        <xsl:call-template name="make-state-transition-table"/>
        <xsl:call-template name="make-state-conditions-table"/>
        <xsl:call-template name="make-state-transition-actions-table"/>
      </section>
      
    </section>
  </xsl:template>

  <!-- intended for processing message_defs when generating the vocabulary table -->
  <xsl:template match="jaus:message_def" mode="vocab_message_defs">
    <xsl:variable name="link-text">
      <!-- computes xml:id of event def to link to. -->
      <xsl:value-of select="ancestor::jaus:service_def/@name"/>
      <xsl:text>.</xsl:text>
      <xsl:value-of select="@name"/>
    </xsl:variable>
    <row>
      <entry><xsl:value-of select="@message_id"/></entry>
      <entry><link linkend="{$link-text}"><emphasis role="hyperlink">
        <xsl:value-of select="@name"/></emphasis></link></entry>
      <entry><xsl:value-of select="@is_command"/></entry>
    </row>
  </xsl:template>

<!-- context when calling this template should be at <jaus:service_def> -->
  <xsl:template name="make-vocabulary-table">
    <!-- create list of all message definitions in service. -->
    <xsl:variable name="num_input_message_defs" 
      select="count(//jaus:input_set/jaus:message_def)"/>
    <xsl:variable name="num_output_message_defs" 
      select="count(//jaus:output_set/jaus:message_def)"/>
    <xsl:if test="($num_input_message_defs + $num_output_message_defs) > 0">
      <span-wrap class-string="vocabulary-table">
        <table>
          <title><xsl:value-of select="@name"/> Service Message Vocabulary</title>
          <tgroup cols="3" align="left" colsep="1" rowsep="1">
            <colspec colname="c1" colwidth="7pc"/>
            <colspec colname="c2" colwidth="16pc"/>
            <colspec colname="c3" colwidth="7pc"/>
            <tbody>
              <row>
                <entry><emphasis role="bold">Message ID (hex)</emphasis></entry>
                <entry><emphasis role="bold">Name</emphasis></entry>
                <entry><emphasis role="bold">Command</emphasis></entry>
              </row>
              <xsl:if test="$num_input_message_defs > 0">
                <row><entry namest="c1" nameend="c3"><emphasis role="bold">Input Set</emphasis>
                </entry></row>
                <xsl:apply-templates select="//jaus:input_set" mode="vocab_message_defs"/>
              </xsl:if>
              <xsl:if test="$num_output_message_defs > 0">
                <row><entry namest="c1" nameend="c3"><emphasis role="bold">Output Set</emphasis></entry></row>
                <xsl:apply-templates select="//jaus:output_set" mode="vocab_message_defs"/>
              </xsl:if>
            </tbody>
          </tgroup>
        </table>
      </span-wrap>
    </xsl:if>
  </xsl:template>
  
  <!-- intended for processing event_defs when generating internal event set table -->
  <xsl:template match="jaus:event_def" mode="event_set_event_defs">
    <xsl:variable name="link-text">
      <!-- computes xml:id of event def to link to. -->
      <xsl:value-of select="ancestor::jaus:service_def/@name"/>
      <xsl:text>.</xsl:text>
      <xsl:value-of select="@name"/>
    </xsl:variable>
    <row>
      <entry>
        <link linkend="{$link-text}">
          <emphasis role="hyperlink"><xsl:value-of select="@name"/></emphasis>
        </link>
      </entry>
      <entry><xsl:value-of select="./jaus:description"/></entry>
    </row>
  </xsl:template>
  
  <!-- context when calling this template should be at <jaus:service_def> -->
  <xsl:template name="make-internal-event-set-table">
    <xsl:variable name="num_event_defs" 
      select="count(//jaus:internal_events_set/jaus:event_def)"/>
    <xsl:if test="$num_event_defs > 0">
      <span-wrap class-string="internal-event-set-table">
        <table>
          <title><xsl:value-of select="@name"/> Service Internal Events Set</title>
          <tgroup cols="2" align="left" colsep="1" rowsep="1">
            <colspec colname="c1" colwidth="10pc"/>
            <colspec colname="c2" colwidth="16pc"/>
            <tbody>
              <row>
                <entry><emphasis role="bold">Name</emphasis></entry>
                <entry><emphasis role="bold">Interpretation</emphasis></entry>
              </row>
              <xsl:apply-templates select="//jaus:internal_events_set" mode="event_set_event_defs"/>
            </tbody>
          </tgroup>
        </table>
      </span-wrap>
    </xsl:if>
  </xsl:template>

  <xsl:template name="make-protocol-behavior-diagram">
    <xsl:variable name="figure_id">fig.<xsl:value-of select="@name"/>_behavior_fig</xsl:variable>
    <figure xml:id="{$figure_id}">
      <title><xsl:value-of select="@name"/> Service Protocol Behavior</title>
      <mediaobject>
        <imageobject>
          <imagedata contentwidth="6.50in" align="center"
            fileref="images/{@name}/protocolBehavior.png" format="PNG"/>
        </imageobject>
      </mediaobject>
    </figure>
  </xsl:template>
  
  <!-- IMD: generates dot-delimited state name string for state transition table -->
  <xsl:template match="jaus:state | jaus:default_state" mode="get-source-states">
    <xsl:variable name="parent-part">
      <xsl:apply-templates select="./parent::jaus:state | ./parent::jaus:default_state" 
        mode="get-source-states"/>
    </xsl:variable>
    <xsl:if test="string($parent-part) !=''">
      <xsl:value-of select="string($parent-part)"/><xsl:text>.</xsl:text>
    </xsl:if>
    <!-- default_state has no name -->
    <xsl:choose>
      <xsl:when test="@name">
        <xsl:value-of select="@name"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:text>default_state</xsl:text>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  
  <!-- calls above template -->
  <xsl:template name="make-source-state-string">
    <xsl:variable name="parent_part">
      <xsl:apply-templates select="./parent::jaus:state | ./parent::jaus:default_state" 
        mode="get-source-states"/>
    </xsl:variable>
    <xsl:copy-of select="$parent_part"/>
  </xsl:template>

  <xsl:template name="make-state-transition-table">
    <!-- counts <transitions> that are immediate children of a <state> -->
    <span-wrap class-string="state-transition-table">
    <table>
      <title><xsl:value-of select="@name"/> Service State Transitions</title>
      <tgroup cols="4" align="left" colsep="1" rowsep="1">
        <colspec colname="c1" colwidth="8pc"/>
        <colspec colname="c2" colwidth="7pc"/>
        <colspec colname="c3" colwidth="11pc"/>
        <colspec colname="c4" colwidth="17pc"/>
        <tbody>
          <row>
            <entry><emphasis role="bold">Start State</emphasis></entry>
            <entry><emphasis role="bold">Trigger</emphasis></entry>
            <entry><emphasis role="bold">Conditions</emphasis></entry>
            <entry><emphasis role="bold">Actions</emphasis></entry>
          </row>
          <xsl:for-each select="//jaus:state/jaus:transition | //jaus:default_state/jaus:transition">
            <row>
              <entry>
                <!-- outputs dot-delimited full name of state -->
                <xsl:call-template name="make-source-state-string"/>
              </entry>
              <entry>
                <!-- trigger column string:
                  the 'trigger' is usually given as the type attribute of a <parameter>
                  in the <transition>, where the <parameter>'s value attrib="msg".  
                  However some <transitions>s don't have any <parameters>.
                  Standard docs I've seen use the transition's name in that case. -->
                <xsl:variable name="msg_parameter_msg_type" 
                  select="string(./jaus:parameter[@value='msg']/@type)"/>
                <xsl:choose>
                  <xsl:when test="$msg_parameter_msg_type = ''">
                    <xsl:value-of select="@name"/>
                  </xsl:when>
                  <xsl:otherwise>
                    <xsl:value-of select="$msg_parameter_msg_type"/>
                  </xsl:otherwise>
                </xsl:choose>
              </entry>
              <entry>
                <!-- there may or may not be a condition. -->
                <xsl:variable name="transition_condition" 
                  select="string(./jaus:guard/@condition)"/>
                <xsl:if test="$transition_condition != ''">
                  <xsl:value-of select="$transition_condition"/>
                </xsl:if>
              </entry>
              <entry>
                <!-- may be zero or more actions in a condition and each 
                     action may have zero or more arguments. -->
                <xsl:for-each select="./jaus:action">
                  <xsl:value-of select="@name"/>
                  (
                  <!-- may be zero or more arguments per action, print out value attrib -->
                  <xsl:for-each select="./jaus:argument">
                    <xsl:value-of select="@value"/>
                    <xsl:if test="position() != last()">,<xsl:processing-instruction name="linebreak"/>
                    </xsl:if>
                  </xsl:for-each>
                  )
                  <xsl:if test="position() != last()">,<xsl:processing-instruction name="linebreak"/></xsl:if> 
                </xsl:for-each>
              </entry>
            </row>
          </xsl:for-each>
        </tbody>
      </tgroup>
    </table>
    </span-wrap>
  </xsl:template>
  
  <xsl:template name="make-state-conditions-table">
    <xsl:variable name="num_guards" 
      select="count(//jaus:state/descendant::jaus:transition/jaus:guard
      | //jaus:default_state/descendant::jaus:transition/jaus:guard )"/>
    <xsl:if test="$num_guards > 0">
      <span-wrap class-string="state-conditions-table">
      <table>
        <title><xsl:value-of select="@name"/> Service Conditions</title>
        <tgroup cols="2" align="left" colsep="1" rowsep="1">
          <colspec colname="c1" colwidth="12pc"/>
          <colspec colname="c2" colwidth="25pc"/>
          <tbody>
            <row>
              <entry><emphasis role="bold">Condition</emphasis></entry>
              <entry><emphasis role="bold">Interpretation</emphasis></entry>
            </row>
            <xsl:for-each select="//jaus:state/descendant::jaus:transition/jaus:guard
              | //jaus:default_state/descendant::jaus:transition/jaus:guard">
              <row>
                <entry><xsl:value-of select="@condition"/></entry>
                <entry><xsl:value-of select="@interpretation"/></entry>
              </row>
            </xsl:for-each>
          </tbody>
        </tgroup>
      </table>
      </span-wrap>
    </xsl:if>
  </xsl:template>
  
  <xsl:template name="make-state-transition-actions-table">
    <xsl:variable name="num_actions" 
      select="count(//jaus:state/descendant::jaus:transition/jaus:action
      | //jaus:default_state/descendant::jaus:transition/jaus:action)"/>
    <xsl:if test="$num_actions > 0">
      <span-wrap class-string="state-transition-actions-table">
        <table>
          <title><xsl:value-of select="@name"/> Service Transition Actions</title>
          <tgroup cols="2" align="left" colsep="1" rowsep="1">
            <colspec colname="c1" colwidth="10pc"/>
            <colspec colname="c2" colwidth="20pc"/>
            <tbody>
              <row>
                <entry><emphasis role="bold">Action</emphasis></entry>
                <entry><emphasis role="bold">Interpretation</emphasis></entry>
              </row>
              <xsl:for-each select="//jaus:state/descendant::jaus:transition/jaus:action
                | //jaus:default_state/descendant::jaus:transition/jaus:action">
                <row>
                  <entry><xsl:value-of select="@name"/></entry>
                  <entry><xsl:value-of select="@interpretation"/></entry>
                </row>
              </xsl:for-each>
            </tbody>
          </tgroup>
        </table>
      </span-wrap>
    </xsl:if>
  </xsl:template>

  <!-- formerly used literallayout instead of para -->
  <xsl:template match="jaus:description">
    <section>
      <title>Description</title>
      <para>
        <xsl:value-of select="."/>
      </para>
    </section>
  </xsl:template>

  <xsl:template match="jaus:assumptions">
    <section>
      <title>Assumptions</title>
      <para>
        <xsl:value-of select="."/>
      </para>
    </section>
  </xsl:template>


  <xsl:template match="jaus:message_set">
    <xsl:apply-templates select="jaus:input_set"/>
    <xsl:apply-templates select="jaus:output_set"/>
  </xsl:template>
  
  <xsl:template match="jaus:internal_events_set">
    <!-- internal events set may have no event_defs inside. -->
    <xsl:if test="count(jaus:event_def) > 0">
      <section>
        <title>Internal Event Set</title>
      <xsl:apply-templates select="jaus:event_def"/>
      </section>
    </xsl:if>
  </xsl:template>

  <xsl:template match="jaus:input_set">
    <!-- input set may have no message_defs inside. -->
    <xsl:if test="count(jaus:message_def) > 0">
      <section>
      <title>Input Set</title>
      <xsl:apply-templates select="jaus:message_def"/>
      </section>
    </xsl:if>
  </xsl:template>

  <xsl:template match="jaus:output_set">
    <xsl:if test="count(jaus:message_def) > 0">
      <!-- output set may have no message_defs inside. -->
      <section>
      <title>Output Set</title>
      <xsl:apply-templates select="jaus:message_def"/>
      </section>
    </xsl:if>
  </xsl:template>
  
  <xsl:template match="jaus:event_def">
    <xsl:if test="not (@type = 'internal')">
      <xsl:variable name="event_def_id">
        <xsl:value-of select="ancestor::jaus:service_def/@name"/>
        <xsl:text>.</xsl:text>
        <xsl:value-of select="@name"/>
      </xsl:variable>
      <section xml:id="{$event_def_id}">
        <title><xsl:value-of select="@name"/></title>
        <para><xsl:value-of select="jaus:description"/></para>
        <span-wrap class-string="event-def-table">
          <table>
            <title><xsl:value-of select="@name"/> Event Encoding</title>
            <tgroup cols="6" align="left" colsep="1" rowsep="1">
              <colspec colname="c1" colwidth="3pc" align="center"/>  <!-- field number -->
              <colspec colname="c2" colwidth="10pc"/>                 <!-- field name -->
              <colspec colname="c3" colwidth="10pc"/>                 <!-- type -->
              <colspec colname="c4" colwidth="4pc"/>                 <!-- units -->
              <colspec colname="c5" colwidth="4pc"/>                 <!-- optional status -->
              <colspec colname="c6" colwidth="14pc"/>                <!-- interpretation -->
              <tbody>
                <!-- always have row for structure diagram -->
                <row>
                  <entry namest="c1" nameend="c6">
                    <!-- row for structure diagram -->
                    <inlinemediaobject><imageobject>
                      <imagedata fileref="images/event_{@name}.png" format="PNG" />
                    </imageobject></inlinemediaobject>
                  </entry>
                </row>
                <xsl:choose>
                  <!-- may have a body with no messages. -->
                  <xsl:when test="jaus:body/*">
                    <xsl:apply-templates select="jaus:body"/>
                  </xsl:when>
                  <xsl:otherwise>
                    <row>
                    <entry namest="c1" nameend="c6">Empty message body</entry>
                    </row>
                  </xsl:otherwise>
                </xsl:choose>
              </tbody>
            </tgroup>
          </table>
        </span-wrap>
      </section>
    </xsl:if>
  </xsl:template>
  
  <xsl:template match="jaus:message_def">
    <xsl:if test="not (@type = 'internal')">
      <xsl:variable name="msg_def_id">
        <xsl:value-of select="ancestor::jaus:service_def/@name"/>
        <xsl:text>.</xsl:text>
        <xsl:value-of select="@name"/>
      </xsl:variable>
      <section xml:id="{$msg_def_id}">
        <title>
          <xsl:text>ID </xsl:text><xsl:value-of select="@message_id"/>
          <xsl:text>: </xsl:text><xsl:value-of select="@name"/>
        </title>
        <para><xsl:value-of select="jaus:description"/></para>
      <span-wrap class-string="message-def-table">
        <table>
          <title><xsl:value-of select="@name"/> Message Encoding</title>
          <tgroup cols="6" align="left" colsep="1" rowsep="1">
            <colspec colname="c1" colwidth="3pc" align="center"/>  <!-- field number -->
            <colspec colname="c2" colwidth="10pc"/>                 <!-- field name -->
            <colspec colname="c3" colwidth="10pc"/>                 <!-- type -->
            <colspec colname="c4" colwidth="4pc"/>                 <!-- units -->
            <colspec colname="c5" colwidth="4pc"/>                 <!-- optional status -->
            <colspec colname="c6" colwidth="14pc"/>                <!-- interpretation -->
            <tbody>
              <!-- always have row for structure diagram -->
              <row>
                <entry namest="c1" nameend="c6">
                  <!-- row for structure diagram -->
                    <inlinemediaobject><imageobject>
                        <imagedata fileref="images/msg_{@name}.png" format="PNG" />
                    </imageobject></inlinemediaobject>
                </entry>
              </row>
              <xsl:choose>
                <!-- may have a body with no messages. -->
                <xsl:when test="jaus:body/*">
                  <xsl:apply-templates select="jaus:body"/>
                </xsl:when>
                <xsl:otherwise>
                  <row>
                  <entry namest="c1" nameend="c6">Empty message body</entry>
                  </row>
                </xsl:otherwise>
              </xsl:choose>
            </tbody>
          </tgroup>
        </table>
      </span-wrap>
      </section>
    </xsl:if>
  </xsl:template>

  <xsl:template match="jaus:uses-messages">
    <row>
      <entry/>
      <entry/>
      <entry><emphasis role="bold">uses-messages</emphasis></entry>
      <entry><xsl:value-of select="@name"/></entry>
      <entry><xsl:value-of select="@version"/></entry>
      <entry><xsl:value-of select="@id"/></entry>
    </row>
  </xsl:template>

  <xsl:template match="jaus:declared_type_set">
    <xsl:variable name="tablename">
      <xsl:text>table.</xsl:text>
      <xsl:value-of select="../@name"/>
      <xsl:text>_TYPES</xsl:text>
    </xsl:variable>
    <table frame="all" xml:id="{$tablename}">
      <title>
        <xsl:text>DECLARED TYPES FOR SERVICE </xsl:text>
        <xsl:value-of select="../@name"/>
        <xsl:text> V</xsl:text>
        <xsl:value-of select="../@version"/>
      </title>
      <xsl:call-template name="type_table_group"/>
    </table>
  </xsl:template>

  <xsl:template name="type_table_group">
      <tgroup cols="5" align='left' colsep='1' rowsep='1'>
        <colspec colname="c1" colwidth="1*"/>
        <colspec colname="c2" colwidth="4*"/>
        <colspec colname="c3" colwidth="3*"/>
        <colspec colname="c4" colwidth="2*"/>
        <colspec colname="c5" colwidth="4*"/>
        <tbody>
          <xsl:call-template name="process.fields">
            <xsl:with-param name="nodelist"
                            select="jaus:declared_type_set_ref
                                    | jaus:record
                                    | jaus:list
                                    | jaus:fixed_field
                                    | jaus:array
                                    | jaus:bit_field
                                    | jaus:variable_field
                                    | jaus:fixed_length_string
                                    | jaus:variable_length_string
                                    | jaus:variable_length_field
                                    | jaus:variable_format_field"/>
          </xsl:call-template>
        </tbody>
      </tgroup>
  </xsl:template>
  
  <xsl:template match="jaus:declared_type_set_ref">
    <row>
      <entry>XREF</entry>
      <entry><xsl:value-of select="@id"/></entry>
      <entry><xsl:value-of select="@name"/></entry>
      <entry><xsl:value-of select="@version"/></entry>
    </row>
  </xsl:template>

  <xsl:template match="jaus:body">
    <xsl:if test="./*">
      <xsl:apply-templates select="jaus:record_count
                                   | jaus:record
                                   | jaus:declared_record
                                   | jaus:list_count
                                   | jaus:list
                                   | jaus:sequence
                                   | jaus:variant
                                   | jaus:declared_list"/>
    </xsl:if>
  </xsl:template>

  <xsl:template match="jaus:list_count">
    <row>
      <entry/><!-- Never optional, so no pvbit -->
      <entry>
        <emphasis role="bold"><xsl:value-of select="@name"/></emphasis>
      </entry>
      <xsl:apply-templates select="jaus:count_field"/>
    </row>
  </xsl:template>

  <xsl:template match="jaus:list">
    <xsl:apply-templates select="jaus:record_count
                                 | jaus:record
                                 | jaus:list
                                 | jaus:variant
                                 | jaus:sequence
                                 "/>
  </xsl:template>

  <xsl:template match="jaus:sequence">
    <!-- contains one or more of any of record, list, variant, sequence -->
    <!-- can also contain declared_* version of any of the above but assuming we will not encounter 
         these in generated JSIDL.  -->
    <xsl:apply-templates select="jaus:record
                               | jaus:list
                               | jaus:variant
                               | jaus:sequence"/>
  </xsl:template>
  
  <xsl:template match="jaus:variant">
    <!-- situation here matches sequence.  we are only concerned with the contents of records for now
      so just dig deeper into the tree. -->
    <xsl:apply-templates select="jaus:record
                               | jaus:list
                               | jaus:variant
                               | jaus:sequence"/>
  </xsl:template>

  <xsl:template match="jaus:record_count">
    <row>
      <entry/><!-- Never optional, so no pvbit -->
      <entry>
        <emphasis role="bold"><xsl:value-of select="@name"/></emphasis>
      </entry>
      <xsl:apply-templates select="jaus:count_field"/>
    </row>
  </xsl:template>

  <xsl:template match="jaus:count_field">
    <entry>
      <xsl:choose>
        <xsl:when test="@field_type_unsigned">
          <xsl:value-of select="@field_type_unsigned"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:text>N/A</xsl:text>
        </xsl:otherwise>
      </xsl:choose>
    </entry>
    <entry>
      <xsl:text>count</xsl:text>
    </entry>
    <entry>
      <xsl:text>list of [</xsl:text>
      <xsl:value-of select="@min_count"/>
      <xsl:text>,</xsl:text>
      <xsl:value-of select="@max_count"/>
      <xsl:text>] records.</xsl:text>
    </entry>
  </xsl:template>

  <xsl:template match="jaus:declared_list">
    <xsl:param name="pvbit" select="n/a"/>
    <row>
      <xsl:choose>
        <xsl:when test="@optional = 'true'">
          <entry><xsl:value-of select="$pvbit"/></entry>
          <entry><emphasis role="optionalfield"><xsl:value-of select="@name"/></emphasis></entry>
        </xsl:when>
        <xsl:otherwise>
          <entry/><!-- no pvbit -->
          <entry><emphasis role="bold"><xsl:value-of select="@name"/></emphasis></entry>
        </xsl:otherwise>
      </xsl:choose>
      <entry><emphasis role="bold"><xsl:value-of select="@declared_type_name"/></emphasis></entry>
      <entry/><entry/>
    </row>
  </xsl:template>

  <!-- Recursive template to record presence vector bit numbers -->
    <xsl:template name="process.fields">
    <xsl:param name="fieldnum" select="1"/>
    <xsl:param name="pvbit" select="0"/>
    <xsl:param name="nodelist"/>
    <xsl:choose>
      <xsl:when test="$fieldnum > count($nodelist)"></xsl:when> 
      <xsl:otherwise>
        <xsl:variable name="this" select="$nodelist[$fieldnum=position()]"/>
        <xsl:apply-templates select="$this">
          <xsl:with-param name="pvbit">
            <xsl:choose>
              <xsl:when test="$this/@optional='true'">
                <xsl:value-of select="$pvbit"/>
              </xsl:when>
              <xsl:otherwise>
                <xsl:text>-</xsl:text>
              </xsl:otherwise>
            </xsl:choose>
          </xsl:with-param>
        </xsl:apply-templates>
        <xsl:call-template name="process.fields">
          <xsl:with-param name="fieldnum" select="$fieldnum+1"/>
          <xsl:with-param name="nodelist" select="$nodelist"/>
          <xsl:with-param name="pvbit">
            <xsl:choose>
              <xsl:when test="$this/@optional = 'true'">
                <xsl:value-of select="$pvbit+1"/>
              </xsl:when>
              <xsl:otherwise>
                <xsl:value-of select="$pvbit"/>
              </xsl:otherwise>
            </xsl:choose>
          </xsl:with-param>
        </xsl:call-template>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>

  <xsl:template match="jaus:record">
    <row>
      <entry namest="c1" nameend="c6" align="left"><emphasis role="bold">Record Name = </emphasis>
        <xsl:choose>
          <xsl:when test="@name">
            <xsl:value-of select="@name"/>
          </xsl:when>
          <xsl:otherwise><xsl:text>N/A</xsl:text></xsl:otherwise>
        </xsl:choose>
      </entry>
    </row>
    <row>
      <entry><emphasis role="bold">Field #</emphasis></entry>
      <entry><emphasis role="bold">Name</emphasis></entry>
      <entry><emphasis role="bold">Type</emphasis></entry>
      <entry><emphasis role="bold">Units</emphasis></entry>
      <entry><emphasis role="bold">Optional</emphasis></entry>
      <entry><emphasis role="bold">Interpretation</emphasis></entry>
    </row>
    <xsl:call-template name="process.fields">
      <xsl:with-param name="nodelist" select="jaus:presence_vector
                                              | jaus:fixed_field
                                              | jaus:array
                                              | jaus:variable_field
                                              | jaus:bit_field
                                              | jaus:fixed_length_string
                                              | jaus:variable_length_string
                                              | jaus:variable_length_field
                                              | jaus:variable_format_field
                                              | jaus:declared_fixed_field
                                              | jaus:declared_variable_field
                                              | jaus:declared_bit_field
                                              | jaus:declared_fixed_length_string
                                              | jaus:declared_variable_length_string
                                              | jaus:declared_variable_length_field
                                              | jaus:declared_variable_format_field"/>
    </xsl:call-template>
  </xsl:template>

  <xsl:template match="jaus:presence_vector">
    <row>
      <!-- generate number based on count of entities below a <record> -->
      <entry><xsl:number count="record|*" format="1"/></entry>
      <entry>
        <xsl:choose>
          <xsl:when test="@name">
            <emphasis role="bold">
              &lt;presence_vector&gt;
              <xsl:processing-instruction name="linebreak"/>
            </emphasis>
            <xsl:value-of select="@name"/>
          </xsl:when>
          <xsl:otherwise>
            <emphasis role="bold"><xsl:text>&lt;presence_vector&gt;</xsl:text></emphasis>
          </xsl:otherwise>
        </xsl:choose>
      </entry>
      <entry>
        <xsl:choose>
          <xsl:when test="@field_type_unsigned">
            <xsl:value-of select="@field_type_unsigned"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:text>N/A</xsl:text>
          </xsl:otherwise>
        </xsl:choose>
      </entry>
      <entry/>
      <entry/>
      <entry/>
    </row>
  </xsl:template>

  <xsl:template match="jaus:bit_field">
    <xsl:param name="pvbit" select="n/a"/>
    <row>
      <entry><xsl:number count="record|*" format="1"/></entry>
      <entry>
        <emphasis role="bold">&lt;bit_field&gt;</emphasis>
        <xsl:processing-instruction name="linebreak"/>
        <xsl:value-of select="@name"/>
      </entry>
      <entry>
        <xsl:choose>
          <xsl:when test="@field_type_unsigned">
            <xsl:value-of select="@field_type_unsigned"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:text>N/A</xsl:text>
          </xsl:otherwise>
        </xsl:choose>
      </entry>
      <entry>
        <xsl:choose>
          <xsl:when test="@field_units">
            <xsl:value-of select="@field_units"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:text>bit_field</xsl:text>
          </xsl:otherwise>
        </xsl:choose>
      </entry>
      <entry><xsl:value-of select="@optional"/></entry>
      <entry>
        <xsl:if test="@interpretation">
          <xsl:value-of select="@interpretation"/>
          <xsl:processing-instruction name="linebreak"/>
          <xsl:processing-instruction name="linebreak"/>
        </xsl:if>
        <xsl:if test="jaus:sub_field">
            <xsl:apply-templates select="jaus:sub_field" mode="cell"/>
          </xsl:if>
      </entry>
    </row>
  </xsl:template>

  <xsl:template match="jaus:fixed_field">
    <xsl:param name="pvbit" select="n/a"/>
    <row>
      <entry><xsl:number count="record|*" format="1"/></entry>
      <entry>
        <emphasis role="bold">&lt;fixed_field&gt;</emphasis>
        <xsl:processing-instruction name="linebreak"/>
        <xsl:value-of select="@name"/>
      </entry>
      <entry>
        <xsl:choose>
          <xsl:when test="@field_type">
            <xsl:value-of select="@field_type"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:text>N/A</xsl:text>
          </xsl:otherwise>
        </xsl:choose>
      </entry>
      <entry>
        <xsl:choose>
          <xsl:when test="@field_units">
            <xsl:value-of select="@field_units"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:text>N/A</xsl:text>
          </xsl:otherwise>
        </xsl:choose>
      </entry>
      <entry>
        <xsl:value-of select="@optional"/>
      </entry>
      <entry>
        <span-wrap style-string="font-size: 8pt">
        <xsl:if test="@interpretation">
          <xsl:value-of select="@interpretation"/>
          <xsl:processing-instruction name="linebreak"/>
          <xsl:processing-instruction name="linebreak"/>
        </xsl:if>
        <!-- fixed_field may have either a scale_range or a value_set -->
        <xsl:if test="jaus:scale_range">
            <xsl:text> (scaled range = </xsl:text>
            <xsl:apply-templates select="jaus:scale_range"/>
            <xsl:text>)</xsl:text>
        </xsl:if>
        <xsl:if test="count(jaus:value_set/jaus:value_range) > 1 or
          count(jaus:value_set/jaus:value_enum) > 0">
          <xsl:apply-templates select="jaus:value_set" mode="cell"/>
        </xsl:if>
        </span-wrap>
      </entry>
    </row>
  </xsl:template>
  
  <xsl:template match="jaus:array">
    <row>
      <!-- field #, field name, field type, units, optional status, interpretation (including detailed type info) -->
      <entry><xsl:number count="record|*" format="1"/></entry>
      <entry>
        <emphasis role="bold">&lt;array&gt;</emphasis>
        <xsl:processing-instruction name="linebreak"/>
        <xsl:value-of select="@name"/>
      </entry>
      <entry>
        <xsl:text>Array of </xsl:text>
        <xsl:choose>
          <!-- if child has a field_type attrib, use type info there -->
          <xsl:when test="child::*/@field_type">
            <xsl:value-of select="child::*/@field_type"/>
          </xsl:when>
          <!-- if child has a field_type_unsigned attrib, use type info there -->
          <xsl:when test="child::*/@field_type_unsigned">
            <xsl:value-of select="child::*/@field_type_unsigned"/>
          </xsl:when>
        </xsl:choose>
      </entry>
      <entry>N/A</entry>
      <entry><xsl:value-of select="@optional"/></entry>
      <entry>
        <span-wrap style-string="font-size: 8pt">
          <xsl:if test="@interpretation">
            <xsl:value-of select="@interpretation"/>
            <xsl:processing-instruction name="linebreak"/>
            <xsl:processing-instruction name="linebreak"/>
          </xsl:if>
          <!-- output dimension info -->
        <xsl:for-each select="jaus:dimension">
          <xsl:text>dimension size = </xsl:text><xsl:value-of select="@size"/>
          <xsl:processing-instruction name="linebreak"/>
        </xsl:for-each>
        <xsl:processing-instruction name="linebreak"/>
        
        <!-- note <array> should have a field as its first child. -->
        <!-- output the name of the field -->
        <xsl:text>&lt;</xsl:text><xsl:value-of select="name(./*[1])"/><xsl:text>&gt;</xsl:text>
        <!-- output additional info about the field if additional info exists. -->
        <xsl:apply-templates select="./*[1]/*" mode="cell" />
        </span-wrap>
      </entry>
    </row>
  </xsl:template>

  <xsl:template match="jaus:variable_field">
    <xsl:param name="pvbit" select="n/a"/>
    <row>
      <entry><xsl:number count="record|*" format="1"/></entry>
      <entry>
        <emphasis role="bold">&lt;variable_field&gt;</emphasis>
        <xsl:processing-instruction name="linebreak"/>
        <xsl:value-of select="@name"/>
      </entry>
      <entry>variable</entry>
      <entry>N/A</entry> <!-- units -->
      <entry><xsl:value-of select="@optional"/></entry>
      <entry>
        <span-wrap style-string="font-size: 8pt">
          <xsl:if test="@interpretation">
            <xsl:value-of select="@interpretation"/>
            <xsl:processing-instruction name="linebreak"/>
            <xsl:processing-instruction name="linebreak"/>
          </xsl:if>
          <xsl:apply-templates mode="cell"/>
        </span-wrap>
      </entry>
    </row>
  </xsl:template>

  <xsl:template match="jaus:fixed_length_string">
    <xsl:param name="pvbit" select="n/a"/>
    <row>
      <entry><xsl:number count="record|*" format="1"/></entry>
      <entry><emphasis role="bold">&lt;fixed_length_string&gt;</emphasis>
        <xsl:processing-instruction name="linebreak"/>
        <xsl:value-of select="@name"/>
      </entry>
      <entry>
        <xsl:text>String of </xsl:text>
        <xsl:value-of select="@string_length"/>
        <xsl:text> bytes</xsl:text>
      </entry>
      <entry>N/A</entry> <!-- no units for a fixed length string -->
      <entry>
        <xsl:value-of select="@optional"/>
      </entry> 
      <entry>
        <span-wrap style-string="font-size: 8pt">
        <xsl:value-of select="@interpretation"/>
        </span-wrap>
      </entry>
    </row>
  </xsl:template>

  <xsl:template match="jaus:variable_length_string">
    <xsl:param name="pvbit" select="n/a"/>
    <row>
      <entry><xsl:number count="record|*" format="1"/></entry>
      <entry>
        <emphasis role="bold">&lt;variable_length_string&gt;</emphasis>
        <xsl:processing-instruction name="linebreak"/>
        <xsl:value-of select="@name"/></entry>
      <entry>
        <xsl:text>variable length string (byte[])</xsl:text>
      </entry>
      <entry>N/A</entry> <!-- no 'units' for variable-length string -->
      <entry><xsl:value-of select="@optional"/></entry>
      <entry>
        <span-wrap style-string="font-size: 8pt">
          <xsl:if test="@interpretation">
            <xsl:value-of select="@interpretation"/>
            <xsl:processing-instruction name="linebreak"/>
            <xsl:processing-instruction name="linebreak"/>
          </xsl:if>
        <xsl:if test="jaus:count_field">
        <xsl:text> (Length min..max = </xsl:text>
        <xsl:value-of select="jaus:count_field/@min_count"/>
        <xsl:text>..</xsl:text>
        <xsl:value-of select="jaus:count_field/@max_count"/>
        <xsl:text>)</xsl:text>
      </xsl:if>
         </span-wrap>
      </entry>
    </row>
  </xsl:template>

  <xsl:template match="jaus:variable_length_field">
    <xsl:param name="pvbit" select="n/a"/>
    <row>
      <entry><xsl:number count="record|*" format="1"/></entry>
      <entry>
        <emphasis role="bold">&lt;variable_length_field&gt;</emphasis>
        <xsl:processing-instruction name="linebreak"/>
        <xsl:value-of select="@name"/>
      </entry>
      <entry> <!-- using format as 'type'.  i have no way to link to a list, as is done in the hand-made
                   docs. -->
        <xsl:choose>
          <xsl:when test="@field_format">
            <xsl:value-of select="@field_format"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:text>N/A</xsl:text>
          </xsl:otherwise>
        </xsl:choose>      
      </entry>
      <entry> 
        <xsl:text>N/A</xsl:text>
      </entry>
      <entry><xsl:value-of select="@optional"/></entry>
      <entry>
        <span-wrap style-string="font-size: 8pt">
        <xsl:if test="@interpretation">
          <xsl:value-of select="@interpretation"/>
          <xsl:processing-instruction name="linebreak"/>
          <xsl:processing-instruction name="linebreak"/>
        </xsl:if>
        <xsl:if test="jaus:count_field">
        <xsl:text>Count field type = </xsl:text><xsl:value-of select="jaus:count_field/@field_type_unsigned"/>
        <xsl:processing-instruction name="linebreak"/>        
        <xsl:text> (min/max count = </xsl:text>
        <xsl:value-of select="jaus:count_field/@min_count"/>
        <xsl:text>..</xsl:text>
        <xsl:value-of select="jaus:count_field/@max_count"/>
        <xsl:text>)</xsl:text>
      </xsl:if>
      <xsl:processing-instruction name="linebreak"/>
      <xsl:if test="count(jaus:value_set/jaus:value_range) > 1 or
         count(jaus:value_set/jaus:value_enum) > 0">
        <xsl:apply-templates select="jaus:value_set" mode="cell"/>
      </xsl:if>
          </span-wrap>
      </entry>
    </row>
  </xsl:template>

  <xsl:template match="jaus:variable_format_field">
    <!-- IMD TODO: how to provide more useful info in this message? -->
    <xsl:param name="pvbit" select="n/a"/>
    <row>
      <entry><xsl:number count="record|*" format="1"/></entry>
      <entry>
        <emphasis role="bold">&lt;variable_format_field&gt;</emphasis>
        <xsl:processing-instruction name="linebreak"/>
        <xsl:value-of select="@name"/>
      </entry>
      <entry>
        <xsl:choose>
          <xsl:when test="jaus:count_field/@field_type_unsigned">
            <xsl:text>count_field = </xsl:text>
            <xsl:value-of select="jaus:count_field/@field_type_unsigned"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:text>N/A</xsl:text>
          </xsl:otherwise>
        </xsl:choose>
      </entry>
      <entry>N/A</entry>
      <entry><xsl:value-of select="@optional"/></entry>
      <entry>
        <span-wrap style-string="font-size: 8pt">
        <xsl:if test="@interpretation">
          <xsl:value-of select="@interpretation"/>
          <xsl:processing-instruction name="linebreak"/>
          <xsl:processing-instruction name="linebreak"/>
        </xsl:if>
        <xsl:if test="jaus:count_field">
        <xsl:text> (min/max count = </xsl:text>
        <xsl:value-of select="jaus:count_field/@min_count"/>
        <xsl:text>..</xsl:text>
        <xsl:value-of select="jaus:count_field/@max_count"/>
        <xsl:text>)</xsl:text>
        </xsl:if>
        </span-wrap>
      </entry>
    </row>
    <xsl:apply-templates select="jaus:format_field" mode="row"/>
  </xsl:template>

  <!-- FIELD DETAIL COMPONENTS -->

  <!-- used by variable_fields -->
  <xsl:template match="jaus:type_and_units_field">
    <row>
      <entry/>
      <entry/>
      <entry><emphasis role="bold">Enum Index</emphasis></entry>
      <entry><emphasis role="bold">Field Type</emphasis></entry>
      <entry><emphasis role="bold">Field Units</emphasis></entry>
      <entry><emphasis role="bold">Interpretation</emphasis></entry>
    </row>
    <xsl:apply-templates/>
  </xsl:template>

  <xsl:template match="jaus:type_and_units_field" mode="cell">
    <xsl:apply-templates mode="cell"/>
  </xsl:template>
  
  <!-- used by variable_fields via type_and_units_field.  1 or more of them may exist -->
  <xsl:template match="jaus:type_and_units_enum" mode="cell">
    <xsl:text>Index = </xsl:text><xsl:value-of select="@index"/><xsl:processing-instruction name="linebreak"/>
    <xsl:if test="@field_type">
        <xsl:text>Field Type = </xsl:text><xsl:value-of select="@field_type"/>
      <xsl:processing-instruction name="linebreak"/>
    </xsl:if>
    <xsl:if test="@field_units">
        <xsl:text>Field Units = </xsl:text><xsl:value-of select="@field_units"/>
      <xsl:processing-instruction name="linebreak"/>
    </xsl:if>
    
    <xsl:choose>
      <xsl:when test="jaus:scale_range">
          <xsl:text> (scaled range = </xsl:text>
          <xsl:apply-templates select="jaus:scale_range"/>
          <xsl:text>)</xsl:text>
        <xsl:processing-instruction name="linebreak"/>
      </xsl:when> 
      <!-- should treat following like any value_range and not just jam
        onto interpretation, but for now, special case it. -->
      <xsl:when test="count(jaus:value_set/jaus:value_range) = 1 and
        count(jaus:value_set/jaus:value_enum) = 0">
          <xsl:text>value range = </xsl:text>
        <xsl:apply-templates select="jaus:value_set" mode="cell"/>
        <xsl:processing-instruction name="linebreak"/>
      </xsl:when>
    </xsl:choose>
  </xsl:template>
  
  <!-- used by variable_fields via type_and_units_field.  1 or more of them may exist -->
  <xsl:template match="jaus:type_and_units_enum">
    <row>
      <entry/>
      <entry/>
      <entry><xsl:value-of select="@index"/></entry>
      <entry><xsl:value-of select="@field_type"/></entry>
      <entry><xsl:value-of select="@field_units"/></entry>
      <entry>
        <span-wrap style-string="font-size: 8pt">
        <xsl:value-of select="interpretation"/>
        <xsl:choose>
          <xsl:when test="jaus:scale_range">
            <xsl:text> (scaled range = </xsl:text>
            <xsl:apply-templates select="jaus:scale_range"/>
            <xsl:text>)</xsl:text>
          </xsl:when>
          <!-- should treat following like any value_range and not just jam
            onto interpretation, but for now, special case it. -->
          <xsl:when test="count(jaus:value_set/jaus:value_range) = 1 and
            count(jaus:value_set/jaus:value_enum) = 0">
              <xsl:text>value range = </xsl:text>
            <xsl:apply-templates select="jaus:value_set" mode="cell"/>
          </xsl:when>
        </xsl:choose>
        </span-wrap>
      </entry>
    </row>
  </xsl:template>

  <!-- used by variable_fields via type_and_units_field.  1 or more of them may exist -->
<!--  <xsl:template match="jaus:type_and_units_enum" mode="cell">
    <row>
      <xsl:text>Type/Unit Enum:</xsl:text>
      <xsl:processing-instruction name="linebreak"/>
      <xsl:text>Field Type:</xsl:text><xsl:value-of select="@field_type"/>
      <xsl:processing-instruction name="linebreak"/>
      <xsl:text>Field Units:</xsl:text><xsl:value-of select="@field_units"/>
      <xsl:processing-instruction name="linebreak"/>
      <xsl:choose>
        <xsl:when test="jaus:scale_range">
          <xsl:text>Scale range = </xsl:text>
          <xsl:apply-templates select="jaus:scale_range"/>
        </xsl:when>
        <!-\- should treat following like any value_range and not just jam
        onto interpretation, but for now, special case it. -\->
        <xsl:when test="value_set">
          <xsl:apply-templates select="jaus:value_set" mode="cell"/>
        </xsl:when>
        <xsl:when test="count(jaus:value_set/jaus:value_range) = 1 and
          count(jaus:value_set/jaus:value_enum) = 0">
          <xsl:text>(value range = </xsl:text>
          <xsl:apply-templates select="jaus:value_set" mode="cell"/>
          <xsl:text>)</xsl:text>
        </xsl:when>
      </xsl:choose>
    </row>
  </xsl:template>
-->  




  <xsl:template match="jaus:type_enum">
    <row>
      <entry/><!-- no pvbit -->
      <entry/>
      <entry><xsl:value-of select="@index"/></entry>
      <entry><xsl:value-of select="@field_type"/></entry>
      <entry>
        <xsl:text> </xsl:text>
        <xsl:if test="count(jaus:value_set/jaus:value_range) = 1 and
                      count(jaus:value_set/jaus:value_enum) = 0">
          <xsl:value-of
              select="jaus:value_set/jaus:value_range/@interpretation"/>
          <xsl:text> address </xsl:text>
          <xsl:apply-templates select="jaus:value_set" mode="cell"/>
        </xsl:if>
      </entry>
    </row>
  </xsl:template>

  <xsl:template match="jaus:format_field" mode="cell">
    <xsl:apply-templates select="jaus:format_enum" mode="cell"/>
  </xsl:template>
  
  <xsl:template match="jaus:format_enum" mode="cell">
      <xsl:value-of select="@index"/>
      <xsl:text> = </xsl:text>
      <xsl:value-of select="@field_format"/>
    <xsl:processing-instruction name="linebreak"/>
  </xsl:template>

  <xsl:template match="jaus:value_set" mode="row">
    <xsl:if test="jaus:value_enum">
      <row>
        <entry namest="c1" nameend="c3"/> <!-- no pvbit -->
        <entry><emphasis role="bold">Index</emphasis></entry>
        <entry><emphasis role="bold">Constant</emphasis></entry>
        <entry><emphasis role="bold">Interpretation</emphasis></entry>
      </row>
      <xsl:apply-templates select="jaus:value_enum"/>
    </xsl:if>
    <xsl:if test="jaus:value_range">
      <row>
        <entry namest="c1" nameend="c4"/>
        <entry><emphasis role="bold">Value Range</emphasis></entry>
        <entry><emphasis role="bold">Interpretation</emphasis></entry>
      </row>
      <xsl:apply-templates select="jaus:value_range" mode="row"/>
    </xsl:if>
  </xsl:template>

  <xsl:template match="jaus:value_enum" mode="row">
    <row>
      <entry/> <!-- no pvbit -->
      <entry/>
      <entry/>
      <entry>
        <xsl:value-of select="@enum_index"/>
      </entry>
      <entry namest="c5" nameend="c6">
        <!-- span two cols so we can show long constant names without hyphenation -->
        <xsl:choose>
          <xsl:when test="@enum_const">
            <xsl:value-of select="@enum_const"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:text>None</xsl:text>
          </xsl:otherwise>  
        </xsl:choose>
        <xsl:if test="@interpretation">
          <xsl:text> (</xsl:text>
          <xsl:value-of select="@interpretation"/>
          <xsl:text>)</xsl:text>
        </xsl:if>
      </entry>
    </row>
  </xsl:template>
  
  <xsl:template match="jaus:value_enum" mode="cell">

    <xsl:value-of select="@enum_index"/>
    
    <xsl:choose>
      <xsl:when test="@enum_const">
        <xsl:text>= </xsl:text><xsl:value-of select="@enum_const"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:text>= None</xsl:text>
      </xsl:otherwise>  
    </xsl:choose>

    <xsl:if test="@interpretation">
      <xsl:text> (</xsl:text>
      <xsl:value-of select="@interpretation"/>
      <xsl:text>)</xsl:text>
    </xsl:if>
    
    <xsl:processing-instruction name="linebreak"/>
  </xsl:template>
  
  <xsl:template match="jaus:value_range" mode="row">
    <row>
      <entry/>
      <entry/>
      <entry/>
      <entry/>
      <entry>
        <xsl:choose>
          <xsl:when test="@lower_limit_type = 'inclusive'">
            <xsl:text>[</xsl:text>
          </xsl:when>
          <xsl:when test="@upper_limit_type = 'exclusive'">
            <xsl:text>(</xsl:text>
          </xsl:when>
          <xsl:otherwise>
            <xsl:text>[</xsl:text>
          </xsl:otherwise>
        </xsl:choose>
        <xsl:value-of select="@lower_limit"/>,<xsl:value-of select="@upper_limit"/>
        <xsl:choose>
          <xsl:when test="@lower_limit_type = 'inclusive'">
            <xsl:text>]</xsl:text>
          </xsl:when>
          <xsl:when test="@lower_limit_type = 'exclusive'">
            <xsl:text>)</xsl:text>
          </xsl:when>
          <xsl:otherwise>
            <xsl:text>]</xsl:text>
          </xsl:otherwise>
        </xsl:choose>
      </entry>
      <entry>
        <xsl:choose>
          <xsl:when test="@interpretation">
            <xsl:value-of select="@interpretation"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:text>N/A</xsl:text>
          </xsl:otherwise>
        </xsl:choose>
      </entry>
    </row>
  </xsl:template>
  
  <xsl:template match="jaus:value_range" mode="cell">
    <xsl:choose>
      <xsl:when test="@lower_limit_type = 'inclusive'">
        <xsl:text>[</xsl:text>
      </xsl:when>
      <xsl:when test="@upper_limit_type = 'exclusive'">
        <xsl:text>(</xsl:text>
      </xsl:when>
      <xsl:otherwise>
        <xsl:text>[</xsl:text>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:value-of select="@lower_limit"/>,<xsl:value-of select="@upper_limit"/>
    <xsl:choose>
      <xsl:when test="@lower_limit_type = 'inclusive'">
        <xsl:text>]</xsl:text>
      </xsl:when>
      <xsl:when test="@lower_limit_type = 'exclusive'">
        <xsl:text>)</xsl:text>
      </xsl:when>
      <xsl:otherwise>
        <xsl:text>]</xsl:text>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:processing-instruction name="linebreak"/>
  </xsl:template>
  
  <!-- used by bit_field -->
  <xsl:template match="jaus:sub_field" mode="row">
    <row>
      <entry/>
      <entry/>      
      <entry>
        <xsl:text>Bits </xsl:text>
        <xsl:value-of select="jaus:bit_range/@from_index"/>
        <xsl:text>..</xsl:text>
        <xsl:value-of select="jaus:bit_range/@to_index"/>
        <xsl:text></xsl:text>
      </entry>
      <!-- TODO: try to fix awkwardness of mixed value_enum,
        value_range in schema -->
      <entry>
        <xsl:value-of select="@name"/>
      </entry>
      <entry>
        <xsl:if test="count(jaus:value_set/jaus:value_range) = 1 and
          count(jaus:value_set/jaus:value_enum) = 0">
          <xsl:apply-templates select="jaus:value_set" mode="cell"/>
        </xsl:if>
      </entry>
      <entry>
        <xsl:value-of
          select="jaus:value_set/jaus:value_range/@interpretation"/>
      </entry>
    </row>
    <!-- Multiple values/ranges? -->
    <xsl:if test="count(jaus:value_set/jaus:value_range)
      + count(jaus:value_set/jaus:value_enum)
      + count(jaus:value_set/jaus:scale_range) > 1">
      <xsl:apply-templates select="jaus:value_set" mode="row"/>
    </xsl:if>
  </xsl:template>

  <!-- used by bit_field -->
  <xsl:template match="jaus:sub_field" mode="cell">
    <xsl:text>Bits </xsl:text>
     <xsl:value-of select="jaus:bit_range/@from_index"/>
     <xsl:text>..</xsl:text>
     <xsl:value-of select="jaus:bit_range/@to_index"/>
     <xsl:text>, </xsl:text>
    <xsl:processing-instruction name="linebreak"/>
    <xsl:apply-templates select="jaus:value_set" mode="cell"/>
    <xsl:processing-instruction name="linebreak"/>
  </xsl:template>

  <xsl:template match="jaus:scale_range">
    <xsl:text>[</xsl:text>
    <xsl:value-of select="@real_lower_limit"/>,<xsl:value-of select="@real_upper_limit"/>
    <xsl:text>], </xsl:text>
    <xsl:value-of select="@integer_function"/>
    <xsl:if test="@interpretation">
      <xsl:text>, </xsl:text>
      <xsl:value-of select="@interpretation"/>
    </xsl:if>
  </xsl:template>
  
  <xsl:template match="jaus:value_set" mode="cell">
    <!-- mandatory attribute -->
    <xsl:text>Value set, </xsl:text>
    <xsl:text>offset=</xsl:text>
    <xsl:value-of select="@offset_to_lower_limit"/>
    <xsl:text>,</xsl:text> ranges/enums: 
    <xsl:processing-instruction name="linebreak"/>
    <xsl:apply-templates select="jaus:value_range | jaus:value_enum" mode="cell"/>
  </xsl:template>
  <!-- END FIELD DETAIL COMPONENTS -->

  <!-- DECLARED FIELDS, not currently used.  -->
  <!--
  <xsl:template match="jaus:declared_fixed_field">
    <xsl:param name="pvbit" select="n/a"/>
    <row>
      <entry>
        <xsl:number count="record|*" format="1"/>
      </entry>
      <entry>
        <emphasis role="bold">&lt;declared_fixed_field&gt;</emphasis>
        <xsl:processing-instruction name="linebreak"/>
        <xsl:value-of select="@name"/>
      </entry>
      <entry>
        <emphasis role="bold">Refers to field:</emphasis>
        <xsl:value-of select="@declared_type_ref"/>
      </entry>
      <entry>N/A</entry>
      <entry>N/A</entry>
      <entry>
        <xsl:value-of select="@interpretation"/>
      </entry>
    </row>
  </xsl:template>
  
  <xsl:template match="jaus:declared_variable_field">
    <xsl:param name="pvbit" select="n/a"/>
    <row>
      <entry>
        <xsl:number count="record|*" format="1"/>
      </entry>
      <entry>
        <emphasis role="bold">&lt;declared_variable_field&gt;</emphasis>
        <xsl:processing-instruction name="linebreak"/>
        <xsl:value-of select="@name"/>
      </entry>
      <entry>
        <emphasis role="bold">Refers to field:</emphasis>
        <xsl:value-of select="@declared_type_ref"/>
      </entry>
      <entry>N/A</entry>
      <entry>N/A</entry>
      <entry>
        <xsl:value-of select="@interpretation"/>
      </entry>
    </row>
  </xsl:template>
  
  <xsl:template match="jaus:declared_bit_field">
    <xsl:param name="pvbit" select="n/a"/>
    <row>
      <entry>
        <xsl:number count="record|*" format="1"/>
      </entry>
      <entry>
        <emphasis role="bold">&lt;declared_bit_field&gt;</emphasis>
        <xsl:processing-instruction name="linebreak"/>
        <xsl:value-of select="@name"/>
      </entry>
      <entry>
        <emphasis role="bold">Refers to field:</emphasis>
        <xsl:value-of select="@declared_type_ref"/>
      </entry>
      <entry>N/A</entry>
      <entry>N/A</entry>
      <entry>
        <xsl:value-of select="@interpretation"/>
      </entry>
    </row>
  </xsl:template>
  
  <xsl:template match="jaus:declared_fixed_length_string">
    <xsl:param name="pvbit" select="n/a"/>
    <row>
      <entry>
        <xsl:number count="record|*" format="1"/>
      </entry>
      <entry>
        <emphasis role="bold">&lt;declared_fixed_length_string&gt;</emphasis>
        <xsl:processing-instruction name="linebreak"/>
        <xsl:value-of select="@name"/>
      </entry>
      <entry>
        <emphasis role="bold">Refers to field:</emphasis>
        <xsl:value-of select="@declared_type_ref"/>
      </entry>
      <entry>N/A</entry>
      <entry>N/A</entry>
      <entry>
        <xsl:value-of select="@interpretation"/>
      </entry>
    </row>
  </xsl:template>

  <xsl:template match="jaus:declared_variable_length_string">
    <xsl:param name="pvbit" select="n/a"/>
    <row>
      <entry>
        <xsl:number count="record|*" format="1"/>
      </entry>
      <entry>
        <emphasis role="bold">&lt;declared_variable_length_string&gt;</emphasis>
        <xsl:processing-instruction name="linebreak"/>
        <xsl:value-of select="@name"/>
      </entry>
      <entry>
        <emphasis role="bold">Refers to field:</emphasis>
        <xsl:value-of select="@declared_type_ref"/>
      </entry>
      <entry>N/A</entry>
      <entry>N/A</entry>
      <entry>
        <xsl:value-of select="@interpretation"/>
      </entry>
    </row>
  </xsl:template>
  
  <xsl:template match="jaus:declared_variable_length_field">
    <xsl:param name="pvbit" select="n/a"/>
    <row>
      <entry>
        <xsl:number count="record|*" format="1"/>
      </entry>
      <entry>
        <emphasis role="bold">&lt;declared_variable_length_field&gt;</emphasis>
        <xsl:processing-instruction name="linebreak"/>
        <xsl:value-of select="@name"/>
      </entry>
      <entry>
        <emphasis role="bold">Refers to field:</emphasis>
        <xsl:value-of select="@declared_type_ref"/>
      </entry>
      <entry>N/A</entry>
      <entry>N/A</entry>
      <entry>
        <xsl:value-of select="@interpretation"/>
      </entry>
    </row>
  </xsl:template>
  
  <xsl:template match="jaus:declared_variable_format_field">
    <xsl:param name="pvbit" select="n/a"/>
    <row>
      <entry>
        <xsl:number count="record|*" format="1"/>
      </entry>
      <entry>
        <emphasis role="bold">&lt;declared_variable_format_field&gt;</emphasis>
        <xsl:processing-instruction name="linebreak"/>
        <xsl:value-of select="@name"/>
      </entry>
      <entry>
        <emphasis role="bold">Refers to field:</emphasis>
        <xsl:value-of select="@declared_type_ref"/>
      </entry>
      <entry>N/A</entry>
      <entry>N/A</entry>
      <entry>
        <xsl:value-of select="@interpretation"/>
      </entry>
    </row>
  </xsl:template>
  
  <xsl:template match="jaus:declared_record">
    <xsl:param name="pvbit" select="n/a"/>
    <row>
      <entry></entry>
      <entry>
        <emphasis role="bold">Declared record:</emphasis>
        <xsl:processing-instruction name="linebreak"/>
        <xsl:value-of select="@name"/>
      </entry>
      <entry>
        <emphasis role="bold">Refers to field:</emphasis>
        <xsl:value-of select="@declared_type_ref"/>
      </entry>
      <entry>N/A</entry>
      <entry>N/A</entry>
      <entry><xsl:value-of select="interpretation"/></entry>
    </row>
  </xsl:template> -->

</xsl:stylesheet>
